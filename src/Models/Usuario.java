package Models;
/**
 *
 * @author rousc
 */
public class Usuario {
    private int id;
    private int edad;
    private String nombre;
    private String correo;
    private String contrasenia;
    
    public Usuario() {
    }

//crear constructir de la clase
    public Usuario(int edad, String nombre, String correo, String contrasenia) {
        this.edad = edad;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
    
    //crear constructir de la clase a la BD

    public Usuario(int id, int edad, String nombre, String correo, String contrasenia) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    