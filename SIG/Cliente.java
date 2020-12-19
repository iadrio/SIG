import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Representa un cliente. Contiene todos los datos relacionados con el cliente. Contiene el historial de acciones relacionadas con
 * el cliente. 
 * 
 * @author Ivan Adrio Muñiz 
 * @version 2018.04.19
 */
public class Cliente extends Persona
{
    private String idEmpleado;
    private String[] accion;
    private ArrayList<String[]> historial;
    private int numeroAccion;
    private SimpleDateFormat formatoFecha;

    /**
     * Crea un objeto tipo cliente e introduce los datos 
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectronico correo electrónico de contacto del cliente
     * @param telefono teléfono de contacto del cliente
     * @param idEmpleado DNI del empleado que da de alta la ficha
     * @param formatoFecha formato empleado para la fecha
     */
    public Cliente(String identificacion, String nombre, String apellidos, String correoElectronico,
    String telefono,String idEmpleado,SimpleDateFormat formatoFecha)
    {
     super(identificacion,nombre,apellidos,correoElectronico,telefono);
     this.idEmpleado=idEmpleado;
     setRol(Roles.CLIENTE);
     numeroAccion=0;
     historial = new ArrayList<String[]>();
     this.formatoFecha=formatoFecha;
    }
    
    /**
     * Imprime los detalles de cada persona
     * @return detalles del cliente
     */
    public String toString()
    {
        return super.toString();
    }
    
    /**
     * Devuelve el historial de acciones del cliente en una lista
     * @return lista de acciones realizadas con el cliente
     */
    public ArrayList<String[]> getHistorial()
    {
        return historial;
    }
    
    /**
     * Devuelve una lista en la que cada linea es la descripción de una accion
     * @return historial de acciones
     */
    public ArrayList<String> getHistorialString()
    {
        ArrayList<String> historialAcciones = new ArrayList<String>();
        historialAcciones.add("HISTORIAL DE INTERACCIONES CON EL CLIENTE");
        if(!historial.isEmpty())
        {
            int i=0;
            while(i<historial.size())
            {
                String [] accion = historial.get(i);
                historialAcciones.add(formatea("Numero de accion ="+accion[0],23)+formatea(accion[1],70)+formatea("Empleado: "+
                accion[2],22)+"Fecha: "+accion[3]);
                i++;
            }
        }
        
        return historialAcciones;
    }
    
    /**
     * Añade una accion al historial de acciones del cliente 
     * @param descripcion descripcion de la accion
     * @param idEmpleado DNI del empleado que realiza la acción
     */
    public void setAccion(String descripcion,String idEmpleado)
    {
        Date date = new Date();
        String fecha = formatoFecha.format(date);
        accion = new String[4];
        accion[0] = Integer.toString(numeroAccion);
        accion[1] = descripcion;
        accion[2] = idEmpleado;
        accion[3] = fecha;
        historial.add(accion);
        numeroAccion++;
    }
    
    /**
     * Modifica los datos de un cliente
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectrónico correo electronico del cliente
     * @param telefono telefono del cliente
     * @param empleadoActual DNI del empleado que modifica al cliente
     */
    public void modificarPersona(String nombre, String apellidos, String correoElectronico, String telefono, String empleadoActual)
    {
        super.modificarPersona(nombre,apellidos,correoElectronico,telefono);
        this.idEmpleado=empleadoActual;
        setAccion("Modificados los datos del cliente",empleadoActual);
    }
}
