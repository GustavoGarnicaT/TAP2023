/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gustavo
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
    Connection Conexion;
    Statement transaccion;
    ResultSet cursor;
   
    String CadenaConexion = "jdbc:mysql://buoqkfpruhep7he4n6lf-mysql.services.clever-cloud.com:3306/buoqkfpruhep7he4n6lf?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String usuario = "uv3z2wfrso3ykkfl";
    String pass = "3QXUYT0RsZlq4ZoSoyTJ";
    public BaseDatos(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Conexion = DriverManager.getConnection(CadenaConexion,usuario,pass);
        transaccion = Conexion.createStatement();
    }catch(SQLException e){
    
    }catch(ClassNotFoundException e){
    
    }
  } 
    public boolean Insertar(Persona p){
        String SQL_Insertar = "INSERT INTO `persona` (`ID`, `NOMBRE`, `DOMICILIO`, `TELEFONO`) VALUES (NULL, '%NOM%', '%DOM%', '%TEL%');";
        SQL_Insertar = SQL_Insertar.replace("%NOM%", p.Nombre);
        SQL_Insertar = SQL_Insertar.replace("%DOM%", p.Domicilio);
        SQL_Insertar = SQL_Insertar.replace("%TEL%", p.Telefono);
        try{
            transaccion.execute(SQL_Insertar);
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public ArrayList<Persona> mostrarTodos(){
     ArrayList<Persona> datos = new ArrayList<Persona>();
    String SQL_consulta = "SELECT * FROM `persona`";
        try {
            //RESULTSET = variable que maneja las tuplas resultado
            cursor = transaccion.executeQuery(SQL_consulta);
            
            if(cursor.next()){
                do{
                    Persona p = new Persona(
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    );
                    datos.add(p);
                }while(cursor.next());
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    public Persona obtenerporID(String IDaBuscar){
        String SQL_consulta = "SELECT * FROM `persona` WHERE `ID`="+IDaBuscar;
    
        try {
            
            cursor = transaccion.executeQuery(SQL_consulta);
            
            if(cursor.next()){
                
                    Persona p = new Persona(
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    );
                    return p;
                
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Persona(-1,"","","");
    }
    
    public boolean eliminar(String IDaEliminar){
        try{
          String SQL_eliminar = "DELETE FROM `persona` WHERE `ID` = "+IDaEliminar;
          transaccion.execute(SQL_eliminar);
        }catch(SQLException ex){
            return false;
        }
        return true;
    }
    
    public boolean actualizar(Persona p){
    //su codigo es MUY SIMIlar A INSERTAR ... QUEDA DE TAREA
        String SQL_Actualizar = "UPDATE `persona` SET `NOMBRE`='%NOM%', `DOMICILIO`='%DOM%', `TELEFONO`='%TEL%' WHERE `ID`="+p.id;
        SQL_Actualizar = SQL_Actualizar.replace("%NOM%", p.Nombre);
        SQL_Actualizar = SQL_Actualizar.replace("%DOM%", p.Domicilio);
        SQL_Actualizar = SQL_Actualizar.replace("%TEL%", p.Telefono);
        try {
            transaccion.executeUpdate(SQL_Actualizar);
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }
    
}
