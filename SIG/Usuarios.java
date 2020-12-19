import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
/**
 * Gestiona todo lo relacionado con los usuarios del programa y los cliente. Contiene métodos para añadir clientes, añadir usuarios, 
 * eliminarlos, modificar sus datos, añadir permisos, comprobar los permisos etc.
 * 
 * @author Iván Adrio Muñiz
 * @version 2018.04.19 
 */
public class  Usuarios
{
    private HashMap<String,Empleado> listaEmpleados;
    private HashMap<String,Cliente> listaClientes;
    private final int LONGITUDCLAVE = 5;
    //usuario inicial del sistema. En el primer inicio sin datos en el programa, sera el unico empleado capaz de logarse
    private final String USUARIOADMINISTRADOR = "admin";
    private final String CLAVEADMINISTRADOR = "admin";    
    private Empleado administrador;
    private Empleado empleadoActual;
    private String ruta;
    private SimpleDateFormat formatoFecha;
    private final String ARCHIVOEMPLEADOS = "/empleados.txt";
    private final String ARCHIVOCLIENTES = "/clientes.txt";
    private Map<String,Roles> diccionarioRoles;
    /**
     * Crea las listas de empleados y de clientes, añade un usuario inicial al sistema y fija los archivos en los que se guardarán los
     * datos
     * @param ruta ruta de la carpeta donde se guardaran los datos
     * @param formatoFecha formato empleado para la fecha
     */
    public Usuarios(String ruta,SimpleDateFormat formatoFecha)
    {
        listaEmpleados = new HashMap<String,Empleado>();
        listaClientes = new HashMap<String,Cliente>();
        administrador = new Empleado(USUARIOADMINISTRADOR,CLAVEADMINISTRADOR);
        listaEmpleados.put(USUARIOADMINISTRADOR,administrador);
        this.ruta=ruta;
        this.formatoFecha=formatoFecha;
        diccionarioRoles= new HashMap<String,Roles>();
        for(Roles rol:Roles.values())
        {
            diccionarioRoles.put(rol.toString(),rol);
        }
    }

    /**
     * Comprueba si los datos de un usuario son correctos
     * @param id DNI del usuario
     * @param clave clave de acceso personal
     * @return true si los datos son correctos
     */
    public boolean login(String id,String clave)
    {
        boolean seguir=true;
        boolean valid=false;
        if(!getExisteEmpleado(id)){
            valid=false;
        }
        else
        {
            if(getClave(id).equals(clave))
            {
                
                empleadoActual = getEmpleado(id);
                setEmpleadoActual(empleadoActual);
                valid=true;
                seguir=false;
            }
            else
            {
                valid=false;
            }
        }  
        return valid;
    }
    
    /**
     * Devuelve el empleado que está logado actualmente en el programa
     * @return empleado actual
     */
    public Empleado getEmpleadoActual()
    {
        return empleadoActual;
    }
    
    /**
     * Indica la longitud que debe tener la clave de empleado
     * @return longitud de clave de empleado
     */
    public int getLongitudClave()
    {
        return LONGITUDCLAVE;
    }
    
    /**
     * Devuelve un string con los permisos que puede tener un empleado
     * @return permisos validos para un empleado
     */
    public String getPermisosValidos()
    {
        return Roles.listaRolesEmpleado();
    }
    
    
    /**
     * Comprueba si un rol está dentro de la lista de roles validos
     * @return true si el rol es valido
     */
    public boolean getRolValido(String rol)
    {
        if(diccionarioRoles.get(rol)==null){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Devuelve un string con los roles que puede tener un empleado
     * @return roles validos para un empleado
     */
    public String getStringRolesValidos()
    {
        return Roles.listaRolesEmpleado();
    }
    
    
    /**
     * Añade un empleado a la lista de empleados
     * @param identificación DNI del empleado
     * @param nombre nombre del empleado
     * @param apellidos apellidos del empleado
     * @param correoElectronico correo electrónico del empleado
     * @param telefono teléfono de contacto del empleado
     * @param clave clave de acceso del empleado
     * @param rol rol del empleado ( cajero, tecnico, etc)
     * @return true si el empleado se ha añadido correctamente
     */
    public boolean añadirEmpleado(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono,
    String clave,String rol)
    {
        if(!getExisteEmpleado(identificacion)){
            Empleado empleado = new Empleado(identificacion,nombre,apellidos,correoElectronico,telefono,clave,diccionarioRoles.get(rol));
            listaEmpleados.put(identificacion,empleado);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Añade un cliente a la lista de cliente
     * @param identificación DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectronico correo electrónico del cliente
     * @param telefono teléfono de contacto del cliente
     * @return true si el empleado se ha añadido correctamente
     */
    public boolean añadirCliente(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono)
    {   
        if(!getExisteCliente(identificacion)){
            Cliente cliente = new Cliente(identificacion,nombre,apellidos,correoElectronico,telefono,empleadoActual.getIdentificacion(),formatoFecha);
            listaClientes.put(identificacion,cliente);
            cliente.setAccion("Alta usuario",empleadoActual.getIdentificacion());
            return true;
        }
        else
        {
            listaClientes.get(identificacion).toString();
            return false;
        }
    }
    
    /**
     * Permite eliminar un cliente de la base de datos
     * @param idCliente identificacion del cliente que se desea eliminar
     * @return true si se ha eliminado correctamente
     */
    public boolean quitarCliente(String idCliente)
    {
        if(getExisteCliente(idCliente))
        {
            listaClientes.remove(idCliente);
            return true;
        }
        else
        {
            return false;
        }        
    }
    
    /**
     * Comprueba si un empleado existe en la lista de empleados
     * @param id DNI del empleado
     * @return true si un empleado existe
     */
    public boolean getExisteEmpleado(String id)
    {
        boolean existe=false;
        
        if(listaEmpleados.isEmpty())
        {
            existe=false;
        }
        else
        {
            existe=listaEmpleados.containsKey(id);
        }
        
        return existe;
    }
    
    /**
     * Comprueba si un cliente existe en la lista de empleados
     * @param id DNI del cliente
     * @return true si un cliente existe
     */
    public boolean getExisteCliente(String id)
    {
        boolean existe=false;
        
        if(listaClientes.isEmpty())
        {
            existe=false;
        }
        else
        {
            existe=listaClientes.containsKey(id);
        }
        
        return existe;
    }
    
    /**
     * Busca un cliente en la lista de clientes usando el DNI del cliente
     * @param id DNI del cliente
     * @return objeto clase cliente 
     */
    public Cliente getCliente(String id)
    {
            String dni = id;
            return listaClientes.get(dni);

    }
    
    /**
     * Busca un cliente en la lista de clientes usando el DNI del empleado
     * @param id DNI del empleado
     * @return objeto clase empleado
     */
    public Empleado getEmpleado(String id)
    {
        return listaEmpleados.get(id);
    }
    
    /**
     * Nos indica la clave de empleado de un empleado
     * @param id DNI del empleado
     * @return clave del empleado
     */
    public String getClave(String id)
    {
        return listaEmpleados.get(id).getClave();
    }
    
    /**
     * Cambia el usuario actual por uno nuevo
     * @param empleado empleado que queremos poner como usuario actual
     */
    public void setEmpleadoActual(Empleado empleado)
    {
        empleadoActual=empleado;
    }
    
    /**
     * Comprueba si un permiso está dentro de la lista de permisos validos
     * @param permiso permiso que queremos comprobar
     * @return true si el permiso pertenece a la lista de permisos validos
     */
    public boolean getPermisoValido(String permiso)
    {
        if(diccionarioRoles.get(permiso)!=null)
        {
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * Añade un permiso a la lista de permisos del usuario
     * @param identificación DNI del empleado
     * @param permiso permiso que queremos añadir
     * @return true si el permiso se añade correctamente
     */
    public boolean añadirPermiso(String identificacion,String permiso)
    {
        if(getExisteEmpleado(identificacion))
        {
            if(getPermisoValido(permiso))
            {
                getEmpleado(identificacion).setAñadirPermiso(diccionarioRoles.get(permiso));
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        } 
    }
    
    /**
     * Quita un permiso de la lista de permisos del empleado
     * @param identificacion DNI del empleado
     * @param permiso permiso que queremos retirar
     * @return true si el permiso se retira correctamente
     */
    public boolean quitarPermiso(String identificacion,String permiso)
    {
        if(getExisteEmpleado(identificacion))
        {
            if(getPermisoValido(permiso))
            {
                getEmpleado(identificacion).setAñadirPermiso(diccionarioRoles.get(permiso));
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        } 
    }
    
    
    /**
     * Busca un empleado en la lista de empleados utilizando su DNI y nos lo describe
     * @param id DNI del empleado
     * @return descripción del empleado
     */
    public String getEmpleadoId(String id)
    {
        String descripcion;
        
        if(getExisteEmpleado(id))
        {
            descripcion=getEmpleado(id).toString();
        }
        else
        {
            descripcion=("El empleado no existe");
        }
        
        return descripcion;
    }
    
    /**
     * Busca un cliente en la lista de clientes utilizando su DNI y nos lo describe
     * @param id DNI del cliente
     * @return descripción del cliente
     */
    public String getClienteId(String id)
    {
        String descripcion;
        
        if(getExisteCliente(id))
        {
            descripcion=getCliente(id).toString();
        }
        else
        {
            descripcion=("El cliente no existe");
        }
        
        return descripcion;
    }
    
    /**
     * Busca en la lista de empleados todos aquellos empleados que su nombre o sus apellidos contienen el criterio de busqueda
     * @param nombre criterio de busqueda
     * @return lista de empleados filtrada
     */
    public ArrayList<String> getEmpleadoNombre(String nombre)
    {   
        ArrayList<String> lista= new ArrayList<String>();
        for(HashMap.Entry<String,Empleado> entry: listaEmpleados.entrySet())
        {
            if(!(entry.getValue().getNombre()==null))
            {
                if(entry.getValue().getNombre().equals(nombre)||entry.getValue().getApellidos().contains(nombre))
                {
                    lista.add(entry.getValue().toString());
                }
            }
        }
        
        return lista;
    }
    
    /**
     * Busca en la lista de clientes todos aquellos clientes que su nombre o sus apellidos contienen el criterio de busqueda
     * @param nombre criterio de busqueda
     * @return lista de clientes filtrada
     */
    public ArrayList<String> getClienteNombre(String nombre)
    {        
        ArrayList<String> lista=new ArrayList<String>();
        for(HashMap.Entry<String,Cliente> entry: listaClientes.entrySet())
        {
            if(entry.getValue().getNombre().equals(nombre)||entry.getValue().getApellidos().contains(nombre))
            {
                lista.add(entry.getValue().toString());
            }
        }
        
        return lista;
    }
    
    /**
     * Devuelve una lista con las descripciones de los empleados
     * @return lista de empleados con descripciones
     */
    public ArrayList<String> getListaEmpleados()
    {
        ArrayList<String> lista = new ArrayList<String>();
        for(HashMap.Entry<String,Empleado> entrada:listaEmpleados.entrySet())
        {
            String empleado = entrada.getValue().toString();
            lista.add(empleado);
        }
        return lista;
    }
    
    /**
     * Devuelve una lista con las descripciones de los clientes
     * @return lista de clientes con descripciones
     */
    public ArrayList<String> getListaClientes()
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<String,Cliente> entrada:listaClientes.entrySet())
        {
            String cliente = entrada.getValue().toString();
            lista.add(cliente);
        }
        return lista;
    }
    
    /**
     * Devuelve una lista de los clientes
     * @return lista de clientes
     */
    public ArrayList<String> getClientes()
    {   
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<String,Cliente> entrada:listaClientes.entrySet())
        {
            lista.add(entrada.getKey());
        }
        
        return lista;
    }
    
    /**
     * Modifica los datos de un cliente
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectrónico correo electronico del cliente
     * @param telefono telefono del cliente
     * @param empleadoActual DNI del empleado que modifica al cliente
     * @return true si los datos se han modificado correctamente
     */
    public boolean modificarCliente(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono,String empleadoActual)
    {
        
        if(getExisteCliente(identificacion))
        {
            Cliente cliente = listaClientes.get(identificacion);
            cliente.modificarPersona(nombre,apellidos,correoElectronico,telefono,empleadoActual);
            return true;
        }
        else
        {
            return false;
        }
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
     * @return true si los datos se han modificado correctamente
     */
    public boolean modificarEmpleado(String identificacion,String nombre, String apellidos, String correoElectronico, String telefono, 
    String empleadoActual,String clave,String rol)
    {
        
        if(getExisteEmpleado(identificacion))
        {
            Empleado empleado = listaEmpleados.get(identificacion);
            empleado.modificarPersona(nombre,apellidos,correoElectronico,telefono);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Graba la lista de clientes en un archivo
     */
    public void escribeArchivoClientes()
    {
      try{
          FileOutputStream file = new FileOutputStream(ruta+ARCHIVOCLIENTES);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(listaClientes);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }      
    }
    
    /**
     * Graba la lista de empleados en un archivo
     */
    public void escribeArchivoEmpleados()
    {
      try{
          FileOutputStream file = new FileOutputStream(ruta+ARCHIVOEMPLEADOS);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(listaEmpleados);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }      
    }
    
    /**
     * Lee la lista de clientes de un archivo
     */
    public void leeArchivoClientes()
    {
      File fichero = new File(ruta+ARCHIVOCLIENTES);  
      if(fichero.exists())
      {
          try{
              FileInputStream file = new FileInputStream(fichero);
              ObjectInputStream oos = new ObjectInputStream(file);
              listaClientes = (HashMap<String,Cliente>) oos.readObject();
              oos.close();
             }catch(Exception e){
                 e.printStackTrace();
             }
      }
      else
      {
          try{
              fichero.createNewFile();
              escribeArchivoClientes();
            }catch(Exception e){
                e.printStackTrace();
            }
          leeArchivoClientes();
      }                   
    }
    
    /**
     * Lee la lista de empleados de un archivo
     */
    public void leeArchivoEmpleados()
    {
      File fichero = new File(ruta+ARCHIVOEMPLEADOS);
      if(fichero.exists())
      {
      try{
          FileInputStream file = new FileInputStream(fichero);
          ObjectInputStream oos = new ObjectInputStream(file);
          listaEmpleados = (HashMap<String,Empleado>) oos.readObject();
          oos.close();
         }catch(Exception e){
             e.printStackTrace();
         }
      }
      else
      {
          try{
              fichero.createNewFile();
              escribeArchivoEmpleados();
             }catch(Exception e){
                 e.printStackTrace();
             }
          leeArchivoEmpleados();
      }
            
    }
    
}
