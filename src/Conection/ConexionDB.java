package Conection;
import java.sql.*;
/**
 *
 * @author Jared
 */
public class ConexionDB {
    // Manejo dd las constantes de datos de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/finver";
    private static final String USER = "root";
    private static final String PASS = "";
    
    //Metodo para realizar la conexion
    public static Connection conexion(){
        Connection conn = null;
        // Manejar el error en la conexion con la base de datos
        try{
            
            //Guardar conexion en el objeto connection
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa");
            
        }catch(SQLException e){
            //Manejar el error de SQL
            System.out.print("Error en la conexion: "+e.getMessage());
        }
        
        return conn;
    }
    
}
