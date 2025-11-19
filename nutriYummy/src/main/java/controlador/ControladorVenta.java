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
 *
 * @author rocha
 */
public class ControladorVenta {

    private final ModeloVenta modeloVenta;
    private final ModeloUsuario modeloUsuario;
    private final ModeloProducto modeloProducto;

    public ControladorVenta() {
        this.modeloVenta = new ModeloVenta();
        this.modeloUsuario = new ModeloUsuario();
        this.modeloProducto = new ModeloProducto();
    }

    public String getAllVentasTabla() {
        List<Venta> ventas = modeloVenta.getAllVentas();

        if (ventas == null || ventas.isEmpty()) {
            return "<div class='alert alert-warning text-center'>No hay pedidos registrados.</div>";
        }

        // 1. StringBuilder para la TABLA
        StringBuilder htmlTabla = new StringBuilder();
        // 2. StringBuilder para los MODALES (fuera de la tabla)
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
                .append("<th>Acciones</th>")
                .append("</tr>")
                .append("</thead>")
                .append("<tbody>");

        for (Venta venta : ventas) {
            Usuario usuario = modeloUsuario.getUsuario(venta.getIdUsuario());

            // --- AQUI CONSTRUIMOS LA FILA (TR) ---
            htmlTabla.append("<tr>")
                    .append("<td>").append(venta.getFolio()).append("</td>")
                    .append("<td>").append(formatearFecha(venta.getFechaHora().toString())).append("</td>")
                    .append("<td>$").append(String.format("%.2f", venta.getTotal())).append("</td>")
                    .append("<td>").append(String.format("%05d", usuario.getNumero())).append("</td>")
                    .append("<td>")
                    // Botón detalles
                    .append("<button class='btn btn-light btn-sm' data-bs-toggle='modal' data-bs-target='#detallesVentaModal")
                    .append(venta.getId()).append("'><i class='bi bi-eye'></i></button>")
                    .append("</td>")
                    .append("<td>")
                    // Botón editar
                    .append("<button class='btn btn-primary btn-sm' data-bs-toggle='modal' data-bs-target='#editarVentaModal")
                    .append(venta.getId()).append("'><i class='bi-pencil'></i></button>")
                    .append("</td>")
                    .append("</tr>");

            // --- AQUI CONSTRUIMOS LOS MODALES (DIVS) PARA ESTA VENTA ---
            // 1. Modal de Detalles
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

            // Tabla interna del modal (Detalles)
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

            // 2. Modal de Editar
            htmlModals.append("<div class='modal fade' id='editarVentaModal").append(venta.getId())
                    .append("' tabindex='-1' aria-labelledby='editarVentaModalLabel").append(venta.getId())
                    .append("' aria-hidden='true'>")
                    .append("<div class='modal-dialog'>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>")
                    .append("<h5 class='modal-title' id='editarVentaModalLabel").append(venta.getId())
                    .append("'>Editar venta ").append(venta.getFolio()).append("</h5>")
                    .append("<button type='button' class='btn-close' data-bs-dismiss='modal'></button>")
                    .append("</div>")
                    .append("<div class='modal-body'>")
                    .append("<form action='EditarVenta' method='POST'>")
                    .append("<input type='hidden' name='id' value='").append(venta.getId()).append("'>")
                    .append("<div class='mb-3'><label class='form-label'>Folio:</label>")
                    .append("<input type='text' name='folio' class='form-control' value='").append(venta.getFolio()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Fecha y hora:</label>")
                    .append("<input type='text' name='fechaHora' class='form-control' value=\"").append(venta.getFechaHora()).append("\" required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Total:</label>")
                    .append("<input type='number' step='0.01' name='total' class='form-control' value='").append(venta.getTotal()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Número de usuario:</label>")
                    .append("<input type='number' name='usuarioId' class='form-control' value='").append(usuario.getNumero()).append("' required></div>")
                    .append("<button type='submit' class='btn btn-success w-100'>Guardar cambios</button>")
                    .append("</form>")
                    .append("</div>") // modal-body
                    .append("</div></div></div>"); // fin modal editar
        }

        // Cerramos la tabla principal
        htmlTabla.append("</tbody></table>");

        // CONCATENAMOS: Primero la tabla completa, y al final todos los modales
        return htmlTabla.toString() + htmlModals.toString();

    }

    private String formatearFecha(String fechaISO) {
        if (fechaISO == null || fechaISO.isEmpty()) {
            return "";
        }

        LocalDateTime fecha = LocalDateTime.parse(fechaISO);

        DateTimeFormatter salida = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        return fecha.format(salida);
    }
}
