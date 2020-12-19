
/**
 * Clase abstracta que define las características de un producto de la sección de hogar
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.21
 */ 
public abstract  class Hogar extends Electrodomestico
{
    private String seccion = "hogar";
   
    /**
     * Crea un producto de la seccion hogar
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     */
    public Hogar(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        setSeccion(seccion);
    }
           
}
