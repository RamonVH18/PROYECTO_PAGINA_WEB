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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rocha
 */
public class ModeloVenta extends Conexion {
    
    public List<Venta> getAllVentas() {
        List<Venta> ventas = new ArrayList<>();

        String sql = "call getAllVentas()";

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql); ResultSet rs = pst.executeQuery()) {

            Venta ventaActual = null;
            int idVentaActual = -1;

            while (rs.next()) {

                int idVenta = rs.getInt("id");

                // Si cambió la venta
                if (idVenta != idVentaActual) {
                    ventaActual = new Venta();
                    ventaActual.setId(idVenta);
                    ventaActual.setFolio(rs.getString("folio"));
                    ventaActual.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
                    ventaActual.setIdUsuario(rs.getInt("idUsuario"));
                    ventaActual.setTotal(rs.getDouble("totalVenta"));
                    ventaActual.setDetalles(new ArrayList<>());

                    ventas.add(ventaActual);
                    idVentaActual = idVenta;
                }

                // Crear detalle
                DetallesVenta det = new DetallesVenta();
                det.setId(rs.getInt("idDetalle"));
                det.setIdProducto(rs.getInt("idProducto"));
                det.setCantidad(rs.getInt("cantidad"));
                det.setPrecio(rs.getDouble("precioUnitario"));
                det.setIva(rs.getDouble("iva"));   // IVA en dinero
                det.setTotal(rs.getDouble("totalDetalle"));

                ventaActual.getDetalles().add(det);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ventas: " + e.getMessage());
        }

        return ventas;
    }

}
