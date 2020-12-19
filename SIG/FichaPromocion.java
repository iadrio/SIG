import java.io.*;
import java.util.*;
/**
 * Almacena datos relacionados con una promoción
 * 
 * @author Iván Adrio Muñiz 
 * @version 24.04.2018
 */
public class FichaPromocion extends Documentos
{
    private boolean activada;
    protected String nombre;
    protected String descripcion;
    /**
     * Crea una ficha de promoción.
     * @param idEmpleado DNI del empleado que realiza la venta
     * @param fecha fecha de compra
     * @param numeroDeFicha número de ficha de promoción
     */
    public FichaPromocion(String idComercial, Date fecha,int numeroFicha,String nombre, String descripcion)
    {
        super(idComercial,fecha);
        activada=false;
        setEstado("desactivada");
        setNumeroDocumento(numeroFicha);
        setTipo("Ficha de promocion");
        this.nombre= nombre;
        this.descripcion=descripcion;
    }
    
    /**
     * Activa una promoción
     */
    public void activar()
    {
        activada=true;
        setEstado("activada");
    }
    
    /**
     * Desactiva una promoción
     */
    public void desactivar()
    {
        activada=false;
        setEstado("desactivada");
    }
    
    /**
     * Ofrece una descripcion breve de la promoción
     * @return resumen de la promocion
     */
    public String toString()
    {
        return("Nombre de la promocion: "+nombre+" "+getTipo()+" "+getNumeroDocumento()+" Empleado: "+getIdEmpleado()+" "+getFormatoFecha().format(getFecha())+
        " "+getEstado());
    }      
    
    /**
     * Comprueba si una promoción está activada
     * @return true si la promoción está activada
     */
    public boolean getActivada()
    {
        return activada;
    }
    
    /**
     * Consulta el nombre de una promoción
     * @return devuelve el nombre de la promoción
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Consulta la descripción de la promoción
     * @return devuelve la descripción de la promoción
     */
    public String getDescripcion()
    {
        return descripcion;
    }
}
