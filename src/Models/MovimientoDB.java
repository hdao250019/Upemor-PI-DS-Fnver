/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Conection.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDB {
    
    public List<Movimiento> consultarMovimientos(){
        List<Movimiento> listaMov = new ArrayList<>();
        String query_sql = "SELECT * FROM movimiento";
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            ResultSet result = stmt.executeQuery();
            
            // Ciclo para obtener todos los resgistros
            while(result.next()){
                int id_usr = result.getInt("id_usuario");
                int id_categ = result.getInt("id_categoria");
                double monto = result.getDouble("monto");
                String fecha = result.getString("fecha");
                String desc = result.getString("descripcion");
                String tipo = result.getString("tipo_movimiento");
                
                Movimiento mov = new Movimiento(id_usr, id_categ, monto, fecha, desc, tipo);
                listaMov.add(mov);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaMov;
    }
}
