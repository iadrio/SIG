import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
/**
 * Gestiona todo lo relacionado con el proceso de compra en la tienda
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.18
 */
public class Caja
{
    // instance variables - replace the example below with your own
    private HashMap<Integer,FichaDeCompra> historialVentas;
    private Date date;
    private int numeroDeFicha;
    private String fecha;
    private SimpleDateFormat formateaFecha;
    private String ruta;

    /**
     * Crea el historial de ventas  y fija la ruta en la que se va a guardar.
     * @param formato formato empleado para las fechas
     * @param ruta archivo en el que se guardará el historial de ventas
     */
    public Caja(SimpleDateFormat formato,String ruta)
    {
       historialVentas = new HashMap<Integer,FichaDeCompra>();
       numeroDeFicha = 0;
       formateaFecha=formato;
       this.ruta=ruta+"/historalVentas.txt";
    }        
    
    /**
     * Devuelve el numero de la ultima ficha de compra
     * @param historialVentas historial de venas de la tienda
     * @return numero de ficha de la ultima ficha creada
     */
    public int getUltimoNumeroFicha(HashMap<Integer,FichaDeCompra> historialVentas)
    {
        int numeroFicha=0;
        for(HashMap.Entry<Integer,FichaDeCompra> ficha: historialVentas.entrySet())
        {
            if(ficha.getKey()>numeroFicha)
            {
                numeroFicha=ficha.getKey();
            }
        }
        return numeroFicha;
    }
    
    /**
     * Crea una ficha de compra y la añade al historial de compras
     * @param idCliente DNI del cliente
     * @param idUsuarioActual DNI del usuario actual
     * @return numero de ficha de compra
     */
    public int crearFichaDeCompra(String idCliente,String idUsuarioActual)
    {
        date = new Date();
        numeroDeFicha++;
        FichaDeCompra fichaDeCompra = new FichaDeCompra(idCliente,idUsuarioActual,date,numeroDeFicha);
        historialVentas.put(numeroDeFicha,fichaDeCompra);
        return numeroDeFicha;
    }    
    
    /**
     * Añade un producto a la ficha de compra
     * @param numeroDeFicha numero de ficha de compra
     * @param idProducto codigo de producto 
     * @param cantidad unidades a añadir
     * @param precio coste del producto
     */
    public void añadirProducto(int numeroDeFicha, String idProducto,int cantidad,double precio)
    {
            historialVentas.get(numeroDeFicha).añadirProducto(idProducto,cantidad);           
            historialVentas.get(numeroDeFicha).setTotal(historialVentas.get(numeroDeFicha).getTotal()+cantidad*precio);
    }
    
    /**
     * Quita un producto de la ficha de compra
     * @param numeroDeFicha numero de ficha de compra
     * @param idProducto codigo de producto 
     * @param cantidad unidades a añadir
     * @param precio coste del producto
     * @return true si se ha retirado el producto correctamente
     */
    public boolean quitarProducto(int numeroDeFicha, String idProducto,int cantidad,double precio)
    {
        if(cantidad<=historialVentas.get(numeroDeFicha).getCantidadProducto(idProducto))
        {
            historialVentas.get(numeroDeFicha).quitarProducto(idProducto,cantidad);
            historialVentas.get(numeroDeFicha).setTotal(historialVentas.get(numeroDeFicha).getTotal()-cantidad*precio);
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    /**
     * Finaliza una compra marcandola con un estado
     * @param numeroDeFicha numero de ficha de compra
     * @param estado estado del producto(pagado, pendiente...)
     */
    public void finalizarCompra(int numeroDeFicha,String estado)
    {
        if(historialVentas.get(numeroDeFicha).getListaProductos().isEmpty())
        {
            historialVentas.remove(numeroDeFicha);
            this.numeroDeFicha--;
        }
        else
        {
            historialVentas.get(numeroDeFicha).setEstado(estado);
        }
    } 
    
    /**
     * Devuelve una lista con todas las ventas realizadas en la tienda
     * @return historial de ventas de la tienda
     */
    public HashMap<Integer,FichaDeCompra> getHistorial()
    {
        return historialVentas;
    }
    
    /**
     * Borra una ficha de compra del historial de ventas
     * @param numeroDeFicha numero de ficha de compra
     */
    public void eliminaFicha(int numeroDeFicha)
    {
        historialVentas.remove(numeroDeFicha);
        this.numeroDeFicha--;
    }
    
    /**
     * Devuelve una lista con el historial de ventas y una descripcion de cada una de las ventas
     * @return historial de ventas con descripcion
     */
    public ArrayList<String> getHistorialVentas()
    {
        ArrayList<String> historial = new ArrayList<String>();
        for(HashMap.Entry<Integer,FichaDeCompra> venta:historialVentas.entrySet())
        {
            historial.add(venta.getValue().toString());
        }
        
        return historial;
    }   
    
    /**
     * Busca una ficha de compra en el historial por el número de ficha
     * @return ficha de compra 
     */
    public FichaDeCompra getFichaPorNumero(int numero)
    {
        if(getExisteFicha(numero))
        {
            return historialVentas.get(numero);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Busca todas las fichas de compra que se encuentran en un determinado estado
     * @param estado estado de la ficha
     * @return lista de fichas de compra con un determinado estado
     */
    public ArrayList<String> getFichasEstado(String estado)
    {
        ArrayList<String> historial = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaDeCompra> venta:historialVentas.entrySet())
        {
            if(venta.getValue().getEstado().equals(estado))
            {
                historial.add(venta.getValue().toString());
            }
        }
        
        return historial;
    }    

    /**
     * Busca todas las fichas que pertenecen a un determinado cliente
     * @param idCliente DNI del cliente
     * @return lista de fichas que pertenecen a un cliente
     */
    public ArrayList<String> getFichasCliente(String idCliente)
    {
        ArrayList<String> historial = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaDeCompra> venta:historialVentas.entrySet())
        {
            if(venta.getValue().getIdCliente().equals(idCliente))
            {
                historial.add(venta.getValue().toString());
            }
        }
        
        return historial;
    }
    
    /**
     * Busca todas las fichas que pertenezcan a un cliente y que tengan un determinado estado
     * @param idCliente DNI del cliente
     * @param estado estado de la ficha de compra
     * @return lista de ficha de compra que pertenecen a un cliente y que tienen un determinado estado
     */
    public ArrayList<String> getFichasClienteEstado(String idCliente,String estado)
    {
        ArrayList<String> historial = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaDeCompra> venta:historialVentas.entrySet())
        {
            if(venta.getValue().getIdCliente().equals(idCliente))
            {
                if(venta.getValue().getEstado().equals(estado))
                {
                    historial.add(venta.getValue().toString());
                }
            }
        }
        
        return historial;
    }
    
    /**
     * Comprueba si una ficha de compra existe
     * @param numeroFicha numero de ficha de compra
     * @return true si la ficha de compra existe
     */
    public boolean getExisteFicha(int numeroFicha)
    {
       if(historialVentas.get(numeroFicha)==null)
       {
           return false;
       }
       else
       {
           return true;
       }
    }   
    
    /**
     * Guarda el historial de ventas en un archivo
     */
    public void escribeArchivo()
    {
      try{
          FileOutputStream file = new FileOutputStream(ruta);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(historialVentas);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }
            
    }
    
    /**
     * Importa el ultimo historial de ventas
     */
    public void leeArchivo()
    {
      File fichero = new File(ruta);
      if(fichero.exists())
      {
          try{
              FileInputStream file = new FileInputStream(fichero);
              ObjectInputStream oos = new ObjectInputStream(file);
              historialVentas = (HashMap<Integer,FichaDeCompra>) oos.readObject();
              oos.close();
              numeroDeFicha = getUltimoNumeroFicha(historialVentas)+1;
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
