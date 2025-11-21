/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ModeloUsuario;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import utils.Validador;

/**
 *
 * @author Ramon Valencia
 */
@WebServlet(name = "CambiarContra", urlPatterns = {"/CambiarContra"})
public class CambiarContra extends HttpServlet {

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
        // Configurar codificación
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ModeloUsuario modeloUsuario = new ModeloUsuario();

        // Obtener valores
        String email = request.getParameter("email");
        String contrasenia = request.getParameter("contrasenia");
        String nuevaContra = request.getParameter("nuevaContra");
        String confirmarNuevaContra = request.getParameter("confirmarNuevaContra");

        if (Validador.estaVacio(contrasenia)) {
            error(session, response, "Debe ingresar una contraseña.");
            return;
        }

        if (Validador.estaVacio(nuevaContra)) {
            error(session, response, "Debe ingresar una nueva contraseña.");
            return;
        }

        if (Validador.estaVacio(confirmarNuevaContra)) {
            error(session, response, "Debe confirmar la nueva contraseña.");
            return;
        }

        try {
            String hashAlmacenado = modeloUsuario.getContraseña(email);
            
            boolean coincide = BCrypt.checkpw(contrasenia, hashAlmacenado);

            if (!coincide) {
                error(session, response, "La contraseña actual ingresada no es correcta.");
                return;
            }

            if (nuevaContra.equals(contrasenia)) {
                error(session, response, "La contraseña actual y la nueva no pueden ser la misma.");
                return;
            }

            // Se hashea la contraseña nueva
            String hash = BCrypt.hashpw(nuevaContra, BCrypt.gensalt());
            boolean exito = modeloUsuario.cambiarContraseña(email, hash);

            if (exito) {
                session.setAttribute("mensajeExito", "Contraseña modificada con éxito.");
            } else {
                error(session, response, "Error el modificar la contraseña.");
                return;
            }

            response.sendRedirect("perfil.jsp");
        } catch (IOException e) {
            error(session, response, "Error inesperado: " + e.getMessage());
        }

    }

    private void error(HttpSession session, HttpServletResponse response, String msg) throws IOException {
        session.setAttribute("mensajeError", msg);
        response.sendRedirect("perfil.jsp");
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
