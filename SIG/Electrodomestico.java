import java.io.*;
/**
 * Clase abstracta que define las características generales de un producto de la tienda. 
 * 
 * @author Ivan Adrio Muñiz 
 * @version 2018.03.05
 */
public abstract class Electrodomestico implements Serializable
{
    private String codigoDeProducto,color,marca,modelo,tipoDeProducto,seccion;
    private int cantidad,ventas;
    private double precio;
    /**
     * Crea un producto de tipo genérico
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     */
    public Electrodomestico(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad)
    {
         this.codigoDeProducto=codigoDeProducto;
         this.marca=marca;
         this.modelo=modelo;
         this.precio=precio;
         this.cantidad=cantidad;
         this.color=color;
    }
       
    /**
     * Crea un producto de tipo genérico
     * @param electrodomestico objeto tipo electrodomestico
     */
    public Electrodomestico(Electrodomestico electrodomestico)
    {
         this.codigoDeProducto=electrodomestico.getCodigoDeProducto();
         this.tipoDeProducto = electrodomestico.getTipo();
         marca=electrodomestico.getMarca();
         modelo=electrodomestico.getModelo();
         precio=electrodomestico.getPrecio();
         cantidad=electrodomestico.getCantidad();
         color=electrodomestico.getColor();
    }
    /**
     * Indica el color del electrodomestico
     * @return color del electrodomestico
     */
    public String getColor()
    {
        return color;
    }   
    
    /**
     * Indica el codigo del electrodomestico
     * @return codigo de producto
     */
    public String getCodigoDeProducto()
    {
        return codigoDeProducto;
    }
    
    /**
     * Indica la marca del electrodomestico
     * @return marca del electrodomestico
     */
    public String getMarca()
    {
        return marca;
    }
    
    /**
     * Indica el modelo del electrodomestico
     * @return model del electrodomestico
     */
    public String getModelo()
    {
        return modelo;
    }
    
    /**
     * Indica el precio del producto
     * @return precio del producto
     */
    public double getPrecio()
    {
        return precio;
    }
    
    /**
     * Indica la cantidad de productos disponibles
     * @return cantidad de productos
     */
    public int getCantidad()
    {
        return cantidad;
    }
    
    /**
     * Indica el tipo de producto
     * @return tipo de producto
     */
    public String getTipo()
    {
        return tipoDeProducto;
    }
    
    /**
     * Cambia el color del producto
     * @param nuevoColor nuevo color del producto
     */
    public void setColor(String nuevoColor)
    {
        color=nuevoColor;
    }
    
    /**
     * Cambia el codigo del producto
     * @param nuevoCP nuevo codigo de producto
     */
    public void setCodigoDeProducto(String nuevoCP)
    {
        codigoDeProducto=nuevoCP;
    }
    
    /**
     * Cambia la marca del producto
     * @param nuevaM nueva marca del producto
     */
    public void setMarca(String nuevaM)
    {
        marca=nuevaM;
    }
    
    /**
     * Cambia el modelo del producto
     * @param nuevoMod nuevo modelo del producto
     */
    public void setModelo(String nuevoMod)
    {
        modelo=nuevoMod;
    }
    
    /**
     * Cambia el precio del producto
     * @nuevoPrecio nuevo precio del producto
     */
    public void setPrecio(double nuevoPrecio)
    {
        precio=nuevoPrecio;
    }
    
    /**
     * Cambia el numero de unidades disponibles de un producto
     * @param nuevaCantidad nuevo numero de unidades disponibles
     */
    public void setCantidad(int nuevaCantidad)
    {
        cantidad=nuevaCantidad;
    }
    
    /**
     * Cambia el tipo del producto
     * @param tipo nuevo tipo del producto
     */
    public void setTipo(String tipo)
    {
        tipoDeProducto=tipo;
    }
        
    /**
     * Cambia la seccion a la que pertenece un producto
     * @param seccion nueva seccion a la que pertenece el producto
     */
    public void setSeccion(String seccion)
    {
        this.seccion=seccion;
    }
    
    /**
     * Da formato a un String
     * @param string string a formatear
     * @param espacio tamaño maximo del string
     * @return string formateado
     */
    public String formatea(String string,int espacio)
    {
        return String.format("%-"+espacio+"s",string);
    }  
    
    /**
     * Da una descripcion breve de un producto
     * @return descripcion del producto
     */
    public String toShortString()
    {
        return (formatea("cp: "+getCodigoDeProducto()+" "+getTipo(),30)+formatea("marca:"+getMarca(),30)+formatea("modelo:"+getModelo(),30)+" "+
        formatea("color: "+getColor(),30)+formatea("precio:"+getPrecio()+"€",30));
    }
    
    /**
     * Da una descripcion de un producto
     * @return descripcion del producto
     */
    public String toString()
    {
        return (formatea("cp: "+getCodigoDeProducto()+" "+getTipo(),30)+formatea("marca:"+getMarca(),30)+formatea("modelo:"+getModelo(),30)+" "+
        formatea("color: "+getColor(),30)+formatea("precio:"+getPrecio()+"€",30));
    }    
    
}
