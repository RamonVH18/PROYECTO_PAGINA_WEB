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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light"> <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10">
                
                <div class="card shadow-lg border-0 rounded-lg">
                    
                    <div class="card-header bg-primary text-white text-center py-4">
                        <h3 class="mb-0">Mi Perfil de Usuario</h3>
                    </div>

                    <div class="card-body p-5">
                        
                        <div class="text-center mb-5">
                            <img src="img/usuario.png" alt="Foto Perfil" class="rounded-circle img-thumbnail mb-3" width="150">
                            
                            <h2 class="fw-bold text-dark">
                                <%= usuario.getNombre() %> <%= usuario.getApellidoPaterno() %>
                            </h2>
                        </div>

                        <form>
                            <div class="row g-3"> <div class="col-md-6">
                                    <label class="form-label text-muted small">Nombre</label>
                                    <input type="text" class="form-control bg-white" value="<%= usuario.getNombre() %>" readonly>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label text-muted small">Apellidos</label>
                                    <input type="text" class="form-control bg-white" value="<%= usuario.getApellidoPaterno() %> <%= usuario.getApellidoMaterno() %>" readonly>
                                </div>

                                <div class="col-12">
                                    <label class="form-label text-muted small">Correo Electrónico</label>
                                    <input type="text" class="form-control bg-white" value="<%= usuario.getEmail() %>" readonly>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>