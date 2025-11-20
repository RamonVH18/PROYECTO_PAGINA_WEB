/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa una venta realizada en el sistema. 
 * 
 * Contiene información como el folio, fecha y hora, total de la venta, 
 * usuario que la registró, y la lista de detalles que componen dicha venta.
 * 
 * @author rocha
 */
public class Venta {
   /**
     * Identificador único de la venta.
     */
    private int id;

    /**
     * Folio generado para la venta.
     */
    private String folio;

    /**
     * Fecha y hora en que la venta fue realizada.
     * Se almacena como LocalDateTime para preservar precisión completa.
     */
    private LocalDateTime fechaHora;

    /**
     * Lista de detalles que componen la venta.
     */
    private List<DetallesVenta> detalles;

    /**
     * Total general de la venta. Es la suma de los totales de los detalles.
     */
    private double total;

    /**
     * Identificador del usuario asociado a la venta.
     */
    private int idUsuario;

    /**
     * Constructor por defecto.
     */
    public Venta() {
    }

    /**
     * Constructor completo para inicializar una venta con todos sus valores.
     *
     * @param id Identificador único de la venta.
     * @param folio Folio de la venta.
     * @param fechaHora Fecha y hora de la operación.
     * @param detalles Lista de detalles de la venta.
     * @param total Total general de la venta.
     * @param idUsuario Usuario que realizó la venta.
     */
    public Venta(int id, String folio, LocalDateTime fechaHora, List<DetallesVenta> detalles, double total, int idUsuario) {
        this.id = id;
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.detalles = detalles;
        this.total = total;
        this.idUsuario = idUsuario;
    }

    public Venta(String folio, int idUsuario) {
        this.folio = folio;
        this.idUsuario = idUsuario;
    }

     /**
     * Obtiene el identificador de la venta.
     *
     * @return id de la venta.
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el identificador de la venta.
     *
     * @param id nuevo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el folio de la venta.
     *
     * @return folio.
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Asigna el folio de la venta.
     *
     * @param folio nuevo folio.
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * Obtiene la fecha y hora de la venta.
     *
     * @return fechaHora de tipo LocalDateTime.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y la hora en que se realizó la venta.
     *
     * @param fechaHora fecha y hora a asignar.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene la lista de detalles de la venta.
     *
     * @return lista de DetallesVenta.
     */
    public List<DetallesVenta> getDetalles() {
        return detalles;
    }

    /**
     * Asigna una lista de detalles a la venta.
     *
     * @param detalles lista nueva de detalles de venta.
     */
    public void setDetalles(List<DetallesVenta> detalles) {
        this.detalles = detalles;
    }

    /**
     * Obtiene el total de la venta.
     *
     * @return total general.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Asigna el total general de la venta.
     *
     * @param total nuevo total.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene el id del usuario asociado a la venta.
     *
     * @return id del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el id del usuario asociado a la venta.
     *
     * @param idUsuario nuevo id del usuario.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Devuelve una representación en texto de la venta.
     *
     * @return cadena con los datos de la venta.
     */
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", folio=" + folio + ", fechaHora=" + fechaHora + ", detalles=" + detalles + ", total=" + total + '}';
    }
    
}
