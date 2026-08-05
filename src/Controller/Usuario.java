/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Angel H
 */
public class Usuario {
    private int id_usuario;
    private String nombre;
    private String mail;
    private String passwrd;
    
    public Usuario(){}

    public Usuario(int id_usuario, String nombre, String mail, String passwrd) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.mail = mail;
        this.passwrd = passwrd;
    }
    
    
}
