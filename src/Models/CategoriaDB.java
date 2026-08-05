/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


import Conection.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDB {
    
    public boolean insertar(Categoria cat){
        String sql_query = "INSERT INTO categoria(id_usuario, nombre_categoria, descripcion) VALUES(?, ?, ?)";
        
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            // Enviar los datos del modelo
            stmt.setInt(1, cat.getId_usuario());
            stmt.setString(2, cat.getNombre());
            stmt.setString(3, cat.getDescripcion());
            
            //Ejecutar el query en la DB
            stmt.executeUpdate();
            
            // Cerrar Statement y la conexion a la DB
            stmt.close();
            conn.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Error al insertar categoria: "+ e.getMessage());
            return false;
        }
    }

    public List<Categoria> obtenerCategorias(int id_usr){
        List<Categoria> listaCateg = new ArrayList<>();
        String query_sql = "SELECT id_categoria, nombre_categoria, descripcion FROM categoria WHERE id_usuario = " + id_usr;
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            ResultSet result = stmt.executeQuery();
            
            // Ciclo para obtener todos los resgistros
            while(result.next()){
                int id = result.getInt("id_categoria");
                String nombre = result.getString("nombre_categoria");
                String desc = result.getString("descripcion");
                
                Categoria categ = new Categoria(id, nombre, desc);
                listaCateg.add(categ);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaCateg;
    }
    
     public boolean actualizarCat(Categoria categoria){
        
        String query_sql = "UPDATE categoria SET nombre_categoria = ?, descripcion = ? WHERE id_categoria = ? AND id_usuario = ?";
        
        try{
             //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            // Enviar los datos del modelo
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getId());
            stmt.setInt(4, categoria.getId_usuario());
              // Verificar el numero de filas que cambiaron 
            int filas_cambiadas = stmt.executeUpdate();
            stmt.close();
            conn.close();
              
              // Cuando es booean no es necesario aplicar un if
            return filas_cambiadas > 0;

            
              
        }catch(SQLException e){
            System.out.println("ERROR AL ACTUALIZAR EN LA BD" + e.getMessage());
            return false;
            
        }
    }
     
         // METODO PARA ELIMINAR CATEGORIAS
    public boolean eliminarCat(int idCat){
            
        String query_sql = "DELETE FROM categoria WHERE id_categoria = ?";
            
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
