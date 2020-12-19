
/**
 * Define objetos tipo camara. Pueden ser camaras fotograficas, de video o ambas.
 * 
 * @author Ivan Adrio Muñiz 
 * @version 2018.04.22
 */
public class Camara extends Imagen
{
    private String resolucionVideo;
    private String pixeles;   
    
    /**
     * Crea productos tipo camara
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param resolucionVideo resolucion de video que es capaz de grabar la camara
     * @param pixeles resolución de la camara fotográfica
     */
    public Camara(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String resolucionVideo,String pixeles)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.resolucionVideo=resolucionVideo;
        this.pixeles=pixeles;
        setTipo("Camara");
    }
    
    /**
     * Ofrece una desripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea(pixeles+"Mpx de camara fotografica",60)+formatea(resolucionVideo+"p. de video",60));
    }
}
