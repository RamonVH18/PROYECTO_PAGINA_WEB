<%-- 
    Document   : perfil
    Created on : 20 nov 2025, 3:35:02 a.m.
    Author     : Ramon Valencia 
--%>


<%@page import="modelo.Usuario"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // --- VERIFICACIÓN DE SEGURIDAD ---
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario) sesion.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="Tienda Online, Comida, Snacks, Saludables, Productos, Catálogo">
    <meta name="author" content="Nutri Yummy">

    <title>Mi perfil - Nutri Yummy</title>
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
                        <a class="nav-link click-scroll dropdown-toggle" href="#"
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
                        <a href="perfil.jsp" class="nav-link click-scroll active"><i class="bi bi-person-circle text-dark"></i> Mi perfil</a>
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
        <section class="section-padding">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10">

                        <div class="card shadow-lg border-0 rounded-lg">

                            <div class="card-header bg-warning text-center py-4">
                                <h3 class="mb-0 text-white">Mi perfil</h3>
                            </div>

                            <div class="card-body p-5">

                                <div class="text-center mb-5">
                                    <img src="img/usuario.png" alt="Foto Perfil" class="rounded-circle img-thumbnail mb-3" width="150">

                                    <h2 class="fw-bold text-dark">
                                        <%= usuario.getNombre()%> <%= usuario.getApellidoPaterno()%>
                                    </h2>
                                </div>

                                <form>
                                    <div class="row g-3"> <div class="col-md-6">
                                            <label class="form-label text-muted small">Nombre</label>
                                            <input type="text" class="form-control bg-white" value="<%= usuario.getNombre()%>" readonly>
                                        </div>

                                        <div class="col-md-6">
                                            <label class="form-label text-muted small">Apellidos</label>
                                            <input type="text" class="form-control bg-white" value="<%= usuario.getApellidoPaterno()%> <%= usuario.getApellidoMaterno()%>" readonly>
                                        </div>

                                        <div class="col-12">
                                            <label class="form-label text-muted small">Correo Electrónico</label>
                                            <input type="text" class="form-control bg-white" value="<%= usuario.getEmail()%>" readonly>
                                        </div>

                                        <div class="col-12">
                                            <label class="form-label text-muted small">Contraseña</label>
                                            <input type="password" class="form-control bg-white" value="passwordfake123" readonly>
                                        </div>

                                    </div>
                                </form>

                                <div class="d-grid gap-2 mt-5">
                                    <a href="index.jsp" class="btn btn-link text-decoration-none text-muted text-center mt-2">
                                        Volver al inicio
                                    </a>
                                </div>

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