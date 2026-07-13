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
    
    // metodo para validar el login
    public Usuario login(String usuario, String contrasenia) {
        // Buscamos un registro que coincida con ambos datos
        String query_sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
    
    try {
        Connection conn = ConexionDB.conexion();
        PreparedStatement stmt = conn.prepareStatement(query_sql);
        
        stmt.setString(1, usuario);
        stmt.setString(2, contrasenia);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Usuario usuarioEncontrado = new Usuario(
                rs.getInt("id"),
                rs.getInt("edad"),
                rs.getString("usuario"),
                rs.getString("correo"),
                rs.getString("contraseña")
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
        String sql_query = "INSERT INTO usuarios(usuario, correo, edad, contraseña) VALUES(?, ?, ?, ?)";
        
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            // Enviar los datos del modelo
            stmt.setString(1, usuarios.getUsuario());
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
                String nombre = result.getString("usuario");
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
    
    //Metodo para registrar categorias en la BD
    public boolean categorias(Categoria categoria){
         // CREAR SENTENCIA SQL
        String sql_query = "INSERT INTO categoria(categoria, descripcion, total_ing) VALUES(?, ?, ?)";
        
        try{
             //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            // Enviar los datos del modelo
            stmt.setString(1, categoria.getCategoria());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setDouble(3, categoria.getTotal());
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
    public List<Categoria> consultarCategorias(){
        List<Categoria> listaCat = new ArrayList<>();
        String query_sql = "SELECT * FROM categoria";
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            ResultSet result = stmt.executeQuery();
            
            // Ciclo para obtener todos los resgistros
            while(result.next()){
                int id = result.getInt("id_cat");
                String categoria = result.getString("categoria");
                String desc = result.getString("descripcion");
                Double total = result.getDouble("total_ing");
                
                // crear objeto usuario y guardarlos en la lista
                Categoria cat = new Categoria(id, categoria, desc, total);
                listaCat.add(cat);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaCat;
    }
    
    // METODO PARA ACTUALIZAR USUARIOS
    public boolean actualizar(Usuario usuario){
        
        String query_sql = "UPDATE usuarios SET usuario = ?, edad = ?, correo = ?, contraseña = ? WHERE id = ?";
        
        try{
             //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            // Enviar los datos del modelo
            stmt.setString(1, usuario.getUsuario());
            stmt.setInt(2, usuario.getEdad());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasenia());
            stmt.setInt(5, usuario.getId());
              
              // Verificar el numero de filas que cambiaron 
              int filas_cambiadas = stmt.executeUpdate();
              
              // Cuando es booean no es necesario aplicar un if
              return filas_cambiadas > 0;
              
            
            
        }catch(SQLException e){
            System.out.println("ERROR AL ACTUALIZAR EN LA BD" + e.getMessage());
            return false;
            
        }
    }
        
    // METODO PARA ELIMINAR USUARIOS
    public boolean eliminar(int idUsuario){
            
        String query_sql = "DELETE FROM usuarios WHERE id = ?";
            
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql); 
                
            stmt.setInt(1, idUsuario);
            
            // Valor de las filas afectadas
            int filas_cambiadas = stmt.executeUpdate();
            return filas_cambiadas > 0; 
           
        }catch(SQLException e){
            System.out.println("ERROR AL BORRAR USUARIO EN LA BD: " + e.getMessage());
            return false;
        }
            
        }
    
    // METODO PARA ACTUALIZAR CATEGORIAS
    public boolean actualizarCat(Categoria categoria){
        
        String query_sql = "UPDATE categoria SET categoria = ?, descripcion = ?, total_ing = ? WHERE id_cat = ?";
        
        try{
             //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            // Enviar los datos del modelo
            stmt.setString(1, categoria.getCategoria());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setDouble(3, categoria.getTotal());
            stmt.setInt(4, categoria.getId());
              
              // Verificar el numero de filas que cambiaron 
              int filas_cambiadas = stmt.executeUpdate();
              
              // Cuando es booean no es necesario aplicar un if
              return filas_cambiadas > 0;
              
            
            
        }catch(SQLException e){
            System.out.println("ERROR AL ACTUALIZAR EN LA BD" + e.getMessage());
            return false;
            
        }
    }
        
    // METODO PARA ELIMINAR CATEGORIAS
    public boolean eliminarCat(int idCat){
            
        String query_sql = "DELETE FROM categoria WHERE id_cat = ?";
            
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql); 
                
            stmt.setInt(1, idCat);
            
            // Valor de las filas afectadas
            int filas_cambiadas = stmt.executeUpdate();
            return filas_cambiadas > 0; 
           
        }catch(SQLException e){
            System.out.println("ERROR AL BORRAR USUARIO EN LA BD: " + e.getMessage());
            return false;
        }
            
        }
}

