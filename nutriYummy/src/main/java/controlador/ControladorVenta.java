/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import modelo.DetallesVenta;
import modelo.ModeloProducto;
import modelo.ModeloUsuario;
import modelo.ModeloVenta;
import modelo.Producto;
import modelo.Usuario;
import modelo.Venta;

/**
 * Controlador encargado de obtener y construir la tabla HTML para mostrar
 * todas las ventas, así como los modales con los detalles de cada venta.
 *
 * @author rocha
 */
public class ControladorVenta {

    private final ModeloVenta modeloVenta;
    private final ModeloUsuario modeloUsuario;
    private final ModeloProducto modeloProducto;

    /**
     * Constructor que inicializa los modelos utilizados por el controlador.
     */
    public ControladorVenta() {
        this.modeloVenta = new ModeloVenta();
        this.modeloUsuario = new ModeloUsuario();
        this.modeloProducto = new ModeloProducto();
    }

    /**
     * Genera el código HTML de una tabla que contiene todas las ventas
     * registradas, además de construir los modales de detalles para cada una.
     *
     * @return String con el HTML completo de la tabla y los modales.
     */
    public String getAllVentasTabla() {
        List<Venta> ventas = modeloVenta.getAllVentas();

        // Si no existen ventas, se muestra una alerta
        if (ventas == null || ventas.isEmpty()) {
            return "<div class='alert alert-warning text-center'>No hay pedidos registrados.</div>";
        }

        // StringBuilder para la tabla
        StringBuilder htmlTabla = new StringBuilder();
        // StringBuilder para los modales
        StringBuilder htmlModals = new StringBuilder();

        // Encabezado de la tabla
        htmlTabla.append("<table class='table table-bordered table-striped'>")
                .append("<thead class='table-warning'>")
                .append("<tr>")
                .append("<th>Folio</th>")
                .append("<th>Fecha y hora</th>")
                .append("<th>Total</th>")
                .append("<th># de usuario</th>")
                .append("<th>Ver detalles</th>")
                .append("</tr>")
                .append("</thead>")
                .append("<tbody>");

        for (Venta venta : ventas) {
            Usuario usuario = modeloUsuario.getUsuario(venta.getIdUsuario());

            // Fila
            htmlTabla.append("<tr>")
                    .append("<td>").append(venta.getFolio()).append("</td>")
                    .append("<td>").append(formatearFecha(venta.getFechaHora().toString())).append("</td>")
                    .append("<td>$").append(String.format("%.2f", venta.getTotal())).append("</td>")
                    .append("<td>").append(String.format("%05d", usuario.getNumero())).append("</td>")
                    .append("<td>")
                    // Botón detalles
                    .append("<button class='btn btn-light btn-sm' data-bs-toggle='modal' data-bs-target='#detallesVentaModal")
                    .append(venta.getId()).append("'><i class='bi bi-eye'></i></button>")
                    .append("</td>");

            // Modales para la fila
            // Modal de detalles
            htmlModals.append("<div class='modal fade' id='detallesVentaModal").append(venta.getId())
                    .append("' tabindex='-1' aria-labelledby='detallesVentaModalLabel").append(venta.getId())
                    .append("' aria-hidden='true'>")
                    .append("<div class='modal-dialog modal-lg'>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>")
                    .append("<h5 class='modal-title' id='detallesVentaModalLabel").append(venta.getId())
                    .append("'>Detalles de la venta ").append(venta.getFolio()).append("</h5>")
                    .append("<button type='button' class='btn-close' data-bs-dismiss='modal'></button>")
                    .append("</div>")
                    .append("<div class='modal-body'>");

            // Tabla interna del modal de detalles
            htmlModals.append("<table class='table table-bordered'>")
                    .append("<thead class='table-secondary'>")
                    .append("<tr>")
                    .append("<th># de producto</th>")
                    .append("<th>Nombre</th>")
                    .append("<th>Cantidad</th>")
                    .append("<th>Precio unitario</th>")
                    .append("<th>IVA</th>")
                    .append("<th>Subtotal</th>")
                    .append("</tr>")
                    .append("</thead>")
                    .append("<tbody>");

            if (venta.getDetalles() != null) {
                for (DetallesVenta detalle : venta.getDetalles()) {
                    Producto producto = modeloProducto.getProducto(detalle.getIdProducto());
                    htmlModals.append("<tr>")
                            .append("<td>").append(producto.getNumero()).append("</td>")
                            .append("<td>").append(producto.getNombre()).append("</td>")
                            .append("<td>").append(detalle.getCantidad()).append("</td>")
                            .append("<td>$").append(String.format("%.2f", detalle.getPrecio())).append("</td>")
                            .append("<td>$").append(String.format("%.2f", detalle.getIva())).append("</td>")
                            .append("<td>$").append(String.format("%.2f", detalle.getTotal())).append("</td>")
                            .append("</tr>");
                }
            }

            htmlModals.append("</tbody></table>")
                    .append("</div>") // modal-body
                    .append("</div></div></div>"); // fin modal detalles
        }

        htmlTabla.append("</tbody></table>");

        // Concatenar la tabla completa y al final todos los modales
        return htmlTabla.toString() + htmlModals.toString();

    }

    /**
     * Convierte una fecha ISO-8601 (ej. 2025-11-18T18:24:29)
     * a formato amigable: dd/MM/yyyy hh:mm a
     *
     * @param fechaISO cadena en formato ISO (LocalDateTime.toString())
     * @return fecha formateada o cadena vacía si hay error.
     */
    private String formatearFecha(String fechaISO) {
        if (fechaISO == null || fechaISO.isEmpty()) {
            return "";
        }

        LocalDateTime fecha = LocalDateTime.parse(fechaISO);

        DateTimeFormatter salida = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        return fecha.format(salida);
    }
}
