import java.io.*;
import java.util.*;
/**
 * Almacena datos de un contacto con el cliente
 * 
 * @author Iván Adrio Muñiz 
 * @version 24.04.2018
 */
public class CorreoElectronico extends Documentos
{
    private ArrayList<String> contenido;
    private int promocion;
    /**
     * Crea un correo electrónico 
     * @param idCliente identificación del cliente
     * @param idEmpleado identificación del empleado
     * @param fecha fecha de envio
     * @param contenido cuerpo del correo
     */
    public CorreoElectronico(String idCliente,String idEmpleado,Date fecha,ArrayList<String> contenido,int promocion,int numeroCorreo)
    {
        super(idCliente,idEmpleado,fecha);
        this.contenido=contenido;
        this.promocion=promocion;
        setNumeroDocumento(numeroCorreo);
        setEstado("enviado");
        setTipo("Correo electronico");
    }

    /**
     * Indica a que promoción está asociado el correo
     * return número de promocion
     */
    public int getPromocion()
    {
        return promocion;
    }
    
    /**
     * Devuelve el contenido del correo
     * return Devuelve el contenido del correo
     */
    public ArrayList<String> getContenido()
    {
        return contenido;
    }
    
    /**
     * Describe el correo electronico
     * @return resumen del correo
     */
    public String toString()
    {
        return(super.toString()+" promocion:"+promocion);
    }    
}
