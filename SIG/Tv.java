
/**
 * Define las características de un objeto de tipo televisión
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tv extends Imagen
{
    public int tamaño,frecuencia;
    public String resolucion;
    
    /**
     * Crea productos tipo televisión
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param tamaño tamaño de pantalla
     * @param frecuencia frecuencia de refresco
     * @param resolucion resolución de pantalla
     */
    public Tv(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,int tamaño, int frecuencia, String resolucion)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.tamaño=tamaño;
        this.frecuencia=frecuencia;
        this.resolucion=resolucion;
        setTipo("Tv");
    }
    
    /**
     * Devuelve un string con las características del producto
     * @return    características del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea(tamaño+" pulgadas",30)+formatea(resolucion+" de resolucion",30)+formatea(frecuencia+ " Hz de referesco de pantalla",30));    
    }
    
}
