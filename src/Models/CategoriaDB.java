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

    public List<Categoria> consultarCategoria(){
        List<Categoria> listaCateg = new ArrayList<>();
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
                
                Categoria categ = new Categoria();
                listaCateg.add(categ);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaCateg;
    }
}
