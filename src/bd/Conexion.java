/*
 * Recuerde siempre agregar el JAR ojdbc6.jar
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Conexion tipo singleton para realizar facilmente una conexion a una base de datos.
 * @author Lucas Cienfuegos
 */
public class Conexion {
    
    //Atributo estatico auxiliar que permite el funcionamiento como singleton
    private static Connection conexion;
    
    /**
     * constructor privado de la clase conexion sin parametros
     */
    private Conexion(){}
    
    /**
     * Metodo que crea la conexion (si es nula) y la retorna.
     * @return Conexion con la base de datos.
     */
    public static Connection obtenerInstancia()
    {
        if(conexion == null){//creacion de la conexion si esta es nula
            String url = "jdbc:oracle:thin:@MSP-PM-04-03.aws.smartcloud.cl:1521:xe";//Acceso a la base de datos
            String user = "usuario03";//usuario para la conexion
            String pass = "usuario03";//contraseña para la conexion
            System.out.println("Proceso de Coneccion...");
            try {
                conexion = DriverManager.getConnection(url, user, pass);//realiza la conexion a la base de datos especificada con los campos entregados
                System.out.println("Conexión realizada a la base de datos con éxito.");//mensaje de exito
            }catch(SQLException e) {
                System.out.println("Error!, conexión fallida a la base de datos.");//mensaje de error
            }
        }
        return conexion;
    }
}
