<%-- 
    Document   : cambiarContrasenia
    Created on : 20 nov 2025, 1:16:45 p.m.
    Author     : Ramon Valencia 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Iniciar sesión | Nutri Yummy</title>

        <!-- Icono -->
        <link rel="icon" href="img/favicon.ico" type="image/x-icon">

        <!-- Font -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

        <!-- CSS de Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- CSS files -->
        <link href="css/styles.css" rel="stylesheet">
        <link href="css/adminStyles.css" rel="stylesheet">
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
                            <a class="nav-link click-scroll" href="contacto.jsp">Contacto</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <section class="volunteer-section section-padding" id="section_4">
            <div class="container">
                <div class="row justify-content-center">

                    <div class="col-lg-6 col-12">
                        
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
                        <form class="custom-form volunteer-form mb-5 mb-lg-0" action="CambiarContra" method="post" role="form">
                            <h3 class="mb-4">Cambiar Contraseña</h3>

                            <div class="col-12">
                                <div class="col-lg-12 col-12">
                                    <label for="contrasenia" class="form-label">Contraseña <span class="text-danger">*</span></label>
                                    <input type="password" name="contrasenia" id="contrasenia" 
                                           class="form-control" placeholder="Contraseña" required>
                                </div>

                                <div class="col-lg-12 col-12">
                                    <label for="nuevaContrasenia" class="form-label">Nueva Contraseña <span class="text-danger">*</span></label>
                                    <input type="password" name="nuevaContrasenia" id="nuevaContrasenia" 
                                           class="form-control" placeholder="Nueva Contraseña" required>
                                </div>
                            </div>
                            <button type="submit" class="form-control">Cambiar Contraseña</button>
                        </form>
                    </div>

                </div>
            </div>
        </section>

        <!-- Footer -->
        <footer class="site-footer">
            <jsp:include page="footer.jsp" />
        </footer>
    </body>
</html>
