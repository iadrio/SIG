import java.util.*;
import java.text.ParseException;
/**
 * Esta clase se encarga de leer las instrucciones que el usuario introduce en pantalla 
 * Incluye métodos para leer enteros, strings y números reales (Estos pueden ser devueltos como entero o como strings en función del 
 * método elegido).
 * 
 * @author Iván Adrio Muñiz 
 * @version 2018.04.17
 */
public class Lector
{
    private Scanner lector; //scanner que emplearemos para hacer lecturas por pantalla
    
    /**
     * Crea el objeto lector, el cual leera los datos de la pantalla.
     */
    public Lector()
    {
        lector=new Scanner(System.in);
        
    }

    /**
     * Lee una instruccion en pantalla y la devuelve en forma de string de minúsculas.
     * @return  Devuelve un string de minúsculas 
     */
    public String getPalabra()
    {
        Scanner lector= new Scanner(System.in);        
        String lectura = lector.nextLine().toLowerCase();
        return lectura;
    }
    
    /**
     * Lee una instrucción en pantalla y la convierte en número entero. Si la lectura no es parseable a entero solicita que se introduzca
     * otra instrucción de nuevo.
     * @return  Devuelve el número entero introducido
     */
    public int getEntero()
    {    
        int numero = 0;
        boolean Entero=false;
        Scanner lector = new Scanner(System.in);
        
        while(!Entero==true)
        {
            Entero=true;
            String lectura = lector.nextLine();
            
            try{
                numero = Integer.parseInt(lectura);
            }
            catch(NumberFormatException e){
                Entero=false;
                System.out.println("'"+lectura+"'  no es una entrada valida. Debe introducir un numero entero");
            }
        }        
        return numero;
    }
    
    /**
     * Lee una instruccion en pantalla y comprueba si esta es parseable a un número real.Lee una instruccion en pantalla y comprueba 
     * si esta es parseable a un número real. Si no lo es, solicita que se introduzca una nueva instrucción.
     * @return  Devuelve un string que se puede parsear a número real.
     */
    public String getDoubleComoString()
    {    
        double numero = 0;
        boolean esDouble=false;
        String lectura=null;
        Scanner lector = new Scanner(System.in);
        
        while(!esDouble==true)
        {
            esDouble=true;
            lectura = lector.nextLine();
            
            try{
                numero = Double.parseDouble(lectura);
            }
            catch(NumberFormatException e){
                esDouble=false;
                System.out.println("'"+lectura+"'  no es una entrada valida. Debe introducir un numero entero");
            }
        }        
        return lectura;
    }
    
    /**
     * Lee una instruccion en pantalla y comprueba si esta es parseable a un número real. Si no lo es, solicita que se introduzca una 
     * nueva instrucción.
     * @return  Devuelve la lectura convertida en un número real.
     */
    public double getReal()
    {    
        double numero = 0;
        boolean Entero=false;
        Scanner lector = new Scanner(System.in);
        
        while(!Entero==true)
        {
            Entero=true;
            String lectura = lector.nextLine();
            
            try{
                numero = Double.parseDouble(lectura);
            }
            catch(NumberFormatException e){
                Entero=false;
                System.out.println("'"+lectura+"'  no es una entrada valida. Debe introducir un numero entero");
            }
        }        
        return numero;
    }
    
    /**
     * Imprime un mensaje en pantalla solicitando una confirmación. Devuelve true o false en función de si el usuario acepta o no.
     * @return Devuelve true o false en función de si el usuario acepta o no.
     */
    public boolean pedirConfirmacion()
    {
        boolean confirmacion = false;
        System.out.println();
        System.out.println("¿Desea continuar?");
        System.out.println("Escriba 'continuar' si desea continuar");
        System.out.println();
        String lectura = getPalabra();
        if(lectura.equals("continuar"))
        {
            confirmacion=true;
        }
        return confirmacion;
    }
}
