
/**
 * Define las características de una vitrocerámica
 * 
 * @author Iván Adrio Muñiz 
 * @version 22.04.2018
 */
public class Vitroceramica extends GranElectrodomestico
{
    private String tipo, fuegos,potencia;

    /**
     * Crea productos tipo vitroceramica
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
    public Vitroceramica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String tipo,
    String fuegos,String potencia)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica);
        this.tipo=tipo;
        this.fuegos=fuegos;
        this.potencia=potencia;
        setTipo("vitroceramica");
    }
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea("nº fuegos: "+fuegos,30)+formatea("potencia: "+potencia+"w",30)+" etiqueta energética: "+getEtiquetaEnergetica());    
    }

    
}
