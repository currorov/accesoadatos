/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_s4;

import java.sql.*;

/**
 *
 * @author Nacho
 */
public class DBUtil {

    public Connection conn;
    public String servidor="";
    public int puerto=0;
    public String bbdd="";
    public String conexion = "jdbc:mysql://"+servidor+":"+puerto+"/"+bbdd;
    public String usuario = "";
    public String contraseña = "";
    public String error="";

    public Connection getConexion() {

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.conn = DriverManager.getConnection(this.conexion, DBUtil.getDBUtil().usuario, DBUtil.getDBUtil().contraseña);
            return this.conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cerrarConexion() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DBUtil db;
    
    public DBUtil(){};
    
    public synchronized  static DBUtil getDBUtil(){
        if(db==null){
            db=new DBUtil();
        }
        return db;
    }

}
