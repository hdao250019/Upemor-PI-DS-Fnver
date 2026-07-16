package Models;

/**
 *
 * @author Jared
 */
public class Categoria {
    private int id;
    private String categoria;
    private String descripcion;
    private int id_usuario;
    
    // Contructor para la clase vacio
    public Categoria() {
    }

    // Contructor para la clase
    public Categoria(String categoria, String descripcion, int id_usuario) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
    }

    // Contructor para la clase (BD)
    public Categoria(int id, String categoria, String descripcion, int id_usuario) {
        this.id = id;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
    }

    public Categoria(int id, String categoria, String descripcion) {
        this.id = id;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Categoria(String categoria, String descripcion) {
        this.categoria = categoria;
        this.descripcion = descripcion;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
    
}
