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

}
