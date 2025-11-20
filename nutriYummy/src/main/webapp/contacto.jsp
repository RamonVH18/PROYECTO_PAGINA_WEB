<%-- 
    Document   : contacto
    Created on : 15 nov 2025, 8:47:56 p.m.
    Author     : rocha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession(false);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="Tienda Online, Comida, Snacks, Saludables, Contacto">
    <meta name="author" content="Nutri Yummy">

    <title>Contacto | Nutri Yummy</title>
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

<body>
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
                        <a class="nav-link click-scroll active" href="#top">Contacto</a>
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

    <!-- Sección de formulario -->
    <section class="volunteer-section section-padding" id="section_4">
        <div class="container">
            <div class="row">

                <div class="col-lg-6 col-12">
                    <h2 class="text-white mb-4">Contáctanos</h2>

                    <form class="custom-form volunteer-form mb-5 mb-lg-0" action="#" method="post" role="form">
                        <h3 class="mb-4">Envía tus opiniones</h3>

                        <div class="row">
                            <div class="col-lg-6 col-12">
                                <input type="text" name="volunteer-name" id="volunteer-name" class="form-control"
                                       placeholder="Nombre" required>
                            </div>

                            <div class="col-lg-6 col-12">
                                <input type="email" name="volunteer-email" id="volunteer-email" pattern="[^ @]*@[^ @]*"
                                       class="form-control" placeholder="Correo@gmail.com" required>
                            </div>
                        </div>

                        <textarea name="volunteer-message" rows="3" class="form-control" id="volunteer-message"
                                  placeholder="Escribe aquí..."></textarea>

                        <button type="submit" class="form-control">Enviar</button>
                    </form>
                </div>

                <div class="col-lg-6 col-12">
                    <img src="img/logo.png" class="volunteer-image img-fluid" alt="Logo">

                    <div class="custom-block-body text-center">
                        <h4 class="text-white mt-lg-3 mb-lg-3">¡Gracias por tu apoyo!</h4>

                    </div>
                </div>

            </div>
        </div>
    </section>

    <jsp:include page="modalCerrarSesion.jsp" />

    <!-- Footer -->
    <footer class="site-footer">
        <jsp:include page="footer.jsp" />
    </footer>
</body>
</html>
