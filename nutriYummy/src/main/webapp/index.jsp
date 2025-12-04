<%-- 
    Document   : index
    Created on : 15 nov 2025, 8:45:53 p.m.
    Author     : rocha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controlador.ControladorProducto"%>

<%
    HttpSession sesion = request.getSession(false);
    ControladorProducto controlador = new ControladorProducto();
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="description" content="Tienda Online, Comida, Snacks, Saludables">
        <meta name="author" content="Nutri Yummy">

        <title>Inicio | Nutri Yummy</title>
        <!-- Icono -->
        <link rel="icon" href="img/favicon.ico" type="image/x-icon">

        <!-- Font -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

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
                <a class="navbar-brand" href="index.jsp">
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
                            <a class="nav-link click-scroll active" href="#top">Inicio</a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link click-scroll dropdown-toggle" href="#section_5"
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

                        <%
                            if (sesion.getAttribute("usuario") == null) {
                        %>
                        <li class="nav-item">
                            <a href="inicioSesion.jsp" class="nav-link click-scroll"><i class="bi bi-person-circle text-dark"></i> Iniciar sesión</a>
                        </li>
                        <%
                        } else {
                        %>
                        <li class="nav-item">
                            <a href="perfil.jsp" class="nav-link click-scroll"><i class="bi bi-person-circle text-dark"></i> Mi perfil</a>
                        </li>
                        
                        <li class="nav-item">
                            <a href="carrito.jsp" class="nav-link click-scroll"><i class="bi bi-cart3 text-dark"></i> Mi carrito</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#confirmModal">
                                <i class="bi-box-arrow-right"></i> Cerrar sesión
                            </a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </nav>

        <main>
            <!-- Carrusel banner -->
            <section class="hero-section hero-section-full-height">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 col-12 p-0">
                            <div id="hero-slide" class="carousel carousel-fade slide" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src="img/carrusel1.jpg" class="carousel-image img-fluid" alt="...">
                                    </div>

                                    <div class="carousel-item">
                                        <img src="img/carrusel2.png" class="carousel-image img-fluid" alt="...">
                                    </div>

                                    <div class="carousel-item">
                                        <img src="img/carrusel3.png" class="carousel-image img-fluid" alt="...">
                                    </div>
                                </div>

                                <button class="carousel-control-prev" type="button" data-bs-target="#hero-slide"
                                        data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>

                                <button class="carousel-control-next" type="button" data-bs-target="#hero-slide"
                                        data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Sección de opciones -->
            <section class="section-padding">
                <div class="container">
                    <div class="row justify-content-center text-center">

                        <div class="col-lg-10 col-12 text-center mx-auto">
                            <h2 class="mb-5">¡Encuentra tu snack favorito!</h2>
                        </div>

                        <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
                            <div class="featured-block d-flex justify-content-center align-items-center">
                                <a href="#section_2" class="d-block">
                                    <img src="img/icono1.png" class="featured-block-image img-fluid" alt="Mejores vendidos">

                                    <p class="featured-block-text"><strong>Mejores</strong> vendidos</p>
                                </a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 mb-md-4">
                            <div class="featured-block d-flex justify-content-center align-items-center">
                                <a href="#section_3" class="d-block">
                                    <img src="img/icono2.png" class="featured-block-image img-fluid"
                                         alt="Puntos de venta físicos">

                                    <p class="featured-block-text"><strong>Puntos</strong> de <strong>venta</strong> físicos
                                    </p>
                                </a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 mb-md-4">
                            <div class="featured-block d-flex justify-content-center align-items-center">
                                <a href="#section_4" class="d-block">
                                    <img src="img/icono3.jpg" class="featured-block-image img-fluid"
                                         alt="Tutoriales y recetas">

                                    <p class="featured-block-text">Tutoriales y <strong>recetas</strong></p>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Mejores vendidos -->
            <section class="section-padding section-bg" id="section_2">
                <div class="container">
                    <div class="row">
                        <%= controlador.getMejoresVendidos()%>
                    </div>
                </div>
            </section>

            <!-- Botón para catálogo -->
            <section class="cta-section section-padding section-bg">
                <div class="container">
                    <div class="row justify-content-center align-items-center">
                        <div class="col-lg-5 col-12 ms-auto">
                            <h2 class="mb-0">Conoce nuestro <br>catálogo completo.</h2>
                        </div>

                        <div class="col-lg-5 col-12">
                            <a href="productos.jsp" class="custom-btn btn smoothscroll">Ver todos los productos</a>
                        </div>

                    </div>
                </div>
            </section>

            <!-- Puntos de venta -->
            <section class="places-section section-padding" id="section_3">
                <div class="container">
                    <div class="row">

                        <div class="col-lg-8 col-12 mx-auto">
                            <h2 class="mb-lg-3">Puntos de venta físicos</h2>

                            <div id="places-carousel" class="carousel carousel-fade slide" data-bs-ride="carousel">

                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <div class="row align-items-center">
                                            <!-- Columna del mapa -->
                                            <div class="col-md-6 mb-3 mb-md-0">
                                                <iframe
                                                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d8210.717138598!2d-109.95793035401361!3d27.49112378890693!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x86c83fb40d7830b5%3A0x548538b9e693450e!2sLuar!5e0!3m2!1ses!2smx!4v1761241568533!5m2!1ses!2smx"
                                                    width="100%" height="300" style="border:0;" allowfullscreen=""
                                                    loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                                            </div>

                                            <!-- Columna de la información -->
                                            <div class="col-md-6">
                                                <h4 class="carousel-title">Luar Cafetería</h4>
                                                <p>Puedes encontrar: Maicitos, choco obleas y coco obleas</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="carousel-item">
                                        <div class="row align-items-center">
                                            <!-- Columna del mapa -->
                                            <div class="col-md-6 mb-3 mb-md-0">
                                                <iframe
                                                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3539.301043501792!2d-109.97338722475426!3d27.49101157630722!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x86c8158f9999c331%3A0x1011f1ea6a200e37!2sFull%20Cup!5e0!3m2!1ses!2smx!4v1761242481751!5m2!1ses!2smx"
                                                    width="400" height="300" style="border:0;" allowfullscreen=""
                                                    loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                                            </div>

                                            <!-- Columna de la información -->
                                            <div class="col-md-6">
                                                <h4 class="carousel-title">Full Cup Cafetería</h4>
                                                <p>Puedes encontrar: Maicitos, chips de vegetales y choco obleas</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <button class="carousel-control-prev" type="button" data-bs-target="#places-carousel"
                                        data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>

                                <button class="carousel-control-next" type="button" data-bs-target="#places-carousel"
                                        data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

            <!-- Tutoriales y recetas -->
            <section class="tutorial-section section-padding section-bg" id="section_4">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 col-12 mb-5 mb-lg-0">
                            <div class="d-flex justify-content-center">
                                <blockquote class="instagram-media" 
                                            data-instgrm-permalink="https://www.instagram.com/reel/DHrKRk4STwh/"
                                            data-instgrm-version="14">
                                </blockquote>

                                <!-- Script de instagram -->
                                <script async src="//www.instagram.com/embed.js"></script>
                            </div>
                        </div>

                        <div class="col-lg-6 col-12">
                            <div class="custom-text-box">
                                <h2 class="mb-2">Tutoriales y recetas</h2>

                                <h5 class="mb-3">Porque esto solo mejora, descubre cómo darle un toque saludable a tus
                                    antojos favoritos.</h5>

                                <div class="custom-text-box mb-lg-0 text-center">
                                    <p class="mb-0">Te dejamos un reel con una receta de chips preparadas, acompáñalas con
                                        la fruta o vegetal que más te guste. Da click en la imagen para verlo, ¡Estamos
                                        seguros que te encantará!</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="modalCerrarSesion.jsp" />

        <!-- Footer -->
        <footer class="site-footer">
            <jsp:include page="footer.jsp" />
        </footer>
    </body>
</html>
