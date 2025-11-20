/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Articulo;
import modelo.DetallesVenta;
import modelo.ModeloDetallesVenta;
import modelo.ModeloProducto;
import modelo.ModeloVenta;
import modelo.Producto;
import modelo.Usuario;
import modelo.Venta;

/**
 *
 * @author rocha
 */
public class RegistrarVenta extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        ArrayList<Articulo> carrito = (ArrayList<Articulo>) session.getAttribute("carrito");
        System.out.println(carrito);

        if (carrito == null || carrito.isEmpty()) {
            error(session, response, "El carrito está vacío. No se puede registrar una venta.");
            return;
        }
        
        ModeloProducto modeloProducto = new ModeloProducto();
        ModeloVenta modeloVenta = new ModeloVenta();
        ModeloDetallesVenta modeloDetalles = new ModeloDetallesVenta();
       
        try {
            String folioVenta = generarFolioVenta();
            int idUsuario = 1;
            
            Venta venta = new Venta(folioVenta, idUsuario);
          
            // Registrar la venta
            int idVenta = modeloVenta.insertarVenta(venta);

            if (idVenta <= 0) {
                error(session, response, "Ocurrió un error al registrar la venta. No fue posible terminar el proceso.");
                return;
            }
            
            // Registrar cada detalle
            for (Articulo articulo : carrito) {
                Producto producto = modeloProducto.getProducto(articulo.getIdProducto());
                double precio = producto.getPrecio();
                DetallesVenta detalle = new DetallesVenta(articulo.getCantidad(), precio * 0.16, precio, producto.getId());
                modeloDetalles.insertarDetalleVenta(detalle, idVenta);
            }
            
            // Limpiar carrito
            session.removeAttribute("carrito");
            
            session.setAttribute("mensajeExito", "Compra realizada con éxito");
            response.sendRedirect("carrito.jsp");
        } catch (IOException e) {
            error(session, response, "Error inesperado: " + e.getMessage());
        }
    }

    private String generarFolioVenta() {
        int numero = (int) (Math.random() * 100000000);
        return String.format("%08d", numero);
    }
    
    private void error(HttpSession session, HttpServletResponse response, String msg) throws IOException {
        session.setAttribute("mensajeError", msg);
        response.sendRedirect("carrito.jsp");
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
