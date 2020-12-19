
/**
 * Clase abstracta que define las características de un producto de la sección de informática
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.21
 */
public abstract class Informatica extends Electrodomestico
{
    private String seccion = "informática";

    /**
     * Crea un producto de la sección informática
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     */
    public Informatica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        setSeccion(seccion);
    }
}
