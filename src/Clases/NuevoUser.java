package Clases;
/**
 *
 * @author rousc
 */
public class NuevoUser {
    public String nombre, correo, contrasenia;
    public int edad;

//crear constructir de la clase
    public NuevoUser(String nombre, String correo, int edad, String contrasenia) {
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
}
    

