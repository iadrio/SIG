
/**
 * Define las caracteristicas de un reproductor de música
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.22
 */
public class ReproductorMusica extends Sonido
{
    private String formatos;

    /**
     * Crea productos tipo reproductor de musica
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param formatos formatos reproducibles 
     */
    public ReproductorMusica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String formatos)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.formatos=formatos;
        setTipo("Reproductor de musica");
    }
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+"formatos: "+formatos);    
    }
}
