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
public class EliminarUsuario extends HttpServlet {

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
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            Usuario usuario = modeloUsuario.getUsuario(id);
            
            if (usuario == null) {
                error(session, response, "No existe el usuario para eliminar.");
                return;
            }

            if (usuario.getRol() == RolUsuario.ADMIN) {
                boolean hayMasAdmins = modeloUsuario.hayMasAdmins();

                if (!hayMasAdmins) {
                    error(session, response, "Es el único usuario administrador del sistema. No se permite eliminar el usuario.");
                    return;
                }
            }
            
            // Pasarlo al modelo
            boolean eliminado = modeloUsuario.eliminarUsuario(id);
            String numeroFormateado = String.format("%05d", usuario.getNumero());
     
            if (eliminado) {
                session.setAttribute("mensajeExito", "Usuario #" + numeroFormateado + " eliminado con éxito.");
            } else {
                session.setAttribute("mensajeError", "No se pudo eliminar el usuario.");
            }

            response.sendRedirect("usuariosAdmin.jsp");
        } catch (NumberFormatException e) {
            session.setAttribute("mensajeError", "Error al eliminar usuario: " + e.getMessage());
            response.sendRedirect("usuariosAdmin.jsp");
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
