package Models;
import Conection.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jared
 */
public class UsuarioBD {
    // Metodo para registrar en la base de datos
    public boolean insertar(Usuario usuarios){
        // CREAR SENTENCIA SQL
        String sql_query = "INSERT INTO usuarios(nombre, correo, edad, contraseña) VALUES(?, ?, ?, ?)";
        
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            // Enviar los datos del modelo
            stmt.setString(1, usuarios.getNombre());
            stmt.setString(2, usuarios.getCorreo());
            stmt.setInt(3, usuarios.getEdad());
            stmt.setString(4, usuarios.getContrasenia());
            
            //Ejecutar el query en la DB
            stmt.executeUpdate();
            
            // Cerrar Statement y la conexion a la DB
            stmt.close();
            conn.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Error al insertar: "+e.getMessage());
            return false;
        }
    }
    
    
    //Metodo para consultar todos los usuarios
    public List<Usuario> consultarUsuarios(){
        List<Usuario> listaUsuario = new ArrayList<>();
        String query_sql = "SELECT * FROM usuarios";
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            ResultSet result = stmt.executeQuery();
            
            // Ciclo para obtener todos los resgistros
            while(result.next()){
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                int edad = result.getInt("edad");
                String correo = result.getString("correo");
                String contrasenia = result.getString("contraseña");
                
                // crear objeto usuario y guardarlos en la lista
                Usuario usuario = new Usuario(id, edad, nombre, correo, contrasenia);
                listaUsuario.add(usuario);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaUsuario;
    }
}

