
/**
 * Clase abstracta que define las características de un producto de la sección de sonido
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.21
 */
public abstract class Sonido extends Electrodomestico
{
    private String seccion = "sonido";
        
    /**
     * Crea un producto de la seccion sonido
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     */
    public Sonido(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        setSeccion(seccion);
    }
}
