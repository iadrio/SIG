
/**
 * Define las características de una lavadora
 * 
 * @author Iván Adrio Muñiz
 * @version 22.04.2018
 */
public class Lavadora extends GranElectrodomestico
{
    private String capacidad, tipoCarga,revoluciones;
    
    /**
     * Crea productos tipo lavadora
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param etiquetaEnergetica nivel de consumo eléctrico
     * @param capacidad volumen de carga
     * @param tipoCarga carga frotal o superior
     * @param revoluciones revoluciones de centrifugado
     */
    public Lavadora(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String etiquetaEnergetica,String capacidad,
    String tipoCarga,String revoluciones)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica);
        this.capacidad=capacidad;
        this.tipoCarga=tipoCarga;
        this.revoluciones=revoluciones;
        setTipo("lavadora");
    }
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea("tipo de carga: "+tipoCarga,30)+formatea("capacidad: "+capacidad+"kg",30)+" centrifugado"+formatea(revoluciones+"rpm",12)+"etiqueta energética: "+getEtiquetaEnergetica());    
    }
}
