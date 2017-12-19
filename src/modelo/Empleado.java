package modelo;
/**
 *
 * @author Estudio
 */
public class Empleado {
    /*
    * parametros de clase
    */
    private int id;//4 digitos
    private int rut;//10 digitos
    private char dv;//1 caracter 0-9 y K
    private String nombre;//30 caracteres
    private String fechaIngreso;//30 caracteres
    private String fechaNacimiento;//30 caracteres
    private String direccion;//40 caracteres

    public Empleado() {
    }

    public Empleado(int id, int rut, String dv, String nombre, String fechaIngreso, String fechaNacimiento, String direccion) {
        setId(id);
        setRut(rut);
        setDv(dv);
        setNombre(nombre);
        setFechaIngreso(fechaIngreso);
        setFechaNacimiento(fechaNacimiento);
        setDireccion(direccion);
    }

    /**
    *
    * @return int id de empleado
    */
    public int getId() {
        return id;
    }

    /**
    *
    * @return rut del empleado
    */
    public int getRut() {
        return rut;
    }

    /**
    *
    * @return String digito verificador del rut
    */
    public String getDv() {
        return dv+"";
    }

    /**
    *
    * @return String nombre del empleado
    */
    public String getNombre() {
        return nombre;
    }

    /**
    *
    * @return String fecha nacimiento
    */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
    *
    * @return String fecha ingreso
    */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
    *
    * @return String direccion empleado
    */
    public String getDireccion() {
        return direccion;
    }

    /**
     * entre 1000 y 9999
     * @param id 
     */
    public void setId(int id) {
        if(id>999 && id<10000){
            this.id = id;
        }else{
            System.out.println("ID entre 1000 y 9999");
        }
    }

    /**
     * entre 1000000 y 1000000000
     * @param rut 
     */
    public void setRut(int rut) {
        if(rut>999999 && rut<1000000001){
            this.rut = rut;
        }else{
            System.out.println("Rut entre 1000000 y 1000000000");
        }
    }

    /**
     * 1 caracter
     * @param dv 
     */
    public void setDv(String dv) {
        if(dv.equals("0")||dv.equals("1")||dv.equals("2")||dv.equals("3")||dv.equals("4")||dv.equals("5")||dv.equals("6")||dv.equals("7")||dv.equals("8")||dv.equals("9")||dv.toUpperCase().equals("K")){
            this.dv = dv.toUpperCase().charAt(0);
        }else{
            System.out.println("DV invalido (0-9 o K)");
        }
    }

    /**
     * 1 a 30 caracteres
     * @param nombre 
     */
    public void setNombre(String nombre) {
        if(nombre.length()>0 && nombre.length()<31){
            this.nombre = nombre;
        }else{
            System.out.println("nombre entre 1 y 30 caracteres.");
        }
    }

    /**
     * 1 a 30 caracteres
     * @param fechaIngreso 
     */
    public void setFechaIngreso(String fechaIngreso) {
        if(fechaIngreso.length()>7 && fechaIngreso.length()<31){
            this.fechaIngreso = fechaIngreso;
        }else{
            System.out.println("Fecha de Ingreso entre 8 y 30 caracteres.");
        }
    }

    /**
     * 1 a 30 caracteres
     * @param fechaNacimiento 
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        if(fechaNacimiento.length()>7 && fechaNacimiento.length()<31){
            this.fechaNacimiento = fechaNacimiento;
        }else{
            System.out.println("Fecha de Nacimiento entre 8 y 30 caracteres.");
        }
    }

    /**
     * 1 a 40 caracteres
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        if(direccion.length()>0 && direccion.length()<41){
            this.direccion = direccion;
        }else{
            System.out.println("Direccion entre 1 y 40 caracteres.");
        }
    }

    @Override
    public String toString() {
        return "\nID: " + getId() + " Rut: " + getRut() + "-" + getDv() + " Nombre: " + getNombre() + " Fecha Ingreso: " + getFechaIngreso() + " Fecha Nacimiento: " + getFechaNacimiento() + " Direccion: " + getDireccion();
    }
    
}
