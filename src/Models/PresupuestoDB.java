/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Conection.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angel H
 */
public class PresupuestoDB {
    
    public Presupuesto obtenerPresupuestoActivo(int id_usr){
        Presupuesto presup = new Presupuesto();
        String query_sql = "SELECT * FROM presupuesto WHERE concluido = 0 AND id_usuario = " + id_usr+ ";";
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                String fecha_inicio = result.getString("fecha_inicio");
                String fecha_fin = result.getString("fecha_fin");
                double monto_lim = result.getDouble("monto_limite_periodo");
                
                presup = new Presupuesto(fecha_inicio, fecha_fin, monto_lim);
            }
               

        }catch(SQLException e){
            System.out.println("Error en obtenerPresupuesto: " + e.getMessage());
        }
        return presup;
    }
    
    public double obtenerTotalGastado(int id_usr){
        double totalgastado = 0;
        String fecha_inicio = "";
        String fecha_fin = "";
        
        String query_sql1 = "SELECT fecha_inicio, fecha_fin FROM presupuesto WHERE concluido = 0 AND id_usuario = " + id_usr + ";";
        
        try{
            //Conexion a la BD
            Connection conn = ConexionDB.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql1);
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                fecha_inicio = result.getString("fecha_inicio");
                fecha_fin = result.getString("fecha_fin");
            }
            
            
            //String query_sql2 = "SELECT SUM(monto) AS total FROM movimiento WHERE tipo_movimento = 'Gasto' AND fecha >= '" + fecha_inicio + "' AND fecha <= '" + fecha_fin + "' AND id_usuario = " + id_usr +";";
            String query_sql2 = "SELECT SUM(monto) AS total FROM movimiento WHERE tipo_movimiento = 'Gasto' AND id_usuario = " + id_usr + " AND fecha >= '" + fecha_inicio + "' AND fecha <= '" + fecha_fin + "'";
            stmt = conn.prepareStatement(query_sql2);
            result = stmt.executeQuery();
                

            if(result.next()) totalgastado = result.getDouble("total");
             

        }catch(SQLException e){
            System.out.println("Error en obtenerTotalGastado: " + e.getMessage());
        }
        return totalgastado;
    }
}
