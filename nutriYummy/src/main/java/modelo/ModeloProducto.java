/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Types.INTEGER;
import java.util.ArrayList;
import java.util.List;
import modelo.enums.TipoProducto;

/**
 *
 * @author rocha
 */
public class ModeloProducto extends Conexion {

    public ModeloProducto() {
    }

    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();

        String sql = "call getAllProductos()";

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {

                TipoProducto tipo = null;
                try {
                    tipo = TipoProducto.valueOf(rs.getString("tipo").toUpperCase());
                } catch (IllegalArgumentException ex) {
                    System.err.println("Tipo no válido: " + rs.getString("tipo"));
                }

                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("img"),
                        tipo
                );

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return productos;
    }

    public Producto getProducto(int id) {
        String sql = "call getProducto(?)";
        Producto producto = null;

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {

                    TipoProducto tipo = null;
                    try {
                        tipo = TipoProducto.valueOf(rs.getString("tipo").toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        System.err.println("Tipo no válido: " + rs.getString("tipo"));
                    }

                    producto = new Producto(
                            rs.getInt("id"),
                            rs.getInt("numero"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            rs.getString("img"),
                            tipo
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }

        return producto;
    }
    
    public boolean editarProducto(Producto producto) {
        String sql = "call editarProducto(?, ?, ?, ?, ? ,?, ?, ?)";

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setInt(1, producto.getId());
            pst.setInt(2, producto.getNumero());
            pst.setString(3, producto.getNombre());
            pst.setString(4, producto.getDescripcion());
            pst.setDouble(5, producto.getPrecio());
            pst.setInt(6, producto.getStock());
            pst.setString(7, producto.getImg());
            pst.setString(8, producto.getTipo().name());

            // Ejecutar UPDATE
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0; // true si se actualizó al menos una fila
        } catch (SQLException e) {
            System.err.println("Error al editar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "call eliminarProducto(?)";
        
        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setInt(1, id);

            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    public int insertarProducto(Producto producto) {
        String sql = "call insertarProducto(?, ?, ?, ?, ? ,?, ?)";
        int numeroGenerado = 0;

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getStock());
            pst.setString(5, producto.getImg());
            pst.setString(6, producto.getTipo().name());

            pst.registerOutParameter(7, INTEGER);

            pst.execute();

            numeroGenerado = pst.getInt(7);
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }

        return numeroGenerado;
    }
    
    public boolean existeProductoNumero(int numero) {
        String sql = "select existeProductoNumero(?) as existe";
        boolean existe = false;

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            pst.setInt(1, numero);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                existe = rs.getInt("existe") == 1;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar número: " + e.getMessage());
            return false;
        }
        
        return existe;
    }
    
    public List<Producto> getMejoresVendidos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "call getMejoresVendidos()";
        
        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("img")
                );

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener productos mejores vendidos: " + e.getMessage());
        }

        return productos;
    }
}
