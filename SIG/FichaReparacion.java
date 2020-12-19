import java.io.*;
import java.util.*;
/**
 * Almacena datos relacionados con las reparaciones
 * 
 * @author Iván Adrio Muñiz 
 * @version 21.04.2018
 */
public class FichaReparacion extends Documentos
{   
    private String[] pieza;
    private String idProducto;
    private HashSet<String[]> listaPiezas; 
    private ArrayList<String> comentarios;
    private int numeroFichaDeCompra;
    private double horasTrabajadas,presupuesto;
    /**
     * Crea una ficha de reparación
     * @param idProducto código de producto
     * @param idTecnico identificación del empleado que se va asignar la ficha
     * @param numeroFichaDeCompra número de la ficha de compra asociada a la reparación
     * @param idCliente DNI del cliente
     * @param numeroFicha número de ficha de reparación
     * @param fecha fecha de creación de la ficha de reparación
     */
    public FichaReparacion(String idProducto,String idTecnico,int numeroFichaDeCompra,String idCliente,int numeroFicha,Date fecha)
    {
        super(idCliente,idTecnico,fecha);
        comentarios = new ArrayList<String>();
        listaPiezas = new HashSet<String[]>();
        this.idProducto=idProducto;
        añadirProducto(idProducto,1);
        setTipo("Ficha de reparacion");
        setNumeroDocumento(numeroFicha);
        this.numeroFichaDeCompra = numeroFichaDeCompra;
        horasTrabajadas=0;
        setEstado("pendiente");
    }
    
    /**
     * Cambia el valor de la mano de obra dedicada a la ficha
     * @param horas horas de mano de obra dedicadas
     */
    public void setHorasTrabajadas(double horas)
    {
        horasTrabajadas=horas;
    }
    
    /**
     * Consulta las horas de mano de obra dedicadas
     * @return horas de mano de obra dedicadas
     */
    public double getHorasTrabajadas()
    {
        return horasTrabajadas;
    }
    
    /**
     * Suma horas de trabajo a la ficha
     * @param horas horas de mano de obra que queremos sumar a la ficha
     */
    public void sumarHorasTrabajadas(double horas)
    {
        horasTrabajadas = horasTrabajadas + horas;
    }
    
    /**
     * Añade el coste de la reparación a la ficha
     * @param presupueseto coste de la reparación de la ficha
     */
    public void setPresupuesto(double presupuesto)    
    {
        this.presupuesto=presupuesto;
    }
    
    /**
     * Consulta el coste de la reparación
     * @return coste de la reparación
     */
    public double getPresupuesto()
    {
        return presupuesto;
    }
    
    /**
     * Consulta el número de la última ficha de reparación creada
     * @param número de la última ficha de reparación creada 
     */
    public int getNumeroFichaDeCompra()
    {
        return numeroFichaDeCompra;
    }
    
    /**
     * Consulta los comentarios añadidos a la ficha
     * @return lista de comentarios añadidos a la ficha
     * 
     */
    public ArrayList<String> getComentarios()
    {
        return comentarios;
    }
    
    /**
     * Consulta la lista de piezas necesarias para la reparación
     * @return conjunto de piezas necesarias para la reparación
     */
    public HashSet<String[]> getListaPiezas()
    {
        return listaPiezas;
    } 
    
    /**
     * Añade un comentario a la ficha de reparación
     * @param comentario comentario que queremos añadir 
     */
    public void añadirComentario(String comentario)
    {
        comentarios.add(comentario);
    }
    
    /**
     * Añade una pieza a la lista de piezas necesarias
     * @param idPieza codigo de identificación de la pieza
     * @param precio precio de la pieza
     * @param proveedor empresa encargada de suministrar la pieza
     * @param cantidad número de piezas necesarias
     * @param estado estado del pedido
     */
    public void añadirPieza(String idPieza,String descripcion, String precio, String proveedor,String cantidad, String estado)
    {
            //(idPieza,descripcion pieza, precio, proveedor,cantidad, estado)
        pieza = new String[6];
        String[] pieza = {idPieza,descripcion,precio,proveedor,cantidad,estado};
        listaPiezas.add(pieza);
    }
    
    /**
     * Devuelve todas las características de una pieza en un string
     * @param pieza características de la pieza
     * @param pieza[0] codigo de identificación
     * @param pieza[1] descripción
     * @param pieza[2] precio
     * @param pieza[3] proveedor
     * @param pieza[4] cantidad
     * @param pieza[5] estado del pedido
     * @return descripción de la pieza
     */
    public String piezaToString(String[] pieza)
    {
        return (" Codigo de pieza:"+pieza[0]+"  Descripcion: "+pieza[1]+" Precio: "+pieza[2]+"€ Proveedor: "+pieza[3]+" Cantidad: "+
        pieza[4]+" Estado: "+pieza[5]);
    }
    
    /**
     * Quita una pieza de la lista de piezas necesarias
     * @param idPieza codigo de identificación de la pieza que queremos retirar
     */
    public void quitarPieza(String idPieza)
    {
        listaPiezas.remove(getPieza(idPieza));
    }   
    
    /**
     * Busca una pieza en la lista de piezas pendientes usando el codigo de identificación
     * @param idPieza código de identificación de la pieza
     * @return pieza
     */
    public String[] getPieza(String idPieza)
    {
        for(String[] pieza:listaPiezas)
        {
            if(pieza[0].equals(idPieza)){return pieza;}
        }
        return null;
    }   
    
    /**
     * Comprueba si una pieza existe dentro la la lista de piezas necesarias
     * @param idPieza código de identificación de la pieza
     * @return true si la pieza existe
     */
    public boolean getExistePieza(String idPieza)
    {
        if(getPieza(idPieza)==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Cambia el estado del pedido de una pieza
     * @param estado estado nuevo de la pieza
     * @param idPieza código de identificación de la pieza
     */
    public void setEstadoPieza(String estado,String idPieza)
    {
        getPieza(idPieza)[5]=estado;
    }
    
    /**
     * Consulta el producto al cual está asociado la ficha de reparación
     * @return codigo del producto al cual está asociada la ficha de reparación
     */
    public String getIdProducto()
    {
        return idProducto;
    }
    
    
    /**
     * Ofrece una breve descripción del documento
     * @return descripción del documento
     */
    public String toString()
    {

        return (super.toString()+"\n"+"Numero de ficha de compra:"+numeroFichaDeCompra);

    }
    
    
}
