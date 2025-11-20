<%-- 
    Document   : productos
    Created on : 15 nov 2025, 8:47:11 p.m.
    Author     : rocha
--%>

<%@page import="modelo.Articulo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ControladorProducto"%>
<%@page import="modelo.Producto"%>

<%
    ControladorProducto controladorProducto = new ControladorProducto();
%>

<%
    HttpSession sesion = request.getSession(true);
    ArrayList<Articulo> articulos = sesion.getAttribute("carrito") == null ? null : (ArrayList) sesion.getAttribute("carrito");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="Tienda Online, Comida, Snacks, Saludables, Productos, Catálogo">
    <meta name="author" content="Nutri Yummy">

    <title>Mi carrito de compras - Nutri Yummy</title>
    <!-- Icono -->
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">

    <!-- Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- CSS de Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- CSS FILES -->
    <link href="css/styles.css" rel="stylesheet">
</head>

<body id="section_1">

    <header class="site-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-12 d-flex flex-wrap">
                    <p class="d-flex me-4 mb-0">
                        Ciudad Obregón, Sonora.
                    </p>
                </div>

                <!-- Enlaces a redes sociales -->
                <div class="col-lg-3 col-12 ms-auto d-lg-block d-none">
                    <ul class="social-icon">
                        <li class="social-icon-item">
                            <a href="https://www.facebook.com/p/NutriYummy-61575913104823/" target="_blank"
                                class="social-icon-link"><img src="img/facebook.png" alt="Facebook"></a>
                        </li>
                        <li class="social-icon-item">
                            <a href="https://www.instagram.com/nutri.yummymx/" target="_blank"
                                class="social-icon-link"><img src="img/instagram.png" alt="Instagram"></a>
                        </li>
                        <li class="social-icon-item">
                            <a href="mailto:nutriyummy25@gmail.com" target="_blank" class="social-icon-link"><img
                                    src="img/gmail.png" alt="Gmail"></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>

    <!-- Header -->
    <nav class="navbar navbar-expand-lg shadow-lg">
        <div class="container">
            <a class="navbar-brand" href="index.html">
                <img src="img/logo.png" class="logo img-fluid" alt="Nutri Yummy">
                <span>
                    Nutri Yummy
                    <small>Snacks saludables y más</small>
                </span>
            </a>

            <!-- Botón para pantallas pequeñas -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Nav -->
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link click-scroll" href="index.jsp">Inicio</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link click-scroll dropdown-toggle active" href="#"
                           id="navbarLightDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">Productos</a>

                        <ul class="dropdown-menu dropdown-menu-light" aria-labelledby="navbarLightDropdownMenuLink">
                            <li><a class="dropdown-item" href="productos.jsp">Ver todos</a></li>
                            <li><a class="dropdown-item" href="productos.jsp?categoria=chips">Chips</a></li>
                            <li><a class="dropdown-item" href="productos.jsp?categoria=maicitos">Maicitos</a></li>
                            <li><a class="dropdown-item" href="productos.jsp?categoria=obleas">Obleas</a></li>
                            <li><a class="dropdown-item" href="productos.jsp?categoria=otros">Otros</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link click-scroll" href="contacto.jsp">Contacto</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- CONTENIDO -->
    <main class="container py-5">
        <div class="row justify-content-center">

            <div class="col-lg-12 col-12 text-center mb-4">
                <h2>Mi carrito de compras</h2>
            </div>

            <!-- Mensajes de confirmación --> 
                <%
                    if (session != null) {
                        if (session.getAttribute("mensajeExito") != null) {
                %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <%= session.getAttribute("mensajeExito")%>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                </div>
                <%
                        session.removeAttribute("mensajeExito");
                    }

                    if (session.getAttribute("mensajeError") != null) {
                %>
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <%= session.getAttribute("mensajeError")%>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
                </div>
                <%
                            session.removeAttribute("mensajeError");
                        }
                    }
                %>
                
            <!-- LISTA DE PRODUCTOS -->
            <div class="col-lg-7 col-md-12 mb-4">

                <div class="shadow-lg p-4 rounded-4">

                    <h3 class="mb-4 fw-bold">Productos</h3>

                    <%
                        ControladorProducto cp = new ControladorProducto();
                        double total = 0;

                        if (articulos != null) {
                            for (Articulo a : articulos) {
                                Producto producto = cp.getProducto(a.getIdProducto());
                                total += a.getCantidad() * producto.getPrecio();
                    %>

                    <!-- ITEM DEL CARRITO -->
                    <div class="d-flex align-items-center justify-content-between border-bottom pb-3 mb-3">

                        <!-- IMAGEN -->
                        <div>
                            <img src="img/<%= producto.getImg()%>"
                                 alt="<%= producto.getNombre()%>"
                                 class="img-fluid rounded-4"
                                 style="width: 110px;">
                        </div>

                        <!-- INFO -->
                        <div class="flex-grow-1 px-3">
                            <h5 class="fw-bold"><%= producto.getNombre()%></h5>

                            <p class="text-muted small">
                                Número <%= String.format("%05d", producto.getNumero())%>
                            </p>

                            <p class="fw-bold text-success">
                                <% String precioFormateado = String.format("%.2f", producto.getPrecio()); %>
                                $<%=precioFormateado%> MXN
                            </p>
                        </div>

                        <!-- CANTIDAD -->
                        <div class="text-center">
                            <div class="d-flex align-items-center">

                                <a href="#" class="btn btn-outline-secondary btn-sm me-2">-</a>

                                <input type="text"
                                       class="form-control text-center"
                                       value="<%= a.getCantidad()%>"
                                       style="width: 55px;">

                                <a href="#" class="btn btn-outline-secondary btn-sm ms-2">+</a>
                            </div>
                        </div>

                        <!-- TOTAL -->
                        <div class="text-end px-3 fw-bold">
                            $<%= Math.round(producto.getPrecio() * a.getCantidad() * 100.0) / 100.0%>
                        </div>

                        <!-- ELIMINAR -->
                        <div>
                            <form action="EliminarArticulo" method="POST">
                                <input type="hidden" name="idProducto" value="<%= producto.getId()%>">
                                <button class="btn btn-danger btn-sm">
                                    <i class="fa fa-times"></i>
                                </button>
                            </form>
                        </div>

                    </div>
                    <%      }
                        }
                    %>

                    <% if (articulos == null) { %>
                    <h5 class="text-center mt-4">No hay artículos en el carrito</h5>
                    <% }%>

                    <a class="btn btn-warning mt-3 text-light" href="javascript:history.back();">
                        <i class="fa fa-arrow-left me-2"></i> Seguir comprando
                    </a>

                </div>
            </div>


            <!-- RESUMEN DE COMPRA -->
            <div class="col-lg-5 col-md-12">
                <div class="shadow-lg rounded-4 p-4">

                    <h4 class="fw-bold mb-3">Resumen</h4>

                    <div class="d-flex justify-content-between mb-2">
                        <span>Subtotal:</span>
                        <span class="fw-bold">$<%= Math.round(total * 100.0) / 100.0%></span>
                    </div>

                    <div class="d-flex justify-content-between mb-2">
                        <span>IVA:</span>
                        <span class="fw-bold">$0.00</span>
                    </div>

                    <div class="d-flex justify-content-between mb-3">
                        <span class="fw-bold fs-5">Total:</span>
                        <span class="fw-bold fs-5 text-success">$<%= Math.round(total * 100.0) / 100.0%></span>
                    </div>

                    <form action="RegistrarVenta" method="POST">
                        <button type="submit" class="btn btn-success w-100">
                            Finalizar compra
                        </button>
                    </form>
                </div>
            </div>

        </div>
    </main>

    <!-- Footer -->
    <footer class="site-footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-12 mb-4">
                    <img src="img/logo.png" class="logo img-fluid" alt="Logo">
                </div>

                <div class="col-lg-4 col-md-6 col-12 mb-4">
                    <h5 class="site-footer-title mb-3">Enlaces</h5>

                    <ul class="footer-menu">
                        <li class="footer-menu-item"><a href="#" class="footer-menu-link">Inicio</a></li>

                        <li class="footer-menu-item"><a href="#" class="footer-menu-link">Nuestros productos</a></li>

                        <li class="footer-menu-item"><a href="contacto.html" class="footer-menu-link">Contacto</a></li>
                    </ul>
                </div>

                <div class="col-lg-4 col-md-6 col-12 mx-auto">
                    <h5 class="site-footer-title mb-3">Nosotros</h5>

                    <p class="d-flex mb-2">
                        <i class="bi-telephone me-2"></i>

                        <a href="tel: 526442566695" class="site-footer-link">
                            6442566695
                        </a>
                    </p>

                    <p class="d-flex">
                        <i class="bi-envelope me-2"></i>

                        <a href="mailto:nutriyummy25@gmail.com" class="site-footer-link">
                            nutriyummy25@gmail.com
                        </a>
                    </p>

                    <p class="d-flex mt-3">
                        <i class="bi-geo-alt me-2"></i>
                        Ciudad Obregón, Sonora.
                    </p>
                </div>
            </div>
        </div>

        <div class="site-footer-bottom">
            <div class="container">
                <div class="row">

                    <div class="col-lg-6 col-md-7 col-12">
                        <p class="copyright-text mb-0">Nutri Yummy 2025 Copyright © Todos los derechos reservados</p>
                    </div>

                    <div class="col-lg-6 col-md-5 col-12 d-flex justify-content-center align-items-center mx-auto">
                        <ul class="social-icon">
                            <li class="social-icon-item">
                                <a href="https://www.facebook.com/p/NutriYummy-61575913104823/"
                                    class="social-icon-link"><img src="img/facebook.png" alt="Facebook"></a>
                            </li>

                            <li class="social-icon-item">
                                <a href="https://www.instagram.com/nutri.yummymx/" class="social-icon-link"><img
                                        src="img/instagram.png" alt="Instagram"></a>
                            </li>

                            <li class="social-icon-item">
                                <a href="mailto:nutriyummy25@gmail.com" class="social-icon-link"><img
                                        src="img/gmail.png" alt="Gmail"></a>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </footer>
</body>

</html>
