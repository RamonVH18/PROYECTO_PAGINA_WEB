<%-- 
    Document   : cambiarContrasenia
    Created on : 20 nov 2025, 1:16:45 p.m.
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
<html>
    <!-- Modal para cambiar contraseña -->
        <div class="modal fade" id="cambiarContraModal" tabindex="-1" aria-labelledby="cambiarContraModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h5 class="modal-title" id="cambiarContraModalLabel">Cambiar contraseña</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <div class="modal-body">
                        <form action="CambiarContra" method="POST">
                            <input type="hidden" name="email" value="<%= usuario.getEmail()%>">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Contraseña <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" id="contrasenia" name="contrasenia" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Nueva contraseña <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" id="nuevaContra" name="nuevaContra" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Confirmar nueva contraseña <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" id="confirmarNuevaContra" name="confirmarNuevaContra" required>
                            </div>
                            <label class='form-label'><span class="text-danger">* Campos obligatorios</span></label>
                            <button type="submit" class="btn btn-success w-100">Guardar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
</html>
