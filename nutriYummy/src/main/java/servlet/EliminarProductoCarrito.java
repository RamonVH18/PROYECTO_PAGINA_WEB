/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controlador.ControladorProducto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Articulo;
import modelo.Producto;

/**
 *
 * @author rocha
 */
public class EliminarProductoCarrito extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");

        try {
            HttpSession sesion = request.getSession();
            ArrayList<Articulo> carrito = (ArrayList<Articulo>) sesion.getAttribute("carrito");

            if (carrito == null) {
                response.getWriter().write("{\"error\": \"No hay carrito activo\"}");
                return;
            }

            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            ControladorProducto controlador = new ControladorProducto();
            Producto producto = controlador.getProducto(idProducto);

            if (producto == null) {
                response.getWriter().write("{\"error\": \"Producto no encontrado\"}");
                return;
            }

            // Eliminar el producto del carrito 
            boolean eliminado = carrito.removeIf(a -> a.getIdProducto() == idProducto);

            if (!eliminado) {
                response.getWriter().write("{\"error\": \"El producto no está en el carrito\"}");
                return;
            }

            // Recalcular totales
            double subtotal = 0;
            double ivaTotal = 0;

            for (Articulo a : carrito) {
                Producto p = controlador.getProducto(a.getIdProducto());
                double precio = p.getPrecio() * a.getCantidad();
                subtotal += precio;
                ivaTotal += precio * 0.16;
            }

            double total = subtotal + ivaTotal;

            sesion.setAttribute("carrito", carrito);

            // Construcción del JSON
            String json = String.format(
                    "{"
                    + "\"subtotal\":\"%.2f\","
                    + "\"iva\":\"%.2f\","
                    + "\"total\":\"%.2f\","
                    + "\"totalProducto\":\"0.00\","
                    + "\"carritoVacio\": %s"
                    + "}",
                    subtotal, ivaTotal, total,
                    carrito.isEmpty() ? "true" : "false"
            );

            System.out.println(json);
            response.getWriter().write(json);
        } catch (IOException | NumberFormatException e) {
            response.getWriter().write("{\"error\": \"Error en el servidor: " + e.getMessage().replace("\"", "'") + "\"}");
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
