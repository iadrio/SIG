import java.util.*;
import java.io.*;
/**
 * Write a description of class prueba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class prueba
{
    
    public prueba()
    {
        
    }
    
    
    public static void printDateRange(int año,int mes, int dia,int a,int m, int d){
        Calendar calendar = Calendar.getInstance();
        Date fechaInicio;
        Date fechaFinal;
        calendar.set(año,mes,dia);
        fechaInicio = calendar.getTime();
        calendar.set(a,m,d);
        fechaFinal = calendar.getTime();
        System.out.println(fechaInicio);
        System.out.println(fechaFinal);
        System.out.println(fechaInicio.getTime()<fechaFinal.getTime());
    }
}
