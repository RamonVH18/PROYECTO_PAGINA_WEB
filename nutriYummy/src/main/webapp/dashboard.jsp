<%-- 
    Document   : dashboard
    Created on : 15 nov 2025, 10:45:05 p.m.
    Author     : rocha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Panel de administrador | Nutri Yummy</title>

    <link rel="icon" href="img/favicon.ico" type="image/x-icon">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

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
                        <a class="nav-link active" aria-current="page" href="dashboard.jsp">
                            <i class="bi-house-door-fill"></i> Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="productos.jsp">
                            <i class="bi-bag-fill"></i> Productos
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="usuarios.jsp">
                            <i class="bi-people-fill"></i> Usuarios
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="pedidos.jsp">
                            <i class="bi-receipt-cutoff"></i> Pedidos
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
            
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Administrador</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Dashboard</li>
                </ol>
            </nav>

            <p class="lead mb-4">Panel principal ADMINISTRADOR de NutriYummy. Desde aquí puedes gestionar productos, usuarios y pedidos.</p>

            <div class="row">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card border-success shadow-sm">
                        <div class="card-header bg-success text-white">Gestionar productos</div>
                        <div class="card-body">
                            <p class="card-text">Consulta, agrega, edita o elimina productos.</p>
                            <a href="productos.jsp" class="custom-btn btn btn-success">Ir a Productos</a> 
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card border-warning shadow-sm">
                        <div class="card-header bg-warning text-dark">Gestionar usuarios</div>
                        <div class="card-body">
                            <p class="card-text">Administra cuentas, roles y accesos al sistema.</p>
                            <a href="usuarios.jsp" class="custom-btn btn btn-warning text-dark">Gestionar Usuarios</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card border-primary shadow-sm">
                        <div class="card-header bg-primary text-white">Pedidos</div>
                        <div class="card-body">
                            <p class="card-text">Gestiona el historial de pedidos</p>
                            <a href="pedidos.jsp" class="custom-btn btn btn-primary">Ir a Pedidos</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card border-info shadow-sm">
                <div class="card-header bg-info text-white">Ventas Semanales 📈</div>
                <div class="card-body">
                    <canvas id="templatemo-line-chart" height="120" class="w-100"></canvas>
                </div>
            </div>

        </main>
    </div>

    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
        <div class="modal-dialog">
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
</body>

</html>