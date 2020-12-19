import java.util.*;
import java.lang.Math;
import java.text.SimpleDateFormat;
import java.io.*;
/**
 * Gestiona todo lo relacionado con las devoluciones. Crea una ficha de devolucion, añade productos a ella, almacena las fichas de 
 * devolucion en el historial de devoluciones, etc.
 * 
 * @author Ivan Adrio Muñiz 
 * @version 18.04.2018
 */
public class PosVenta
{
    // instance variables - replace the example below with your own
    private HashMap<Integer,FichaDeDevolucion> historialDevoluciones;
    private Date fechaActual;
    private int numeroFicha;
    private SimpleDateFormat formateaFecha;
    private String ruta;
    /**
     * Crea un historial de devoluciones e inicializa las variables. Fija la ruta en la que se guardará el historial de devoluciones.
     * @param formato formato empleado para las fechas
     * @param ruta archivo en el que se guardará el historial de ventas
     */
    public PosVenta(SimpleDateFormat formato,String ruta)
    {
        historialDevoluciones = new HashMap<Integer,FichaDeDevolucion>();
        fechaActual=null;
        numeroFicha=0;
        formateaFecha=formato;
        this.ruta=ruta+"/historialDevoluciones.txt";
    }
    
    /**
     * Busca una ficha de devolución en el historial segun su número de ficha
     * @param numeroFicha número de ficha de devolución
     * @return objeto tipo ficha de devolución
     */
    public FichaDeDevolucion getFicha(int numeroFicha)
    {
        return historialDevoluciones.get(numeroFicha);
    }
    
    /**
     * Crea una ficha de devolución 
     * @param numeroFichaCompra número de la ficha de compra asociada a la devolución
     * @param motivo razón por la que el cliente quiere hacer la devolución
     * @param idUsuarioActual DNI del usuario actual
     * @param idCliente DNI del cliente
     * @return número de ficha de devolución creada
     */
    public int crearFicha(int numeroFichaCompra,String motivo,String idUsuarioActual,String idCliente)
    {
        fechaActual = new Date();
        numeroFicha++;
        FichaDeDevolucion fichaDeDevolucion = new FichaDeDevolucion(idCliente,idUsuarioActual,fechaActual,numeroFicha,numeroFichaCompra,motivo);
        historialDevoluciones.put(fichaDeDevolucion.getNumeroDocumento(),fichaDeDevolucion);
        return numeroFicha;
    }

    //devuelve el conjunto de fichas de devolucion asociadas a una ficha de compra
    /**
     * Devuelve el conjunto de fichas de devolución asociadas a una ficha de compra
     * @param numeroFichaCompra numero de ficha de compra
     * @return lista de fichas de devolución asociadas a una ficha de compra
     */
    public HashSet<FichaDeDevolucion> getDevolucionesPorFichaDeCompra(int numeroFichaCompra)
    {
        HashSet<FichaDeDevolucion> devoluciones = new HashSet<FichaDeDevolucion>();
        
        for(HashMap.Entry<Integer,FichaDeDevolucion> entrada: historialDevoluciones.entrySet())
        {
            if(entrada.getValue().getNumeroFichaDeCompra()==numeroFichaCompra){devoluciones.add(entrada.getValue());}
        }
        
        return devoluciones;
    }    
    
    /**
     * Devuelve los números de las fichas de devolución asociadas a una ficha de compra
     * @param numeroFichaCompra número de ficha de compra
     * @return lista de números de devolución asociados a una ficha de compra
     */
    public ArrayList<Integer> getDevolucionesPorFichaDeCompraNum(int numeroFichaCompra)
    {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        for(HashMap.Entry<Integer,FichaDeDevolucion> entrada: historialDevoluciones.entrySet())
        {
            if(entrada.getValue().getNumeroFichaDeCompra()==numeroFichaCompra){lista.add(entrada.getKey());}
        }
        
        return lista;
    }    

    /**
     * Comprueba que en la ficha de compra queden productos sin devolver suficientes dado un idProducto, el número de ficha de compra 
     * y la cantidad a devolver, precondición de que el producto exista.
     * @param numeroFichaCompra número de ficha de compra
     * @param idProducto codigo de producto
     * @param cantidad unidades del producto
     * @param productosComprados número de productos que el cliente ha comprado anteriormente
     * @return true si quedan productos suficientes en la ficha de compra
     */  
    public boolean getQuedanProductos(int numeroFichaCompra,String idProducto,int cantidad,int productosComprados)
    {
        HashSet<FichaDeDevolucion> devoluciones = getDevolucionesPorFichaDeCompra(numeroFichaCompra);
        int devueltos=0;
        
        for(FichaDeDevolucion ficha: devoluciones)
        {
            if(ficha.getExisteProducto(idProducto)){devueltos = devueltos+ficha.getListaProductos().get(idProducto);}
        }
        
        if(productosComprados-devueltos>=cantidad)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Añade un producto a la ficha de devolución. 
     * @param numeroFicha número de ficha de devolución a la que queremos añadir el producto 
     * @param idProducto código del producto
     * @param cantidad número de unidades del producto
     * @param productosComprados unidades del producto que el cliente había comprado
     * @param numeroFichaDeCompra número de ficha de compra
     * @return true si el producto se añade correctamente
     */
    public boolean añadirProducto(int numeroFicha,String idProducto, int cantidad,int productosComprados,int numeroFichaDeCompra)
    {      
        if(getQuedanProductos(numeroFichaDeCompra,idProducto,cantidad,productosComprados))
        {
            historialDevoluciones.get(numeroFicha).añadirProducto(idProducto,cantidad);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Cancela una devolución
     * @param numeroDeFicha número de ficha de devolución
     */
    public void cancelarDevolucion(int numeroDeFicha)
    {
        if(getExisteFicha(numeroDeFicha))
        {
            HashMap<String,Integer> listaProductos = historialDevoluciones.get(numeroDeFicha).getListaProductos();
            for(HashMap.Entry<String,Integer> entrada:listaProductos.entrySet())
            {
               quitarProducto(numeroDeFicha,entrada.getKey(),entrada.getValue(),true);
            }
            historialDevoluciones.remove(numeroDeFicha);
            numeroFicha--;
        }
    }
    
    /**
     * Comprueba si una ficha de devolución existe
     * @param numeroDeFicha número de ficha de devolución
     * @return true si la ficha existe
     */
    public boolean getExisteFicha(int numeroDeFicha)
    {
        if(historialDevoluciones.get(numeroDeFicha)==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Quita un producto añadido a una ficha de devolución
     * @param numeroFichaDevolucion número de ficha de devolución
     * @param idProducto código de producto
     * @param cantidad unidades del producto que queremos retirar de la ficha
     * @param existeProducto confirmación de que el producto que queremos quitar existe en la ficha
     */
    public boolean quitarProducto(int numeroFichaDevolucion,String idProducto, int cantidad,boolean existeProducto)
    {
        if(existeProducto)
        {
            if(historialDevoluciones.get(numeroFichaDevolucion).getCantidadProducto(idProducto)>=cantidad)
            {
                historialDevoluciones.get(numeroFichaDevolucion).quitarProducto(idProducto,cantidad);
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
     * Historial de devoluciones de un cliente
     * @param idCliente DNI del cliente
     * @return historial de devolucionese de un cliente
     */
    public ArrayList<String> getHistorialCliente(String idCliente)
    {
        ArrayList<String> historial = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaDeDevolucion> entrada:historialDevoluciones.entrySet())
        {
            if(entrada.getValue().getIdCliente().equals(idCliente))
            {
                historial.add(entrada.getValue().toString());
            }
        }
        
        return historial;
    }
    
    /**
     * Devuelve el historial completo de devoluciones de la tienda
     * @return historial de devoluciones de la tienda
     */
    public ArrayList<String> getHistorialCompleto()
    {
        ArrayList<String> historial = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaDeDevolucion> entrada: historialDevoluciones.entrySet())
        {
            historial.add(entrada.getValue().toString());
        }
        
        return historial;
    }
    
    /**
     * Busca una ficha de devolución utilizando el número de ficha
     * @param idFicha número de ficha de devolución
     * @return objeto tipo ficha de devolución
     */
    public FichaDeDevolucion getFichaDeDevolucion(int idFicha)
    {
        return historialDevoluciones.get(idFicha);
    }
    
    /**
     * Guarda el historial de devoluciones en un archivo
     */
    public void escribeArchivo()
    {
      try{
          FileOutputStream file = new FileOutputStream(ruta);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(historialDevoluciones);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }
            
    }
    
    /**
     * Lee el historial de devoluciones guardado en un archivo
     */
    public void leeArchivo()
    {
      File fichero = new File(ruta);
      if(fichero.exists())
      {
          try{
              FileInputStream file = new FileInputStream(fichero);
              ObjectInputStream oos = new ObjectInputStream(file);
              historialDevoluciones = (HashMap<Integer,FichaDeDevolucion>) oos.readObject();
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
          escribeArchivo();
          leeArchivo();
      }
    }
    
    
}
