
/**
 * Define las características de un ordenador
 * 
 * @author Iván Adrio Muñiz
 * @version 2018.04.22
 */
public class Ordenador extends Informatica
{
    private String ram, discoDuro,procesador,tarjetaGrafica,bateria,tipo;

    /**
     * Crea productos tipo ordenador
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param ram memoria ram del sistema
     * @param discoDuro memoria disponible
     * @param procesador modelo de procesador
     * @param tarjetaGrafica modelo de tarjeta grafica
     * @param bateria tamaño de la bateria
     * @param tipo tipo de ordenador (portatil, sobremesa, servidor...) 
     */
    public Ordenador(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String ram , String discoDuro,
    String procesador,String tarjetaGrafica,String bateria,String tipo)
    {
        super(codigoDeProducto,marca,modelo,color,precio,cantidad);
        this.ram=ram;
        this.discoDuro=discoDuro;
        this.procesador=procesador;
        this.tarjetaGrafica=tarjetaGrafica;
        this.bateria=bateria;
        this.tipo=tipo;
        setTipo("ordenador");
    }   
    
    /**
     * Ofrece una breve descripción del producto
     * @return descripción del producto
     */
    public String toString()
    {
        return(super.toString()+"\n"+formatea("memoria ram: "+ram,30)+formatea("disco duro: "+discoDuro,30)+
        formatea("batería: "+bateria,30)+" procesador: "+procesador);    
    }
}
