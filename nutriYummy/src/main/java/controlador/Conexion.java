/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rocha
 */
public class Conexion {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "itson";
    private static final String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/nutriYummy?zeroDateTimeBehavior=CONVERT_TO_NULL";

    /**
     * Permite cargar el driver solo una vez.
     */
    static {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e) {
            System.err.println("Error en: " + e);
        }
    }
    
    /**
     * Obtiene una nueva conexión a la base de datos.
     * 
     * @return nueva Connection o null si hubo error.
     */
    public static Connection getConexion() {
        Connection con =  null;
        
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        
        return con;
    }
}
