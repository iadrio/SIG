
/**
 * Define las características de un smartphone
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.22
 */
public  class Smartphone extends Informatica
{
    private String subSeccion = "Telefonia";
    private String ram, procesador, almacenamiento,tamañoPantalla,resolucionPantalla,bateria;
        
    /**
     * Crea productos tipo smartphone
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param ram memoria ram del sistema
     * @param almacenamiento memoria disponible
     * @param tamañoPantalla tamaño de pantalla
     * @param resolucionPantalla resolución de pantalla
     * @param procesador modelo de procesador
     * @param bateria tamaño de la bateria
     */
    public Smartphone(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String tamañoPantalla,
    String resolucionPantalla,String ram , String almacenamiento,String procesador,String bateria)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.ram=ram;
        this.procesador=procesador;
        this.almacenamiento=almacenamiento;
        this.tamañoPantalla=tamañoPantalla;
        this.resolucionPantalla=resolucionPantalla;
        this.bateria=bateria;
        setTipo("Smartphone");
    }
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+
        "\n"+formatea("memoria ram: "+ram,30)+formatea("almacenamiento: "+almacenamiento,30)+formatea("procesador: "+procesador,60)+
        "\n"+formatea("tamaño de pantalla: "+tamañoPantalla+"pulgadas",30)+formatea("resolucion de pantalla: "+resolucionPantalla,30)+formatea("bateria: "+bateria,30));    
    }
}
