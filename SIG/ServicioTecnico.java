import java.io.*;
import java.util.*;
/**
 * Gestiona todo lo relacionado con el servicio técnico de la tienda. Contiene métodos para crear una ficha de reparación, añadir
 * comentarios y trabajo realizado, realizar comunicaciones con el cliente, calcular presupuesto, añadir piezas necesarias a la lista...
 * 
 * @author Iván Adrio Muñiz 
 * @version  18.04.2018
 */
public class ServicioTecnico
{
    private FichaReparacion fichareparacion;
    private HashMap<Integer,FichaReparacion> historialReparaciones;
    private int numeroFicha;
    private Date fecha;
    private String ruta;

    /**
     * Crea un historial de reparaciones y fija el archivo en el que se guardará
     * @param ruta archivo en el que se guardará el historial de reparaciones
     */
    public ServicioTecnico(String ruta)
    {
        numeroFicha = 0;
        historialReparaciones = new HashMap<Integer,FichaReparacion>();
        this.ruta=ruta+"/historialReparaciones.txt";
    }        
    
    /**
     * Crea una ficha de reparación
     * @param numeroFichaDeCompra numero de ficha de compra
     * @param idCliente DNI del cliente
     * @param idProducto código de producto
     * @param idEmpeladoActual DNI del usuario actual
     * @return número de ficha de reparación
     */
    public int crearFicha(int numeroFichaDeCompra,String idCliente, String idProducto,String idEmpleadoActual)
    {
        numeroFicha++;
        fecha=new Date();
        FichaReparacion fichaReparacion = new FichaReparacion(idProducto,idEmpleadoActual,numeroFichaDeCompra,idCliente,numeroFicha,fecha);
        historialReparaciones.put(numeroFicha,fichaReparacion);
        return numeroFicha;
    }
    
    /**
     * Busca una ficha de reparación usando el número de ficha
     * @param numeroFicha número de ficha de reparación
     * @return objeto clase ficha de reparación
     */
    public FichaReparacion getFicha(int numeroFicha)
    {
        if(historialReparaciones.get(numeroFicha)!=null)
        {
            return historialReparaciones.get(numeroFicha);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Devuelve el historial de reparaciones de la tienda
     * @param numeroFicha numero de ficha de compra
     * @return fichas de reparacion asignadas a una ficha de compra
     */
    public ArrayList<Integer> getReparacionesPorFichaDeCompra(int numeroFicha)
    {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            if(ficha.getValue().getNumeroFichaDeCompra()==numeroFicha){
                lista.add(ficha.getKey());
            }
        }
        
        return lista;
    }
    
    /**
     * Asigna una ficha de reparación a un técnico
     * @param idTecnico DNI el empleado al que se asignará la ficha
     * @param numeroFicha numero de ficha de reparación
     */
    public void asignarTecnico(String idTecnico,int numeroFicha)
    {
        getFicha(numeroFicha).setEmpleado(idTecnico);
    }
    
    /**
     * Devuelve el historial de reparaciones de la tienda
     * @return historial de reparaciones
     */
    public ArrayList<String> getHistorialReparaciones()
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            lista.add(ficha.getValue().toString());
        }
        
        return lista;
    }
    
    /**
     * Devuelve el historial de reparaciones relacionado con un cliente
     * @param idCliente DNI del cliente
     * @return historial de reparaciones relacionado con un cliente
     */
    public ArrayList<String> getHistorialReparacionesCliente(String idCliente)
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            if(ficha.getValue().getIdCliente().equals(idCliente))
            {
                lista.add(ficha.getValue().toString());
            }
        }
        
        return lista;
    }
    
    /**
     * Busca las ficha de reparación asignadas a un determinado usuario
     * @param idEmpleado DNI del usuario
     * @return lista de fichas de reparaci´ón asignadas a un usuario
     */
    public ArrayList<String>  getFichasAsignadas(String idEmpleado)
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            if(ficha.getValue().getIdEmpleado().equals(idEmpleado)&&ficha.getValue().getEstado().equals("pendiente"))
            {
                lista.add(ficha.getValue().toString());
            }
        }
        
        return lista;
    }
    
    /**
     * Busca todas las fichas de reparación relacionadas con un producto y una ficha de compra
     * @param idProducto código de producto
     * @param fichaCompra numero de ficha de compra
     * @return lista de fichas de reparación asociadas a un producto y una ficha de compra
     */
    public ArrayList<String>  getHistorialElectrodomestico(String idProducto, int fichaCompra)
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            if(ficha.getValue().getNumeroFichaDeCompra()==fichaCompra)
            {
                if(ficha.getValue().getExisteProducto(idProducto))
                {
                    lista.add(ficha.getValue().toString());
                }
            }
        }
        
        return lista;
    }
    
    /**
     * Busca todas las fichas de reparación en estado pendiente
     * @return lista de fichas de reparación pendientes
     */
    public ArrayList<String> getReparacionesPendientes()
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            if(ficha.getValue().getEstado().equals("pendiente")){lista.add(ficha.getValue().toString());}
        }
        
        return lista;
    }
    
    /**
     * Busca todas las piezas necesarias para una reparación en estado pendiente
     * @return lista de piezas necesarias pendientes
     */
    public ArrayList<String> getPiezasPendientes()
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            for(String[] pieza:ficha.getValue().getListaPiezas())
            {
                if(pieza[5].equals("pendiente"))
                {
                    lista.add(ficha.getValue().piezaToString(pieza));
                }
            }
        }
        
        return lista;
    }
    
    /**
     * Busca todas las fichas de reparacion que ha gestionado un empleado
     * @param idEmpleado DNI del empleado
     * @return lista de fichas de reparación que ha gestionado un empleado
     */
    public ArrayList<String> getHistorialReparacionesEmpleado(String idEmpleado)
    {
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<Integer,FichaReparacion> ficha:historialReparaciones.entrySet())
        {
            if(ficha.getValue().getIdEmpleado().equals(idEmpleado)){lista.add(ficha.getValue().toString());}
        }
        
        return lista;
    }
    
    /**
     * Comprueba si una ficha de reparación existe
     * @param numeroFicha numero de ficha de reparación
     * @return true si la ficha existe
     */
    public boolean getExisteFicha(int numeroFicha)
    {
        if(historialReparaciones.get(numeroFicha)==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Calcula un presupuesto basandose en las horas de mano de obra y las piezas anotadas en la ficha de reparación
     * @param numeroFicha número de ficha de reparacion
     * @param precioManoObra precio de la mano de obra
     * @param fechaDeCompra fecha en la que se ha realizado la compra
     */
    public void calcularPresupuesto(int numeroFicha,Date fechaDeCompra, double precioManoObra)
    {
        double costePiezas = 0;
        double costeManoObra = 0;
        Date fechaCompra = fechaDeCompra;
        Date fechaReclamacion = historialReparaciones.get(numeroFicha).getFecha();
        double plazo = fechaReclamacion.getTime()-fechaCompra.getTime();
        plazo = plazo/(365*24*60*60*1000);
        if(plazo>2)
        {
            costeManoObra = historialReparaciones.get(numeroFicha).getHorasTrabajadas()*precioManoObra;
            for(String[] pieza:historialReparaciones.get(numeroFicha).getListaPiezas())
            {
                costePiezas = costePiezas + Double.parseDouble(pieza[2]);
            }
            historialReparaciones.get(numeroFicha).setPresupuesto(costeManoObra+costePiezas);
        }
        else
        {
            historialReparaciones.get(numeroFicha).setPresupuesto(0);
        }
    }
    
    /**
     * Devuelve el número de la ultima ficha de reparación abierta
     * @return número de la ultima ficha de reparación abierta
     */
    public int getUltimoNumeroFicha()
    {
        return numeroFicha;
    }
    
    /**
     * Guarda el historial de reparaciones en un archivo
     */
    public void escribeArchivo()
    {
      try{
          FileOutputStream file = new FileOutputStream(ruta);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(historialReparaciones);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }
            
    }
    
    /**
     * Lee el historial de reparaciones guardado previamente en un archivo
     */
    public void leeArchivo()
    {
      File fichero = new File(ruta);
      if(fichero.exists())
      {
          try{
              FileInputStream file = new FileInputStream(fichero);
              ObjectInputStream oos = new ObjectInputStream(file);
              historialReparaciones = (HashMap<Integer,FichaReparacion>) oos.readObject();
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
