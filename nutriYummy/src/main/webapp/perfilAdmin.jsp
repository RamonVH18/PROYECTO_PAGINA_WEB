<%-- 
    Document   : productosAdmin
    Created on : 16 nov 2025, 12:59:00 p.m.
    Author     : rocha
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="controlador.ControladorUsuario"%>

<%
    ControladorUsuario controladorUsuario = new ControladorUsuario();
%>

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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mi perfil | Nutri Yummy</title>

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

        <!-- Checkbox Oculto que Controla el Estado del Sidebar -->
        <!-- Se aplica 'checked' por defecto para que el sidebar esté visible al cargar -->
        <input type="checkbox" id="sidebar-toggle-checkbox" checked> 

        <nav class="navbar navbar-expand-lg fixed-top shadow-lg">
            <div class="container-fluid">

                <!-- Label que actúa como Botón de Toggle (visible solo en escritorio) -->
                <label for="sidebar-toggle-checkbox" class="btn btn-link text-dark d-none d-lg-block toggle-label">
                    <i class="bi-list fs-4"></i>
                </label>

                <a class="navbar-brand d-flex align-items-center" href="dashboard.jsp">
                    <img src="img/logo.png" class="logo img-fluid" alt="Nutri Yummy">
                    <span>
                        Nutri Yummy
                        <small class="d-block">Panel de Administrador</small>
                    </span>
                </a>

                <!-- Botón de Toggle de Bootstrap para móvil (usa la funcionalidad estándar con JS) -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu"
                        aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#confirmModal">
                                <i class="bi-box-arrow-right"></i> Cerrar Sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- EL D-FLEX CONTENEDOR ES AHORA EL QUE DEBE SER MOVIDO POR CSS -->
        <div class="d-flex" id="wrapper">

            <!-- El sidebar ya no es 'collapse' porque el toggle se maneja por CSS -->
            <nav id="sidebarMenu" class="sidebar d-lg-block">
                <div class="position-sticky">
                    <ul class="nav flex-column pt-3">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="dashboard.jsp">
                                <i class="bi-house-door-fill"></i> Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="productosAdmin.jsp">
                                <i class="bi-bag-fill"></i> Productos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="usuariosAdmin.jsp">
                                <i class="bi-people-fill"></i> Usuarios
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="pedidosAdmin.jsp">
                                <i class="bi-receipt-cutoff"></i> Pedidos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="perfilAdmin.jsp">
                                <i class="bi bi-person-circle"></i> Mi perfil
                            </a>
                        </li>
                        <li class="nav-item d-lg-none">
                            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#confirmModal">
                                <i class="bi-box-arrow-right"></i> Cerrar sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <main class="main-content flex-grow-1 p-3 p-md-4">

                <p class="lead mb-4">Consulta los datos de tu perfil de administrador y cambia tu contraseña.</p>

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

                <!-- Cargar la info del perfil --> 
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

                                                <div class="col-md-6">
                                                    <label class="form-label text-muted small">Número de usuario</label>
                                                    <%String numeroFormateado = String.format("%05d", usuario.getNumero());%>
                                                    <input type="text" class="form-control bg-white" value="<%= numeroFormateado%>" readonly>
                                                </div>

                                                <div class="col-12">
                                                    <label class="form-label text-muted small">Correo electrónico</label>
                                                    <input type="text" class="form-control bg-white" value="<%= usuario.getEmail()%>" readonly>
                                                </div>

                                                <div class="col-12">
                                                    <label class="form-label text-muted small">Rol</label>
                                                    <input type="text" class="form-control bg-white" value="<%= usuario.getRol().name()%>" readonly>
                                                </div>
                                            </div>
                                        </form><br>

                                        <!-- Botón para cambiar contraseña -->
                                        <div class="mb-3">
                                            <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#cambiarContraModal">
                                                <i class="bi-pencil"></i> Cambiar contraseña
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
        </div>

        <jsp:include page="modalCambiarContra.jsp" />

        <jsp:include page="modalCerrarSesion.jsp" />

        <!-- Scripts necesarios para Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"></script>
    </body>

</html>
