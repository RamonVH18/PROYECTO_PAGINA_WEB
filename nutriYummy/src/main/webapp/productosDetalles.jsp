<%-- 
    Document   : productos
    Created on : 15 nov 2025, 8:47:11 p.m.
    Author     : rocha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ControladorProducto"%>
<%@page import="modelo.Producto"%>

<%
    HttpSession sesion = request.getSession(false);
    ControladorProducto controladorProducto = new ControladorProducto();
%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Producto producto = controladorProducto.getProducto(id);
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="description" content="Tienda Online, Comida, Snacks, Saludables, Productos, Catálogo">
        <meta name="author" content="Nutri Yummy">

        <title>Detalles del producto - Nutri Yummy</title>
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
                            <%
                                if (sesion.getAttribute("usuario") == null) {
                            %>
                            <li class="social-icon-item">
                                <a href="inicioSesion.jsp" class="social-icon-link"><img
                                        src="img/usuario.png" alt="Gmail"></a>
                            </li>
                            <%
                            } else {
                            %>
                            <li class="social-icon-item">
                                <a href="perfil.jsp" class="btn text-white" style="border-radius: 20px; padding: 5px 15px; background-color:#f5b729">
                                    Perfil
                                </a>
                            </li>
                            <%
                                }
                            %>
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

                <div class="col-lg-6 col-md-8">

                    <div class="custom-block-wrap-product-details shadow-lg p-3 rounded-4">
                        <br>
                        <img src="img/<%= producto.getImg()%>" class="custom-block-image img-fluid rounded-4 d-block mx-auto" alt="<%= producto.getNombre()%>">

                        <div class="custom-block">

                            <div class="custom-block-body text-center">

                                <h3 class="mb-3">
                                    <%= producto.getNombre()%>
                                </h3>

                                <p class="text-muted">
                                    <% String numeroFormateado = String.format("%05d", producto.getNumero());%>
                                    Número del producto: <%= numeroFormateado%>
                                </p>

                                <p class="mb-4">
                                    <%= producto.getDescripcion()%>
                                </p>

                                <h4 class="text-success fw-bold mb-4">
                                    $<%= producto.getPrecio()%> MXN
                                </h4>

                                <!-- Formulario agregar carrito -->
                                <form action="AgregarProductoCarrito" method="POST" class="text-center">
                                    <label class="fw-bold d-block mb-2">Cantidad:</label>

                                    <input type="hidden" name="idProducto" value="<%= producto.getId()%>">

                                    <input type="number"
                                           class="form-control w-50 mx-auto mb-3"
                                           name="cantidad"
                                           value="1"
                                           min="1">

                                    <button type="submit" class="custom-btn btn w-100 py-2">
                                        <i class="fa fa-shopping-cart me-2"></i>
                                        Agregar al carrito
                                    </button>
                                </form>
                            </div>
                        </div>
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
