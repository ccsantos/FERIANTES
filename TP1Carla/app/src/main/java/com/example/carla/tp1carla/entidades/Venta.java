package com.example.carla.tp1carla.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Carla on 12/11/2017.
 */

public class Venta implements Serializable {
    private Integer idVenta;
    private Date fechaVenta;
    private Double montoTotal;
    private Integer cantidad;
    private Integer id_Producto; //es idDuenio

    public Venta(){}

    public Venta(Integer idVenta, Date fechaVenta, Double montoTotal, Integer cantidad, Integer id_Producto) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.cantidad = cantidad;
        this.id_Producto = id_Producto;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(Integer id_Producto) {
        this.id_Producto = id_Producto;
    }
}