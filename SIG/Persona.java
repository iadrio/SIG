import java.util.*;
import java.io.*;

/**
 * La clase persona describe cualquier persona fisica que esta involucrada en la operativa diaria de la tienda.
 * Almacena, muestra y permite modificar los datos de contacto de la persona asi como el rol a cumplir por esta (empleado o cliente).
 * 
 * @author Ivan Adrio 
 * @version 2018.03.05
 */
public  class Persona implements Serializable
{

    private String identificacion, nombre, apellidos, correoElectronico, telefono;
    private Roles rol;
    private Map<String,Roles> diccionarioRoles;
    /**
     * Crea objetos persona
     * 
     * @param identificacion DNI de la persona
     * @param nombre Nombre de la persona
     * @param apellidos Apellidos de la persona
     * @param correoElectronico Correo electronico de la persona
     * @param telefono Numero de telefono de la persona
     */
   
    public Persona(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono)
    {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        
    }
    
    /**
     * Indica la identificación de una persona
     * @return     Devuelve la identificacion (DNI) de la persona 
     */
    public String getIdentificacion()
    {
     return identificacion;
    }
    
    /**
     * Indica el nombre de una persona
     * @return     Devuelve el nombre de la persona 
     */
    public String getNombre()
    {
     return nombre;
    }
    
    /**
     * Indica los apellidos de una persona
     * @return     Devuelve los apellidos de la persona 
     */
    public String getApellidos()
    {
     return apellidos;
    }
    
    /**
     * Indica el correo electrónico de una persona
     * @return     Devuelve el correo electronico de la persona 
     */
    public String getCorreoElectronico()
    {
     return correoElectronico;
    }
    
    /**
     * Indica el teléfono de una persona
     * @return     Devuelve el teléfono de la persona 
     */
    public String getTelefono()
    {
     return telefono;
    }
   
    /**
     * Indica el rol de una persona
     * @return Devuelve el rol de la persona
     */
    public Roles getRol()
    {
        return rol;
    }
    
     /**
     * Modifica el rol de la persona
     * @param nuevoRol Nuevo rol de la persona
     */
    public void setRol(Roles nuevoRol)
    {
        rol=nuevoRol;
    }
    
    /**
     * Modifica la identificación (DNI) de la persona
     * @param nuevaIdentificacion Nuevo DNI de la persona
     */
    public void setIdentificacion(String nuevaIdentificacion)
    {
     identificacion=nuevaIdentificacion;
    }
    
    /**
     * Modifica el nombre de la persona
     * @param nuevoNombre Nuevo nombre de la persona
     */
    public void setNombre(String nuevoNombre)
    {
     nombre=nuevoNombre;
    }
    
    /**
     * Modifica los apellidos de la persona
     * @param nuevosApellidos Nuevos apellidos de la persona
     */
    public void setApellidos(String nuevosApellidos)
    {
     apellidos=nuevosApellidos;
    }    
    
    /**
     * Modifica el correo electronico de la persona
     * @param nuevoCorreoElectronico Nuevo correo electronico de la persona
     */
    public void setCorreoElectronico(String nuevoCorreoElectronico)
    {
     correoElectronico=nuevoCorreoElectronico;
    }
    
    /**
     * Modifica el teléfono de la persona
     * @param nuevoTelefono Nuevo teléfono de la persona
     */
    public void setTelefono(String nuevoTelefono)
    {
     telefono=nuevoTelefono;
    }
    
    /**
     * Describe a la persona
     * @return descripcion
     */
    public String toString()
    {
        return ("****"+formatea(getRol()+": ",15)+formatea(getNombre()+" "+getApellidos(),30)+formatea("id: "+getIdentificacion(),15)+" Numero de telefono: "+
        formatea(getTelefono(),12)+" Correo electronico: "+formatea(getCorreoElectronico(),30));
    }
       
    
    /**
     * Da formato a un string rellenandolo con espacios para que tenga una determinada longitud
     * @param string string que queremos formatear
     * @param espacio longitud que queremos que tenga el string
     */
    public String formatea(String string,int espacio)
    {
        return String.format("%-"+espacio+"s",string);
    }
    
    /**
     * Modifica los datos de una persona
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectrónico correo electronico del cliente
     * @param telefono telefono del cliente
     */
    public void modificarPersona(String nombre,String apellidos,String correoElectronico,String telefono)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }
    
    }

