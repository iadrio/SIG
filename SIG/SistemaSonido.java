
/**
 * Define las características de un sistema de sonido
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.22
 */
public class SistemaSonido extends Sonido
{
    private String potencia;
    private String formatos;

    /**
     * Crea productos tipo sistema de sonido
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param potencia potencia de sonido
     * @param formato formatos reproducibles 
     */
    public SistemaSonido(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String potencia, String formato)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.potencia=potencia;
        this.formatos=formato;
        setTipo("Sistema de sonido");
    }
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea("formatos admitidos "+formatos,60)+potencia+"w de potencia");    
    }
}
