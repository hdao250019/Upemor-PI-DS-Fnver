package Models;


/**
 *
 * @author rousc
 */
public class Usuario {
    public String nombre, correo, contrasenia;
    public int edad;

//crear constructir de la clase
    public Usuario(String nombre, String correo, int edad, String contrasenia) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.edad = edad;
    }
 
    // metodo para mostrar información
    public String mostrarDatos(){
        return"Nombre: "  + nombre + 
               "\nCorreo: " + correo +
               "\nEdad: " + edad +
                "\nContraseña: " + contrasenia;
               
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

  
    
}

    

