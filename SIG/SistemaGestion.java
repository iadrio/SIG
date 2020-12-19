import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Sistema de gestión principal del programa. Se encarga de unificar todas las clases del programa y de gestionar las dependencias entre
 * ellas. Todas las funciones del programa se implementan aquí.
 * 
 * @author Iván Adrio Muñiz
 * @version 2018.04.17
 */
public class SistemaGestion
{
    private Almacen almacen;
    private Comercial comercial;
    private Usuarios  gestionUsuarios;
    private Caja caja;
    private Financiacion financiacion;
    private PosVenta postVenta;
    private ServicioTecnico servicioTecnico;
    private final String ruta = "AlmacenDeDatosSIG(IvanAdrioMuñiz)"; // ruta en la que se guardarán los archivos para la persistencia de
                                                                        // datos
    private SimpleDateFormat formatoFecha;
    
    /**
     * Instancia las clases que representan cada uno de los departamentos de la tienda. Almacena la fecha actual. 
     * Crea el directorio que contendrá los archivos para la persistencia de datos.
     * Carga los datos existentes en ese directorio.
     */
    public SistemaGestion(SimpleDateFormat formatoFecha)
    {
        File datos = new File(ruta);
        datos.mkdirs();
        this.formatoFecha = formatoFecha;
        
        almacen = new Almacen(ruta);
        gestionUsuarios = new Usuarios(ruta,formatoFecha);
        caja = new Caja(formatoFecha,ruta);
        financiacion= new Financiacion(ruta);
        postVenta = new PosVenta(formatoFecha,ruta);
        servicioTecnico= new ServicioTecnico(ruta);
        comercial = new Comercial(ruta);
        
        cargarDatos();
    }
    
    /**
     * Comprueba los datos de un usuario 
     * @param identificacion DNI usuario
     * @param clave personal de acceso del usuario
     * @return true si los datos son correctos
     * enuso
     */
    public boolean login(String identificacion,String clave)
    {
        return gestionUsuarios.login(identificacion,clave);
    }
    
    /**
     * Comprueba si un empleado tiene un determinado permiso
     * @param permiso permiso que queremos comprobar
     * @return true si el usuario tiene ese permiso
     * en uso
     */
    public boolean comprobarPermisoUsuarioActual(String permiso)
    {
        return gestionUsuarios.getEmpleadoActual().comprobarPermiso(permiso);
    }
    
    /**
     * Nos indica la identidad del usuario actual
     * @return DNI del usuario actual
     * en uso
     */
    public String identificarEmpleadoActual()
    {
        return gestionUsuarios.getEmpleadoActual().getIdentificacion();
    }
    
    /**
     * Nos indica los permisos que tiene el usuario actual
     * @return lista de permisos del usuario actual
     * en uso
     */
    public String listaPermisosUsuarioActual()
    {
        return gestionUsuarios.getEmpleadoActual().getListaPermisos();
    }
    
    /**
     * Devuelve el historial de compras de un cliente
     * @identificacion DNI del cliente
     * @return lista de compras del cliente
     * en uso
     */
    public ArrayList<String> getHistorialCliente(String identificacion)
    {
        ArrayList<String> historial = new ArrayList<String>();
        
        if(gestionUsuarios.getExisteCliente(identificacion))
        {
            return gestionUsuarios.getCliente(identificacion).getHistorialString();
        }
        else
        {
            historial.add("El cliente no existe");
            return historial;
        }
    }
    
    /**
     * Comprueba si un empleado existe
     * @param identificacion DNI del empleado
     * @return devuelve true si el empleado existe
     */
    public boolean existeEmpleado(String identificacion)
    {
        return gestionUsuarios.getExisteEmpleado(identificacion);
    }
    
    /**
     * Comprueba si un cliente existe
     * @param identificacion DNI del cliente
     * @return devuelve true si el cliente existe
     */
    public boolean existeCliente(String identificacion)
    {
        return gestionUsuarios.getExisteCliente(identificacion);
    }
    
    /**
     * Comprueba si un rol pertenece a la lista de roles validos
     * @param rol que queremos comprobar
     * @return true si el rol pertenece a la lista
     */
    public boolean comprobarRolEmpleadoValido(String rol)
    {
        return gestionUsuarios.getRolValido(rol);
    }
    
    /**
     * Devuelve la lista de roles de empleado posibles
     * @return lista de roles de empleado
     */
    public String listaRolesEmpleado()
    {
        return gestionUsuarios.getStringRolesValidos();
    }
    
    /**
     * Nos indica la longitud que debe tener la clave de empleado
     * @returns longitud clave de empleado
     */
    public int longitudClaveEmpleado()
    {
        return gestionUsuarios.getLongitudClave();
    }
    
    /**
     * Crea un empleado y lo añade a la lista de usuarios.
     * @param identificacion DNI del empleado
     * @param nombre nombre del empleado
     * @param apellidos apellidos del empleado
     * @param correoElectrónico correo electronico del empleado
     * @param telefono telefono del empleado
     * @param clave clave del empleado
     * @param rol rol del empleado
     * @return true si el empleado se ha añadido correctamente
     */
    public boolean añadirEmpleado(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono,
    String clave,String rol)
    {
        return gestionUsuarios.añadirEmpleado(identificacion,nombre,apellidos,correoElectronico,telefono,clave,rol);
    }
    
    /**
     * Crea un cliente y lo añade a la lista de usuarios.
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectrónico correo electronico del cliente
     * @param telefono telefono del cliente
     * @return true si el cliente se ha añadido correctamente
     */
    public boolean añadirCliente(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono)
    {
        return gestionUsuarios.añadirCliente(identificacion,nombre,apellidos,correoElectronico,telefono);
    }
    
    /**
     * Permite eliminar un cliente de la base de datos
     * @param idCliente identificacion del cliente que se desea eliminar
     * @return true si se ha eliminado correctamente
     */
    public boolean quitarCliente(String idCliente)
    {
        return gestionUsuarios.quitarCliente(idCliente);      
    }
    
    /**
     * Devuelve una lista de empleados
     * @return lista de empleados
     */
    public ArrayList<String> getListaEmpleados()
    {
        return gestionUsuarios.getListaEmpleados();
    }
    
    /**
     * Devuelve una lista de clientes
     * @return lista de clientes
     */
    public ArrayList<String> getListaClientes()
    {
        return gestionUsuarios.getListaClientes();
    }
    
    /**
     * Ofrece una descripcion de un empleado
     * @param id DNI empleado
     * @return descripcion de empleado
     */
    public String describeEmpleado(String id)
    {
        return gestionUsuarios.getEmpleadoId(id);
    }
    
    /**
     * Ofrece una descripcion de un cliente
     * @param id DNI cliente
     * @return descripcion de cliente
     */
    public String describeCliente(String id)
    {
        return gestionUsuarios.getClienteId(id);
    }
    
    /**
     * Devuelve una lista con todos los empleados cuyo nombre o apellidos coincida con el criterio de busqueda. 
     * @param nombre criterio de busqueda
     * @return lista de empleados con un determinado nombre o apellidos
     */
    public ArrayList<String> getEmpleadoNombre(String nombre)
    {
        return gestionUsuarios.getEmpleadoNombre(nombre);
    }
    
    /**
     * Devuelve una lista con todos los clientes cuyo nombre o apellidos coincida con el criterio de busqueda. 
     * @param nombre criterio de busqueda
     * @return lista de clientes con un determinado nombre o apellidos
     */
    public ArrayList<String> getClienteNombre(String nombre)
    {        
        return gestionUsuarios.getClienteNombre(nombre);
    }
    
    /**
     * Devuelve una lista con todos los permisos que puede tener un empleado
     * @return lista de permisos validos
     */
    public String getPermisosEmpleadoValidos()
    {
        return gestionUsuarios.getPermisosValidos();
    }
    
    /**
     * Añade un permiso a un empleado
     * @param identificacion DNI empleado
     * @param permiso permiso que queremos añadir
     * @return true si el permiso se ha añadido correctamente
     */
    public boolean añadirPermiso(String identificacion,String permiso)
    {
        return gestionUsuarios.añadirPermiso(identificacion, permiso);
    }
    
    /**
     * Quita un permiso a un empleado
     * @param identificacion DNI empleado
     * @param permiso permiso que queremos quitar
     * @return true si el permiso se ha quitado correctamente
     */
    public boolean quitarPermiso(String identificacion,String permiso)
    {
        return gestionUsuarios.quitarPermiso(identificacion,permiso);
    }
    
    /**
     * Modifica los datos de un cliente ya existente
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectrónico correo electronico del cliente
     * @param telefono telefono del cliente
     * @return true si los datos se han modificado correctamente
     * @see Cliente.modificarPersona(args[] parametros)
     */
    public boolean modificarCliente(String identificacion, String nombre, String apellidos, String correoElectronico, String telefono)
    {
        return gestionUsuarios.modificarCliente(identificacion,nombre,apellidos,correoElectronico,telefono,identificarEmpleadoActual());
    }
    
    /**
     * Modifica los datos de un empleado ya existente
     * @param identificacion DNI del cliente
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param correoElectrónico correo electronico del cliente
     * @param telefono telefono del cliente
     * @param clave clave de acceso
     * @param rol rol del empleado
     */
    public boolean modificarEmpleado(String identificacion,String nombre, String apellidos, String correoElectronico, String telefono,String clave,String rol)
    {
        return gestionUsuarios.modificarEmpleado(identificacion,nombre,apellidos,correoElectronico,telefono,identificarEmpleadoActual(),clave,rol);
    }
    
    
    //FUNCIONES PARA GESTION DEL ALMACEN
    
    /**
     * Muestra el stock de productos total del almacen
     * @return lista de productos
     * en uso
     */
    public ArrayList<String> getStock()
    {
        return almacen.getListaDeProductos();
    }
    
    /**
     * Aumenta el stock de un producto
     * @param codigoProducto codigo de producto
     * @param cantidad numero de articulos a aumentar
     * @return  true si la operacion se ha completado correctamente
     * en uso
     */
    public boolean recepcionPedidos(String codigoProducto,int cantidad)
    {
        return almacen.setRecepcionDePedidos(codigoProducto,cantidad);   
    }
    
    /**
     * Elimina un producto del almacen
     * @param codigoProducto codigo de producto
     * @return true si el producto se elimina correctamente
     * en uso
     */
    public boolean eliminarProducto(String codigoProducto)
    {
        return almacen.eliminaProducto(codigoProducto);
    }
    
    /**
     * Describe un producto
     * @param codigoProducto  codigo de producto
     * @return descripcion del producto
     * en uso
     */
    public String describirProducto(String codigoProducto)
    {
        return almacen.getDescribeProducto(codigoProducto);
    }
    
    /**
     * Comprueba si un producto existe en el almacen
     * @param cp codigo de producto
     * @return true si el producto existe
     */
    public boolean existeProducto(String cp)
    {
        return almacen.getExiste(cp);
    }
    
    /**
     * Comprueba a que tipo pertenece un producto
     * @param cp codigo de producto
     * @return tipo de producto
     */
    public String tipoProducto(String cp)
    {
        if(almacen.getExiste(cp))
        {
            return almacen.getProductoPorCp(cp).getTipo();
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Comprueba si un string tiene el formato requerido para un codigo de producto
     * @param codigoProducto string a comprobar
     * @return true si el string cumple el formato
     * en uso
     */
    public boolean comprobarCodigoProductoValido(String codigoProducto)
    {
        return almacen.getCpValido(codigoProducto);
    }
    
    /**
     * Indica la longitud que debe tener un codigo de producto
     * @return longitud del codigo de producto
     * en uso
     */
    public int getLongitudCodigoProducto()
    {
        return almacen.getLongitudCodigoProducto();
    }
    
    
    /**
     * Crea una Tv y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param electrodomestico objeto tipo electrodomestico
     * @param tamaño tamaño de pantalla
     * @param frecuencia frecuencia de refresco
     * @param resolucion resolución de pantalla
     * @return true si el producto se añade correctamente
     * en uso
     */
    public boolean añadirTv(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,int tamaño, int frecuencia,
    String resolucion)
    {
        return almacen.añadirTv(codigoDeProducto,marca,modelo,color,precio,cantidad,tamaño,frecuencia,resolucion);
    }
    
    /**
     * Crea un reproductor y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param electrodomestico objeto tipo electrodomestico
     * @param formato formatos reproducibles
     * @return true si el producto se añade correctamente
     */
    public boolean añadirReproductor(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String formato)
    {
        return almacen.añadirReproductor( codigoDeProducto,marca,modelo,color,precio,cantidad, formato);
    }
    
    /**
     * Crea una camara y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param electrodomestico objeto tipo electrodomestico
     * @param resolucionVideo resolucion de video que es capaz de grabar la camara
     * @param pixeles resolución de la camara fotográfica
     * @return true si el producto se añade correctamente
     */
    public boolean añadirCamara(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String resolucionVideo,String pixeles)
    {
        return almacen.añadirCamara( codigoDeProducto,marca,modelo,color,precio,cantidad, resolucionVideo, pixeles);
    }
    
    /**
     * Crea un objeto altavoces y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param electrodomestico objeto tipo electrodomestico
     * @param potencia potencia de sonido
     * @return true si el producto se añade correctamente
     */
    public boolean añadirAltavoces(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String potencia)
    {
        return almacen.añadirAltavoces( codigoDeProducto,marca,modelo,color,precio,cantidad, potencia);
    }
    
    /**
     * Crea un sistema de sonido y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param electrodomestico objeto tipo electrodomestico
     * @param potencia potencia de sonido
     * @param formato formatos reproducibles
     * @return true si el producto se añade correctamente
     */
    public boolean añadirSistemaSonido(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,String potencia, String formato)
    {
        return almacen.añadirSistemaSonido( codigoDeProducto,marca,modelo,color,precio,cantidad, potencia,  formato);
    }
    
    /**
     * Crea un reproductorDeMusica y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param electrodomestico objeto tipo electrodomestico
     * @param formatos formatos reproduciblesorrectamente
     */
    public boolean añadirReproductorMusica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String formatos)
    {
        return almacen.añadirReproductorMusica( codigoDeProducto,marca,modelo,color,precio,cantidad, formatos);
    }
    
     /**
     * Crea un ordenador y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param electrodomestico objeto tipo electrodomestico
     * @param ram memoria ram del sistema
     * @param discoDuro memoria disponible
     * @param procesador modelo de procesador
     * @param tarjetaGrafica modelo de tarjeta grafica
     * @param bateria tamaño de la bateria
     * @param tipo tipo de ordenador (portatil, sobremesa, servidor...)
     */
    public boolean añadirOrdenador(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String ram , String discoDuro,String procesador,String tarjetaGrafica,String bateria,String tipo)
    {
        return almacen.añadirOrdenador( codigoDeProducto,marca,modelo,color,precio,cantidad, ram ,  discoDuro, procesador, tarjetaGrafica, bateria, tipo);
    }
    
    /**
     * Crea un smartphone y lo añade al almacen
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
    public boolean añadirSmartphone(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String tamañoPantalla,String resolucionPantalla,String ram , String almacenamiento,String procesador,String bateria)
    {
        return almacen.añadirSmartphone( codigoDeProducto,marca,modelo,color,precio,cantidad,tamañoPantalla,resolucionPantalla,ram ,
        almacenamiento,procesador, bateria);
    }
    
    /**
     * Crea un pequeño electrodoméstico y lo añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param tipo tipo de pequeño electrodomestico
     * @param descripcion descripcion del producto
     */
    public boolean añadirPequeñoElectrodomestico(String codigoDeProducto,String marca, String modelo,String color, double precio, 
    int cantidad,String tipo,String descripcion)
    {
        return almacen.añadirPequeñoElectrodomestico( codigoDeProducto,marca,modelo,color,precio,cantidad, tipo, descripcion);
    }
    
      /**
     * Crea una vitroceramica y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param potencia potencia eléctrica de la vitrocerámica 
     * @param tipo inducción o vitro
     * @param fuegos número de puntos de calor
     * @param etiquetaEnergetica nivel de consumo eléctrico
     */
    public boolean añadirVitroceramica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String etiquetaEnergetica,String tipo,String fuegos,String potencia)
    {
        return almacen.añadirVitroceramica( codigoDeProducto,marca,modelo,color,precio,cantidad, etiquetaEnergetica, tipo, fuegos, potencia);
    }
    
    /**
     * Crea una lavadora y la añade al almacen
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
    public boolean añadirLavadora(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String etiquetaEnergetica,String capacidad,String tipoCarga,String revoluciones)
    {
        return almacen.añadirLavadora( codigoDeProducto,marca,modelo,color,precio,cantidad, etiquetaEnergetica, capacidad, tipoCarga, revoluciones);
    }
    
    /**
     * Crea una horno y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param etiquetaEnergetica nivel de consumo eléctrico
     * @param capacidad volumen interior útil
     * @param potencia potencia del horno
     */
    public boolean añadirHorno(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String etiquetaEnergetica,String capacidad,String potencia)
    {
        return almacen.añadirHorno( codigoDeProducto,marca,modelo,color,precio,cantidad, etiquetaEnergetica, capacidad, potencia);
    }
    
     
    /**
     * Crea una nevera y la añade al almacen
     * @param codigoDeProducto número de identificación del producto
     * @param marca fabricante del producto
     * @param modelo  
     * @param color
     * @param precio
     * @param cantidad cantidad de productos en el almacen
     * @param etiquetaEnergetica nivel de consumo eléctrico
     * @param alto altura de la nevera
     * @param ancho anchura de la nevera
     * @param volumen volumen interior útil
     */
    public boolean añadirNevera(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad,
    String etiquetaEnergetica,String alto,String ancho,String volumen)
    {
        return almacen.añadirNevera( codigoDeProducto,marca,modelo,color,precio,cantidad, etiquetaEnergetica, alto, ancho, volumen);
    }
     
    
    /**
     * Indica el tipo de electrodomestico al que pertenece un producto
     * @param codigoProducto codigo de producto
     * @return tipo de electrodomestico
     */
    public String comprobarTipoElectrodomestico(String codigoProducto)
    {
        return almacen.getProductoPorCp(codigoProducto).getTipo();
    }
    
    
    //FUNCIONES PARA GESTION DE CAJA
    
    /**
     * Comprueba si una ficha de compra existe
     * @param numeroFicha
     * @return true si la ficha existe
     */   
    public boolean getExisteFichaCompra(int numeroFicha)
    {
        return caja.getExisteFicha(numeroFicha);
    }
    
    /**
     * Devuelve el historial completo de ventas de la tienda
     * @return lista de ventas de la tienda completo
     */
    public ArrayList<String> getHistorialVentas()
    {
        return caja.getHistorialVentas();
    }   
    
    /**
     * Devuelve las fichas de compra en un determinado estado
     * @param estado situacion en la que se encuentra la ficha
     * @return lista de fichas de compra en un determinado estado
     */
    public ArrayList<String> getFichasCompraEstado(String estado)
    {
        return caja.getFichasEstado(estado);
    }
    
    /**
     * Devuelve las fichas de compra de un determinado cliente
     * @param idCliente DNI del cliente
     * @return lista de compras de un cliente
     */
    public ArrayList<String> getFichasCompraCliente(String idCliente)
    {
        return caja.getFichasCliente(idCliente);
    }
    
    /**
     * Devuelve las fichas de compra de un cliente en un determinado estado
     * @param idCliente DNI del cliente
     * @param estado situacion en la que se encuentra la ficha
     * @return lista de fichas de compra de un cliente en un determinado estado
     */
    public ArrayList<String> getFichasCompraClienteEstado(String idCliente,String estado)
    {
        return caja.getFichasClienteEstado(idCliente,estado);
    
    }    
    
    /**
     * Indica en que estado se encuentra una ficha de compra
     * @param numeroFicha numero de ficha de compra
     * @return estado de la ficha de compra
     */
    public String comprobarEstadoFichaCompra(int numeroFicha)
    {
        return caja.getFichaPorNumero(numeroFicha).getEstado();
    }
    
    /**
     * Indica a que cliente pertenece una ficha de compra
     * @param numeroFicha numero de ficha de compra
     * @return DNI del cliente 
     */
    public String comprobarClienteFichaCompra(int numeroFicha)
    {
        return caja.getFichaPorNumero(numeroFicha).getIdCliente();
    }
    
    /**
     * Indica la fecha de compra de una ficha de compra
     * @param numeroFicha numero de ficha de compra
     * @return fecha de compra
     */
    public long comprobarFechaDeCompra(int numeroFicha)
    {
        return caja.getFichaPorNumero(numeroFicha).getFecha().getTime();
    }
    
    /**
     * Crea una ficha de compra
     * @param idCliente DNI del cliente
     * @return numero de ficha de compra
     */
    public int crearFichaDeCompra(String idCliente)
    {
        return caja.crearFichaDeCompra(idCliente,gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Añade un producto a una ficha de compra
     * @param numeroDeFicha numero de ficha de compra
     * @param idProducto codigo del producto que queremos añadir
     * @param cantidad unidades del producto que queremos añadir
     * @return true si el producto se añade correctamente
     */
    public boolean comprarProducto(int numeroDeFicha, String idProducto,int cantidad)
    {       
       if(almacen.comprobarStockSuficiente(idProducto,cantidad))
       {
           int descuento = comercial.consultaDescuento(idProducto,comercial.buscarPromocionAplicable(idProducto));
           double precio = almacen.getProductoPorCp(idProducto).getPrecio();
           if(descuento!=-1)
           {
               precio = precio*(1-(double)descuento/100);
           }
           caja.añadirProducto(numeroDeFicha,idProducto,cantidad,precio);
           almacen.setQuitaArticulos(idProducto,cantidad);
           String identificacion = caja.getFichaPorNumero(numeroDeFicha).getIdCliente();
           gestionUsuarios.getCliente(identificacion).setAccion("Ficha de compra:"+numeroDeFicha+" añade producto a cesta: "+
           idProducto,gestionUsuarios.getEmpleadoActual().getIdentificacion());
           return true;
       }
       else
       {
           return false; 
       }
    }
    
    /**
     * Quita un producto de una ficha de compra
     * @param numeroDeFicha numero de ficha de compra
     * @param idProducto codigo del producto que queremos quitar
     * @param cantidad unidades del producto que queremos quitar
     * @return true si el producto se quita correctamente
     */
    public boolean quitarProductoDeCompra(int numeroDeFicha, String idProducto,int cantidad)
    {
        double precio = almacen.getProductoPorCp(idProducto).getPrecio();
       if(caja.quitarProducto(numeroDeFicha,idProducto,cantidad,precio))
       {
           almacen.setRecepcionDePedidos(idProducto,cantidad);
           String identificacion = caja.getFichaPorNumero(numeroDeFicha).getIdCliente();
           gestionUsuarios.getCliente(identificacion).setAccion("Ficha de compra:"+numeroDeFicha+" quita producto de la cesta: "+
           idProducto,gestionUsuarios.getEmpleadoActual().getIdentificacion());
           return true;
       }
       else
       {
           return false; 
       }
        
    }
    
    /**
     * Marca el estado de una ficha de compra como pagada
     * @param numeroDeFicha numero de ficha de compra
     */
    public void pagarEfectivo(int numeroDeFicha)
    {
        String identificacion = caja.getFichaPorNumero(numeroDeFicha).getIdCliente();
        gestionUsuarios.getCliente(identificacion).setAccion("Ficha de compra:"+numeroDeFicha+"pago de compra ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
        caja.finalizarCompra(numeroDeFicha,"pagado");
    }
    
    /**
     * Marca el estado de una ficha de compra como pendiente de financiar
     * @param numero de ficha de compra
     */
    public void financiarCompra(int numeroDeFicha)
    {
        String identificacion = caja.getFichaPorNumero(numeroDeFicha).getIdCliente();
        gestionUsuarios.getCliente(identificacion).setAccion("Ficha de compra:"+numeroDeFicha+" solicita financiacion ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
        caja.finalizarCompra(numeroDeFicha,"pendiente financiar");
    }
    
    /**
     * Vacia de productos una ficha de compra y la elimina
     * @param numeroDeFicha numero de ficha de compra
     */
    public void cancelarCompra(int numeroDeFicha)
    {
        HashMap<String,Integer> listaProductos = caja.getHistorial().get(numeroDeFicha).getListaProductos();
        
        for(HashMap.Entry<String,Integer> producto:listaProductos.entrySet())
        {
            almacen.setRecepcionDePedidos(producto.getKey(),producto.getValue());
        }
        String identificacion = caja.getFichaPorNumero(numeroDeFicha).getIdCliente();
        caja.eliminaFicha(numeroDeFicha);       
        gestionUsuarios.getCliente(identificacion).setAccion("Ficha de compra:"+numeroDeFicha+" cancela compra ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Verifica si un producto aparece en una ficha de compra
     * @param numeroFichaCompra numero de ficha de compra
     * @param idProducto codigo de producto
     * @return true si el producto aparece en la ficha de compra
     */
    public boolean compruebaProductoComprado(int numeroFichaCompra,String idProducto)
    {
        if(caja.getFichaPorNumero(numeroFichaCompra)!=null)
        {
            return caja.getFichaPorNumero(numeroFichaCompra).getExisteProducto(idProducto);
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Devuelve en una lista todos los datos de una ficha de compra
     * @param numeroFichaDeCompra numero de ficha de compra
     * @return datos de ficha de compra
     */
    public  ArrayList<String>  getFichaCompra(int numeroFichaDeCompra)
    {
        HashMap<String,Integer> ListaDeProductos = caja.getFichaPorNumero(numeroFichaDeCompra).getListaProductos();
        ArrayList<String> ficha = new ArrayList<String>();
        int numeroPromocion;
        ficha.add("***FICHA DE COMPRA***");         
        ficha.add(caja.getFichaPorNumero(numeroFichaDeCompra).toString());
        ficha.add("***Productos comprados:");
        for(HashMap.Entry<String,Integer> producto: ListaDeProductos.entrySet())
        {
            ficha.add(producto.getValue()+"X "+almacen.getDescripcionCorta(producto.getKey()));
            numeroPromocion=comercial.buscarPromocionAplicable(producto.getKey());
            if(numeroPromocion!=-1)
            {
                ficha.add(comercial.aplicarPromocion(producto.getKey(),numeroPromocion));
            }

        }
        
        //muestra las devoluciones efectuadas en esa ficha de compra
        if(!postVenta.getDevolucionesPorFichaDeCompraNum(numeroFichaDeCompra).isEmpty()){
                for(Integer numeroFicha:postVenta.getDevolucionesPorFichaDeCompraNum(numeroFichaDeCompra)){
                    for(String string:getFichaDevolucion(numeroFicha)){
                        ficha.add(string);
                    }
                }
            }
        
        return ficha;
    }
    

    
    /**
     * Da formato a un texto indicando cuanto debe ocupar y rellenando el espacio sobrante con espacios.
     * @param string texto a formatear
     * @param espacio espacio que debe ocupar el texto
     * @return texto formateado
     */
    public String formatea(String string,int espacio)
    {
        return String.format("%-"+espacio+"s",string);
    }
    
    /**
     * Devuelve en una lista el historial de financiaciones de un cliente
     * @param idCliente DNI del cliente
     * @return lista de financiaciones de un cliente
     */
    public ArrayList<String> getHistorialFinanciacionCliente(String idCliente)
    {
        return financiacion.getHistorialCliente(idCliente);
    }
    
    /**
     * Comprueba si una ficha de financiacion existe
     * @param numeroFicha numero de ficha de financiacion
     * @return true si la ficha existe
     */
    public boolean getExisteFichaFinanciacion(int numeroFicha)
    {
        return financiacion.getExisteFicha(numeroFicha);
    }
    
    /**
     * Analiza una ficha de financiacion y la marca como aprobada o rechazada en funcion del resultado
     * @param numeroDeFicha numero de ficha de compra
     * @param nomina sueldo mensual del cliente
     * @param plazo meses en los que el cliente desea pagar la compra
     */
    public void analizarFinanciacion(int numeroDeFicha,double nomina,int plazo)
    {
        String idCliente = caja.getFichaPorNumero(numeroDeFicha).getIdCliente();
        double totalCompra = caja.getFichaPorNumero(numeroDeFicha).getTotal();
        
         if(financiacion.analizarFinanciacion(numeroDeFicha,nomina,plazo,gestionUsuarios.getEmpleadoActual().getIdentificacion(),idCliente,totalCompra))
        {
            caja.getFichaPorNumero(numeroDeFicha).setEstado("financiacion aprobada");
            gestionUsuarios.getCliente(idCliente).setAccion("Ficha de financiacion:"+financiacion.getNumeroUltimaFicha()+" financiacion aprobada ",
            gestionUsuarios.getEmpleadoActual().getIdentificacion());
        }
        else
        {
            caja.getFichaPorNumero(numeroDeFicha).setEstado("financiacion rechazada");
            for(HashMap.Entry<String,Integer> producto:caja.getFichaPorNumero(numeroDeFicha).getListaProductos().entrySet())
            {
                almacen.setRecepcionDePedidos(producto.getKey(),producto.getValue());
            }
            gestionUsuarios.getCliente(idCliente).setAccion("Ficha de financiacion:"+financiacion.getNumeroUltimaFicha()+" financiacion rechazada ",
            gestionUsuarios.getEmpleadoActual().getIdentificacion());
        }

    }
    
    //METODOS PARA GESTIONAR EL DEPARTAMENTO DE POSTVENTA
    
    /**
     * Devuelve una lista con las devoluciones realizadas por un cliente
     * @param idCliente DNI del cliente
     * @return lista de devoluciones del cliente
     */
    public ArrayList<String> getHistorialPostVentaCliente(String idCliente)
    {
        return postVenta.getHistorialCliente(idCliente);
    }
    
    /**
     * Devuelve la lista de devoluciones completa de una tienda
     * @return lista de devolucionese
     */
    public ArrayList<String> getHistorialPostVentaCompleto()
    {
        return postVenta.getHistorialCompleto();
    }   
    
    /**
     * Comprueba que un producto no se haya devuelto con anterioridad
     * @param numeroFichaCompra numero de ficha de compra
     * @param idProducto codigo de producto
     * @param cantidad unidades que el cliente desea devolver
     * @return true si el producto no ha sido devuelto antes
     */
    public boolean compruebaProductoNoDevuelto(int numeroFichaCompra,String idProducto,int cantidad)
    {
        int productosComprados = caja.getFichaPorNumero(numeroFichaCompra).getListaProductos().get(idProducto);
        return postVenta.getQuedanProductos(numeroFichaCompra,idProducto,cantidad,productosComprados);
    }
    
    /**
     * Comprueba si una ficha de devolucion existe
     * @param numeroFicha numero de ficha de devolucion
     * @return true si la ficha de devolucion existe
     */
    public boolean existeFichaDevolucion(int numeroFicha)
    {
        return postVenta.getExisteFicha(numeroFicha);
    }
    
    /**
     * Devuelve en una lista todos los datos de una ficha de devolucion
     * @param numeroFicha numero de ficha de devolucion
     * @return datos de ficha de devolucion
     */
    public ArrayList<String> getFichaDevolucion(int numeroFicha)
    {
        ArrayList<String> ficha = new ArrayList<String>();
        HashMap<String,Integer> ListaDeProductos = postVenta.getFicha(numeroFicha).getListaProductos();
        ficha.add("***FICHA DE DEVOLUCION***");
        ficha.add(postVenta.getFicha(numeroFicha).toString());
        ficha.add("***Productos devueltos");
        ListaDeProductos.forEach((codigoProducto,cantidad)->ficha.add(cantidad+"X "+almacen.getProductoPorCp(codigoProducto).toString()) );
        return ficha;
    }
    
    /**
     * Crea una ficha de devolucion y la añade al historial
     * @param numeroFichaCompra numero de ficha de compra
     * @param motivo razon por la que se devuelve el producto
     * @return numero de ficha de devolucion
     */
    public int crearFichaDevolucion(int numeroFichaCompra,String motivo)
    {
        return postVenta.crearFicha(numeroFichaCompra,motivo,gestionUsuarios.getEmpleadoActual().getIdentificacion(),caja.getFichaPorNumero(numeroFichaCompra).getIdCliente());
    }
    
    /**
     * Añade un producto a una ficha de devolucion
     * @param numeroFicha numero de ficha de devolucion
     * @param idProducto codigo de producto
     * @param cantidad unidades que se quiere devolver
     * @param numeroFichaDeCompra numero de ficha de compra
     */
    public boolean devolverProducto(int numeroFicha,String idProducto, int cantidad, int numeroFichaDeCompra)
    {
        int productosComprados = caja.getFichaPorNumero(numeroFichaDeCompra).getListaProductos().get(idProducto);
        gestionUsuarios.getCliente(postVenta.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de devolucion:"+numeroFicha+" devolucion de producto ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
        if(postVenta.añadirProducto(numeroFicha,idProducto,cantidad,productosComprados,numeroFichaDeCompra))
        {
            almacen.setRecepcionDePedidos(idProducto,cantidad);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Quita un producto de una ficha de devolucion
     * @param numeroFicha numero de ficha de devolucion
     * @param idProducto codigo de producto
     * @param cantidad unidades que se quiere retirar de la ficha
     * @param numeroFichaDeCompra numero de ficha de compra
     */
    public boolean cancelarDevolucionProducto(int numeroFicha,String idProducto, int cantidad, int numeroFichaDeCompra)
    {
        boolean productoComprado = compruebaProductoComprado(numeroFichaDeCompra,idProducto);
        gestionUsuarios.getCliente(postVenta.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de devolucion:"+numeroFicha+
        " cancela devolucion de producto ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
        if(postVenta.quitarProducto(numeroFicha,idProducto,cantidad,productoComprado))
        {
            almacen.setQuitaArticulos(idProducto,cantidad);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Confirma una devolucion y marca su estado como completado
     * @param numeroFicha numero de ficha de devolucion
     */
    public void completarDevolucion(int numeroFicha)
    {
        postVenta.getFicha(numeroFicha).setEstado("Completada");
        gestionUsuarios.getCliente(postVenta.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de devolucion:"+numeroFicha+
        " confirma devolucion ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Quita los productos de una ficha de devolucion y elimina la ficha
     * @param numeroDeFicha numero de ficha de devolucion
     */
    public void cancelarDevolucion(int numeroDeFicha)
    {
        gestionUsuarios.getCliente(postVenta.getFicha(numeroDeFicha).getIdCliente()).setAccion("Ficha de devolucion:"+numeroDeFicha+
        " cancela devolucion ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
        postVenta.cancelarDevolucion(numeroDeFicha);
    }
    
    
    //METODOS PARA GESTIONAR EL SERVICIO TECNICO
    
    /**
     * Crea una ficha de reparacion
     * @param numeroFichaDeCompra numero de ficha de compra
     * @param idProducto codigo de producto
     * @return devuelve numero de ficha de reparacion si se ha abierto correctamente, -1 si el producto
     * no fue comprado,-2 si el producto ya fue devuelto anteriormente y -3 si se esta gestionando ya
     * su reparacion
     */
    public int crearFichaReparacion(int numeroFichaDeCompra, String idProducto)
    {
        if(compruebaProductoComprado(numeroFichaDeCompra,idProducto)) {
            if(compruebaProductoNoDevuelto(numeroFichaDeCompra,idProducto,1)){
                if(!compruebaReparacionAbierta(numeroFichaDeCompra,idProducto)){
                    String idCliente = caja.getFichaPorNumero(numeroFichaDeCompra).getIdCliente(); 
                    String idEmpleadoActual = gestionUsuarios.getEmpleadoActual().getIdentificacion();
                    int numeroFichaReparacion = servicioTecnico.crearFicha(numeroFichaDeCompra, idCliente,  idProducto, idEmpleadoActual);
                    gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFichaReparacion).getIdCliente()).setAccion("Ficha de reparacion:"+
                    servicioTecnico.getUltimoNumeroFicha()+" incidencia abierta ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
                    return numeroFichaReparacion;
                }else{
                    return -3;
                }
            }
            else{
                return -2;
            }
        }
        else{
            return -1;
        }
    }
    
    /**
     * Comprueba si un  producto ya esta siendo gestionada su reparacion
     * @param numeroFichaDeCompra numero de ficha de compra
     * @param idProducto codigo de producto 
     */
    public boolean compruebaReparacionAbierta(int numeroFichaDeCompra, String idProducto)
    {
        boolean existe = false;
        for(Integer numeroFicha:servicioTecnico.getReparacionesPorFichaDeCompra(numeroFichaDeCompra)){
            if(servicioTecnico.getFicha(numeroFicha).getExisteProducto(idProducto)){
                if(servicioTecnico.getFicha(numeroFicha).getEstado().equals("finalizado")){
                existe = true;
            }
            }
        }
        
        return existe;
    }    
    
    /**
     * Añade comentarios a una ficha de reparacion
     * @param numeroFichaReparacion numero de ficha de reparacion
     * @param comentario comentario que se desea añadir a la ficha
     */
    public void  añadirComentarioReparacion(int numeroFichaReparacion, String comentario)
    {
        servicioTecnico.getFicha(numeroFichaReparacion).añadirComentario(comentario);
        gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFichaReparacion).getIdCliente()).setAccion("Ficha de reparacion:"+
        servicioTecnico.getUltimoNumeroFicha()+" comentario añadido en incidencia ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Añlade horas de mano de obra a la ficha de reparacion
     * @param horas horas de mano de hora
     * @param numeroFicha numero de ficha de reparacion
     */
    public void añadirManoDeObra(int numeroFicha,int horas)
    {
        servicioTecnico.getFicha(numeroFicha).sumarHorasTrabajadas(horas);
        gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de reparacion:"+
        servicioTecnico.getUltimoNumeroFicha()+" trabajo añadido en reparacion ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Comprueba si una ficha de reparacion existe
     * @param numero de ficha
     * @return true si la ficha de reparacion existe
     */
    public boolean existeFichaReparacion(int numeroFicha)
    {
        return servicioTecnico.getExisteFicha(numeroFicha);
    }
    
    /**
     * Devuelve un alista con el hitorial de reparacionese
     * @return lista de reparaciones
     */
    public ArrayList<String> getHistorialReparaciones()
    {
        return servicioTecnico.getHistorialReparaciones();
    }
    
    /**
     * Devuelve la lista de reparaciones del historial de un cliente
     * @param idCliente DNI del cliente
     * @return lista de reparacionees de un cliente
     */
    public ArrayList<String> getHistorialReparacionesCliente(String idCliente)
    {
        return servicioTecnico.getHistorialReparacionesCliente(idCliente);
    }
    
    /**
     * Devuelve la lista de reparaciones realizadas por el empleado actual
     * @return lista de reparacionese asignadas al empleado actual
     */
    public ArrayList<String> getHistorialReparacionesEmpleadoActual()
    {
        String idEmpleado = gestionUsuarios.getEmpleadoActual().getIdentificacion();
        return servicioTecnico.getHistorialReparacionesEmpleado(idEmpleado);
    }
    
    /**
     * Historial de reparaciones de un electrodomestico
     * @param idProducto codigo de producto
     * @param fichaCompra ficha de compra a la que pertenece el producto
     * @return lista de reparacionese realizadas a un electrodomestico
     */
    public ArrayList<String>  historialElectrodomestico(String idProducto, int fichaCompra)
    {
        return servicioTecnico.getHistorialElectrodomestico(idProducto,fichaCompra);
    }
    
    /**
     * Asigna una ficha de reparacion al empleado actual
     * @param numeroFichaReparacion numero de ficha de reparacion
     * return true si la ficha se asigna correctamente
     */
    public boolean asignarseFichaReparacion(int numeroFichaReparacion)
    {
        if(servicioTecnico.getExisteFicha(numeroFichaReparacion))
        {
             servicioTecnico.asignarTecnico(gestionUsuarios.getEmpleadoActual().getIdentificacion(),numeroFichaReparacion);
             gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFichaReparacion).getIdCliente()).setAccion("Ficha de reparacion:"+
             servicioTecnico.getUltimoNumeroFicha()+" incidencia asignada a tecnico ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
             return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Devuelve las fichas de reparacion asingadas al empleado actual
     * @return lista de reparaciones asignadas al empleado actual
     */
    public ArrayList<String>  fichasAsignadas()
    {
        String idEmpleado = gestionUsuarios.getEmpleadoActual().getIdentificacion();
        return servicioTecnico.getFichasAsignadas(idEmpleado);
    }
    
    /**
     * Devuelve las reparaciones que estan pendientes
     * @return lista de reparaciones pendientes
     */
    public ArrayList<String> getReparacionesPendientes()
    {
        return servicioTecnico.getReparacionesPendientes();
    }
    
    /**
     * Devuelve una lista de las piezas pendientes de recibir y que son necesarias para realizar una reparacion.
     * @return lista de piezas pendientes
     */
    public ArrayList<String> getPiezasPendientes()
    {
        return servicioTecnico.getPiezasPendientes();
    }
    
    /**
     * Devuelve en una lista todos los datos de una ficha de reparacion
     * @param numeroFicha numero de ficha de reparacion
     * @return datos de ficha de reparacion
     */
    public ArrayList<String> getFichaReparacion(int numeroFicha)
    {
        ArrayList<String> ficha = new ArrayList<String>();      
        ficha.add(servicioTecnico.getFicha(numeroFicha).toString());
        ficha.add("Producto: "+almacen.getProductoPorCp(servicioTecnico.getFicha(numeroFicha).getIdProducto()).toString());
        ficha.add("Comentarios:");
        int i=1;
        for(String comentario:servicioTecnico.getFicha(numeroFicha).getComentarios())
            {
                ficha.add(i+"   "+comentario);
                i++;
            }
        ficha.add("Piezas necesarias");
        i=1;
        for(String[] pieza:servicioTecnico.getFicha(numeroFicha).getListaPiezas())
            {
                ficha.add(i+servicioTecnico.getFicha(numeroFicha).piezaToString(pieza));
                i++;
            }
        ficha.add("Comunicaciones con el cliente");
        i=1;
        for(String comentario:servicioTecnico.getFicha(numeroFicha).getComunicaciones())
        {
            ficha.add(i+"   "+comentario);
            i++;
        }
        
        return ficha;
    }
    
    /**
     * Comprueba a que cliente esta asociada una ficha de reparacion
     * @param numeroFicha numero de ficha de reparacion
     * @return DNI del cliente
     */
    public String compruebaClienteReparacion(int numeroFicha)
    {
        if(servicioTecnico.getFicha(numeroFicha)!=null)
        {
            return servicioTecnico.getFicha(numeroFicha).getIdCliente();
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Añade a la ficha una comunicacion con el cliente
     * @param numeroFicha numero de ficha de reparacion
     * @param mensaje mensaje enviado al cliente
     */
    public void comunicarAlClienteReparacion(String mensaje,int numeroFicha)
    {
        servicioTecnico.getFicha(numeroFicha).comunicarCliente(mensaje);
        gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de reparacion:"+
        servicioTecnico.getUltimoNumeroFicha()+" comunicacion con el cliente ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Añade a la ficha de reparacion una pieza necesaria
     * @param numeroFicha numero de ficha de reparacion
     * @param idPieza identificacion de la pieza
     * @param descripcion descripcion de la pieza
     * @param precio precio de la pieza
     * @param proveedor proveedor de la pieza
     * @param cantidad numero de piezas necesarias
     */
    public void añadirPiezaNecesaria(int numeroFicha,String idPieza,String descripcion,String precio,String proveedor,String cantidad)
    {
        servicioTecnico.getFicha(numeroFicha).añadirPieza(idPieza,descripcion,precio,proveedor,cantidad,"pendiente");
        gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de reparacion:"+
        servicioTecnico.getUltimoNumeroFicha()+" añadida pieza necesaria a reparacion ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Quita un a pieza necesaria de la ficha de reparacion
     * @param idPieza identificacion de la pieza
     * @param numeroFicha numero de ficha de reparacion
     */
    public void quitarPiezaNecesaria(String idPieza, int numeroFicha)
    {
        servicioTecnico.getFicha(numeroFicha).quitarPieza(idPieza);
    }
    
    /**
     * Comprueba si una pieza se encuentra en la lista de piezas necesarias
     * @param numeroFicha numero de ficha de reparacion
     * @param numeroPieza identificacion de la pieza
     * @return true si la pieza esta en la lista
     */
    public boolean comprobarPiezaNecesaria(int numeroFicha, String numeroPieza)
    {
        return servicioTecnico.getFicha(numeroFicha).getExistePieza(numeroPieza);
    }
    
    /**
     * Marca una pieza necesaria par una reparacion como recibida
     * @param numeroFicha numero de ficha de reparacion
     * @param numeroPieza identificacion de la pieza 
     */
    public void recepcionPieza(int numeroFicha, String numeroPieza)
    {
        servicioTecnico.getFicha(numeroFicha).setEstadoPieza("recibida",numeroPieza);
        gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de reparacion:"+
        servicioTecnico.getUltimoNumeroFicha()+" pieza necesaria recibida ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Marca una reparacion como finalizada
     * @param numeroFicha numero de ficha de reparacion
     */
    public void finalizarReparacion(int numeroFicha)
    {
        servicioTecnico.getFicha(numeroFicha).setEstado("finalizado");
        gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de reparacion:"+
        servicioTecnico.getUltimoNumeroFicha()+" reparacion finalizada ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
    }
    
    /**
     * Calcula un presupuesto en funcion de la mano de obra y el coste de las piezas
     * @param numeroFicha numero de ficha de reparacion
     * @param precioManoObra precio de la mano de obra
     * @return presupuesto de reparacion
     */
    public String  presupuestoReparacion(int numeroFicha,double precioManoObra)
    {
        Date fechaDeCompra = caja.getFichaPorNumero(servicioTecnico.getFicha(numeroFicha).getNumeroFichaDeCompra()).getFecha();
        servicioTecnico.calcularPresupuesto(numeroFicha,fechaDeCompra,precioManoObra);
        gestionUsuarios.getCliente(servicioTecnico.getFicha(numeroFicha).getIdCliente()).setAccion("Ficha de reparacion:"+
        servicioTecnico.getUltimoNumeroFicha()+" presupueseto de reparacion emitido ",gestionUsuarios.getEmpleadoActual().getIdentificacion());
        return ("El coste de la reparacion es de: "+servicioTecnico.getFicha(numeroFicha).getPresupuesto());
    }
    
    /**
     * Devuelve en una lista todos los datos de una ficha de financiacion
     * @param numeroFicha numero de ficha de financiacion
     * @return datos de ficha de financiacion
     */
    public ArrayList<String>  fichaFinanciacion(int numeroFicha)
    {
        ArrayList<String>  ficha = new ArrayList<String>();
        ficha.add(financiacion.getFicha(numeroFicha).toString());
        ficha.add("Ultima nomina del cliente: "+financiacion.getFicha(numeroFicha).getUltimaNomina()+"€");
        ficha.add("Plazos solicitados para la financiacion: "+financiacion.getFicha(numeroFicha).getPlazos()+" meses");
        ficha.add("Resultado: "+financiacion.getFicha(numeroFicha).getEstado());
        
        return ficha;
    }
    
    /**
     * Indica el número de la última ficha de financiacion
     * @return número de la última ficha de financiación
     */
    public int getNumeroUltimaFichaFinanciacion()
    {
        return financiacion.getNumeroUltimaFicha();
    }   
    
    //METODOS NECESARIOS PARA DEPARTAMENTO COMERCIAL
    
    /**
     * Crea una ficha de promoción
     * @param idEmpeladoActual DNI del usuario actual
     * @param nombre nombre de la promoción
     * @param descripción descripción de la promoción
     * @return número de ficha de promoción
     */
    public int crearFichaPromocion(String nombre,String descripcion)
    {
        return comercial.crearFicha(gestionUsuarios.getEmpleadoActual().getIdentificacion(),nombre,descripcion);
    }
    
    /**
     * Activa/desactiva una promoción
     * @param trigger true para activar y false para desactivar
     * @param numeroFicha numero de ficha de promocion
     */
    public boolean triggerPromocion(boolean trigger,int numeroFicha)
    {
        return comercial.triggerPromocion(trigger,numeroFicha);
    }
    
    /**
     * Muestra las promociones activas
     * return lista de promociones activas
     */
    public ArrayList<String> getPromocionesActivas()
    {
        return comercial.getPromocionesActivas();
    }
    
    /**
     * Muestra las promociones 
     * return lista de promociones 
     */
    public ArrayList<String> getPromociones()
    {
        return comercial.getPromociones();
    }
    
    /**
     * Añade un producto a la promoción
     * @param numeroFicha número de la ficha de promoción
     * @param descuento descuento a aplicar en el producto
     * @param codigoProducto producto que queremos añadir
     * return true si el producto se añade correctamente
     */
    public boolean añadirProductoPromocion(String codigoProducto,int descuento,int numeroFicha)
    {
        return comercial.añadirProducto(codigoProducto,descuento,numeroFicha);
    }
    
    /**
     * Quita un producto de  la promoción
     * @param numeroFicha número de la ficha de promoción
     * @param codigoProducto producto que queremos añadir
     * return true si el producto se añade correctamente
     */
    public boolean quitarProductoPromocion(String codigoProducto,int numeroFicha)
    {
        return comercial.quitarProducto(codigoProducto,numeroFicha);
    }
    
    /**
     * Devuelve en una lista todos los datos de una ficha de promocion
     * @param numeroFicha numero de ficha de promocion
     * @return datos de ficha de promocion
     */
    public ArrayList<String>  getFichaPromocion(int numeroFicha)
    {
        ArrayList<String>  ficha = new ArrayList<String>();       
        ficha.add("***FICHA DE PROMOCION***");
        if(comercial.getFicha(numeroFicha)!=null)
        {
            HashMap<String,Integer> ListaDeProductos = comercial.getFicha(numeroFicha).getListaProductos();
            ficha.add(comercial.getFicha(numeroFicha).toString());
            ficha.add("***productos rebajados:");
            ListaDeProductos.forEach((codigoProducto,descuento)->ficha.add(almacen.getDescripcionCorta(codigoProducto)+" con un descuento de "+
            descuento+"%") ); 
        }
        else
        {
            ficha.add("La ficha buscada no existe");
        }
        return ficha;
    }
    
    /**
     * Devuelve en una lista todos los datos de una ficha de promocion
     * @param numeroFicha numero de ficha de promocion
     * @return datos de ficha de promocion
     */
    public ArrayList<String>  getClientesObjetivo(int numeroFicha)
    {
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> listaClientes =gestionUsuarios.getClientes();
        Date fechaActual=new Date();
        Date fecha;
        long plazo;
        
        if(!comercial.getExiste(numeroFicha))
        {
            lista.add("La ficha no existe");
            return lista;
        }
        
        for(String entrada:listaClientes)
        {
            fecha=comercial.fechaPromocion(entrada,numeroFicha);
            if(fecha!=null)
            {
                plazo=fechaActual.getTime()-fecha.getTime();
                plazo=plazo/(365*24*60*60*1000);
                if(plazo>1)
                {
                    String cliente = gestionUsuarios.getCliente(entrada).toString();
                    lista.add(cliente);
                }
            }
            else
            {
                String cliente = gestionUsuarios.getCliente(entrada).toString();
                lista.add(cliente);
            }
        }
        
        if(lista.size()==0)
        {
            lista.add("No hay clientes objetivo");
        }
        return lista;

    }
    
    /**
     * Escribe un correo con un mensaje automatizado
     * @param promocion numero de ficha de promocion
     * @param idCliente identificacion del cliente
     * @param nombreCliente nombre del cliente
     * @param correo direccion de correo del cliente
     * @return correo electronico redactado
     */
    public ArrayList<String> getCorreo(String idCliente,String nombreCliente,String correo, int promocion)
    {
        ArrayList<String> correoElectronico=new ArrayList<String>();
        HashMap<String,Integer> ListaDeProductos = comercial.getFicha(promocion).getListaProductos();
        
        correoElectronico.add("From: SIG tienda POO");
        correoElectronico.add("To: "+correo);
        correoElectronico.add("Subject: "+comercial.getFicha(promocion).getNombre());
        correoElectronico.add("Hola "+nombreCliente);
        correoElectronico.add("Desde SIG tienda POO tenemos el placer de ofrecerte la siguiente promocion:");
        correoElectronico.add(comercial.getFicha(promocion).getDescripcion());
        correoElectronico.add("Productos rebajados:");
        
        ListaDeProductos.forEach((codigoProducto,descuento)->correoElectronico.add(almacen.getProductoPorCp(codigoProducto).toString()+
        " con un descuento de "+ descuento+"%") );
        
        correoElectronico.add("Gracias por confiar en nosotros");
        
        return correoElectronico;
    }
    
    /**
     * Escribe un correo con un mensaje automatizado
     * @param promocion numero de ficha de promocion
     * @param idCliente identificacion del cliente
     * @return true si se ha enviado correctamente
     */
    public boolean enviarPromocion(String idCliente,int promocion)
    {
       if(!gestionUsuarios.getExisteCliente(idCliente)||comercial.getExiste(promocion))
       {
           return false;
       }
       String nombreCliente = gestionUsuarios.getCliente(idCliente).getIdentificacion();
       String correo = gestionUsuarios.getCliente(idCliente).getCorreoElectronico();
       ArrayList<String> contenido = getCorreo(idCliente,nombreCliente,correo,promocion);
       gestionUsuarios.getCliente(idCliente).setAccion("Promoción enviada:"+promocion+" "+comercial.getFichaPromocion(promocion).getNombre(),
       gestionUsuarios.getEmpleadoActual().getIdentificacion());
       return  comercial.enviarPromocion(idCliente,gestionUsuarios.getEmpleadoActual().getIdentificacion(),promocion,contenido);
    }
    
    /**
     * Lista de todos los correos enviados en la tienda
     * @return resumen de todos los correos enviados
     */
    public ArrayList<String> historialCorreos()
    {
        return comercial.historialCorreos();
    }
    
    
    /**
     * Consulta un correo electrónico
     * @param numeroCorreo numero de correo electronico
     */
    public ArrayList<String>  consultaCorreo(int numeroCorreo)
    {
        return comercial.getCorreo(numeroCorreo);
    }
    
    
    //METODOS NECESARIOS PARA LA PERSISTENCIA DE DATOS
    
    /**
     * Guarda todos los datos necesarios para la persistencia de datos.
     * en uso
     */
    public void guardarDatos()
    {
        almacen.escribeArchivo();
        gestionUsuarios.escribeArchivoClientes();
        gestionUsuarios.escribeArchivoEmpleados();
        caja.escribeArchivo();
        postVenta.escribeArchivo();
        financiacion.escribeArchivo();
        servicioTecnico.escribeArchivo();
        comercial.escribeArchivoCorreos();
        comercial.escribeArchivoPromociones();
    }
    
    /**
     * Carga  todos los datos necesarios para la persistencia de datos.
     */
    public void cargarDatos()
    {
        almacen.leeArchivo();
        gestionUsuarios.leeArchivoClientes();
        gestionUsuarios.leeArchivoEmpleados();
        caja.leeArchivo();
        postVenta.leeArchivo();
        financiacion.leeArchivo();
        servicioTecnico.leeArchivo();
        comercial.leeArchivoCorreos();
        comercial.leeArchivoPromociones();
    }
}
