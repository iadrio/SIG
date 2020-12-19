import java.io.*;
import java.util.*;
/**
 * Gestiona todo lo relacionado con las promociones especiales de la tienda.
 * 
 * @author Iván Adrio Muñiz 
 * @version  24.04.2018
 */
public class Comercial
{
    
    private FichaPromocion fichaPromocion;
    private HashMap<Integer,FichaPromocion> almacenPromociones;
    private int numeroFicha;
    private int numeroCorreo;
    private Date fecha;
    private String rutaCorreos;
    private String rutaPromociones;
    private ArrayList<CorreoElectronico> promocionesEnviadas;
    
    /**
     * Crea un almacen de promocionese y fija el archivo en el que se guardará
     * @param ruta archivo en el que se guardará el almacén de promociones
     */
    public Comercial(String ruta)
    {
        numeroFicha = 0;
        numeroCorreo = 0;
        almacenPromociones = new HashMap<Integer,FichaPromocion>();
        this.rutaPromociones=ruta+"/almacenPromociones.txt";
        this.rutaCorreos=ruta+"/correos.txt";
        promocionesEnviadas = new ArrayList<CorreoElectronico>();
    }
    
     /**
     * Crea una ficha de promoción
     * @param idEmpeladoActual DNI del usuario actual
     * @param nombre nombre de la promoción
     * @param descripción descripción de la promoción
     * @return número de ficha de promoción
     */
    public int crearFicha(String idEmpleadoActual,String nombre, String descripcion)
    {
        numeroFicha++;
        fecha=new Date();
        FichaPromocion fichaPromocion = new FichaPromocion(idEmpleadoActual,fecha,numeroFicha,nombre,descripcion);
        almacenPromociones.put(numeroFicha,fichaPromocion);
        return numeroFicha;
    }
    
    /**
     * Busca una ficha de promocion
     * @param numeroFicha número de ficha de promoción
     * @return ficha ficha de promoción
     */
    public FichaPromocion getFichaPromocion(int numeroFicha)
    {
        FichaPromocion ficha;
        ficha = almacenPromociones.get(numeroFicha);
        return ficha;
    }
    
    /**
     * Comprueba si una ficha de promoción existe
     * @param numeroFicha número de ficha de promoción
     * @return true si la ficha existe
     */
    public boolean getExiste(int numeroFicha)
    {
        FichaPromocion ficha;
        ficha = almacenPromociones.get(numeroFicha);
        if(ficha==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Activa o desactiva una promocion
     * @param numeroFicha numero de la ficha de promocion;
     * @param trigger true para activar y false para desactivar
     * @return true si se ha modificado correctamente
     */
    public boolean triggerPromocion(boolean trigger,int numeroFicha)
    {
        if(getExiste(numeroFicha))
        {
            if(trigger)
            {
                getFichaPromocion(numeroFicha).activar();
            }
            else
            {
                getFichaPromocion(numeroFicha).desactivar();
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Añade un producto a la promocion
     * @param numeroFicha numero de la ficha de promocion;
     * @param descuento descuento a aplicar en el producto
     * @param codigoProducto producto que queremos añadir
     * @return true si el producto se añade correctamente
     */
    public boolean añadirProducto(String codigoProducto,int descuento,int numeroFicha)
    {
        if(getExiste(numeroFicha))
        {
            getFichaPromocion(numeroFicha).getListaProductos().put(codigoProducto,descuento);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Retira un producto de la promocion
     * @param numeroFicha numero de la ficha de promocion;
     * @param descuento descuento a aplicar en el producto
     * @param codigoProducto producto que queremos añadir
     * @return true si el producto se retira correctamente
     */
    public boolean quitarProducto(String codigoProducto,int numeroFicha)
    {
        if(getExiste(numeroFicha))
        {
            getFichaPromocion(numeroFicha).getListaProductos().remove(codigoProducto);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Muestra las promociones diponibles
     * @return lista de promocionese disponibles
     */
    public ArrayList<String> getPromociones()
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaPromocion> ficha:almacenPromociones.entrySet())
        {
            lista.add(ficha.getValue().toString());
        }
        
        return lista;
    }
    
    /**
     * Muestra las promociones activas
     * @return lista de promociones activas
     */
    public ArrayList<String> getPromocionesActivas()
    {
        ArrayList<String> lista = new ArrayList<String>();        
        for(HashMap.Entry<Integer,FichaPromocion> ficha:almacenPromociones.entrySet())
        {
            if(ficha.getValue().getActivada())
            {
                lista.add(ficha.getValue().toString());
            }
        } 
        if(lista.size()==0)
        {
            lista.add("No hay promociones activas");
        }
        return lista;
    }
    
    /**
     * Envia una promocion a un cliente
     * @param idCliente identificacion del cliente
     * @param idEmpleado identificacion del empleado
     * @param promocion número de ficha de promocion
     * @param contenido contenido del mensaje
     */
    public boolean enviarPromocion(String idCliente,String idEmpleado,int promocion,ArrayList<String> contenido)
    {
        Date fecha=fechaPromocion(idCliente,promocion);
        Date fechaActual=new Date();
        if(fecha!=null)
        {
            Long plazo = fechaActual.getTime()-fecha.getTime();
            plazo = plazo/(365*24*60*60*1000);
            if(plazo<=1)
            {
                return false;
            }
            else
            {
                numeroCorreo++;
                CorreoElectronico correo = new CorreoElectronico(idCliente,idEmpleado,fechaActual,contenido,promocion,numeroCorreo);
                promocionesEnviadas.add(correo);
                return true;
            }
        }
        else
        {
            numeroCorreo++;
            CorreoElectronico correo = new CorreoElectronico(idCliente,idEmpleado,fechaActual,contenido,promocion,numeroCorreo);
            promocionesEnviadas.add(correo);
            return true;
        }
    }
    
    /**
     * Dada una promoción y un identificador de cliente nos indica la fecha del último contacto por esa promoción
     * @param idCliente identificacion del cliente
     * @param promocion numero de ficha de promocion
     * @return fecha de la última comunicación
     */
    public Date fechaPromocion(String idCliente,int promocion)
    {
        Date fecha=null;
        if(getExiste(promocion)&&!(promocionesEnviadas.isEmpty()))
        {
            for(CorreoElectronico correo:promocionesEnviadas)
            {
                if(correo.getIdCliente().equals(idCliente))
                {
                    if(correo.getPromocion()==promocion)
                    {
                        if(fecha!=null)
                        {
                            if(correo.getFecha().getTime()>=fecha.getTime())
                            {
                                fecha=correo.getFecha();
                            }
                        }
                        else
                        {
                            fecha=correo.getFecha();
                        }
                    }
                }
            }
            return fecha;
        }
        else
        {
            return  null;
        }
    }
    
    /**
     * Busca una ficha de promoción empleando el número de ficha
     * @param numeroFicha numero de ficha de promoción
     * @return ficha de promoción
     */
    public FichaPromocion getFicha(int numeroFicha)
    {
        if(almacenPromociones.get(numeroFicha)!=null)
        {
            return almacenPromociones.get(numeroFicha);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Busca un correo electronico en el almacen de correos
     * @param numeroCorreo numero de correo electronico
     * @return correo electronico
     */
    public ArrayList<String>  getCorreo(int numeroFicha)
    {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("El correo buscado no existe");
        if((numeroFicha-1)<0||(numeroFicha-1)>promocionesEnviadas.size())
        {
            return lista;
        }
        else
        {
            return promocionesEnviadas.get(numeroFicha-1).getContenido();
        }
    }
    
    
    /**
     * Muestra el historial de correos de la tienda
     * @return historial de correos
     */
    public ArrayList<String> historialCorreos()
    {
        ArrayList<String> lista = new ArrayList<String>();
        for(CorreoElectronico correo:promocionesEnviadas)
        {
            lista.add(correo.toString());
        }
        
        if(lista.size()==0)
        {
            lista.add("no hay correos enviados");
        }
        return lista;
    }
    
    /**
     * Describe el descuento aplicado a un producto
     * @param idProducto codigo de producto
     * @param numeroPromocion codigo de promocion a aplicar
     * @return descripcion de la promocion
     */
    public String aplicarPromocion(String idProducto,int numeroPromocion)
    {
        String descuento="";
        FichaPromocion promocion = almacenPromociones.get(numeroPromocion);
        
        if(!(consultaDescuento(idProducto,numeroPromocion)==-1))
        {
            descuento = promocion.getNombre()+" ("+numeroPromocion+") "+" Se aplicará un descuento del  "+
            promocion.getListaProductos().get(idProducto)+"% al producto";
        }

        return descuento;
    }
    
    /**
     * Busca una promocion activa aplicable al producto, de existir varias, devuelve aquella que tiene un mayor descuento
     * @param idProducto codigo de producto
     * @return el número de promoción aplicable a un producto, si existe, y -1 si no existe
     */
    public Integer buscarPromocionAplicable(String idProducto)
    {
        int idPromocion=-1;
        int descuento=0;
        for(HashMap.Entry<Integer,FichaPromocion> promocion: almacenPromociones.entrySet())
        {
            if(promocion.getValue().getActivada())
            {
               if(consultaDescuento(idProducto,promocion.getKey())>descuento)
               {
                   idPromocion=promocion.getKey();
                   descuento=consultaDescuento(idProducto,promocion.getKey());
               }
            }
        }
        
        return idPromocion;
    }
    
    
    /**
     * Comprueba si una promocion se aplica a un producto y devuelve el descuento aplicado
     * @param idProducto codigo de producto que queremos comprobar
     * @param numeroPromocion codigo de promocion que queremos comprobar
     * @return si la promoción es aplicable devuelve el descuento aplicado, en caso de que no sea aplicable devuelve -1
     */
    public int consultaDescuento(String idProducto, int numeroPromocion)
    {
        if(getExiste(numeroPromocion))
        {
            if(!(almacenPromociones.get(numeroPromocion).getListaProductos().get(idProducto)==null))
            {
                return (almacenPromociones.get(numeroPromocion).getListaProductos().get(idProducto));
            }
            else
            {
                return -1;
            }
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * Guarda el historial de promociones en un archivo
     */
    public void escribeArchivoCorreos()
    {
      try{
          FileOutputStream file = new FileOutputStream(rutaCorreos);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(promocionesEnviadas);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }
            
    }
    
    /**
     * Lee el historial de promociones guardado en un archivo
     */
    public void leeArchivoCorreos()
    {
      File fichero = new File(rutaCorreos);
      if(fichero.exists())
      {
          try{
              FileInputStream file = new FileInputStream(fichero);
              ObjectInputStream oos = new ObjectInputStream(file);
              promocionesEnviadas = (ArrayList<CorreoElectronico>) oos.readObject();
              oos.close();
             }catch(Exception e){
                 e.printStackTrace();
             }
      }
      else
      {
          try{
          fichero.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
          escribeArchivoCorreos();
          leeArchivoCorreos();
      }
    }
    
    /**
     * Guarda el historial de promociones en un archivo
     */
    public void escribeArchivoPromociones()
    {
      try{
          FileOutputStream file = new FileOutputStream(rutaPromociones);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(almacenPromociones);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }
            
    }
    
    /**
     * Lee el historial de promocionees guardado en un archivo
     */
    public void leeArchivoPromociones()
    {
      File fichero = new File(rutaPromociones);
      if(fichero.exists())
      {
          try{
              FileInputStream file = new FileInputStream(fichero);
              ObjectInputStream oos = new ObjectInputStream(file);
              almacenPromociones = (HashMap<Integer,FichaPromocion>) oos.readObject();
              oos.close();
             }catch(Exception e){
                 e.printStackTrace();
             }
      }
      else
      {
          try{
          fichero.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
          escribeArchivoPromociones();
          leeArchivoPromociones();
      }
    }    
}
