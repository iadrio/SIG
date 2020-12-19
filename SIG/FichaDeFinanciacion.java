import java.util.*;
/**
 * Almacena datos relacionados con una financiación
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.21
 */
public class FichaDeFinanciacion extends Documentos
{
    private double ultimaNomina;
    private int plazos, idFichaDeCompra;
    
    /**
     * Crea una ficha de financiación
     * @param idCliente DNI del cliente
     * @param idEmpleado DNI del empleado que tramita la financiación
     * @param fecha fecha de la financiación
     * @param numeroDeFicha numero de ficha de financiación
     * @param ultimaNomina último sueldo mensual del cliente
     * @param plazos plazos en los que el cliente quiere financiar la compra
     * @param estado estado del tramite
     * @param idFichaDeCompra número de ficha de compra
     */
    public FichaDeFinanciacion(String idCliente,String idEmpleado,Date fecha,int numeroDeFicha, double ultimaNomina, int plazos,
    String estado,int idFichaDeCompra)
    {
        super(idCliente,idEmpleado,fecha);
        setEstado("pendiente");
        setNumeroDocumento(numeroDeFicha);
        setTipo("FichaDeFinanciacion");
        this.idFichaDeCompra=idFichaDeCompra ;
        this.plazos=plazos;
        this.ultimaNomina=ultimaNomina;
    }
    
    /**
     * Consulta número de ficha de compra asociado a la financiación
     * @return número de ficha de compra asociado a la financiación
     */
    public int getIdFichaDeCompra()
    {
        return idFichaDeCompra;
    }
    
    /**
     * Consulta el valor de la última nómina mensual del cliente
     * @return valor de la última nómina mensual del cliente
     */
    public double getUltimaNomina()
    {
        return ultimaNomina;
    }
    
    /**
     * Indica el número de plazos en los que se desea financiar la compra
     * @return número de plazos en los que se desea financiar la compra
     */
    public int getPlazos()
    {
        return plazos;
    }
    
    /**
     * Ofrece una breve descripción del documento
     * @return descripción del documento
     */
    public String toString()
    {

        return (super.toString()+"\n"+"Numero de ficha de compra:"+idFichaDeCompra);

    }
}
