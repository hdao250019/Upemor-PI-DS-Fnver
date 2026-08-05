/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Angel H
 */
public class Movimiento {
    private int id;
    private int id_usuario;
    private String categoria;
    private int id_categoria;
    private double monto;
    private String fecha;
    private String descripcion;
    private String tipo_movimiento;
    
    
    public Movimiento(){
        
    }
    
     public Movimiento(int id, int id_usuario, String categoria, double monto, String fecha, String descripcion, String tipo_movimiento) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.categoria = categoria;
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo_movimiento = tipo_movimiento;
    }
   
    

    public Movimiento(int id_usuario, String categoria, double monto, String fecha, String descripcion, String tipo_movimiento) {
        this.id_usuario = id_usuario;
        this.categoria = categoria;
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo_movimiento = tipo_movimiento;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }
    
    
    
}
