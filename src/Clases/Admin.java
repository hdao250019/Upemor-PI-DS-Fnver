package Clases;

/**
 *
 * @author rousc
 */
public class Admin {
    private String usuario,pass;
    
    // crear constructor
    public Admin(String usuario, String pass){
        //Inicializar los Atributos de los objetos
        this.usuario = usuario;
        this.pass = pass;
        
    }
    
    public String ObtenerNombre(){
        return usuario;
    }
    public String ObtenerPass(){
        return pass;
    }
    
    //validar el inicio de sesion
    public boolean validarLogin(String usuarioLogin, String passLogin){
        return usuario.equals(usuarioLogin) && pass.equals(passLogin);
    }
    
    
}

