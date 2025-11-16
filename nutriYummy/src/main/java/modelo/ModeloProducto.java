/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rocha
 */
public class ModeloProducto extends Conexion {

    public ArrayList<Producto> getAllProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            String sql = "call selectProductos()";
            
            pst = getConexion().prepareCall(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("img_producto"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                
                productos.add(producto);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                
                if (pst != null) {
                    pst.close();
                }
                
                if (getConexion() != null) {
                    getConexion().close();
                }
            } catch (Exception e) {
            }
        }
        
        return productos;
    }

    public Producto getProducto(int id) {
        Producto producto = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            String sql = "call selectProducto(?)";
            
            pst = getConexion().prepareCall(sql);
            pst.setInt(1, id);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("img_producto"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                
                if (pst != null) {
                    pst.close();
                }
                
                if (getConexion() != null) {
                    getConexion().close();
                }
            } catch (Exception e) {
            }
        }
        
        return producto;
    }
}
