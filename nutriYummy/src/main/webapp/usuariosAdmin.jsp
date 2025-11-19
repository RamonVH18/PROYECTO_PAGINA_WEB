<%-- 
    Document   : productosAdmin
    Created on : 16 nov 2025, 12:59:00 p.m.
    Author     : rocha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="controlador.ControladorUsuario"%>

<%
    ControladorUsuario controladorUsuario = new ControladorUsuario();
%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gestionar usuarios | Nutri Yummy</title>

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

                <a class="navbar-brand d-flex align-items-center" href="index.html">
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
                            <a class="nav-link active" href="#.jsp">
                                <i class="bi-people-fill"></i> Usuarios
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="pedidosAdmin.jsp">
                                <i class="bi-receipt-cutoff"></i> Pedidos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="perfilAdmin.jsp">
                                <i class="bi bi-person-circle"></i> Mi perfil
                            </a>
                        </li>
                        <li class="nav-item d-lg-none">
                            <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#confirmModal">
                                <i class="bi-box-arrow-right"></i> Cerrar Sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <main class="main-content flex-grow-1 p-3 p-md-4">

                <p class="lead mb-4">Gestionar usuarios en el sistema.</p>

                <h2 class="mb-4">Listado de usuarios</h2>

                <!-- Botón para agregar producto -->
                <div class="mb-3">
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#agregarUsuarioModal">
                        <i class="bi-plus-lg"></i> Agregar usuario
                    </button>
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
                
                <!-- Tabla de usuarios -->
                <div class="table-responsive">
                    <table class="table table-striped table-bordered align-middle">
                        <thead class="table-warning">
                            <tr>
                                <th>Número</th>
                                <th>Nombre completo</th>
                                <th>Email</th>
                                <th>Rol</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>

                        <tbody>
                            <!-- Cargar usuarios desde controlador -->
                            <%= controladorUsuario.getAllUsuariosTabla()%>
                        </tbody>
                    </table>
                </div>
            </main>
        </div>

        <!-- Modal para agregar usuario -->
        <div class="modal fade" id="agregarUsuarioModal" tabindex="-1" aria-labelledby="agregarUsuarioModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title" id="agregarUsuarioModalLabel">Agregar nuevo usuario</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <div class="modal-body">
                        <form action="AgregarUsuario" method="POST">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>
                            <div class="mb-3">
                                <label for="apellidoPaterno" class="form-label">Apellido paterno <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="apellidoPaterno" name="apellidoPaterno" required>
                            </div>
                            <div class="mb-3">
                                <label for="apellidoMaterno" class="form-label">Apellido materno</label>
                                <input type="text" class="form-control" id="apellidoMaterno" name="apellidoMaterno">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="stock" class="form-label">Contraseña <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" id="contrasenia" name="contrasenia" required>
                            </div>
                            <div class="mb-3">
                                <label for="stock" class="form-label">Confirmar contraseña <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" id="confirmarContra" name="confirmarContra" required>
                            </div>
                            <div class="mb-3">
                                <label for="rol" class="form-label">Rol <span class="text-danger">*</span></label>
                                <select class="form-select" id="rol" name="rol" required>
                                    <option value="ADMIN">Administrador</option>
                                    <option value="CLIENTE">Cliente</option>
                                </select>
                            </div>
                            <label class='form-label'><span class="text-danger">* Campos obligatorios</span></label>
                            <button type="submit" class="btn btn-success w-100">Guardar</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmModalLabel">¿Cerrar sesión?</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-footer">
                        <a href="index.jsp" class="btn btn-primary">Sí</a>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                    </div>

                </div>
            </div>
        </div>

        <!-- Scripts necesarios para Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"></script>
    </body>

</html>
