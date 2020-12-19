
/**
 * Define las características de un altavoz
 * 
 * @author Iván Adrio Muñiz  
 * @version 2018.04.22
 */
public  class Altavoces extends Sonido
{
    private String potencia;

    /**
     * Crea productos tipo altavoces
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param potencia potencia de sonido 
     */
    public Altavoces(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String potencia)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.potencia=potencia;
        setTipo("Altavoces");
    }
        
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+potencia+"w de potencia");    
    }
}
