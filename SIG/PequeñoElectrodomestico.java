/**
 * Define las características de un electrodoméstico pequeño
 * 
 * @author: Iván Adrio Muñiz
 * Date: 22.04.2018
 */
public class PequeñoElectrodomestico extends Hogar
{
    private String subSeccion = "Pequeño electrodoméstico";
    private String tipo;
    private String descripcion;
        
    /**
     * Crea un producto de la seccion pequeño electrodomestico
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param tipo tipo de electrodomestico
     * @param descripcion descripcion del producto
     */
    public PequeñoElectrodomestico(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String tipo,String descripcion)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.tipo=tipo;
        this.descripcion=descripcion;
        setTipo("pequeño electrodomestico");
    }   
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+"descripcion: "+descripcion);    
    }
}
