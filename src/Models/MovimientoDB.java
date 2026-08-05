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
    
     public boolean insertar(Movimiento mov){
        String sql_query = "INSERT INTO movimiento(id_usuario, id_categoria, tipo_movimiento, monto, fecha, descripcion) VALUES(?, ?, ?, ?, NOW(), ?)";
        
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            // Enviar los datos del modelo
            stmt.setInt(1, mov.getId_usuario());
            stmt.setInt(2, mov.getId_categoria());
            stmt.setString(3, mov.getTipo_movimiento());
            stmt.setDouble(4, mov.getMonto());
            //stmt.setString(5, mov.getFecha());
            stmt.setString(5, mov.getDescripcion());
            
            //Ejecutar el query en la DB
            stmt.executeUpdate();
            
            // Cerrar Statement y la conexion a la DB
            stmt.close();
            conn.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Error al insertar movimiento: "+ e.getMessage());
            return false;
        }
    }
    
    public List<Movimiento> consultarMovimientos(int id_user){
        List<Movimiento> listaMov = new ArrayList<>();
        String query_sql = "SELECT m.id_movimiento, m.id_usuario, c.nombre_categoria, m.monto, m.fecha, m.descripcion, m.tipo_movimiento FROM movimiento AS m "
                + "INNER JOIN categoria AS c ON m.id_categoria = c.id_categoria WHERE m.id_usuario = " + id_user + " ORDER BY m.fecha DESC";
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            ResultSet result = stmt.executeQuery();
            
            // Ciclo para obtener todos los resgistros
            while(result.next()){
                int id = result.getInt("id_movimiento");
                int id_usr = result.getInt("id_usuario");
                String categ = result.getString("nombre_categoria");
                double monto = result.getDouble("monto");
                String fecha = result.getString("fecha");
                String desc = result.getString("descripcion");
                String tipo = result.getString("tipo_movimiento");
                
                Movimiento mov = new Movimiento(id, id_usr, categ, monto, fecha, desc, tipo);
                listaMov.add(mov);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaMov;
    }
}
