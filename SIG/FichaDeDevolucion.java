import java.util.*;
/**
 * Almacena datos relacionados con una devolución
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.21
 */
public class FichaDeDevolucion extends Documentos
{
    private String motivo;
    private int numeroFichaDeCompra;
    /**
     * Crea una ficha de devolución
     * @param idCliente DNI del cliente
     * @param idEmpleado DNI del empleado que tramita la devolución
     * @param fecha fecha de la devolución
     * @param numeroDeFicha numero de ficha de devolución
     * @param numeroFichaDeCompra numero de ficha de compra
     * @param motivo razón de la devolución
     */
    public FichaDeDevolucion(String idCliente,String idEmpleado,Date fecha,int numeroDeFicha,int numeroFichaDeCompra,String motivo)
    {
        super(idCliente,idEmpleado,fecha);
        setTipo("FichaDeDevolucion");
        this.numeroFichaDeCompra=numeroFichaDeCompra;
        setNumeroDocumento(numeroDeFicha);
        this.motivo=motivo;
        setEstado("pendiente");
    }
    
    /**
     * Nos indica el número de la ficha
     * @return numero de la ficha de devolución
     */
    public int getNumeroFichaDeCompra()
    {
        return numeroFichaDeCompra;
    }
    
    /**
     * Nos indica los motivos de la devolución
     * @return motivo de la devolución
     */
    public String getMotivo()
    {
        return motivo;
    }
    
    /**
     * Ofrece una breve descripción del documento
     * @return descripción del documento
     */
    public String toString()
    {

        return (super.toString()+"\n"+"motivo: "+motivo);

    }
}
