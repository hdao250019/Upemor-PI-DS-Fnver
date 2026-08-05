package Models;
import Conection.ConexionDB;
import java.sql.*;
/**
 *
 * @author Jared
 */
public class UsuarioBD {
    
    // metodo para validar el login
    public Usuario login(String usuario, String contrasenia) {
        // Buscamos un registro que coincida con ambos datos
        String query_sql = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?";
    
    try {
        Connection conn = ConexionDB.conexion();
        PreparedStatement stmt = conn.prepareStatement(query_sql);
        
        stmt.setString(1, usuario);
        stmt.setString(2, contrasenia);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Usuario usuarioEncontrado = new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasena")
            );
            return usuarioEncontrado; 
        }
        
    } catch (SQLException e) {
        System.out.println("ERROR EN LOGIN BD: " + e.getMessage());
    }
    
    return null;
}
    
    // Metodo para registrar en la base de datos
    public boolean insertar(Usuario usuarios){
        String sql_query = "INSERT INTO usuarios(usuario, correo, contrasena) VALUES(?, ?, ?)";
        
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            // Enviar los datos del modelo
            stmt.setString(1, usuarios.getUsuario());
            stmt.setString(2, usuarios.getCorreo());
            stmt.setString(3, usuarios.getContrasenia());
            
            //Ejecutar el query en la DB
            stmt.executeUpdate();
            
            // Cerrar Statement y la conexion a la DB
            stmt.close();
            conn.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Error al insertar: "+ e.getMessage());
            return false;
        }
    }
   
}

