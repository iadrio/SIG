import java.util.*;
/**
 * Crea objetos tipo cajero 
 * 
 * @author Ivan Adrio Muñiz 
 * @version 2018.03.05
 */
public class Empleado extends Persona
{
    private String clave;
    private HashSet<Roles> permisos;
    private Roles roles;
     /**
     * Crea objetos tipo cajero
     * @param identificacion DNI de la persona
     * @param nombre Nombre de la persona
     * @param apellidos Apellidos de la persona
     * @param correoElectronico Correo electronico de la persona
     * @param telefono Numero de telefono de la persona
     */    
    
    public Empleado(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono,String clave,Roles rol)
    {
        super(identificacion,nombre,apellidos,correoElectronico,telefono);
        permisos = new HashSet<Roles>();
        this.clave=clave;
        setRol(rol);
        setAñadirPermiso(rol);
    }
    
    /**
     * Crea un usuario por defecto usando solo el identificacion y la clave. Este usuario tendrá rol de administrador y permisos de 
     * administrador. Los demás características permaneceran en vacías.
     * @param usuarioAdministrador identificador del usuario
     * @param claveAdministrador clave del usuario
     */
    public Empleado(String usuarioAdministrador,String claveAdministrador)
    {
     super(usuarioAdministrador,"default","default","default","default");
     permisos = new HashSet<Roles>();
     clave=claveAdministrador;
     setRol(Roles.ADMINISTRADOR);
     setAñadirPermiso(Roles.ADMINISTRADOR);     
    }    
    
    /**
     * Devuelve la clave de acceso personal del usuario
     */
    public String getClave()
    {
        return clave;
    }
    
    /**
     * Cambia el valor de la clave de usuario
     * @param nuevaClave clave que queremos fijar
     */
    public void setClave(String nuevaClave)
    {
        clave = nuevaClave;
    }
    
    /**
     * Añade un permiso a la lista de permisos del usuario
     * @param permiso permiso que queremos añadir al usuario
     */
    public void setAñadirPermiso(Roles permiso)
    {
        permisos.add(permiso);
    }
    
    /**
     * Retira un permiso de la lista de permisos del usuario
     * @param permiso permiso que queremos retirar
     */
    public void setQuitarPermiso(String permiso)
    {
        permisos.remove(permiso);
    }
    
    /**
     * Comprueba si un empleado tiene un determinado permiso
     * @param permiso permiso que queremos comprobar
     * @return true si el usuario tiene el permiso
     */
    public boolean comprobarPermiso(String permiso)
    {
        boolean valido=false;
        for(Roles entrada:permisos)
        {
            if(entrada.toString().equals(permiso)){
                valido=true;
            }
        }
        
        if(!valido)
        {
            System.out.println("El usuario no tiene permiso para esta accion, contacte con un administrador.");
        }
        return valido;
    }
        
    /**
     * Devuelve la lista de permisos que tiene el usuario
     * @return lista de permisos del usuario
     */
    public String getListaPermisos()
    {
        String lista=null;
        for(Roles permiso: permisos)
        {
            if(lista==null)
            {
                lista=permiso.toString();
            }
            else
            {
                lista=lista+" "+permiso.toString();
            }
        }
        
        return lista;
    }
    
     /**
     * Devuelve todos los detalles de una persona en un STring
     * @return detalles de la persona
     */
    public String toString()
    {
            return(super.toString()+"clave: "+formatea(getClave(),8)+"Lista de permisos: "+getListaPermisos());
    }    
    
    /**
     * Modifica los datos de un empleado
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectrónico correo electronico del cliente
     * @param telefono telefono del cliente
     * @param empleadoActual identificación del empleado que modifica los datos
     * @param clave clave de acceso
     * @param rol rol del empleado
     */
    public void modificarPersona(String nombre, String apellidos, String correoElectronico, String telefono, String empleadoActual,String clave,Roles rol)
    {
        super.modificarPersona(nombre,apellidos,correoElectronico,telefono);
        this.clave = clave;
        setRol(rol);
    }
}
