package com.example.carla.tp1carla.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Carla on 12/11/2017.
 */

public class Venta implements Serializable {
    private Integer idVenta;
    private Date fechaVenta;
    private Float montoTotal;
    private Integer cantidadV;
    private Integer id_Producto; //es idDuenio

    public Venta(){}

    public Venta(Integer idVenta, Date fechaVenta, Float montoTotal, Integer cantidadV, Integer id_Producto) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.cantidadV = cantidadV;
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

    public Float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadV() {
        return cantidadV;
    }

    public void setCantidadV(Integer cantidadV) {
        this.cantidadV = cantidadV;
    }

    public Integer getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(Integer id_Producto) {
        this.id_Producto = id_Producto;
    }
}