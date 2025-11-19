/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author rocha
 */
public class DetallesVenta {
    private int id;
    private int cantidad;
    private double iva;
    private double precio;
    private double total;
    private int idProducto;
    
    public DetallesVenta() {
    }

    public DetallesVenta(int id, int cantidad, double iva, double precio, double total, int idProducto, int idVenta) {
        this.id = id;
        this.cantidad = cantidad;
        this.iva = iva;
        this.precio = precio;
        this.total = total;
        this.idProducto = idProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "DetallesVenta{" + "id=" + id + ", cantidad=" + cantidad + ", iva=" + iva + ", precio=" + precio + ", total=" + total + ", idProducto=" + idProducto + '}';
    }

}
