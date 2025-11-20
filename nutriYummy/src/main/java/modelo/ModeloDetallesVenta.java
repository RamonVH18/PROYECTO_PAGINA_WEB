/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author rocha
 */
public class ModeloDetallesVenta extends Conexion {

    public ModeloDetallesVenta() {
    }
    
    public void insertarDetalleVenta(DetallesVenta detalles, int idVenta) {
        String sql = "call insertarDetallesVenta(?, ? ,? ,? ,?)";
        
        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setInt(1, detalles.getCantidad());
            pst.setDouble(2, detalles.getPrecio());
            pst.setDouble(3, detalles.getIva());
            pst.setInt(4, detalles.getIdProducto());
            pst.setInt(5, idVenta);

            pst.execute();

        } catch (SQLException e) {
            System.err.println("Error al insertar detalles de la venta: " + e.getMessage());
        }
    }
}
