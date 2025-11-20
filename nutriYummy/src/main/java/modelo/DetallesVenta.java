/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase que representa un detalle individual dentro de una venta.
 *
 * Cada detalle contiene la información de un producto vendido, la cantidad
 * adquirida, el precio unitario, el IVA aplicado (almacenado como monto en
 * dinero) y el total correspondiente a ese renglón.
 *
 * @author rocha
 */
public class DetallesVenta {

    /**
     * Identificador único del detalle
     */
    private int id;

    /**
     * Cantidad de unidades del producto vendidas en este detalle
     */
    private int cantidad;

    /**
     * IVA aplicado expresado en dinero (no en porcentaje)
     */
    private double iva;

    /**
     * Precio unitario del producto
     */
    private double precio;

    /**
     * Total del detalle (cantidad * precio + IVA)
     */
    private double total;

    /**
     * ID del producto asociado a este detalle
     */
    private int idProducto;

    /**
     * Constructor por defecto
     */
    public DetallesVenta() {
    }

    /**
     * Constructor completo para inicializar todos los campos del detalle.
     *
     * @param id Identificador del detalle
     * @param cantidad Cantidad de productos vendidos
     * @param iva IVA aplicado en dinero
     * @param precio Precio unitario del producto
     * @param total Total del detalle
     * @param idProducto ID del producto asociado
     * @param idVenta (NO se usa — parámetro sobrante)
     */
    public DetallesVenta(int id, int cantidad, double iva, double precio, double total, int idProducto, int idVenta) {
        this.id = id;
        this.cantidad = cantidad;
        this.iva = iva;
        this.precio = precio;
        this.total = total;
        this.idProducto = idProducto;
    }

    public DetallesVenta(int cantidad, double iva, double precio, int idProducto) {
        this.cantidad = cantidad;
        this.iva = iva;
        this.precio = precio;
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el identificador del detalle de venta.
     *
     * @return id del detalle.
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el identificador del detalle.
     *
     * @param id nuevo identificador del detalle.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la cantidad del producto vendida.
     *
     * @return cantidad de unidades.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad vendida para este detalle.
     *
     * @param cantidad nueva cantidad.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el IVA en dinero asociado al detalle.
     *
     * @return monto del IVA.
     */
    public double getIva() {
        return iva;
    }

    /**
     * Asigna el monto de IVA en dinero.
     *
     * @param iva monto del IVA.
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Obtiene el precio unitario del producto.
     *
     * @return precio unitario.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio unitario del producto.
     *
     * @param precio nuevo precio unitario.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el total correspondiente a este detalle.
     *
     * @return total.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Asigna el total del detalle.
     *
     * @param total nuevo total.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene el identificador del producto asociado.
     *
     * @return idProducto.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Asigna el identificador del producto del detalle.
     *
     * @param idProducto id del producto.
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Devuelve una representación en texto del detalle de venta.
     *
     * @return cadena con los datos del detalle.
     */
    @Override
    public String toString() {
        return "DetallesVenta{" + "id=" + id + ", cantidad=" + cantidad + ", iva=" + iva + ", precio=" + precio + ", total=" + total + ", idProducto=" + idProducto + '}';
    }

}
