/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Jared
 */
public class Categoria {
    private int id;
    private String categoria;
    private String descripcion;
    private double total;
    
    // Contructor para la clase vacio
    public Categoria() {
    }

    // Contructor para la clase
    public Categoria(String categoria, String descripcion, double total) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
    }

    // Contructor para la clase (BD)
    public Categoria(int id, String categoria, String descripcion, double total) {
        this.id = id;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
    }
    
    // getter and setter para la clase

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
