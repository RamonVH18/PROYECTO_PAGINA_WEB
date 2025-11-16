/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ModeloProducto;
import modelo.Producto;
import modelo.enums.TipoProducto;

/**
 *
 * @author rocha
 */
public class AgregarProducto extends HttpServlet {

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
        
        ModeloProducto modeloProducto = new ModeloProducto();

        try {
            // Crear objeto Producto con los datos del formulario
            Producto producto = new Producto(
                    Integer.parseInt(request.getParameter("numero")),
                    request.getParameter("nombre"),
                    request.getParameter("descripcion"),
                    Double.parseDouble(request.getParameter("precio")),
                    Integer.parseInt(request.getParameter("stock")),
                    request.getParameter("img"),
                    TipoProducto.valueOf(request.getParameter("tipo"))
            );

            // Pasarlo al modelo
            boolean exito = modeloProducto.insertarProducto(producto);

            // Usar la sesión para almacenar mensajes
            HttpSession session = request.getSession();
     
            System.out.println(exito);
            if (exito) {
                session.setAttribute("mensajeExito", "Producto agregado correctamente.");
            } else {
                session.setAttribute("mensajeError", "No se pudo agregar el producto.");
            }

            response.sendRedirect("productosAdmin.jsp");
        } catch (NumberFormatException e) {
            HttpSession session = request.getSession();
            session.setAttribute("mensajeError", "Error al insertar producto: " + e.getMessage());
            response.sendRedirect("productosAdmin.jsp");
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
