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

/**
 * Clase encargada de gestionar todas las operaciones relacionadas con
 * las ventas en la base de datos. 
 *
 * Extiende la clase Conexion para obtener el acceso directo a la conexión
 * con la base de datos.
 *
 * @author rocha
 */
public class ModeloVenta extends Conexion {
    
    /**
     * Obtiene todas las ventas almacenadas en la base de datos, junto con sus detalles.
     * @return Lista de ventas, cada una con sus detalles cargados.
     */
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
                DetallesVenta detalle = new DetallesVenta();
                detalle.setId(rs.getInt("idDetalle"));
                detalle.setIdProducto(rs.getInt("idProducto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getDouble("precioUnitario"));
                detalle.setIva(rs.getDouble("iva"));   // IVA en dinero
                detalle.setTotal(rs.getDouble("totalDetalle"));

                ventaActual.getDetalles().add(detalle);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ventas: " + e.getMessage());
        }

        return ventas;
    }
    
    public int insertarVenta(Venta venta) {
        String sql = "call insertarVenta(?, ?, ?)";
        int idVenta = 0;
        
        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setString(1, venta.getFolio());
            pst.setInt(2, venta.getIdUsuario());

            pst.registerOutParameter(3, INTEGER);

            pst.execute();

            idVenta = pst.getInt(3);
        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
        }

        return idVenta;
    }

}
