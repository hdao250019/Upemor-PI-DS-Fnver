/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finver;

/**
 *
 * @author rousc
 */
public class Usuario {
    String usuario,pass;
    
    // crear constructor
    public Usuario(String usuario, String pass){
        //Inicializar los Atributos de los objetos
        this.usuario = usuario;
        this.pass = pass;
        
    }
    
    //validar el inicio de sesion
    boolean validarLogin(String usuarioLogin, String passLogin){
        return usuario.equals(usuarioLogin) && pass.equals(passLogin);
    }
}

