/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.ModeloUsuario;
import modelo.Usuario;

/**
 *
 * @author rocha
 */
public class ControladorUsuario {
    
    private final ModeloUsuario modeloUsuario;

    public ControladorUsuario() {
        this.modeloUsuario = new ModeloUsuario();
    }
    
    public String getAllUsuariosTabla() {
        StringBuilder html = new StringBuilder();

        for (Usuario usuario : modeloUsuario.getAllUsuarios()) {
            // Fila de la tabla
            html.append("<tr>")
                    .append("<td>").append(String.format("%05d", usuario.getNumero())).append("</td>")
                    .append("<td>").append(usuario.getNombreCompleto()).append("</td>")
                    .append("<td>").append(usuario.getEmail()).append("</td>")
                    .append("<td>").append(usuario.getRol()).append("</td>")
                    .append("<td>")
                    .append("<button type='button' class='btn btn-primary btn-sm' data-bs-toggle='modal' data-bs-target='#editarUsuarioModal")
                    .append(usuario.getId())
                    //Botón para editar
                    .append("'><i class='bi-pencil'></i></button>")
                    // Botón para eliminar
                    .append("<button type='button' class='btn btn-danger btn-sm' data-bs-toggle='modal' data-bs-target='#eliminarUsuarioModal")
                    .append(usuario.getId())
                    .append("'><i class='bi-trash'></i></button>")
                    .append("</td>")
                    .append("</tr>");

            // Modal para editar
            html.append("<div class='modal fade' id='editarUsuarioModal").append(usuario.getId())
                    .append("' tabindex='-1' aria-labelledby='editarUsuarioModalLabel").append(usuario.getId())
                    .append("' aria-hidden='true'>")
                    .append("<div class='modal-dialog modal-dialog-centered'>") // igual que Agregar
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>")
                    .append("<h5 class='modal-title' id='editarUsuarioModalLabel").append(usuario.getId())
                    .append("'>Editar usuario: ").append(usuario.getNombreCompleto()).append("</h5>")
                    .append("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Cerrar'></button>")
                    .append("</div>")
                    .append("<div class='modal-body'>")
                    .append("<form action='EditarUsuario' method='POST'>")
                    .append("<input type='hidden' name='id' value='").append(usuario.getId()).append("'>")
                    .append("<div class='mb-3'><label class='form-label'>Número (sin ceros) <span class='text-danger'>*</span></label>")
                    .append("<input type='text' name='numero' class='form-control' value='").append(usuario.getNumero()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Nombre <span class='text-danger'>*</span></label>")
                    .append("<input type='text' name='nombre' class='form-control' value='").append(usuario.getNombre()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Apellido paterno <span class='text-danger'>*</span></label>")
                    .append("<input type='text' name='apellidoPaterno' class='form-control' value='").append(usuario.getApellidoPaterno()).append("' required></div>")
                    .append("<div class='mb-3'><label class='form-label'>Apellido materno</label>")
                    .append("<input type='text' name='apellidoMaterno' class='form-control' value='").append(usuario.getApellidoMaterno()).append("'></div>")
                    .append("<div class='mb-3'><label class='form-label'>Email <span class='text-danger'>*</span></label>")
                    .append("<input type='email' name='email' class='form-control' value='").append(usuario.getEmail()).append("' required></div>")
                    .append("<input type='hidden' name='contrasenia' class='form-control' value='").append(usuario.getContrasenia()).append("'>")
                    .append("<input type='hidden' name='confirmarContra' class='form-control' value='").append(usuario.getContrasenia()).append("'>")
                    .append("<div class='mb-3'><label class='form-label'>Rol <span class='text-danger'>*</span></label>")
                    .append("<select name='rol' class='form-select' required>")
                    .append("<option value='ADMIN'").append(usuario.getRol().name().equals("ADMIN") ? " selected" : "").append(">Administrador</option>")
                    .append("<option value='CLIENTE'").append(usuario.getRol().name().equals("CLIENTE") ? " selected" : "").append(">Cliente</option>")
                    .append("</select></div>")
                    .append("<label class='form-label'><span class=\"text-danger\">* Campos obligatorios</span></label>")
                    .append("<button type='submit' class='btn btn-success w-100'>Guardar</button>")
                    .append("</form>")
                    .append("</div>") // modal-body
                    .append("</div>") // modal-content
                    .append("</div>") // modal-dialog
                    .append("</div>"); // modal

            // Modal para eliminar
            html.append("<div class='modal fade' id='eliminarUsuarioModal").append(usuario.getId())
                    .append("' tabindex='-1' aria-labelledby='eliminarUsuarioModalLabel").append(usuario.getId())
                    .append("' aria-hidden='true'>")
                    .append("<div class='modal-dialog modal-dialog-centered'>")
                    .append("<div class='modal-content'>")
                    .append("<div class='modal-header'>")
                    .append("<h5 class='modal-title' id='eliminarUsuarioModalLabel").append(usuario.getId())
                    .append("'>Eliminar usuario: ").append(usuario.getNombreCompleto()).append("</h5>")
                    .append("<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Cerrar'></button>")
                    .append("</div>")
                    .append("<div class='modal-body'>")
                    .append("<p>¿Estás seguro de que deseas eliminar este usuario?</p>")
                    .append("</div>")
                    .append("<div class='modal-footer'>")
                    .append("<form action='EliminarUsuario' method='POST'>")
                    .append("<input type='hidden' name='id' value='").append(usuario.getId()).append("'>")
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
