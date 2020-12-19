import java.util.*;
import java.io.*;

/**
 * Almacena y gestiona todo lo relativo a los productos. 
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.18
 */
public class Almacen
{
    
    private HashMap<String,Electrodomestico>  almacen;
    private final int longitudCp = 4; //Longitud que debe tener un codigo de producto
    private String ruta;

    /**
     * Crea un objeto almacén y establece la ruta en la que se guardará el objeto
     */
    public Almacen(String ruta)
    {
      almacen=new HashMap<String,Electrodomestico>();
      this.ruta=ruta+"/almacen.txt";
    }
    
    /**
     * Crea una Tv y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param tamaño tamaño de pantalla
     * @param frecuencia frecuencia de refresco
     * @param resolucion resolución de pantalla
     * @return true si el producto se añade correctamente
     */
    public boolean añadirTv(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,int tamaño, int frecuencia, String resolucion)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Tv(codigoDeProducto,marca,modelo,color,precio,cantidad,tamaño,frecuencia,resolucion);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea un reproductor y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param formato formatos reproducibles
     * @return true si el producto se añade correctamente
     */
    public boolean añadirReproductor(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String formato)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Reproductor(codigoDeProducto,marca,modelo,color,precio,cantidad,formato);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea una camara y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param resolucionVideo resolucion de video que es capaz de grabar la camara
     * @param pixeles resolución de la camara fotográfica
     * @return true si el producto se añade correctamente
     */
    public boolean añadirCamara(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String resolucionVideo,String pixeles)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Camara(codigoDeProducto,marca,modelo,color,precio,cantidad,resolucionVideo,pixeles);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea un objeto altavoces y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param potencia potencia de sonido
     * @return true si el producto se añade correctamente
     */
    public boolean añadirAltavoces(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String potencia)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Altavoces(codigoDeProducto,marca,modelo,color,precio,cantidad,potencia);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea un sistema de sonido y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param potencia potencia de sonido
     * @param formato formatos reproducibles
     * @return true si el producto se añade correctamente
     */
    public boolean añadirSistemaSonido(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String potencia, String formato)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new SistemaSonido(codigoDeProducto,marca,modelo,color,precio,cantidad,potencia,formato);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea un reproductorDeMusica y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param formatos formatos reproduciblesorrectamente
     */
    public boolean añadirReproductorMusica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String formatos)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new ReproductorMusica(codigoDeProducto,marca,modelo,color,precio,cantidad,formatos);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * Crea un ordenador y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param ram memoria ram del sistema
     * @param discoDuro memoria disponible
     * @param procesador modelo de procesador
     * @param tarjetaGrafica modelo de tarjeta grafica
     * @param bateria tamaño de la bateria
     * @param tipo tipo de ordenador (portatil, sobremesa, servidor...)
     */
    public boolean añadirOrdenador(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String ram , String discoDuro,String procesador,String tarjetaGrafica,String bateria,String tipo)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Ordenador(codigoDeProducto,marca,modelo,color,precio,cantidad,ram,discoDuro,procesador,tarjetaGrafica,bateria,tipo);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea un smartphone y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param ram memoria ram del sistema
     * @param almacenamiento memoria disponible
     * @param tamañoPantalla tamaño de pantalla
     * @param resolucionPantalla resolución de pantalla
     * @param procesador modelo de procesador
     * @param bateria tamaño de la bateria
     */
    public boolean añadirSmartphone(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String tamañoPantalla,String resolucionPantalla,String ram , String almacenamiento,
    String procesador,String bateria)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Smartphone(codigoDeProducto,marca,modelo,color,precio,cantidad,tamañoPantalla,resolucionPantalla,ram,almacenamiento,procesador,bateria);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea un pequeño electrodoméstico y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param tipo tipo de electrodomestico
     * @param descripcion descripcion del producto
     */
    public boolean añadirPequeñoElectrodomestico(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String tipo,String descripcion)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new PequeñoElectrodomestico(codigoDeProducto,marca,modelo,color,precio,cantidad,tipo,descripcion);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea una vitroceramica y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param potencia potencia eléctrica de la vitrocerámica 
     * @param tipo inducción o vitro
     * @param fuegos número de puntos de calor
     * @param etiquetaEnergetica nivel de consumo eléctrico
     */
    public boolean añadirVitroceramica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String tipo,String fuegos,String potencia)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Vitroceramica(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica,tipo,fuegos,potencia);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea una lavadora y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param etiquetaEnergetica nivel de consumo eléctrico
     * @param capacidad volumen de carga
     * @param tipoCarga carga frotal o superior
     * @param revoluciones revoluciones de centrifugado
     */
    public boolean añadirLavadora(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String capacidad,String tipoCarga,String revoluciones)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Lavadora(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica,capacidad,tipoCarga,revoluciones);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea una horno y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param etiquetaEnergetica nivel de consumo eléctrico
     * @param capacidad volumen interior útil
     * @param potencia potencia del horno
     */
    public boolean añadirHorno(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String capacidad,String potencia)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Horno(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica,capacidad,potencia);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Crea una nevera y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param etiquetaEnergetica nivel de consumo eléctrico
     * @param alto altura de la nevera
     * @param ancho anchura de la nevera
     * @param volumen volumen interior útil
     */
    public boolean añadirNevera(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String alto,String ancho,String volumen)
    {
        if(!getExiste(codigoDeProducto)){
            Electrodomestico producto = new Nevera(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica,alto,ancho,volumen);
            almacen.put(codigoDeProducto,producto);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Busca un producto en el almacén usando el codigo de producto
     * @param codigoProducto codigo de producto
     * @return un objeto tipo Electrodomestico
     */
    public Electrodomestico getProductoPorCp(String codigoProducto)
    {
        return almacen.get(codigoProducto);     
    }
    
    /**
     * Comprueba si un producto existe en el almacen
     * @param cp codigo de producto
     * @return true si el producto existe
     */
    public boolean getExiste(String cp)
    {
        boolean exists;

        if(getProductoPorCp(cp)==null){
            exists=false;
        }
        else{
            exists=true;
        }
        return exists;        
    }
    
    /**
     * Comprueba si un string tiene formato valido para ser un codigo de producto
     * @param cp codigo de producto
     * @return true si el formato es valido
     */
    public boolean getCpValido(String cp)
    {
        if(cp.length()!=longitudCp)
        {
            return false;
        }
        else
        {
            return true;
        }  
    }
    
    /**
     * Indica la longitud que debe tener un codigo de producto
     * @return longitud del codigo de producto
     */
    public int getLongitudCodigoProducto()
    {
        return longitudCp;
    }
    
    /**
     * Elimina un producto del almacen
     * @param cp codigo de producto
     * @return true si el producto se ha eliminado correctamente
     */
    public boolean eliminaProducto(String cp)
    {
        if(getExiste(cp))
        {
            almacen.remove(cp);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Devuelve una lista de los productos almacenados en el almacen
     * @return lista de productos
     */
    public ArrayList<String> getListaDeProductos()
    {    
        ArrayList<String> lista = new ArrayList<String>();
        
        for(HashMap.Entry<String,Electrodomestico> producto:almacen.entrySet())
        {
            lista.add(((Electrodomestico)producto.getValue()).toString()+"\n"+"unidades en stock:"+producto.getValue().formatea(Integer.toString(producto.getValue().getCantidad()),3));
        }
        
        return lista;
    }
    
    /**
     * cuenta el número de artículos en el almacen
     * @return numero de articulos en el almacen
     */
    public int getContarArticulos()
    {
        int cantidadNueva=0;
        Set<Map.Entry<String,Electrodomestico>> entradas = almacen.entrySet();
        for(Map.Entry<String,Electrodomestico> entrada:entradas)
        {
            Electrodomestico producto = entrada.getValue();
            cantidadNueva = cantidadNueva + producto.getCantidad();
        }
        
        return cantidadNueva;
    }
    
    /**
     * Describe un producto
     * @param cp codigo de productos
     * @return descripcion del producto
     */
    public String getDescribeProducto(String cp)
    {
        if(getExiste(cp)){
            Electrodomestico producto = getProductoPorCp(cp);
            return producto.toString()+" unidades en stock:"+producto.formatea(Integer.toString(producto.getCantidad()),3);
        }else{
            return ("El producto no existe");
        }
    }
    
    /**
     * Describe un producto brevemente
     * @param cp codigo de productos
     * @return descripcion del producto
     */
    public String getDescripcionCorta(String cp)
    {
        if(getExiste(cp)){
            Electrodomestico producto = getProductoPorCp(cp);
            return (producto.toShortString());
        }else{
            return ("El producto no existe");
        }
    }
    
    /**
     * Incrementa el numero de unidades de un producto
     * @param cp codigo de producto
     * @param cantidad numero de articulos
     * @return true si la operacion se completa correctamente
     */
    public boolean setRecepcionDePedidos(String cp, int cantidad)
    {    
        if(getExiste(cp)==true){
            
            if(cantidad>=0){
                Electrodomestico producto=almacen.get(cp);
                producto.setCantidad(producto.getCantidad()+cantidad);
                return true;
            }else{
                return false;
            }     
             
        }else{
            return false;        
        }          
    }    
    
    /**
     * Disminuye el numero de articulos en una determinada cantidad
     * @param cp codigo de producto
     * @param cantidad numero de articulos a restar
     */
    public void setQuitaArticulos(String cp,int cantidad)
    {
        
        if(getExiste(cp)==true)
        {
            Electrodomestico producto=almacen.get(cp);
            int cantidadActual=producto.getCantidad();
            cantidadActual=cantidadActual-cantidad;
            producto.setCantidad(cantidadActual);
        }
    } 
    
    /**
     * Comprueba si para un articulo existen al menos un determinado numero de unidades
     * @param idProducto codigo de producto
     * @param cantidad numero de unidades del producto
     * @return true si existen unidades suficientes
     */
    public boolean comprobarStockSuficiente(String idProducto,int cantidad)
    {
        Electrodomestico producto = getProductoPorCp(idProducto);
        if(getExiste(idProducto))
        {
            if(producto.getCantidad()>=cantidad)
            {
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
     * Guarda los datos en un archivo
     */
    public void escribeArchivo()
    {
      try{
          FileOutputStream file = new FileOutputStream(ruta);
          ObjectOutputStream oos = new ObjectOutputStream(file);
          oos.writeObject(almacen);
          oos.close();
         }catch(IOException e){
             e.printStackTrace();
         }
            
    }
    
    /**
     * Lee los datos de un archivo
     */
    public void leeArchivo()
    {
      File fichero = new File(ruta);
      if(fichero.exists())
      {
          try{
              FileInputStream file = new FileInputStream(fichero);
              ObjectInputStream oos = new ObjectInputStream(file);
              almacen = (HashMap<String,Electrodomestico>) oos.readObject();
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
