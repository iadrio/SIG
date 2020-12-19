
/**
 * Define las características de una nevera
 * 
 * @author Iván Adrio Muñiz 
 * @version 22.04.2018
 */
public class Nevera extends GranElectrodomestico
{
    private String alto, ancho, volumen;
    
    /**
     * Crea productos tipo nevera
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
    public Nevera(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String alto,
    String ancho,String volumen)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica);
        this.alto=alto;
        this.ancho=ancho;
        this.volumen=volumen;
        setTipo("nevera");
    }
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea("altura: "+alto+"cm",30)+formatea(" anchura: "+ancho+"cm",30)+formatea("volumen: "+volumen+"litros",30)+
        " etiqueta energética: "+getEtiquetaEnergetica());    
    }
}
