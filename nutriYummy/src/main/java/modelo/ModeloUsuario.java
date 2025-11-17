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
import static java.sql.Types.INTEGER;
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
                        rol,
                        rs.getString("contrasenia")
                );

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        
        return usuarios;
    }
    
    public Usuario getUsuario(int id) {
        String sql = "call getUsuario(?)";
        Usuario usuario = null;

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {

                    RolUsuario rol = null;
                    try {
                        rol = RolUsuario.valueOf(rs.getString("rol").toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        System.err.println("Rol no válido: " + rs.getString("rol"));
                    }

                    usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getInt("numero"),
                            rs.getString("nombre"),
                            rs.getString("apellidoPaterno"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("email"),
                            rol,
                            rs.getString("contrasenia")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        }

        return usuario;
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

            // Ejecutar
            int filasAfectadas = pst.executeUpdate();

            return filasAfectadas > 0; // true si se actualizó al menos una fila
        } catch (SQLException e) {
            System.err.println("Error al editar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public int insertarUsuario(Usuario usuario) {
        String sql = "call insertarUsuario(?, ?, ?, ?, ? ,?, ?)";
        int numeroGenerado = 0;

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            // Setear parámetros
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellidoPaterno());
            pst.setString(3, usuario.getApellidoMaterno());
            pst.setString(4, usuario.getEmail());
            pst.setString(5, usuario.getRol().name());
            pst.setString(6, usuario.getContrasenia());
            
            pst.registerOutParameter(7, INTEGER);

            pst.execute();
            
            numeroGenerado = pst.getInt(7);
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
        
        return numeroGenerado;
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
    
    public boolean existeEmailUsuario(String email) {
        String sql = "select existeEmailUsuario(?) as existe";
        boolean existe = false;

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            pst.setString(1, email);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                existe = rs.getInt("existe") == 1;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar email: " + e.getMessage());
            return false;
        }
        
        return existe;
    }
    
    public boolean existeUsuarioNumero(int numero) {
        String sql = "select existeUsuarioNumero(?) as existe";
        boolean existe = false;

        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            pst.setInt(1, numero);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                existe = rs.getInt("existe") == 1;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar número: " + e.getMessage());
            return false;
        }
        
        return existe;
    }
    
    public boolean hayMasAdmins() {
        String sql = "select hayMasAdmins() as total";
        boolean resultado = false;
        
        try (Connection conn = getConexion(); CallableStatement pst = conn.prepareCall(sql);) {
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                resultado = rs.getInt("total") == 1;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si hay más administradores en el sistema: " + e.getMessage());
            return false;
        }
        
        return resultado;
    }
}
