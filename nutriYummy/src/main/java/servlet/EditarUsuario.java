/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ModeloUsuario;
import modelo.Usuario;
import modelo.enums.RolUsuario;
import utils.Validador;

/**
 *
 * @author rocha
 */
public class EditarUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        ModeloUsuario modeloUsuario = new ModeloUsuario();

        // Obtener parámetros
        String idStr = request.getParameter("id");
        String numeroStr = request.getParameter("numero");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String email = request.getParameter("email");
        String rol = request.getParameter("rol");
        String contrasenia = request.getParameter("contrasenia");
        String confirmarContra = request.getParameter("confirmarContra");

        // Validar campos
        if (!Validador.esEnteroValido(idStr) || !Validador.esEnteroValido(numeroStr)) {
            error(session, response, "ID o número inválido.");
            return;
        }

        if (Validador.estaVacio(nombre) || !Validador.tieneLongitudMinima(nombre, 2) || !Validador.tieneLongitudMaxima(nombre, 50)) {
            error(session, response, "El nombre debe tener entre 2 y 50 caracteres.");
            return;
        }

        if (Validador.estaVacio(apellidoPaterno) || !Validador.tieneLongitudMinima(apellidoPaterno, 2) || !Validador.tieneLongitudMaxima(apellidoPaterno, 50)) {
            error(session, response, "El apellido paterno debe tener entre 2 y 50 caracteres.");
            return;
        }

        if (!Validador.estaVacio(apellidoMaterno)) {
            if (!Validador.tieneLongitudMinima(apellidoMaterno, 2) || !Validador.tieneLongitudMaxima(apellidoMaterno, 50)) {
                error(session, response, "El apellido materno debe tener entre 2 y 50 caracteres.");
                return;
            }
        }
    
        if (Validador.estaVacio(email)) {
            error(session, response, "El email es obligatorio.");
            return;
        }
        
        if (!Validador.esEmailValido(email)) {
            error(session, response, "El formato del email no es válido.");
            return;
        }
      
        if (Validador.estaVacio(rol)) {
            error(session, response, "El rol es obligatorio.");
            return;
        }

        if (Validador.estaVacio(contrasenia)) {
            error(session, response, "La contraseña es obligatoria.");
            return;
        }

        if (Validador.estaVacio(confirmarContra)) {
            error(session, response, "Debe confirmar la contraseña.");
            return;
        }
        
        if (!contrasenia.equals(confirmarContra)) {
            error(session, response, "Las contraseñas deben coincidir.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int numero = Integer.parseInt(numeroStr);
            
            String numeroFormateado = String.format("%05d", numero);
            
            Usuario usuarioViejo = modeloUsuario.getUsuario(id);
            
            if (usuarioViejo == null) {
                error(session, response, "No existe el usuario #" + numeroFormateado + " para editar.");
                return;
            }
            
            if (!usuarioViejo.getRol().name().equals(rol) && usuarioViejo.getRol() == RolUsuario.ADMIN) {
                boolean hayMasAdmins = modeloUsuario.hayMasAdmins();

                if (!hayMasAdmins) {
                    error(session, response, "Es el único usuario administrador del sistema. No se permite editar el rol del usuario.");
                    return;
                }
            }

            // Validar email único
            if (!usuarioViejo.getEmail().equals(email) && modeloUsuario.existeEmailUsuario(email)) {
                error(session, response, "Ya existe un usuario con el email " + email + ".");
                return;
            }
            
            // Validar numero único
            if (usuarioViejo.getNumero() != numero && modeloUsuario.existeUsuarioNumero(numero)) {
                error(session, response, "Ya existe un usuario con el numero #" + numeroFormateado + ".");
                return;
            }

            // Crear objeto
            Usuario usuario = new Usuario(
                    id,
                    numero,
                    nombre.trim(),
                    apellidoPaterno.trim(),
                    (apellidoMaterno == null ? null : apellidoMaterno.trim()),
                    email.trim(),
                    RolUsuario.valueOf(rol),
                    contrasenia
            );

            // Pasarlo al modelo
            boolean actualizado = modeloUsuario.editarUsuario(usuario);
            
            if (actualizado) {
                session.setAttribute("mensajeExito", "Usuario actualizado correctamente.");
            } else {
                session.setAttribute("mensajeError", "No se pudo actualizar el usuario.");
            }

            response.sendRedirect("usuariosAdmin.jsp");
        } catch (IOException e) {
            error(session, response, "Error inesperado: " + e.getMessage());
        }
    }

    private void error(HttpSession session, HttpServletResponse response, String msg) throws IOException {
        session.setAttribute("mensajeError", msg);
        response.sendRedirect("usuariosAdmin.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
