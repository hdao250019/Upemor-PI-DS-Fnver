/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


import Conection.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaPresupuestoDB {
    
    public List<CategoriaPresupuesto> obtenerCategorias(int id_usr){
        
        int id_repo = 1;
        List<CategoriaPresupuesto> listaCateg = new ArrayList<>();
        String query_sql1 = "SELECT id_presupuesto FROM presupuesto WHERE concluido = 0 AND id_usuario = " + id_usr + ";";
        
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql1);
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                id_repo = result.getInt("id_presupuesto");
            }
            
            String query_sql2 = "SELECT p.id_presupuesto, p.id_categoria, c.nombre_categoria, p.monto_limite, p.monto_gastado FROM presupuesto_categoria p INNER JOIN categoria c"
                    + " ON c.id_categoria = p.id_categoria WHERE id_presupuesto = " + id_repo;
            
            stmt = conn.prepareStatement(query_sql2);
            result = stmt.executeQuery();

            
            // Ciclo para obtener todos los resgistros
            while(result.next()){
                int id_p = result.getInt("id_presupuesto");
                int id_cat = result.getInt("id_categoria");
                String cat =  result.getString("nombre_categoria");
                double m1 = result.getDouble("monto_limite");
                double m2 = result.getDouble("monto_gastado");
                
                CategoriaPresupuesto categ = new CategoriaPresupuesto(id_p, id_cat, m1, m2, cat);
                listaCateg.add(categ);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaCateg;
    }
    
}
