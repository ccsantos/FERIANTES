package com.example.carla.tp1carla.entidades;

/**
 * Created by Carla on 12/11/2017.
 */
import java.io.Serializable;

public class Producto implements Serializable {
    private Integer idP;
    private String nombreP;
    private Integer codigo;
    private String descripcion;
    private byte[] imagenP;
    private Double precio;
    private Integer cantidad;


    public Producto() {
    }

    public Producto(Integer idP, String nombreP, Integer codigo, String descripcion, byte[] imagenP,Double precio, Integer cantidad) {
        this.idP = idP;
        this.nombreP = nombreP;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.imagenP = imagenP;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Integer getIdP() {
        return idP;
    }

    public void setIdP(Integer idP) {
        this.idP = idP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagenP() {
        return imagenP;
    }

    public void setImagenP(byte[] imagenP) {
        this.imagenP = imagenP;
    }
}