/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Angel H
 */
public class CategoriaPresupuesto {
    
    private int id_presup;
    private int id_categ;
    private Double monto_lim;
    private Double monto_gasto;
    private String nombrecat;

    public CategoriaPresupuesto(int id_presup, int id_categ, Double monto_lim, Double monto_gasto) {
        this.id_presup = id_presup;
        this.id_categ = id_categ;
        this.monto_lim = monto_lim;
        this.monto_gasto = monto_gasto;
    }

    public CategoriaPresupuesto(int id_presup, int id_categ, Double monto_lim, Double monto_gasto, String nombrecat) {
        this.id_presup = id_presup;
        this.id_categ = id_categ;
        this.monto_lim = monto_lim;
        this.monto_gasto = monto_gasto;
        this.nombrecat = nombrecat;
    }

    public String getNombrecat() {
        return nombrecat;
    }

    public void setNombrecat(String nombrecat) {
        this.nombrecat = nombrecat;
    }
    
    
    
    

    public int getId_presup() {
        return id_presup;
    }

    public void setId_presup(int id_presup) {
        this.id_presup = id_presup;
    }

    public int getId_categ() {
        return id_categ;
    }

    public void setId_categ(int id_categ) {
        this.id_categ = id_categ;
    }

    public Double getMonto_lim() {
        return monto_lim;
    }

    public void setMonto_lim(Double monto_lim) {
        this.monto_lim = monto_lim;
    }

    public Double getMonto_gasto() {
        return monto_gasto;
    }

    public void setMonto_gasto(Double monto_gasto) {
        this.monto_gasto = monto_gasto;
    }
    
    
    
    
}
