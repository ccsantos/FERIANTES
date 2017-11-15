package com.example.carla.tp1carla.utilidades;

/**
 * Created by Carla on 12/11/2017.
 */
//ACA DEFINO TODOS STRINGS DE LOS CAMPOS DE LA BASE DE DATOS Y TABLA
public class Utilidades {
    //Constantes campos tabla producto
    public static final String TABLA_PRODUCTO = "t_producto";
    public static final String CAMPO_IDPRODUCTO = "idP";
    public static final String CAMPO_NOMBREP = "nombreP";
    public static final String CAMPO_CODIGO = "codigo";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_CANTIDAD_P = "cantidad";
    public static final String CAMPO_IMAGEN = "imagenP";

    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE " +
            "" + TABLA_PRODUCTO + "( " + CAMPO_IDPRODUCTO + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAMPO_NOMBREP + " TEXT NOT NULL," + CAMPO_CODIGO + " INTEGER UNIQUE NOT NULL,"
            + CAMPO_DESCRIPCION + " TEXT," + CAMPO_PRECIO + " REAL NOT NULL," + CAMPO_CANTIDAD_P + " INTEGER NOT NULL," + CAMPO_IMAGEN + " BLOB)";

    //Constantes campos tabla venta
    public static final String TABLA_VENTA = "venta";
    public static final String CAMPO_IDVENTA = "idV";
    public static final String CAMPO_FECHAVENTA = "fechaVenta";
    public static final String CAMPO_MONTO = "montoTotal";
    public static final String CAMPO_CANTIDAD_V = "cantidad";
    public static final String CAMPO_ID_PRODUCTO = "id_producto";

    public static final String CREAR_TABLA_VENTA = "CREATE TABLE " +
            "" + TABLA_VENTA + " (" + CAMPO_IDVENTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_FECHAVENTA + " DATETIME NOT NULL, " + CAMPO_MONTO + " REAL NOT NULL," + CAMPO_CANTIDAD_V + " INTEGER NOT NULL," + CAMPO_ID_PRODUCTO + " INTEGER NOT NULL)";
}