/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import static controlador.Conexion.getConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.enums.RolUsuario;

/**
 *
 * @author rocha
 */
public class ModeloUsuario extends Conexion {
    
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        
        String sql = "call getAllUsuarios()";
        
        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql); ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {

                RolUsuario rol = null;
                try {
                    rol = RolUsuario.valueOf(rs.getString("rol").toUpperCase());
                } catch (IllegalArgumentException ex) {
                    System.err.println("Rol no válido: " + rs.getString("rol"));
                }

                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getString("email"),
                        rol
                );

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        
        return usuarios;
    }
    
    public boolean editarUsuario(Usuario usuario) {
        String sql = "call editarUsuario(?, ?, ?, ?, ? ,?, ?, ?)";

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setInt(1, usuario.getId());
            pst.setInt(2, usuario.getNumero());
            pst.setString(3, usuario.getNombre());
            pst.setString(4, usuario.getApellidoPaterno());
            pst.setString(5, usuario.getApellidoMaterno());
            pst.setString(6, usuario.getEmail());
            pst.setString(7, usuario.getRol().name());
            pst.setString(8, usuario.getContrasenia());

            // Ejecutar UPDATE
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0; // true si se actualizó al menos una fila
        } catch (SQLException e) {
            System.err.println("Error al editar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "call insertarUsuario(?, ?, ?, ?, ? ,?, ?)";

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setInt(1, usuario.getNumero());
            pst.setString(2, usuario.getNombre());
            pst.setString(3, usuario.getApellidoPaterno());
            pst.setString(4, usuario.getApellidoMaterno());
            pst.setString(5, usuario.getEmail());
            pst.setString(6, usuario.getRol().name());
            pst.setString(7, usuario.getContrasenia());

            pst.execute();
            
            int filasAfectadas = pst.getUpdateCount();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarUsuario(int id) {
        String sql = "call eliminarUsuario(?)";
        
        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setInt(1, id);

            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
