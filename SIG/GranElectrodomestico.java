
/**
 * Define las características de un gran electrodoméstico
 * 
 * @author Iván Adrio Muñiz 
 * @version 22.04.2018
 */
public abstract class GranElectrodomestico extends Hogar
{
    private String subSeccion = "GranElectrodomestico";
    private String etiquetaEnergetica;    
    
    /**
     * Crea un producto de la seccion gran electrodomestico
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param etiquetaEnergetica etiqueta energética del producto
     */
    public GranElectrodomestico(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.etiquetaEnergetica=etiquetaEnergetica;
    }
    
    /**
     * Modifica la etiqueta energética del producto
     * @param etiqueta etiqueta energética del producto
     */
    public void setEtiquetaEnergetica(String etiqueta)
    {
        this.etiquetaEnergetica=etiqueta;
    }
    
    
    /**
     * Devuelve el valor de la etiqueta energética
     * @return etiqueta energética
     */
    public String getEtiquetaEnergetica()
    {
        return etiquetaEnergetica;
    }
    
}
