/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.ModeloProducto;
import modelo.Producto;

/**
 *
 * @author rocha
 */
public class ControladorProducto {

    private final ModeloProducto modeloProducto;

    public ControladorProducto() {
        this.modeloProducto = new ModeloProducto();
    }

    public String getAllProductosTabla() {
        List<Producto> productos = modeloProducto.getAllProductos();

        if (productos == null || productos.isEmpty()) {
            return "<div class='alert alert-warning text-center'>No hay productos registrados.</div>";
        }
    
        StringBuilder html = new StringBuilder();

        html.append("<table class='table table-bordered table-striped'>")
                .append("<thead class='table-warning'>")
                .append("<tr>")
                .append("<th>Número</th>")
                .append("<th>Nombre</th>")
                .append("<th>Descripción</th>")
                .append("<th>Precio</th>")
                .append("<th>Stock</th>")
                .append("<th>Tipo</th>")
                .append("<th>Ruta (imagen)</th>")
                .append("<th>Acciones</th>")
                .append("</tr>")
                .append("</thead>")
                .append("<tbody>");

        for (Producto producto : productos) {
            // Fila de la tabla
            html.append("<tr>")
                    .append("<td>").append(String.format("%05d", producto.getNumero())).append("</td>")
                    .append("<td>").append(producto.getNombre()).append("</td>")
                    .append("<td>").append(producto.getDescripcion()).append("</td>")
                    .append("<td>$").append(producto.getPrecio()).append("</td>")
                    .append("<td>").append(producto.getStock()).append("</td>")
                    .append("<td>").append(producto.getTipo()).append("</td>")
                    .append("<td>").append(producto.getImg()).append("</td>")
                    .append("<td>")
                    .append("<button type='button' class='btn btn-primary btn-sm' data-bs-toggle='modal' data-bs-target='#editarProductoModal")
                    .append(producto.getId())
                    //Botón para editar
                    .append("'><i class='bi-pencil'></i></button>")
                    // Botón para eliminar
                    .append("<button type='button' class='btn btn-danger btn-sm' data-bs-toggle='modal' data-bs-target='#eliminarProductoModal")
                    .append(producto.getId()).append("'><i class='bi-trash'></i></button>")
                    .append("</td>")
                    .append("</tr>");

            // Modal para editar
            html.append("<div class='modal fade' id='editarProductoModal").append(producto.getId())
                    .append("' tabindex='-1' aria-labelledby='editarProductoModalLabel").append(producto.getId())
                    .append("' aria-hidden='true'>")
                    .append("<div class='modal-dialog modal-dialog-centered'>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>")
                    .append("<h5 class='modal-title' id='editarProductoModalLabel").append(producto.getId())
                    .append("'>Editar producto: ").append(producto.getNombre()).append("</h5>")
                    .append("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Cerrar'></button>")
                    .append("</div>")
                    .append("<div class='modal-body'>")
                    .append("<form action='EditarProducto' method='POST'>")
                    .append("<input type='hidden' name='id' value='").append(producto.getId()).append("'>")
                    .append("<div class='mb-3'><label class='form-label'>Número (sin ceros) <span class='text-danger'>*</span></label>")
                    .append("<input type='text' name='numero' class='form-control' value='").append(producto.getNumero()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Nombre <span class='text-danger'>*</span></label>")
                    .append("<input type='text' name='nombre' class='form-control' value='").append(producto.getNombre()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Descripción <span class='text-danger'>*</span></label>")
                    .append("<input type='text' name='descripcion' class='form-control' value='").append(producto.getDescripcion()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Precio <span class='text-danger'>*</span></label>")
                    .append("<input type='number' step='0.01' name='precio' class='form-control' value='").append(producto.getPrecio()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Stock <span class='text-danger'>*</span></label>")
                    .append("<input type='number' name='stock' class='form-control' value='").append(producto.getStock()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Tipo <span class='text-danger'>*</span></label>")
                    .append("<select name='tipo' class='form-select' required>")
                    .append("<option value='OBLEAS'").append(producto.getTipo().name().equals("OBLEAS") ? " selected" : "").append(">Obleas</option>")
                    .append("<option value='MAICITOS'").append(producto.getTipo().name().equals("MAICITOS") ? " selected" : "").append(">Maicitos</option>")
                    .append("<option value='CHIPS'").append(producto.getTipo().name().equals("CHIPS") ? " selected" : "").append(">Chips</option>")
                    .append("<option value='OTROS'").append(producto.getTipo().name().equals("OTROS") ? " selected" : "").append(">Otros</option>")
                    .append("</select></div>")
                    .append("<div class='mb-3'><label class='form-label'>Imagen (nombre del archivo) <span class='text-danger'>*</span></label>")
                    .append("<input type='text' name='img' class='form-control' value='").append(producto.getImg()).append("' required></div>")
                    .append("<label class='form-label'><span class=\"text-danger\">* Campos obligatorios</span></label>")
                    .append("<button type='submit' class='btn btn-success w-100'>Guardar</button>")
                    .append("</form>")
                    .append("</div>") // modal-body
                    .append("</div>") // modal-content
                    .append("</div>") // modal-dialog
                    .append("</div>"); // modal

            // Modal para eliminar
            html.append("<div class='modal fade' id='eliminarProductoModal").append(producto.getId())
                    .append("' tabindex='-1' aria-labelledby='eliminarProductoModalLabel").append(producto.getId())
                    .append("' aria-hidden='true'>")
                    .append("<div class='modal-dialog modal-dialog-centered'>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>")
                    .append("<h5 class='modal-title' id='eliminarProductoModalLabel").append(producto.getId())
                    .append("'>Eliminar producto: ").append(producto.getNombre()).append("</h5>")
                    .append("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Cerrar'></button>")
                    .append("</div>")
                    .append("<div class='modal-body'>")
                    .append("<p>¿Estás seguro de que deseas eliminar este producto?</p>")
                    .append("</div>")
                    .append("<div class='modal-footer'>")
                    .append("<form action='EliminarProducto' method='POST'>")
                    .append("<input type='hidden' name='id' value='").append(producto.getId()).append("'>")
                    .append("<button type='submit' class='btn btn-danger'>Eliminar</button>")
                    .append("</form>")
                    .append("<button type='button' class='btn btn-secondary' data-bs-dismiss='modal'>Cancelar</button>")
                    .append("</div>")
                    .append("</div>")
                    .append("</div>")
                    .append("</div>");
        }
        
        html.append("</tbody></table>");

        return html.toString();
    }
    
    public String getProductosPublicosHtml() {
        StringBuilder html = new StringBuilder();

        // Obtiene los productos de la BD (del Modelo)
        for (Producto producto : modeloProducto.getAllProductos()) {
            
            // Este es el HTML de las tarjetas de productos.jsp
            html.append("<div class='col-lg-4 col-md-6 col-12 mb-4'>");
            html.append("  <div class='custom-block-wrap'>");
            
            // Inserta datos dinámicos
            html.append("    <img src='img/").append(producto.getImg()).append("' class='custom-block-image img-fluid' alt='").append(producto.getNombre()).append("'>");
            
            html.append("    <div class='custom-block'>");
            html.append("      <div class='custom-block-body'>");
            html.append("        <h5 class='mb-3'>").append(producto.getNombre()).append("</h5>");
            html.append("        <p>").append(producto.getDescripcion()).append("</p>");
            html.append("        <div class='d-flex align-items-center my-2'>");
            
            String precioFormateado = String.format("%.2f", producto.getPrecio());
            html.append("          <p class='mb-0'><strong>Precio:</strong> <span class='text-success'>$").append(precioFormateado).append(" MXN</span></p>");
            
            html.append("        </div>");
            html.append("      </div>");
            html.append("      <a href='#' class='custom-btn btn'>Ver más</a>"); 
            html.append("    </div>");
            html.append("  </div>");
            html.append("</div>");
        }

        return html.toString();
    }
}
