/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author rocha
 */
public class Venta {
    private int id;
    private String folio;
    private LocalDateTime fechaHora;
    private List<DetallesVenta> detalles;
    private double total;
    private int idUsuario;

    public Venta() {
    }

    public Venta(int id, String folio, LocalDateTime fechaHora, List<DetallesVenta> detalles, double total, int idUsuario) {
        this.id = id;
        this.folio = folio;
        this.fechaHora = fechaHora;
        this.detalles = detalles;
        this.total = total;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public List<DetallesVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesVenta> detalles) {
        this.detalles = detalles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", folio=" + folio + ", fechaHora=" + fechaHora + ", detalles=" + detalles + ", total=" + total + '}';
    }
    
}
