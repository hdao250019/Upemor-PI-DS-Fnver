/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conection;

import java.sql.*;

/**
 *
 * @author Angel H
 */
public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/prueba_3b";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection conexion(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa");
        }catch(SQLException e){
            System.out.println("Error en la conexion: " + e.getMessage() );
        }
        
        return conn;
    }
}
