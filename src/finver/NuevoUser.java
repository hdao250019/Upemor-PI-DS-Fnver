package finver;
/**
 *
 * @author rousc
 */
public class NuevoUser {
    String nombre, correo, contrasenia;
int edad;

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
    

