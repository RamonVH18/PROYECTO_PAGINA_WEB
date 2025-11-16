/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public boolean insertarProducto(Producto producto) {
        String sql = "call insertarProducto(?, ?, ?, ?, ? ,?, ?)";

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setInt(1, producto.getNumero());
            pst.setString(2, producto.getNombre());
            pst.setString(3, producto.getDescripcion());
            pst.setDouble(4, producto.getPrecio());
            pst.setInt(5, producto.getStock());
            pst.setString(6, producto.getImg());
            pst.setString(7, producto.getTipo().name());

            pst.execute();
            
            int filasAfectadas = pst.getUpdateCount();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }
}
