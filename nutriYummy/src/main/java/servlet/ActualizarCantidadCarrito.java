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
public class ActualizarCantidadCarrito extends HttpServlet {

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

        HttpSession sesion = request.getSession();
        ArrayList<Articulo> carrito = (ArrayList<Articulo>) sesion.getAttribute("carrito");

        if (carrito == null) {
            response.getWriter().write("{\"error\": \"No hay carrito activo\"}");
            return;
        }

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cambio = Integer.parseInt(request.getParameter("cambio"));

        if (cambio < 1) {
            response.getWriter().write("{\"error\": \"Cantidad inválida\"}");
            return;
        }

        ControladorProducto controlador = new ControladorProducto();
        Producto producto = controlador.getProducto(idProducto);

        if (producto == null) {
            response.getWriter().write("{\"error\": \"Producto no encontrado\"}");
            return;
        }

        int stock = producto.getStock();
        if (cambio > stock) {
            String numeroFormateado = String.format("%05d", producto.getNumero());
            response.getWriter().write("{\"error\": \"Stock insuficiente para el producto # " + numeroFormateado + ". Solo hay " + stock + " piezas disponibles.\"}");
            return;
        }

        // Total del producto modificado (precio * cantidad)
        double totalProducto = producto.getPrecio() * cambio;

        // Actualizar cantidad del artículo
        for (Articulo a : carrito) {
            if (a.getIdProducto() == idProducto) {
                a.setCantidad(cambio);
                break;
            }
        }

        // Calcular totales del carrito
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

        String json = String.format(
                "{"
                + "\"subtotal\":\"%.2f\","
                + "\"iva\":\"%.2f\","
                + "\"total\":\"%.2f\","
                + "\"totalProducto\":\"%.2f\""
                + "}",
                subtotal, ivaTotal, total, totalProducto
        );

        System.out.println(json);

        response.getWriter().write(json);
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
