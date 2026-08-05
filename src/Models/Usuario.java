package Models;
/**
 *
 * @author rousc
 */
public class Usuario {
    private int id;
    private String usuario;
    private String correo;
    private String contrasenia;
    
    public Usuario() {
    }

//crear constructir de la clase
    public Usuario(String usuario, String correo, String contrasenia) {
        this.usuario = usuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
    
    //crear constructir de la clase a la BD

    public Usuario(int id, String usuario, String correo, String contrasenia) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String nombre) {
        this.usuario = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
   
}
    