/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

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
        StringBuilder html = new StringBuilder();

        for (Producto producto : modeloProducto.getAllProductos()) {
            // Fila de la tabla
            html.append("<tr>")
                    .append("<td>").append(producto.getNumero()).append("</td>")
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
                    .append("<div class='modal-dialog modal-dialog-centered'>") // igual que Agregar
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>")
                    .append("<h5 class='modal-title' id='editarProductoModalLabel").append(producto.getId())
                    .append("'>Editar producto: ").append(producto.getNombre()).append("</h5>")
                    .append("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Cerrar'></button>")
                    .append("</div>")
                    .append("<div class='modal-body'>")
                    .append("<form action='EditarProducto' method='POST'>")
                    .append("<input type='hidden' name='id' value='").append(producto.getId()).append("'>")
                    .append("<div class='mb-3'><label class='form-label'>Número</label>")
                    .append("<input type='text' name='numero' class='form-control' value='").append(producto.getNumero()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Nombre</label>")
                    .append("<input type='text' name='nombre' class='form-control' value='").append(producto.getNombre()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Descripción</label>")
                    .append("<input type='text' name='descripcion' class='form-control' value='").append(producto.getDescripcion()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Precio</label>")
                    .append("<input type='number' step='0.01' name='precio' class='form-control' value='").append(producto.getPrecio()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Stock</label>")
                    .append("<input type='number' name='stock' class='form-control' value='").append(producto.getStock()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Tipo</label>")
                    .append("<select name='tipo' class='form-select' required>")
                    .append("<option value='OBLEAS'").append(producto.getTipo().name().equals("OBLEAS") ? " selected" : "").append(">Obleas</option>")
                    .append("<option value='MAICITOS'").append(producto.getTipo().name().equals("MAICITOS") ? " selected" : "").append(">Maicitos</option>")
                    .append("<option value='CHIPS'").append(producto.getTipo().name().equals("CHIPS") ? " selected" : "").append(">Chips</option>")
                    .append("<option value='OTROS'").append(producto.getTipo().name().equals("OTROS") ? " selected" : "").append(">Otros</option>")
                    .append("</select></div>")
                    .append("<div class='mb-3'><label class='form-label'>Imagen (nombre del archivo)</label>")
                    .append("<input type='text' name='img' class='form-control' value='").append(producto.getImg()).append("' required></div>")
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

        return html.toString();
    }
}
