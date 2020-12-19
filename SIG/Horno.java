
/**
 * Define las características de un horno
 * 
 * @author Iván Adrio Muñiz 
 * @version 22.04.2018
 */
public class Horno extends GranElectrodomestico
{
    private String capacidad,potencia;
  
    /**
     * Crea productos tipo horno
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
    public Horno(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String capacidad,
    String potencia)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica);
        this.capacidad=capacidad;
        this.potencia=potencia;
        setTipo("horno");
    }
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea("capacidad: "+capacidad,30)+formatea("potencia: "+potencia,30)+" etiqueta energética: "+getEtiquetaEnergetica());    
    }
}
