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
import modelo.ModeloProducto;
import modelo.Producto;
import modelo.enums.TipoProducto;
import utils.Validador;

/**
 *
 * @author rocha
 */
public class EditarProducto extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        ModeloProducto modeloProducto = new ModeloProducto();

        // Obtener valores
        String idStr = request.getParameter("id");
        String numeroStr = request.getParameter("numero");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precioStr = request.getParameter("precio");
        String stockStr = request.getParameter("stock");
        String img = request.getParameter("img");
        String tipo = request.getParameter("tipo");

        // Validar campos
        if (!Validador.esEnteroValido(idStr)) {
            error(session, response, "ID inválido.");
            return;
        }

        if (!Validador.esEnteroValido(numeroStr)) {
            error(session, response, "Número de producto inválido.");
            return;
        }

        if (Validador.estaVacio(nombre) || !Validador.tieneLongitudMinima(nombre, 2) || !Validador.tieneLongitudMaxima(nombre, 50)) {
            error(session, response, "El nombre debe tener entre 2 y 50 caracteres.");
            return;
        }

        if (Validador.estaVacio(descripcion) || !Validador.tieneLongitudMinima(descripcion, 2) || !Validador.tieneLongitudMaxima(descripcion, 100)) {
            error(session, response, "La descripción debe tener entre 2 y 100 caracteres.");
            return;
        }

        if (!Validador.esDecimalValido(precioStr)) {
            error(session, response, "El precio no es válido.");
            return;
        }

        double precio = Double.parseDouble(precioStr);
        if (precio < 0) {
            error(session, response, "El precio del producto no puede ser negativo.");
            return;
        }

        if (!Validador.esEnteroValido(stockStr)) {
            error(session, response, "El stock no es válido.");
            return;
        }

        int stock = Integer.parseInt(stockStr);
        if (stock < 0) {
            error(session, response, "El stock del producto no puede ser negativo.");
            return;
        }

        if (Validador.estaVacio(img) || !Validador.tieneLongitudMinima(img, 6) || !Validador.tieneLongitudMaxima(img, 255)) {
            error(session, response, "La ruta de la imagen debe tener entre 6 y 255 caracteres.");
            return;
        }

        if (Validador.estaVacio(tipo)) {
            error(session, response, "El tipo de producto es obligatorio.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int numero = Integer.parseInt(numeroStr);

            String numeroFormateado = String.format("%05d", numero);

            Producto productoViejo = modeloProducto.getProducto(id);
            
            if (productoViejo == null) {
                error(session, response, "No existe el producto #" + numeroFormateado + " para editar.");
                return;
            }
            
            if (productoViejo.getNumero() != numero && modeloProducto.existeProductoNumero(numero)) {
                error(session, response, "Ya existe un producto con el número #" + numeroFormateado + ".");
                return;
            }

            // Crear objeto
            Producto producto = new Producto(
                    id,
                    numero,
                    nombre.trim(),
                    descripcion.trim(),
                    precio,
                    stock,
                    img.trim(),
                    TipoProducto.valueOf(tipo)
            );

            boolean actualizado = modeloProducto.editarProducto(producto);

            if (actualizado) {
                session.setAttribute("mensajeExito", "Producto actualizado correctamente.");
            } else {
                session.setAttribute("mensajeError", "No se pudo actualizar el producto.");
            }

            response.sendRedirect("productosAdmin.jsp");

        } catch (IOException | NumberFormatException e) {
            error(session, response, "Error inesperado: " + e.getMessage());
        }
    }

    private void error(HttpSession session, HttpServletResponse response, String msg) throws IOException {
        session.setAttribute("mensajeError", msg);
        response.sendRedirect("productosAdmin.jsp");
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
