package Models;

/**
 *
 * @author Jared
 */
public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
    private int id_usuario;
    
    // Contructor para la clase vacio
    public Categoria() {
    }

    public Categoria(int id, String nombre, String descripcion, int id_usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
    }

    public Categoria(String nombre, String descripcion, int id_usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
    }

    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
    
   
}
