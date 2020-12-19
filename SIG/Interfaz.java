import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
/**
 * Sirve de sistema de comunicación con el usuario. Imprime en pantalla las ordenes posibles y los resultados de ejecutarlas.
 * Incluye métodos para guiar al usuario durante los procesos de venta, postventa, reparaciones... recopilando los datos necesarios para
 * comunicarselos al gestor principal del programa.
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.17
 */

public class Interfaz
{
    // instance variables - replace the example below with your own
    private Lector lector; //scanner que emplearemos para hacer lecturas por pantalla
    private ArrayList<String> listaOrdenesAlmacen,listaOrdenesPosventa,listaOrdenesUsuarios,listaOrdenesCaja,listaOpcionesInicio,
    listaOrdenesFinanciacion,listaOrdenesServicioTecnico,listaOrdenesComercial; //listas de ordenes que se imprimiran en pantalla
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-yyyy"); //formato empleado para las fechas
    private Date date;  //fecha actual
    private SistemaGestion sistemaGestion; // gestor principal del programa
    
    /**
     * Crea las listas de ordenes e instancia un objeto "lector" para recibir ordenes
     * Añade al objeto date la fecha actual
     */
    public Interfaz()
    {
        date = new Date();
        lector = new Lector();
        
        sistemaGestion= new SistemaGestion(formatoFecha);
        
        listaOrdenesAlmacen = new ArrayList<String>();
        listaOrdenesUsuarios = new ArrayList<String>();
        listaOrdenesCaja = new ArrayList<String>();
        listaOpcionesInicio = new ArrayList<String>();
        listaOrdenesFinanciacion = new ArrayList<String>();
        listaOrdenesPosventa = new ArrayList<String>();
        listaOrdenesServicioTecnico = new ArrayList<String>();
        listaOrdenesComercial = new ArrayList<String>();
        setListaOrdenes();
    }
    
    /**
     * Inicia la interfaz textual del programa. 
     * Solicita el login del usuario y si es correcto muestra la pantalla con las opciones de inicio
     */
    
    public  void inicio()
    {
        String orden;
        boolean finalizado;      
        imprimirBienvenida();
        finalizado=login();
        
        while(finalizado==false)
        {   
            opcionesDeInicio();           
            System.out.println();
            orden=lector.getPalabra();
            
            switch (orden)
            {
                
                case "1": 
                    gestionCaja();
                    break;
                
                case "2": 
                    gestionFinanciacion();
                    break;
                
                case "3": 
                    gestionServicioTecnico() ;
                    break;
                
                case "4": 
                    gestionComercial() ;
                    break;
                
                case "5": 
                    gestionPosventa() ;
                    break;
                
                case "6": 
                    gestionUsuarios() ;
                    break;
                    
                case "7": 
                    gestionDeAlmacen() ;
                    break;
                
                case "8":
                    login();
                    break;
                
                case "9": 
                    acercaDelPrograma();
                    break;
              
                case "10":
                    System.out.println("Introduzca el DNI del cliente");
                    String idCliente=lector.getPalabra();
                    imprimeArray(sistemaGestion.getHistorialCliente(idCliente));  
                    break;
                
                case "11": 
                    finalizado=true;
                    break;
                               
                //Salida por defecto para entradas erroneas
                default: 
                System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                break;

            }
            
            sistemaGestion.guardarDatos();
        }
    }

    /**
     * Solicita por pantalla el codigo de empleado y la clave de acceso de este empleado.
     * @return Devuelve true si los datos son correctos.
     */
    
    public boolean login()
    {
        System.out.println("Introduzca su codigo de empleado");
        String id = lector.getPalabra();
        System.out.println("Introduzca su clave de acceso");
        String clave = lector.getPalabra();
        if(sistemaGestion.login(id,clave))
        {
            System.out.println("Login correcto");
            return false;
        }
        else
        {
            System.out.println("Login fallido");
            return true;
        }
        
    }
    
    /**
     * Imprime en pantalla el mensaje de bienvenida
     */
    public void imprimirBienvenida( )
    {
        imprimeDivision();
        imprimeDivision();
        System.out.println("Practica de Programación Orientada a Objetos – Curso 2017-2018");
        imprimeDivision();
        System.out.println("Alumno:               Ivan Adrio Muñiz");   
        System.out.println("Correo electronico:   ivan.adrio@gmail.com");
        System.out.println("Telfono de contacto:  660634998");
        imprimeDivision();
    }
    
    /**
     * Imprime en pantalla las opciones disponibles al inicio del programa
     */
    public void opcionesDeInicio( )
    {
        imprimeDivision();
        imprimeDivision();
        System.out.println(formatoFecha.format(date)+" Usuario actual:"+sistemaGestion.identificarEmpleadoActual()); 
        System.out.println("Permisos: "+sistemaGestion.listaPermisosUsuarioActual());
        imprimeDivision();
        System.out.println("Escoja la opcion que desee escribiendo el numero correspondiente");
        imprimeDivision();
        getOpcionesInicio();
        System.out.println();
    }
    
    
    /**
     * Imprime en pantalla las opciones disponibles para gestionar el almacén.
     */
    public void gestionDeAlmacen()
    {
        String orden,codigoProducto;
        int cantidad;
        boolean finalizado;

        if(sistemaGestion.comprobarPermisoUsuarioActual("administrador"))
        {
            finalizado=false;
        }
        else
        {
            finalizado=true;
        }
        
        while(!finalizado)
        {
            getListaOrdenesAlmacen();
            orden = lector.getPalabra();
            
            switch (orden){
                
                case "1":
                    System.out.println("STOCK EN ALMACEN");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getStock());
                    imprimeDivision();
                    break;
                
                case "2":
                    System.out.println("Escriba el codigo del producto");
                    codigoProducto = lector.getPalabra();
                    System.out.println("Escriba la cantidad");
                    cantidad = lector.getEntero();
                    if(sistemaGestion.recepcionPedidos(codigoProducto,cantidad))
                    {
                        System.out.println("Pedido registrado correctamente");
                    }
                    else
                    {
                        System.out.println("El codigo de producto no existe o la cantidad introducida es menor que 0");
                    }
                    break;
                
                case "3": 
                    System.out.println("Escriba el codigo del producto a eliminar de la base de datos");
                    codigoProducto = lector.getPalabra();
                    if(sistemaGestion.eliminarProducto(codigoProducto))
                    {
                        System.out.println("Producto eliminado!");
                    }
                    else
                    {
                        System.out.println("El codigo de producto no existe");
                    }
                    break;
                
                case "4": 
                    añadirProducto();
                    break;
                    
                case "5":
                    System.out.println("Escriba el codigo del producto que desea consultar");
                    codigoProducto = lector.getPalabra();
                    System.out.println(sistemaGestion.describirProducto(codigoProducto));
                    break;
                    
                case "6":
                    modificarProducto();
                    break;
    
                case "7":
                    finalizado=true;
                    break;
                
                case "volver":
                    finalizado=true;
                    break;
                
                //Salida por defecto para entradas erroneas
                default: 
                    System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                    break;
            }
        }
    }
    
    
    /**
     * Recopila datos de un producto por pantalla
     * @return datos de producto
     */
    public void añadirProducto()
    {
        boolean añadido = false;
        String codigoDeProducto="";
        String color="";
        String modelo="";
        String marca="";
        String tipo="";
        int cantidad=0;
        int ventas=0;
        double precio=0;

        System.out.println("¿Que tipo de producto?");
        System.out.println("(1)TV");
        System.out.println("(2)Reproductor");
        System.out.println("(3)Camara");
        System.out.println("(4)Reproductor musica");
        System.out.println("(5)Sistema sonido");
        System.out.println("(6)Altavoces");
        System.out.println("(7)Ordenador");
        System.out.println("(8)Smartphne");
        System.out.println("(9)pequeño electrodomestico");
        System.out.println("(10)vitroceramica");
        System.out.println("(11)lavadora");
        System.out.println("(12)nevera");
        System.out.println("(13)horno");
        System.out.println("(14)Volver");
        String orden = lector.getPalabra();
        
        if(!orden.equals("14")){
            System.out.println("Introduzca el codigo de producto");
            codigoDeProducto=lector.getPalabra();
            if(!sistemaGestion.comprobarCodigoProductoValido(codigoDeProducto))
            {
                boolean valido = false;
                while(!valido)
                {
                    System.out.println("El codigo de producto debe tener "+sistemaGestion.getLongitudCodigoProducto()+" digitos");
                    codigoDeProducto=lector.getPalabra();
                    valido=sistemaGestion.comprobarCodigoProductoValido(codigoDeProducto);
                }
                
            }
            System.out.println("Introduzca la marca del producto");
            marca=lector.getPalabra();
            System.out.println("Introduzca el modelo del producto");
            modelo=lector.getPalabra();
            System.out.println("Introduzca el color del producto");
            color=lector.getPalabra();
            System.out.println("Introduzca el precio del producto");
            precio=lector.getReal();         
            System.out.println("Introduzca el numero de articulos inicial");
            cantidad=lector.getEntero();
        }        

        switch (orden)
        {            
            case "1":
                añadirTv(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
            
            case "2":
                añadirReproductor(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
            
            case "3":
                añadirCamara(codigoDeProducto,marca,modelo,color,precio,cantidad);                
                break;
                
            case "4":
                añadirReproductorMusica(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
                
            case "5":
                añadirSistemaSonido(codigoDeProducto,marca,modelo,color,precio,cantidad);                
                break;
             
            case "6":
                añadirAltavoces(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
                
            case "7":
                añadirOrdenador(codigoDeProducto,marca,modelo,color,precio,cantidad);                
                break;
                
            case "8":
                añadirSmartphone(codigoDeProducto,marca,modelo,color,precio,cantidad);                
                break;
                
            case "9":
                añadirPequeñoElectrodomestico(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
                
            case "10":
                añadirVitroceramica(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
                
            case "11":
                añadirLavadora(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
                
            case "12":
                añadirNevera(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
                
            case "13":
                añadirHorno(codigoDeProducto,marca,modelo,color,precio,cantidad);
                break;
            
            case "14":
                break;
                       
            default: 
                System.out.println("La opcion elegida no existe");
                break;
        }

    }
    
    
    public boolean añadirTv(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String resolucion;
        int tamaño,frecuencia;
        System.out.println("Introduzca el tamaño de pantalla en pulgadas");
        tamaño=lector.getEntero();
        System.out.println("Introduzca la resolucion de la pantalla");
        resolucion=lector.getPalabra();
        System.out.println("Introduzca la frecuencia de refresco de pantalla");
        frecuencia=lector.getEntero();
        return sistemaGestion.añadirTv(codigoDeProducto,marca,modelo,color,precio,cantidad,tamaño,frecuencia,resolucion);
    }
    
    public boolean añadirReproductor(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String formato;
        System.out.println("Introduzca el formato del reproductor(DVD,Bluray,VHS...)");
        formato=lector.getPalabra();
        return sistemaGestion.añadirReproductor(codigoDeProducto,marca,modelo,color,precio,cantidad, formato);
    }
    
    public boolean añadirCamara(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String resolucion,pixeles;
        System.out.println("Introduzca la resolución de video");
        resolucion=lector.getPalabra();
        System.out.println("Megapixeles del sensor fotografico");
        pixeles=lector.getPalabra();
        return sistemaGestion.añadirCamara(codigoDeProducto,marca,modelo,color,precio,cantidad, resolucion,pixeles);
    }
    
    public boolean añadirAltavoces(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String potencia;
        System.out.println("¿Que potencia tienen los altavoces?");
        potencia=lector.getPalabra();
        return sistemaGestion.añadirAltavoces(codigoDeProducto,marca,modelo,color,precio,cantidad, potencia);
    }
    
    public boolean añadirSistemaSonido(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String potencia,formatos;
        System.out.println("¿Que potencia tienen los altavoces?");
        potencia=lector.getPalabra();
        System.out.println("¿Que formatos admite el reproductor? Introduzcalos en una sola linea separados por comas");
        formatos=lector.getPalabra();
        return sistemaGestion.añadirSistemaSonido(codigoDeProducto,marca,modelo,color,precio,cantidad, potencia,formatos);
    }
    
    public boolean añadirReproductorMusica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String formatos;
        System.out.println("¿Que formatos admite este reproductor?Introduzcalos en una sola linea separados por comas");
        formatos=lector.getPalabra();
        return sistemaGestion.añadirReproductorMusica(codigoDeProducto,marca,modelo,color,precio,cantidad, formatos);
    }
    
    public boolean añadirOrdenador(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String ram,discoDuro,procesador,tarjetaGrafica,bateria,tipo;
        System.out.println("Cantidad de memoria ram.Introduzca las unidades (tb,gb,mb...)");
        ram=lector.getPalabra();
        System.out.println("Cantidad de disco duro.Introduzca las unidades (tb,gb,mb...)");
        discoDuro=lector.getPalabra();
        System.out.println("Modelo de procesador");
        procesador=lector.getPalabra();
        System.out.println("Modelo de tarjeta gráfica");
        tarjetaGrafica=lector.getPalabra();
        System.out.println("Batería.Introduzca las unidades(mAh)");
        bateria=lector.getPalabra();
        System.out.println("¿Que potencia tipo de ordenador es? Portatil, sobremesa, servidor...");
        tipo=lector.getPalabra();
        return sistemaGestion.añadirOrdenador(codigoDeProducto,marca,modelo,color,precio,cantidad, ram ,  discoDuro, procesador, tarjetaGrafica, bateria, tipo);
    }
    
    public boolean añadirSmartphone(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String ram,memoria,procesador,tamañoPantalla,bateria,resolucionPantalla;
        System.out.println("Cantidad de memoria ram.Introduzca las unidades (tb,gb,mb...)");
        ram=lector.getPalabra();
        System.out.println("Modelo de procesador");
        memoria=lector.getPalabra();
        System.out.println("Memoria interna.Introduzca las unidades (tb,gb,mb...)");
        procesador=lector.getPalabra();
        System.out.println("Tamaño de pantalla");
        tamañoPantalla=lector.getPalabra();
        System.out.println("Resolucion de pantalla");
        resolucionPantalla=lector.getPalabra();
        System.out.println("Batería.Introduzca las unidades(mAh)");
        bateria=lector.getPalabra();
        return sistemaGestion.añadirSmartphone(codigoDeProducto,marca,modelo,color,precio,cantidad,tamañoPantalla,resolucionPantalla,ram,memoria,procesador, bateria);
    }
    
    public boolean añadirPequeñoElectrodomestico(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String tipo,descripcion;
        System.out.println("¿Que tipo de producto es?");
        tipo=lector.getPalabra();
        System.out.println("Describa el producto");
        descripcion=lector.getPalabra();
        return sistemaGestion.añadirPequeñoElectrodomestico(codigoDeProducto,marca,modelo,color,precio,cantidad,tipo, descripcion);
    }
    
    public boolean añadirVitroceramica(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String etiquetaEnergetica,tipo,fuegos,potencia;
        System.out.println("Calificacion energetica (A,A++,B,C...)");
        etiquetaEnergetica=lector.getPalabra();
        System.out.println("Tipo de vitroceramica (induccion o vitro)");
        tipo=lector.getPalabra();
        System.out.println("Número de fuegos");
        fuegos=lector.getPalabra();
        System.out.println("Potencia de la vitroceramica");
        potencia=lector.getPalabra();
        return sistemaGestion.añadirVitroceramica(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica, tipo, fuegos, potencia);
    }
    
    public boolean añadirNevera(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String etiquetaEnergetica,altura,anchura,volumen;
        System.out.println("Calificacion energetica (A,A++,B,C...)");
        etiquetaEnergetica=lector.getPalabra();
        System.out.println("Altura en cm");
        altura=lector.getPalabra();
        System.out.println("Anchura en cm");
        anchura=lector.getPalabra();
        System.out.println("Volumen útil en litros");
        volumen=lector.getPalabra();
        return sistemaGestion.añadirNevera(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica,altura,anchura,volumen);
    }
    
    public boolean añadirLavadora(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String etiquetaEnergetica,capacidad, tipoCarga, revoluciones;
        System.out.println("Calificacion energetica (A,A++,B,C...)");
        etiquetaEnergetica=lector.getPalabra();
        System.out.println("Tipo de carga: superior, frontal...");
        tipoCarga=lector.getPalabra();
        System.out.println("Carga máxima");
        capacidad=lector.getPalabra();
        System.out.println("Revoluciones del centrifugado");
        revoluciones=lector.getPalabra();
        return sistemaGestion.añadirLavadora(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica, capacidad, tipoCarga, revoluciones);
    }
    
    public boolean añadirHorno(String codigoDeProducto,String marca, String modelo,String color, double precio, int cantidad){
        String etiquetaEnergetica,capacidad,potencia;
        System.out.println("Calificacion energetica (A,A++,B,C...)");
        etiquetaEnergetica=lector.getPalabra();
        System.out.println("Potencia del horno");
        potencia=lector.getPalabra();
        System.out.println("Volumen útil");
        capacidad=lector.getPalabra();
        return sistemaGestion.añadirHorno(codigoDeProducto,marca,modelo,color,precio,cantidad,etiquetaEnergetica, capacidad, potencia);
    }
    
    /**
     * Guia al usuario durante el proceso de modificar un producto existente en el almacen. Le muestra las opciones disponibles y 
     * recopila por pantalla los datos necesarios para cada opcion.
     */
    public void modificarProducto()
    {
        System.out.println("¿Que producto desea modificar? Introduzca el codigo de producto");
        String codigoProducto = lector.getPalabra();
        System.out.println(sistemaGestion.describirProducto(codigoProducto));
        if(!sistemaGestion.existeProducto(codigoProducto))
        {
            return;
        }
        
        if(lector.pedirConfirmacion())
        {
            sistemaGestion.eliminarProducto(codigoProducto);
            añadirProducto();
        }
        else
        {
            System.out.println("Operación abortada");
            return;
        }
        
    }        
    
    /**
     * Comprueba si un usuario tiene permisos para acceder a esta opcion y si los tiene le muestra las opcionese disponibles para 
     * gestionar los usuarios de la aplicacion (alta, baja, modificacion,gestion de permisos etc). Una vez el usuario a indicado que 
     * ha terminado de ralizar las gestionaes necesarias lo devuelve a la pantalla inicial.
     */
    public void gestionUsuarios()
    {
        String orden,identificacion,permiso,rol=null,clave=null,nombre=null,apellidos=null,correoElectronico=null,telefono=null;
        boolean finalizado;
        System.out.println();
        System.out.println();
        
        
        if(sistemaGestion.comprobarPermisoUsuarioActual("administrador"))
        {
            finalizado=false;
        }
        else
        {
            finalizado=true;
        }
        
        
        while(!finalizado)
          {
             getListaOrdenesUsuarios();
             orden = lector.getPalabra();
             if(orden.equals("1")||orden.equals("2")||orden.equals("12")||orden.equals("13")){
                System.out.println("Introduzca el nombre del usuario");
                nombre=lector.getPalabra();
                System.out.println("Introduzca los apellidos del usuario");
                apellidos=lector.getPalabra();
                System.out.println("Introduzca el correo electronico del usuario");
                correoElectronico=lector.getPalabra();
                System.out.println("Introduzca el numero de telefono del usuario");
                telefono=lector.getPalabra();                   
             }
    
             switch (orden){
                
                case "1":
                    añadirEmpleado(nombre,apellidos,correoElectronico,telefono);
                    break;
                
                case "2":
                    System.out.println("Introduzca el DNI. Se usara como identifificador");
                    identificacion=lector.getPalabra();
                    
                    if(identificacion.equals("")){
                        System.out.println("El codigo de cliente no es correcto");
                        break;
                    }
                    
                    if(sistemaGestion.añadirCliente(identificacion,nombre,apellidos,correoElectronico,telefono))
                    {
                        System.out.println("Cliente añadido correctamente");
                    }
                    else
                    {
                        System.out.println("El cliente ya existe");   
                    }
                    break;
                    
                case "3":
                    System.out.println("Introduzca el DNI del cliente");
                    if(sistemaGestion.quitarCliente(lector.getPalabra())){
                        System.out.println("El cliente se ha eliminado correctamente");
                    }else{
                        System.out.println("El cliente no existe");
                    }
                    break;
                
                case "4":
                    System.out.println("LISTA DE EMPLEADOS");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getListaEmpleados());
                    imprimeDivision();
                    break;
                
                case "5":
                    System.out.println("LISTA DE CLIENTES");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getListaClientes());
                    imprimeDivision();
                    break;
                
                case "6":
                    System.out.println("Introduzca el codigo de empleado");
                    System.out.println("DATOS DEL EMPLEADO");
                    imprimeDivision();
                    System.out.println(sistemaGestion.describeEmpleado(lector.getPalabra()));
                    imprimeDivision();
                    break;
                
                case "7":
                    System.out.println("Introduzca el codigo de cliente");
                    identificacion = lector.getPalabra();
                    System.out.println("FICHA DEL CLIENTE: "+identificacion);
                    imprimeDivision();
                    imprimeDivision();
                    System.out.println(sistemaGestion.describeCliente(identificacion));
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getHistorialCliente(identificacion));
                    imprimeDivision();
                    break;
                
                case "8":
                    System.out.println("Escriba el nombre del empleado/s");
                    identificacion=lector.getPalabra();
                    System.out.println("LISTA DE COINCIDENCIAS");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getEmpleadoNombre(identificacion));
                    imprimeDivision();
                    break;
                
                case "9":
                    System.out.println("Escriba el nombre del cliente/s");
                    identificacion=lector.getPalabra();
                    System.out.println("LISTA DE COINCIDENCIAS");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getClienteNombre(identificacion));
                    imprimeDivision();
                    break;
                
                case "10":
                    System.out.println("Introducza el codigo de empleado");
                    identificacion = lector.getPalabra();
                    System.out.println("Escriba el permiso a añadir: "+ sistemaGestion.getPermisosEmpleadoValidos());
                    permiso = lector.getPalabra();
                    
                    if(sistemaGestion.añadirPermiso(identificacion,permiso))
                    {
                        System.out.println("El cambio se hara efectivo en el siguiente login");    
                    }
                    else
                    {
                        System.out.println("Los datos introducidos no son correctos"); 
                    }
                    break;
                
                case "11":
                    System.out.println("Introducza el codigo de empleado");
                    identificacion = lector.getPalabra();
                    System.out.println("Escriba el permiso a retirar: "+sistemaGestion.getPermisosEmpleadoValidos());
                    permiso = lector.getPalabra();
                    if(sistemaGestion.quitarPermiso(identificacion,permiso))
                    {
                        System.out.println("El cambio se hara efectivo en el siguiente login");    
                    }
                    else
                    {
                        System.out.println("Los datos introducidos no son corrrectos"); 
                    }
                    break;
                
                case "12":
                    System.out.println("Introducza el codigo de cliente");
                    identificacion = lector.getPalabra();

                    if(sistemaGestion.modificarCliente(identificacion,nombre,apellidos,correoElectronico,telefono))
                    {
                        System.out.println("Cliente modificado correctamente");
                    }
                    else
                    {
                        System.out.println("El cliente no existe");
                    }
                    break;
                
                case "13":
                    modificarEmpleado(nombre,apellidos,correoElectronico,telefono);
                    break;
                                     
                case "14":
                    finalizado=true;
                    break;
                
                case "volver":
                    finalizado=true;
                    break;
    
                //Salida por defecto para entradas erroneas
                default: 
                System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                break;
            }
        }
    }   
    
    /**
     * Añade un empleado al sistema
     * @param nombre nombre del empleado
     * @param apellidos apellidos del empleado
     * @param correoElectronico correo electrónico del empleado
     * @param telefono telefono de contacto del empleado
     */
    public void añadirEmpleado(String nombre,String apellidos,String correoElectronico,String telefono)
    {
        String orden,identificacion,permiso,rol=null,clave=null;
       
        System.out.println("Introduzca el DNI. Se usuara como identifificador");
        identificacion=lector.getPalabra();
        if(identificacion.equals("")||nombre.equals("")||apellidos.equals(""))
        {
            System.out.println("Los campos identificacion, nombre y apellidos no pueden ser nulos");
            return;
        }
        
        boolean salir=false;
        boolean noValido = sistemaGestion.existeEmpleado(identificacion);
        while(noValido){
            System.out.println("El codigo de empleado ya existe, intentalo con otro o pulsa enter para salir");
            identificacion=lector.getPalabra();
            noValido = sistemaGestion.existeEmpleado(identificacion);
            if(identificacion.equals("")){
                noValido=false;
                salir=true;
            }
        }
        
        
        if(!salir){
            boolean valido =false;
            while(!valido){
                System.out.println("Indique el rol del empleado: "+sistemaGestion.listaRolesEmpleado());
                rol=lector.getPalabra();
                if(sistemaGestion.comprobarRolEmpleadoValido(rol)){
                    valido=true;
                }
                else{
                    System.out.println("El rol no es valido");
                }
            }
            
            valido=false;
            while(!valido){
                System.out.println("Introduzca su clave. Debera recordarla para acceder al sistema");
                clave=lector.getPalabra();
                if(clave.length()==sistemaGestion.longitudClaveEmpleado()){
                    valido=true;
                }
                else{
                    System.out.println("Longitud de clave no valida, debe contener "+sistemaGestion.longitudClaveEmpleado()+" digitos");
                }
            }
                
            if(sistemaGestion.añadirEmpleado(identificacion,nombre,apellidos,correoElectronico,telefono,clave,rol)){
                System.out.println("Empleado añadido correctamente");
            }
            else{                
                System.out.println("El empleado ya existe");
            }
        }  
    }
    
    /**
     * Modifica los datos de un empleado
     * @param nombre nombre del empleado
     * @param apellidos apellidos del empleado
     * @param correoElectronico correo electrónico del empleado
     * @param telefono telefono de contacto del empleado
     */
    public void modificarEmpleado(String nombre, String apellidos, String correoElectronico, String telefono)
    {
        String identificacion,clave,rol;
        clave=null;
        rol=null;
        System.out.println("Introducza el codigo de empleado");
        identificacion = lector.getPalabra();
        boolean valido = false;
        while(!valido)
            {
                System.out.println("Indique el rol del empleado: "+sistemaGestion.listaRolesEmpleado());
                rol=lector.getPalabra();
                if(sistemaGestion.comprobarRolEmpleadoValido(rol))
                {
                    valido=true;
                }
                else
                {
                    System.out.println("El rol no es valido");
                }
            }
        valido=false;
        while(!valido)
            {
                System.out.println("Introduzca la nueva clave");
                clave=lector.getPalabra();
                if(clave.length()==sistemaGestion.longitudClaveEmpleado())
                {
                    valido=true;
                }
                else
                {
                    System.out.println("Longitud de clave no valida, debe contener "+sistemaGestion.longitudClaveEmpleado()+" digitos");
                }
            }
                        
        if(sistemaGestion.modificarEmpleado(identificacion,nombre,apellidos,correoElectronico,telefono,clave,rol))
        {
            System.out.println("Empleado modificado correctamente");
        }
        else
        {
            System.out.println("El empleado no existe");
        }
    }
    
    /**
     * Comprueba si un usuario tiene permisos para acceder a esta opcion y si los tiene le muestra las opciones disponibles para 
     * gestionar la caja de la tienda (ventas, consulta de historicos, consutlar fichas de compra...). Una vez el usuario a indicado que 
     * ha terminado de ralizar las gestionaes necesarias lo devuelve a la pantalla inicial.
     */
    public void gestionCaja()
    {
        String orden,identificacion,idProducto;
        int cantidad,numeroDeFicha;
        boolean finalizado;
        System.out.println();
        System.out.println();
        
        
        if(sistemaGestion.comprobarPermisoUsuarioActual("cajero"))
        {
            finalizado = false;
        }
        else
        {
            finalizado = true;
        }
        
        while(!finalizado)
        {
            getListaOrdenesCaja();
            orden = lector.getPalabra();
            switch (orden){
                
                case "1":
                    System.out.println("Introduzca el DNI del cliente");
                    vender(lector.getPalabra());
                    break;
                
                case "2":
                    System.out.println("HISTORIAL DE VENTAS");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getHistorialVentas());
                    imprimeDivision();
                    break;
                
                case "3":
                    System.out.println("Introduzca el codigo del cliente");
                    identificacion = lector.getPalabra();
                    System.out.println("HISTORIAL DE VENTAS DE: "+identificacion);
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getFichasCompraCliente(identificacion));
                    imprimeDivision();
                    break;
                
                case "4":
                    System.out.println("Introduzca el numero de ficha");
                    numeroDeFicha = lector.getEntero();
                    System.out.println("FICHA DE COMPRA");
                    imprimeDivision();
                    imprimeFichaCompra(numeroDeFicha);
                    imprimeDivision();
                          
                case "5":
                    finalizado = true;
                    break;
                
                case "volver":
                    finalizado = true;
                    break;
                
                //Salida por defecto para entradas erroneas
                default: 
                    System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                    break;
            }
        }
    }
    
    /**
     * Comprueba si un usuario tiene permisos para acceder a esta opcion y si los tiene le muestra las opciones disponibles para 
     * gestionar las financiaciones de la tienda (consultar gestiones pendientes, gestionar fichas de financiacion, consultar 
     * historicos...). Una vez el usuario a indicado que ha terminado de ralizar las gestionaes necesarias lo devuelve a la pantalla 
     * inicial.
     */
    public void gestionFinanciacion()
    {
        String orden,identificacion;
        boolean finalizado;

        if(sistemaGestion.comprobarPermisoUsuarioActual("financiero"))
        {
            finalizado=false;
        }else
        {
            finalizado=true;
        }
        
        while(!finalizado)
        {
            getListaOrdenesFinanciacion();
            orden = lector.getPalabra();
            switch (orden){
                
                case "1":    
                    System.out.println("FICHAS PENDIENTES DE FINANCIAR");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getFichasCompraEstado("pendiente financiar"));
                    imprimeDivision();
                    break;
                
                case "2":
                    System.out.println("Introduzca el codigo de cliente");
                    identificacion = lector.getPalabra();
                    System.out.println("FICHAS PENDIENTES DE FINANCIAR DEL CLIENTE: "+identificacion);
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getFichasCompraClienteEstado(identificacion,"pendiente financiar"));
                    imprimeDivision();
                    break;
                
                case "3":
                    System.out.println("Introduzca el codigo de cliente");
                    identificacion=lector.getPalabra();
                    System.out.println("HISTORIAL DE FINANCIACION DEL CLIENTE");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getHistorialFinanciacionCliente(identificacion));
                    imprimeDivision();
                    break;
                
                case "4":
                    System.out.println("Introduzca el numero de ficha");
                    int numeroFicha = lector.getEntero();
                    System.out.println("FICHA DE FINANCIACION");
                    imprimeDivision();
                    imprimeFichaFinanciacion(numeroFicha);
                    imprimeDivision();
                    break;
                
                case"5":
                    System.out.println("Introduzca el numero de ficha que desea analizar");
                    int numeroDeFicha = lector.getEntero();
                    System.out.println("Introduzca el valor de la ultima nomina del cliente en €");
                    double nomina = lector.getEntero();
                    System.out.println("Introduzca el numero de plazos en los que desea financiar la compra en meses");
                    int plazo = lector.getEntero();
                    boolean continuar = false;
                    
                    if(!sistemaGestion.getExisteFichaCompra(numeroDeFicha))
                    {
                        System.out.println("La ficha no existe");
                        continuar=false;
                    }
                    
                    else if(!sistemaGestion.comprobarEstadoFichaCompra(numeroDeFicha).equals("pendiente financiar"))
                    {
                        System.out.println("La ficha ya ha sido gestionada");
                        continuar=false;
                    }
                    else if(0>nomina)
                    {
                        System.out.println("La nomina debe ser mayor que 0");
                        continuar=false;
                    }
                    else if(0>plazo||plazo>60)
                    {
                        System.out.println("El plazo para financiar debe estar entre 0 y 60 meses");
                        continuar=false;
                    }
                    else
                    {
                        continuar=true;
                    }
                    
                    if(continuar)
                    {
                        sistemaGestion.analizarFinanciacion(numeroDeFicha,nomina,plazo);
                        imprimeFichaFinanciacion(sistemaGestion.getNumeroUltimaFichaFinanciacion());
                    }
                break;
                          
                case "6":
                finalizado=true;
                break;

                //Salida por defecto para entradas erroneas
                default: 
                System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                gestionCaja();
                break;
            }
        }
    }
    
    /**
     * Comprueba si un usuario tiene permisos para acceder a esta opcion y si los tiene le muestra las opciones disponibles para 
     * gestionar las devoluciones de la tienda (devolver productos, consulta de historicos, consultar fichas de devoluciones...). 
     * Una vez el usuario a indicado que ha terminado de ralizar las gestionaes necesarias lo devuelve a la pantalla inicial.
     */                                                                                                             
    public void gestionPosventa()
    {
        String orden;
        boolean finalizado;
        
        if(sistemaGestion.comprobarPermisoUsuarioActual("postventa"))
        {
            finalizado=false;
        }
        else
        {
            finalizado=true;
        }
        
        while(!finalizado)
        {
            getListaOrdenesPosventa();
            orden = lector.getPalabra();
            
            switch (orden){
                
                
                case "1":
                System.out.println("Introduzca el numero de ficha de compra sobre el que desea realizar la devolucion");
                int numeroFichaDeCompra = lector.getEntero();
                System.out.println("Introduzca el DNI del cliente");
                String idCliente = lector.getPalabra();
                posventa(numeroFichaDeCompra,idCliente);
                break;
                
                case "2":
                System.out.println("Introduzca el codigo del cliente");
                imprimeArray(sistemaGestion.getHistorialPostVentaCliente(lector.getPalabra()));
                break;
                
                case "3":
                imprimeArray(sistemaGestion.getHistorialPostVentaCompleto());
                break;
                
                case "4":    
                finalizado=true;
                break;
                
                case "volver":
                finalizado=true;
                break;
                
                //Salida por defecto para entradas erroneas
                default: 
                System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                gestionCaja();
                break;
            }
        }
    }

    /**
     * Comprueba si un usuario tiene permisos para acceder a esta opcion y si los tiene le muestra las opciones disponibles para 
     * gestionar las reparaciones de la tienda (gestionar reparacion, piezas pendientes,, consulta de historicos, consultar fichas 
     * de reparacion...). 
     * Una vez el usuario a indicado que ha terminado de ralizar las gestionaes necesarias lo devuelve a la pantalla inicial.
     */ 
    public void gestionServicioTecnico()
    {
        int numeroFichaReparacion;
        boolean finalizado;
        String orden;
        
        if(sistemaGestion.comprobarPermisoUsuarioActual("tecnico")){
            finalizado=false;
        }else{
            finalizado=true;
        }
        
        while(!finalizado)
        {
            getListaOrdenesServicioTecnico();
            orden = lector.getPalabra();
            switch (orden){           
                //abrir ficha de reparacion
                case "1":
                    System.out.println("Introduzca el numero de ficha de compra a la que pertenece el producto. Debe solicitarle al cliente el ticket de compra.");
                    int numeroFichaDeCompra = lector.getEntero();
                    imprimeFichaCompra(numeroFichaDeCompra);
                    System.out.println("Introduzca el codigo del producto averiado");
                    String idProducto = lector.getPalabra();
                    numeroFichaReparacion = sistemaGestion.crearFichaReparacion(numeroFichaDeCompra,idProducto);
                    if(numeroFichaReparacion>=0){         
                        System.out.println("¿Que es lo que le pasa?");
                        String comentario = lector.getPalabra();
                        sistemaGestion.añadirComentarioReparacion(numeroFichaReparacion,comentario);
                    }
                    else if(numeroFichaReparacion==-1){
                        System.out.println("El producto no existe en esa ficha de compra");
                    }
                    else if(numeroFichaReparacion==-2){
                        System.out.println("Este producto ha sido devuelto anteriormente");
                    }
                    else if(numeroFichaReparacion==-3){
                        System.out.println("Ya se esta gestionando una reparacion para este producto");
                    }
                    break;
                
                case "2":
                    System.out.println("Introduzca el numero de ficha de reparacion");
                    gestionarReparacion(lector.getEntero());
                    break;
                
                case "3":
                    System.out.println("Introduzca el numero de ficha de reparacion");
                    numeroFichaReparacion = lector.getEntero();
                    imprimeFichaReparacion(numeroFichaReparacion);
                    break;
                    
                case "4":
                    imprimeArray(sistemaGestion.getHistorialReparaciones());
                    break;
                    
                case "5":
                    System.out.println("Introduzca el codigo de cliente");
                    imprimeArray(sistemaGestion.getHistorialReparacionesCliente(lector.getPalabra()));
                    break;
                    
                case"6":
                    imprimeArray(sistemaGestion.getHistorialReparacionesEmpleadoActual());
                    break;
                    
                case"7":
                    System.out.println("Introduzca el numero de ficha que desea asignarse");
                    numeroFichaReparacion = lector.getEntero();
                    if(sistemaGestion.asignarseFichaReparacion(numeroFichaReparacion))
                    {
                        System.out.println("Ficha asignada correctamente");
                    }
                    else
                    {
                        System.out.println("La ficha no existe");
                    }
                    break;
                    
                case "8":
                    System.out.println("Introduzca el codigo de producto");
                    idProducto=lector.getPalabra();
                    System.out.println("Introduzca el numero de ficha de compra");
                    numeroFichaDeCompra=lector.getEntero();
                    imprimeArray(sistemaGestion.historialElectrodomestico(idProducto,numeroFichaDeCompra));
                    break;
                    
                case "9":
                    System.out.println("TUS FICHAS ASIGNADAS");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.fichasAsignadas());
                    imprimeDivision();
                    break;
                    
                case "10":
                    System.out.println("FICHAS PENDINTES DE RESOLVER");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getReparacionesPendientes());
                    imprimeDivision();
                    break;
                    
                case "11":
                    System.out.println("PIEZAS PENDIENTES DE RECIBIR");
                    imprimeDivision();
                    imprimeArray(sistemaGestion.getPiezasPendientes());
                    imprimeDivision();
                    break;                                         
                    
                case "12":
                    finalizado=true;
                    break;                
                
                //Salida por defecto para entradas erroneas
                default: 
                System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                gestionServicioTecnico();
                break;
            }
        }
    }
    
    /**
     * imprime en pantalla todo lo relacionado con una ficha de reparacion
     * @param numeroFicha numero de ficha de reparacion
     */ 
    public void imprimeFichaReparacion(int numeroFicha)
    {
        if(sistemaGestion.existeFichaReparacion(numeroFicha))
        {
            System.out.println("FICHA DE REPARACION");
            imprimeArray(sistemaGestion.getFichaReparacion(numeroFicha));
        }
        else
        {
            System.out.println("La ficha no existe");
        }
    }
    
    /**
     * Crea los dintintos grupos de accionees que se imprimiran en pantalla
     */ 
    public void setListaOrdenes()
    {
        listaOrdenesAlmacen.add("(1)Stock:                    Imprime un listado de los productos");
        listaOrdenesAlmacen.add("(2)Pedido:                   Permite introducir aumentos de stock de un producto existente en la base de datos");
        listaOrdenesAlmacen.add("(3)Eliminar producto:        Elimina el producto de la base de datos. Atencion! Elimina todos los productos de la base de datos con ese codigo de producto");
        listaOrdenesAlmacen.add("(4)Añadir producto:          Añade un producto nuevo a la base de datos");
        listaOrdenesAlmacen.add("(5)Buscar producto:          Busca un articulo por codigo de producto y nos lo describe");
        listaOrdenesAlmacen.add("(6)Modificar producto:       Modifica un producto ya existente");
        listaOrdenesAlmacen.add("(7)Volver:                   Regresa a la pantalla de inicio");
        listaOrdenesUsuarios.add("(1)Añadir Empleado:              Añade un empleado nuevo a la  base de datos");
        listaOrdenesUsuarios.add("(2)Añadir Cliente:               Añade un cliente nuevo a la  base de datos");
        listaOrdenesUsuarios.add("(3)Quitar Cliente:               Quita un cliente de la base de datos");
        listaOrdenesUsuarios.add("(4)Lista de empleados:           Imprime en pantalla una lista de empleados actuales");
        listaOrdenesUsuarios.add("(5)Lista de clientes:            Imprime en pantalla una lista de clientes actuales");
        listaOrdenesUsuarios.add("(6)Ver ficha de empleado:        Busca una ficha de empleado por codigo");
        listaOrdenesUsuarios.add("(7)Ver ficha de cliente:         Busca una ficha de cliente por codigo");
        listaOrdenesUsuarios.add("(8)Buscar empleado por nombre:   Busca una ficha de empleado por nombre. Si hay varios empleados con ese nombre, imprimira todas las fichas");
        listaOrdenesUsuarios.add("(9)Buscar cliente por nombre:    Busca una ficha de empleado por nombre. Si hay varios clientes con ese nombre, imprimira todas las fichas");
        listaOrdenesUsuarios.add("(10)Añadir permisos a usuario:    Permitir a un usuario acceder a mas partes del sistema");
        listaOrdenesUsuarios.add("(11)Retirar permisos a usuario:  Impedir a un usuario acceder a algunas partes del sistema");
        listaOrdenesUsuarios.add("(12)Modificar cliente:           Modifica los datos de un cliente");
        listaOrdenesUsuarios.add("(13)Modificar empleado:          Modifica los datos de un empleado");
        listaOrdenesUsuarios.add("(14)Volver:                       Regresa a la pantalla anterior");
        listaOrdenesCaja.add("(1)vender:                      Inicia una operacion de venta");
        listaOrdenesCaja.add("(2)Historial:                   Imprime el historial completo de ventas de la caja");
        listaOrdenesCaja.add("(3)Historial por cliente:       Imprime el historial completo de un cliente");
        listaOrdenesCaja.add("(4)Consultar ficha :            Imprime una ficha de compra");
        listaOrdenesCaja.add("(5)volver:                      Regresa a la pantalla anterior");
        listaOrdenesFinanciacion.add("(1)Pendientes:                      Imprime una lista de financiaciones pendientes de tramintar");
        listaOrdenesFinanciacion.add("(2)Pendientes por cliente:          Imprime una lista de las financiaciones pendientes de tramitar de un cliente");
        listaOrdenesFinanciacion.add("(3)Historial Cliente:               Muestra el historial de solicitudes de financiacion de un cliente");
        listaOrdenesFinanciacion.add("(4)Consultar ficha de financiacion: Imprime una ficha de financiacion");
        listaOrdenesFinanciacion.add("(5)Gestionar ficha:                 Tramita una financiacion de una ficha de compra");
        listaOrdenesFinanciacion.add("(6)Volver:                          Regresa a la pantalla anterior");
        listaOrdenesPosventa.add("(1)Tramitar devolucion:                 Inicia una operacion de devolucion");        
        listaOrdenesPosventa.add("(2)Historial por cliente:               Imprime el historial completo de un cliente");
        listaOrdenesPosventa.add("(3)Historial:                           Imprime el historial completo de devoluciones de la caja");
        listaOrdenesPosventa.add("(4)volver:                              Regresa a la pantalla anterior");       
        listaOrdenesServicioTecnico.add("(1)Abrir ficha de reparacion:             Abre una nueva ficha de reparacion para gestionar un nuevo caso");
        listaOrdenesServicioTecnico.add("(2)Gestionar ficha de reparacion:         Permite realizar acciones sobre una ficha de reparacion");
        listaOrdenesServicioTecnico.add("(3)Consultar ficha de reparacion:         Imprime una ficha de reparacion");
        listaOrdenesServicioTecnico.add("(4)Historial:                             Imprime un resumen de todas las fichas de reparacion gestionadas en la tienda");
        listaOrdenesServicioTecnico.add("(5)Historial Cliente:                     Imprime un resumen de todas las fichas de reparacion gestionadas a un cliente");
        listaOrdenesServicioTecnico.add("(6)Historial empleado:                    Imprime un resumen de todas las fichas de reparacion gestionadas por un empleado");
        listaOrdenesServicioTecnico.add("(7)Asignar ficha de reparacion:           Asignarse ficha de reparacion");
        listaOrdenesServicioTecnico.add("(8)Ver historial electrodomestico:        Imprime todas las fichas relacionadas con un electrodomestico");
        listaOrdenesServicioTecnico.add("(9)Mis fichas asignadas:                  Imprime un resumen de mis fichas asignadas");
        listaOrdenesServicioTecnico.add("(10)Reparaciones pendientes               Imprime un resumen de las reparaciones pendintes de finalizar");
        listaOrdenesServicioTecnico.add("(11)Piezas pendientes:                    Imprime un resumen de las piezas pendientes de recibir");
        listaOrdenesServicioTecnico.add("(12)volver:                               Regresa a la pantalla anterior");
        listaOpcionesInicio.add("(1)  Caja");
        listaOpcionesInicio.add("(2)  Financiacion");
        listaOpcionesInicio.add("(3)  Servicio tecnico");
        listaOpcionesInicio.add("(4)  Comercial");
        listaOpcionesInicio.add("(5)  Servicio posventa");
        listaOpcionesInicio.add("(6)  Gestion de usuarios");
        listaOpcionesInicio.add("(7)  Gestion de almacen");
        listaOpcionesInicio.add("(8)  Cambiar de usuario");
        listaOpcionesInicio.add("(9)  Acerca del programa");
        listaOpcionesInicio.add("(10) Historial de Cliente");
        listaOpcionesInicio.add("(11) Salir");
        listaOrdenesComercial.add("(1)Añadir promoción:            Crea una nueva promoción. Se crea desactivada por defecto");
        listaOrdenesComercial.add("(2)Activar promoción:           Activa una promoción");
        listaOrdenesComercial.add("(3)Desactivar promoción:        Desactiva una promoción");
        listaOrdenesComercial.add("(4)Ver promociones :            Muestra las promociones disponibles");
        listaOrdenesComercial.add("(5)Consultar promoción:         Muestra en pantalla los datos de una promoción");
        listaOrdenesComercial.add("(6)Añadir producto:             Añade un producto a una promoción");
        listaOrdenesComercial.add("(7)Quitar producto:             Quita un producto de una promoción");
        listaOrdenesComercial.add("(8)Ver clientes ojetivo:        Muestra los clientes que pueden ser objetivo de una promoción");
        listaOrdenesComercial.add("(9)Enviar promoción:            Envía una promoción a un cliente");
        listaOrdenesComercial.add("(10)Lista de correos:           Muestra todos los correos enviados");
        listaOrdenesComercial.add("(11)Consultar correo:           Muestra el contenido de un correo");
        listaOrdenesComercial.add("(12)Consultar stock:            Muestra una lista con los productos disponiblese en la tienda");
        listaOrdenesComercial.add("(13)Volver");
        
    }
    
    /**
     * imprime en pantalla las ordenes de inicio
     */ 
    public void getOpcionesInicio()
    {
        for(String orden:listaOpcionesInicio)
        {
            System.out.println(orden);
        }
    }
    
    /**
     * Imprime en pantalla las ordenes de almacen
     */ 
    public void getListaOrdenesAlmacen()
    {
        for(String orden:listaOrdenesAlmacen)
        {
            System.out.println(orden);
        }
    }
    
    /**
     * Imprime en pantalla las ordenes de gestion de usuarios
     */ 
    public void getListaOrdenesUsuarios()
    {
        for(String orden:listaOrdenesUsuarios)
        {
            System.out.println(orden);
        }
    }
    
    /**
     * Imprime en pantalla las ordenes de gestion de caja
     */ 
    public void getListaOrdenesCaja()
    {
        for(String orden:listaOrdenesCaja)
        {
            System.out.println(orden);
        }
    }
    
    /**
     * Imprime en pantalla las ordenes de gestion de financiacion
     */ 
    public void getListaOrdenesFinanciacion()
    {
        for(String orden:listaOrdenesFinanciacion)
        {
            System.out.println(orden);
        }
    }
    
    /**
     * Imprime en pantalla las ordenes de gestion posventa
     */ 
    public void getListaOrdenesPosventa()
    {
        for(String orden:listaOrdenesPosventa)
        {
            System.out.println(orden);
        }
    }
    
    /**
     * Imprime en pantalla las ordenes de gestion de servicion tecnico
     */ 
    public void getListaOrdenesServicioTecnico()
    {
        for(String orden:listaOrdenesServicioTecnico)
        {
            System.out.println(orden);
        }
    }
    
    
    /**
     * Guia al usuario en el proceso de venta. Solicita por pantalla los datos necesarios para cumplimentar la ficha de compra.
     * @param identificacion Identificacion personal del cliente(DNI).
     */
    public void vender(String identificacion)
    {
        String idProducto,nombre,apellidos,correoElectronico,telefono;
        int numeroDeFicha,cantidad;

        if(!sistemaGestion.existeCliente(identificacion))
        {
            System.out.println("Debe abrirse una ficha de cliente nueva. El cliente no existe en la base de datos");
            System.out.println("Introduzca el nombre del usuario");
            nombre=lector.getPalabra();
            System.out.println("Introduzca los apellidos del usuario");
            apellidos=lector.getPalabra();
            System.out.println("Introduzca el correo electronico del usuario");
            correoElectronico=lector.getPalabra();
            System.out.println("Introduzca el numero de telefono del usuario");
            telefono=lector.getPalabra();
            sistemaGestion.añadirCliente(identificacion,nombre,apellidos,correoElectronico,telefono); 
        }
        numeroDeFicha=sistemaGestion.crearFichaDeCompra(identificacion);
        boolean finalizado = false;
        while(!finalizado){
            System.out.println("¿Que desea hacer?");
            System.out.println("(1) Añadir Producto");
            System.out.println("(2) Quitar Producto");
            System.out.println("(3) Pagar en efectivo");
            System.out.println("(4) Financiar la compra");
            System.out.println("(5) Cancelar y volver al menu anterior");
            String orden = lector.getPalabra();
                
            switch (orden){
                case "1": 
                    System.out.println("Introduzca el codigo del producto");
                    idProducto=lector.getPalabra();
                    System.out.println("Introduzca la cantidad");
                    cantidad = lector.getEntero();
                    if(sistemaGestion.comprarProducto(numeroDeFicha,idProducto,cantidad))
                    {
                        imprimeFichaCompra(numeroDeFicha);
                    }
                    else
                    {
                        System.out.println("No hay stock suficiente de ese producto");
                    }
                    break;
                
                case "2":
                    System.out.println("Introduzca el codigo del producto");
                    idProducto=lector.getPalabra();
                    System.out.println("Introduzca la cantidad");
                    cantidad = lector.getEntero();
                     if(sistemaGestion.quitarProductoDeCompra(numeroDeFicha,idProducto,cantidad))
                    {
                        imprimeFichaCompra(numeroDeFicha);
                    }
                    else
                    {
                        System.out.println("No hay suficientes productos en la lista");
                    }
                    break;
                
                case "3":
                    sistemaGestion.pagarEfectivo(numeroDeFicha);
                    finalizado=true;
                    break;
                
                case "4":
                    sistemaGestion.financiarCompra(numeroDeFicha);
                    System.out.println("El cliente debe hablar con un financiero para finalizar su compra");
                    finalizado=true;
                    break;
                
                case "5":
                    sistemaGestion.cancelarCompra(numeroDeFicha);
                    finalizado=true;
                    break;
                
                default: 
                    System.out.println("No es una orden valida");
                    break;
            }
        }       
    }    
    
    /**
     * Imprime en pantalla todos los datos relacionados con una ficha de compra.
     * @param numeroFichaDeCompra número de ficha que queremos imprimir.
     */
    public void imprimeFichaCompra(int numeroFichaDeCompra)
    {
        if(sistemaGestion.getExisteFichaCompra(numeroFichaDeCompra))
        {
            imprimeArray(sistemaGestion.getFichaCompra(numeroFichaDeCompra));
        }
    }
    
    /**
     * Guia al usuario durante un proceso de devolución. Comprueba que la ficha de compra que presenta el cliente pertenece al cliente 
     * actual y lo rechaza en caso de que no se así. Comprueba tambien que la ficha de compra esté en estado "pagado" o "financiado" y 
     * rechaza las demás. Por último comprueba que el plazo de devolucion esté vigente. Durante la gestión de la devolución comprueba
     * que los productos que se quieran devolver aparezcan dentro de la ficha de compra, y además que no hay asido devuelto ya. 
     * @param numeroFichaDeCompra numero de ficha de compra que presenta el cliente
     * @param idCliente identificacion del cliente actual(DNI).
     */
    public void posventa(int numeroFichaDeCompra,String idCliente)
    {
        int numeroFichaDevolucion=0,cantidad;
        String idProducto;
        boolean finalizado=false;
        if(sistemaGestion.getExisteFichaCompra(numeroFichaDeCompra))
        {
            if(sistemaGestion.comprobarClienteFichaCompra(numeroFichaDeCompra).equals(idCliente))
            {
                if(sistemaGestion.comprobarEstadoFichaCompra(numeroFichaDeCompra).equals("pagado")||sistemaGestion.comprobarEstadoFichaCompra(numeroFichaDeCompra).equals("financiacion aprobada"))
                {
                    Date fechaActual = new Date();
                    long plazo =fechaActual.getTime()-sistemaGestion.comprobarFechaDeCompra(numeroFichaDeCompra);
                    plazo=plazo/(24*60*60*1000);
                    if(plazo<=3)
                    {
                        System.out.println("¿Cual es el motivo de la devolucion?");
                        numeroFichaDevolucion = sistemaGestion.crearFichaDevolucion(numeroFichaDeCompra,lector.getPalabra());                       
                    }
                    else
                    {
                        System.out.println("El plazo de devolucion ha expirado");
                        finalizado=true;
                    }
                }
                else
                {
                    System.out.println("La ficha de compra corresponde con una compra que no se ha finalizado");
                    finalizado=true;
                }
            }
            else
            {
                System.out.println("La ficha introducida no es propiedad de este cliente");
                finalizado=true;   
            }
        }
        else
        {
            System.out.println("La ficha introducida no existe");
            finalizado=true;
        }
        
        while(!finalizado){
            imprimeFichaCompra(numeroFichaDeCompra);
            System.out.println("¿Que desea hacer?");
            System.out.println("(1) Devolver producto");
            System.out.println("(2) Cancelar devolucion de un producto");
            System.out.println("(3) Confirmar la devolucion");
            System.out.println("(4) Cancelar y volver al menu anterior");
            String orden = lector.getPalabra();
                
            switch (orden){
                case "1": 
                    System.out.println("Introduzca el codigo del producto a devolver");
                    idProducto=lector.getPalabra();
                    System.out.println("Introduzca la cantidad");
                    cantidad = lector.getEntero();
                    if(sistemaGestion.compruebaProductoComprado(numeroFichaDeCompra,idProducto))
                    {
                        if(sistemaGestion.devolverProducto(numeroFichaDevolucion,idProducto,cantidad,numeroFichaDeCompra))
                        {

                        }
                        else
                        {
                            System.out.println("El cliente no dispone de tantos articulos en su ficha de compra");
                        }
                    }
                    else
                    {
                        System.out.println("El producto no existe");
                    }
                    break;
                    
                case "2":
                    System.out.println("Introduzca el codigo del producto a cancelar");
                    idProducto=lector.getPalabra();
                    System.out.println("Introduzca la cantidad");
                    cantidad = lector.getEntero();
                    if(sistemaGestion.cancelarDevolucionProducto(numeroFichaDevolucion,idProducto,cantidad,numeroFichaDeCompra))
                    {

                    }
                    else
                    {
                        System.out.println("El producto no se encuentra en la ficha de compra");
                    }
                    break;
                
                case "3":
                    sistemaGestion.completarDevolucion(numeroFichaDevolucion);
                    finalizado=true;
                    break;
                    
                case "4":
                    sistemaGestion.cancelarDevolucion(numeroFichaDevolucion);
                    finalizado=true;
                    break;
                
                default: 
                    System.out.println("No es una orden valida");
                    break;
            }
        } 
        
    }
    
    
    /**
     * Imprime todos los datos relacionados con una ficha de devolución.
     * @param numeroFicha numero de la ficha de devolución que queremos imprimir.
     */
    public void imprimeFichaDevolucion(int numeroFicha)
    {
        if(sistemaGestion.existeFichaDevolucion(numeroFicha))
        {
            imprimeArray(sistemaGestion.getFichaDevolucion(numeroFicha));
        }
        else
        {
            System.out.println("La ficha no existe");
        }
    }
    
    /**
     * Accede a una ficha de compra y nos da opciones para trabajar con ella.(Añadir comentarios, añadir una comunicacion con el cliente
     * elaborar un presupuesto...)
     * @param numeroFicha numero de ficha de reparacion que queremos gestionar
     */
    public void gestionarReparacion(int numeroFicha)
    {
        String descripcion,estado, proveedor,idPieza,precio, cantidad, numeroPieza;
        String idCliente = sistemaGestion.compruebaClienteReparacion(numeroFicha);
        boolean finalizado;
        if(idCliente!=null)
        {
            finalizado = false;
        }
        else
        {
            System.out.println("La ficha de reparacion indicada no es correcta");
            finalizado = true;
        }
        
        while(!finalizado)
        {
        System.out.println("¿Que desea hacer?");
        System.out.println("(1) Añadir un comentario o trabajo a la ficha");
        System.out.println("(2) Añadir una comunicacion con el cliente");
        System.out.println("(3) Añadir pieza necesaria");
        System.out.println("(4) Quitar pieza de la ficha de reparacion");
        System.out.println("(5) Recepcion pieza necesaria");
        System.out.println("(6) Resolver ficha");
        System.out.println("(7) Consultar ficha");
        System.out.println("(8) Calcular presupuesto");
        System.out.println("(0) Volver al menu anterior");
        String orden = lector.getPalabra();
            
            switch (orden)
            {
                case "0":
                    finalizado=true;
                    break;
                
                case "1": 
                    System.out.println("Escriba el comentario");
                    sistemaGestion.añadirComentarioReparacion(numeroFicha,lector.getPalabra());
                    System.out.println("¿Quiere añadir horas de mano de obra a la ficha?");
                    System.out.println("Pulse 1 para añadir horas de mano de obra a la ficha, introduzca cualquier otro dato para continuar");
                    if(lector.getEntero()==1)
                    {
                        System.out.println("Introduzca el numero de horas");
                        int horas = lector.getEntero();
                        sistemaGestion.añadirManoDeObra(numeroFicha,horas);
                    }
                    break;
                
                case "2":
                    System.out.println("Escriba los detalles de la comunicacion con el cliente");
                    String mensaje = lector.getPalabra();
                    sistemaGestion.comunicarAlClienteReparacion(mensaje,numeroFicha);
                    break;
                    
                case "3":
                    System.out.println("Introduzca una breve descripcion del a pieza");
                    descripcion=lector.getPalabra();
                    System.out.println("Introduzca el numero de piezas");
                    cantidad=lector.getPalabra();
                    System.out.println("Introduzca el nombre del proveedor");
                    proveedor=lector.getPalabra();
                    System.out.println("Introduzca el codigo de identificacion de la pieza");
                    idPieza=lector.getPalabra();
                    System.out.println("Introduzca el precio de la pieza en €");
                    precio=lector.getDoubleComoString();
                    sistemaGestion.añadirPiezaNecesaria(numeroFicha,idPieza,descripcion,precio,proveedor,cantidad);
                    break;
                    
                case "4":
                    System.out.println("Introduzca el codigo de la pieza");
                    idPieza = lector.getPalabra();
                    sistemaGestion.quitarPiezaNecesaria(idPieza,numeroFicha);
                    break;
                    
                case "5":
                    System.out.println("Introduzca el codigo de la pieza");
                    numeroPieza = lector.getPalabra();
                    if(sistemaGestion.comprobarPiezaNecesaria(numeroFicha,numeroPieza))
                    {
                        sistemaGestion.recepcionPieza(numeroFicha,numeroPieza);
                        
                    }
                    else
                    {
                        System.out.println("La pieza no existe");
                    }
                    break;
                    
                case "6":
                    System.out.println("Introduzca el comentario de resolucion");
                    sistemaGestion.añadirComentarioReparacion(numeroFicha,lector.getPalabra());
                    sistemaGestion.finalizarReparacion(numeroFicha);
                    finalizado=true;
                    break;
                    
                case "7":
                    imprimeFichaReparacion(numeroFicha);
                    break;
                    
                case "8":
                    System.out.println("Introduzca el precio de la mano de obra");
                    System.out.println(sistemaGestion.presupuestoReparacion(numeroFicha,lector.getReal()));
                    break;
                 
                default: 
                    System.out.println("No es una orden valida");
                    break;
            }
        
        }
    }
    
    /**
     * Imprime en pantalla todos los datos relacionados con una ficha de financiación
     * @param numeroFicha numero de ficha de financiación que queremos imprimir
     */
    public void imprimeFichaFinanciacion(int numeroFicha)
    { 
        if(sistemaGestion.getExisteFichaFinanciacion(numeroFicha))
        {
            imprimeArray(sistemaGestion.fichaFinanciacion(numeroFicha));
        }
    }
    
        /**
     * Comprueba si un usuario tiene permisos para acceder a esta opcion y si los tiene le muestra las opciones disponibles para 
     * gestionar las promocionese de la tienda.
     * Una vez el usuario a indicado que ha terminado de ralizar las gestionaes necesarias lo devuelve a la pantalla inicial.
     */ 
    public void gestionComercial()
    {
        String nombre, descripcion, codigoProducto,idCliente;
        int numeroFicha,descuento;
        boolean finalizado;
        String orden;
        
        if(sistemaGestion.comprobarPermisoUsuarioActual("comercial")){
            finalizado=false;
        }else{
            finalizado=true;
        }
        
        while(!finalizado)
        {
            getListaOrdenesComercial();
            orden = lector.getPalabra();
            switch (orden){           
                
                case "1":
                    System.out.println("Introduzca el nombre de la promoción");
                    nombre = lector.getPalabra();
                    System.out.println("Introduzca la descripcion de la promoción. Será la descripción que reciban los clientes en su correo");
                    descripcion = lector.getPalabra();
                    sistemaGestion.crearFichaPromocion(nombre,descripcion);
                    break;
                
                case "2":
                    System.out.println("Introduzca el numero de ficha de de la promoción");
                    numeroFicha = lector.getEntero();
                    if(sistemaGestion.triggerPromocion(true,numeroFicha))
                    {
                        System.out.println("Promocion activada correctamente");
                    }
                    else
                    {
                        System.out.println("La promocion no se ha activado correctamente");
                    }  
                    break;
                    
                case "3":
                    System.out.println("Introduzca el numero de ficha de de la promoción");
                    numeroFicha = lector.getEntero();
                    if(sistemaGestion.triggerPromocion(false,numeroFicha))
                    {
                        System.out.println("Promocion desactivada correctamente");
                    }
                    else
                    {
                        System.out.println("La promocion no se ha desactivado correctamente");
                    }
                    break;
                    
                case "4":
                    imprimeArray(sistemaGestion.getPromociones());
                    break;
                    
                case "5":
                    System.out.println("Introduzca el numero de ficha de de la promoción");
                    numeroFicha = lector.getEntero();
                    imprimeArray(sistemaGestion.getFichaPromocion(numeroFicha));
                    break;
                    
                case "6":
                    System.out.println("Introduzca el numero de ficha de de la promoción");
                    numeroFicha = lector.getEntero();
                    System.out.println("Introduzca el codigo de producto que desea añadir");
                    codigoProducto = lector.getPalabra();
                    System.out.println("Introduzca el descuento a aplicar. Introduzca el valor en tanto por cien");
                    descuento = lector.getEntero();
                    if(sistemaGestion.añadirProductoPromocion(codigoProducto,descuento,numeroFicha))
                    {
                        System.out.println("Producto añadido correctamente");
                    }
                    else
                    {
                        System.out.println("La ficha de promoción indicada no existe");
                    }
                    break;
                    
                case "7":
                    System.out.println("Introduzca el numero de ficha de de la promoción");
                    numeroFicha = lector.getEntero();
                    System.out.println("Introduzca el codigo de producto que desea quitar");
                    codigoProducto = lector.getPalabra();
                    if(sistemaGestion.quitarProductoPromocion(codigoProducto,numeroFicha))
                    {
                        System.out.println("Producto retirado correctamente");
                    }
                    else
                    {
                        System.out.println("Producto no encontrado en la promoción");
                    }
                    break;
                    
                case "8":
                    System.out.println("Introduzca el numero de ficha de de la promoción");
                    numeroFicha = lector.getEntero();
                    System.out.println("***CLIENTES OBJETIVO***");
                    imprimeArray(sistemaGestion.getClientesObjetivo(numeroFicha));
                    break;
                    
                case "9":
                    System.out.println("Introduzca el numero de ficha de de la promoción");
                    numeroFicha = lector.getEntero();
                    System.out.println("Introduzca el identificador del cliente");
                    idCliente = lector.getPalabra();
                    if(sistemaGestion.enviarPromocion(idCliente,numeroFicha))
                    {
                        System.out.println("Promoción enviada correctamente");
                    }
                    else
                    {
                        System.out.println("La promoción no se ha enviado. Compruebe que existen clientes objetivo y que la promoción existe");
                    }
                    break;   
                    
                case "10":
                    System.out.println("***HISTORIAL DE CORREOS***");
                    imprimeArray(sistemaGestion.historialCorreos());
                    break;
                    
                case "11":
                    System.out.println("Introduzca el numero de correo que desea consultar");
                    numeroFicha = lector.getEntero();
                    imprimeArray(sistemaGestion.consultaCorreo(numeroFicha));
                    break;
                    
                case "12":
                    System.out.println("***STOCK EN EL ALMACEN***");
                    imprimeArray(sistemaGestion.getStock());
                    break;
                        
                
                case "13":
                    finalizado=true;
                    break;                
                
                //Salida por defecto para entradas erroneas
                default: 
                System.out.println("La opcion elegida no esta disponible. Por favor, intentelo con otra.");
                gestionServicioTecnico();
                break;
            }
        }
    }
    
    /**
     * Da informacion basica aceraca del programa
     */
    public void acercaDelPrograma()
    {
        System.out.println("***ACERCA DEL PROGRAMA***");
        imprimirBienvenida();
        System.out.println("");
        System.out.println("SIG v1.0");
        System.out.println("Programa de gestiÓn para tienda de electrodomÉsticos que se realiza con el fin de superar la asignatura programaciÓn orientada a");
        System.out.println("objetos de la UNED curso 2018-2019");
        System.out.println("El programa se ha desarrollado con fines educativos y se desaconseja totalmente uso profesional");
    }
    
    /**
     * Imprime en pantalla las ordenes de gestion comercial
     */ 
    public void getListaOrdenesComercial()
    {
        for(String orden:listaOrdenesComercial)
        {
            System.out.println(orden);
        }
    }
    
    /**
     * Dado un array de strings imprime cada componente en una linea diferente
     * @param array array de strings que queremos imprimir
     */
    public void imprimeArray(ArrayList<String> array)
    {
        for(String string:array)
        {
            System.out.println(string);
        }
    }
    
    /**
     * Imprime en pantall una linea de separación compuesta por asterísticos
     */
    public void imprimeDivision()
    {
        System.out.println("*************************************************************************************************************************************");
    }
}
