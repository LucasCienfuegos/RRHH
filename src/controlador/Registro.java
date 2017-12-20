package controlador;

import bd.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Empleado;

/**
 * Clase Registro, contiene los metodos CRUD luego de la conexion con la base de datos, estructura tipo singleton
 * @author Lucas Cienfueos
 */
public class Registro {
    //atributo estatico auxiliar que permite la estructura singleton
    private static Registro aux;
    
    /**
     * constructor privado de la clase Registro sin parametros
     */
    private Registro(){}
    
    /**
     * Metodo que crea la conexion (si es nula) y la retorna.
     * @return Registro para utilizar los metodos de la clase sin necesidad de instanciarla.
     */
    public static Registro metodo(){
        if(aux == null){//se instancia la clase si esta es nula
            aux = new Registro();//se asigna valor a vriable axuliar
        }
        return aux;//se retorna el registro para usarse
    }
    
    
    public boolean agregar(Empleado empleado){
        
        try {
            Statement s = Conexion.obtenerInstancia().createStatement();
            if(!buscar(empleado.getId())){
                String sentencia = "INSERT INTO RRHH(ID, RUT, DV, NOMBRE, FECHAI, FECHAN, DIRECCION)"
                + "VALUES("+empleado.getId()+","+empleado.getRut()+",'"+empleado.getDv()+"','"+empleado.getNombre()+"','"+empleado.getFechaIngreso()+"','"+empleado.getFechaNacimiento()+"','"+empleado.getDireccion()+"')";
                       

                s.execute(sentencia);

                System.out.println("Insertado con exito");
                return true;
            }else{
                System.out.println("ID repetido");
            }
        } catch (SQLException ex) { ex.printStackTrace();
            System.out.println("No se pudo agregar");
        }
        return false;
    }
    
    
    
    /**
     * Metodo boolean modificar el registro de la base de datos que fue seleccionado, genera mensajes de avance durante el proceso
     * @param nuevo
     * @return true si fue modificado de manera correcta a la base de datos
     */
    public boolean modificar(Empleado nuevo){
        
        try {
            Statement s = Conexion.obtenerInstancia().createStatement();//se crea el statment de la conexion
            if(buscar(nuevo.getRut())){//se usa el metodo buscar para comprobar si el rut ya esta registrado
                String sentencia =  "UPDATE RRHH SET "+ 
                                        "NOMBRE = "+nuevo.getNombre()+","+
                                        "FECHAI = "+nuevo.getFechaIngreso()+","+
                                        "FECHAN = '"+nuevo.getFechaNacimiento()+"',"+
                                        "DIRECCION = '"+nuevo.getDireccion()+"'"+
                                    "WHERE CODIGO = "+nuevo.getRut();//busca el rut especifico

                s.execute(sentencia);//se ejecuta la sentencia sql

                System.out.println("Modificado con exito");//mensaje de exito
                return true;//reorno de exito
            }else{
                System.out.println("Codigo no encontrado");//mensaje de error si el codigo no esta registrado
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo modficar");//mensaje de error si no se modificó
        }
        return false;//retorno de error
    }
    
    /**
     * Metodo boolean borrar registro por rut, genera mensajes de avance durante el proceso
     * @param rut
     * @return true si fue borrado de manera correcta a la base de datos
     */
    public boolean borrar(int rut){
        if(buscar(rut)){//se usa el metodo buscar para comprobar si elcodigo ya esta registrado
            String sentencia="DELETE FROM RRHH WHERE RUT="+rut;//String de la sentencia para borrar el registro por el rut
            try {
                Statement s = Conexion.obtenerInstancia().createStatement();//se crea el statment de la conexion
                s.executeUpdate(sentencia);//se ejecuta la sentencia sql
                System.out.println(" registro borrado...");//mensaje de exito
                return true;//retorno de exito al borrar
            } catch (SQLException ex) {
                System.out.println("registro no encontrado");//mensaje de error
            }
            return false;//retorno de error al borrar
        }
        return false;//retorno de no se encuentra en la base de datos
    }

    /**
     * Metodo ResultSet mostrar todos los registros de la base de datos, genera mensajes de avance durante el proceso
     * @return resultado de la consulta a la base de datos
     */
    public ResultSet mostrar(){
        String sentencia = "SELECT ID,RUT||'-'||DV,NOMBRE,FECHAN,FECHAI,DIRECCION FROM RRHH ORDER BY RUT ASC";//String de la sentencia para buscar los registros agregados
        Statement s;//se crea el statment de la conexion
        
        try {
            s = Conexion.obtenerInstancia().createStatement();//se instancia el statment de la conexion
            System.out.println(" Cargando datos...");
            ResultSet resultado=s.executeQuery(sentencia);//se ejecuta la sentencia sql y se le da valor al resulltado
            return resultado;//se retorna el resulatdo de la consulta como ResultSet
        } 
        catch (SQLException ex) {
            System.out.println("Error en la carga de datos");//mensaje de error
            return null;//retorna nulo si hubo un fallo en la carga de datos
        } 
    }

    /**
     * Metodo ResultSet mostrar el registro de la base de datos que tiene ese rut, genera mensajes de avance durante el proceso
     * @param rut
     * @return resultado de la consulta a la base de datos
     */
    public ResultSet mostrar(int rut){
        String sentencia = "SELECT ID,RUT||'-'||DV,NOMBRE,FECHAN,FECHAI,DIRECCION FROM RRHH WHERE RUT = "+rut;//String de la sentencia para buscar los registros agregados
        Statement s;//se crea el statment de la conexion
        
        try {
            s = Conexion.obtenerInstancia().createStatement();//se instancia el statment de la conexion
            System.out.println(" Cargando datos...");
            ResultSet resultado=s.executeQuery(sentencia);//se ejecuta la sentencia sql y se le da valor al resulltado
            return resultado;//se retorna el resulatdo de la consulta como ResultSet
        } 
        catch (SQLException ex) {
            System.out.println("Error en la carga de datos");//mensaje de error
            return null;//retorna nulo si hubo un fallo en la carga de datos
        } 
    }

    /**
     * Metodo boolean buscar por el rut del registro en la base de datos, genera mensajes de avance durante el proceso
     * @param rut
     * @return true si fue enontrado en la base de datos
     */
    public boolean buscar(int rut){
        String sentencia = "SELECT * FROM RRHH WHERE RUT = "+rut;//String de la sentencia para buscar el registro
        Statement s;//se crea el statment de la conexion
        
        try {
            s = Conexion.obtenerInstancia().createStatement();//se instancia el statment de la conexion
            System.out.println(" Cargando datos...");
            ResultSet resultado=s.executeQuery(sentencia);//se captura el resultado de la consulta
            if(resultado.next()){//se comprueba que no esté vacio
                System.out.println("encontrado");//mensaje de encontrado
                return true;//retorno de encontrado
            }else{
                System.out.println("no encontrado");//mensaje de no encontrado
                return false;//retorno de no encontrado
            }
        } 
        catch (SQLException ex) {
            System.out.println("Error en la carga de datos");//mensaje de error en la consulta
            return false;//retorno de error en la consulta
        }
    }
    
    /**
     * Metodo Empleado Obtener retorna los datos de un registro ya guardado, genera mensajes de avance durante el proceso
     * @param rut
     * @return producto con el codigo especificado
     */
    public Empleado obtener(int rut){
        if(buscar(rut)){//se usa el metodo buscar para comprobar si elcodigo ya esta registrado
            try {
                ResultSet resultado = mostrar(rut);//se recupera un registro con el metodo mostrar
                resultado.next();//se verifica que no este vacio
                int id = resultado.getInt("ID");//se guarda el ID            
                String dv = resultado.getString("DV");//se guarda el DV
                String nombre = resultado.getString("NOMBRE");//se guarda el Nombre
                String fechan = resultado.getString("FECHAN");//se guarda la Fecha de Nacimiento
                String fechai = resultado.getString("FECHAI");//se guarda la Fecha de Ingreso
                String direccion = resultado.getString("DIRECCION");//se guarda la Direccion
                
                Empleado registro = new Empleado(id,rut,dv,nombre,fechai,fechan,direccion);//se instancia un empleado con los datos recuperados
                return registro;//retorna el empleado que se instancio
            } catch (SQLException ex) {
                System.out.println("Error al crear el producto");//mensaje de error
            }
        }
        return null;
    }    
}
