package Conexion;

import java.sql.*;

public class DbConexion {


    private static final String url = "jdbc:mysql://localhost:3306/estudiante";
    private static final String USER = "root";
    private static final String pass = "Prueba123456.";



    public static Connection conectar(){
        try{
            return DriverManager.getConnection(url,USER,pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}