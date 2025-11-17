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
        // Configurar codificación
        request.setCharacterEncoding("UTF-8");
        
        ModeloUsuario modeloUsuario = new ModeloUsuario();

        try {
            // Crear objeto Usuario con los datos del formulario
            Usuario usuario = new Usuario(
                    Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("numero")),
                    request.getParameter("nombre"),
                    request.getParameter("apellidoPaterno"),
                    request.getParameter("apellidoMaterno"),
                    request.getParameter("email"),
                    RolUsuario.valueOf(request.getParameter("rol")),
                    request.getParameter("contrasenia")
            );

            // Pasarlo al modelo
            boolean actualizado = modeloUsuario.editarUsuario(usuario);

            // Usar la sesión para almacenar mensajes
            HttpSession session = request.getSession();
     
            if (actualizado) {
                session.setAttribute("mensajeExito", "Usuario actualizado correctamente.");
            } else {
                session.setAttribute("mensajeError", "No se pudo actualizar el usuario.");
            }

            response.sendRedirect("usuariosAdmin.jsp");
        } catch (NumberFormatException e) {
            HttpSession session = request.getSession();
            session.setAttribute("mensajeError", "Error al editar usuario: " + e.getMessage());
            response.sendRedirect("usuariosAdmin.jsp");
        }
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
