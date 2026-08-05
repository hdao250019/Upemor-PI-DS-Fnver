/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Angel H
 */
public class Presupuesto {
    private int id_presupuesto;
    private int id_usuario;
    private String fecha_inicio;
    private String fecha_fin;
    private double monto_limite_periodo;
    private boolean estado;
    
    public Presupuesto(){
        
    }

    public Presupuesto(int id_presupuesto, int id_usuario, String fecha_inicio, String fecha_fin, double monto_limite_periodo, boolean estado) {
        this.id_presupuesto = id_presupuesto;
        this.id_usuario = id_usuario;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.monto_limite_periodo = monto_limite_periodo;
        this.estado = estado;
    }

    public Presupuesto(String fecha_inicio, String fecha_fin, double monto_limite_periodo) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.monto_limite_periodo = monto_limite_periodo;
    }
    
    

    public int getId_presupuesto() {
        return id_presupuesto;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public double getMonto_limite_periodo() {
        return monto_limite_periodo;
    }

    public void setMonto_limite_periodo(double monto_limite_periodo) {
        this.monto_limite_periodo = monto_limite_periodo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
