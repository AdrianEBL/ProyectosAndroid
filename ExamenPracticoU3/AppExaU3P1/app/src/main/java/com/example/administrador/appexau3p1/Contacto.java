package com.example.administrador.appexau3p1;

import java.io.Serializable;

/**
 * Created by administrador on 06/11/17.
 */

public class Contacto implements Serializable {
    String cantidad, nombre, proveedor, fecha, descripcion;

    public Contacto(String Cantidad, String Nombre, String Proveedor, String Fecha, String Descripcion){
        this.cantidad = Cantidad;
        this.nombre = Nombre;
        this.proveedor = Proveedor;
        this.fecha = Fecha;
        this.descripcion = Descripcion;
    }
}
