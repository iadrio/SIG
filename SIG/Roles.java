
/**
 * Enumeration class Roles - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Roles
{
    TECNICO("tecnico"),CAJERO("cajero"),POSTVENTA("postventa"),FINANCIERO("financiero"),
    ADMINISTRADOR("administrador"),COMERCIAL("comercial"),CLIENTE("cliente");
    
    String rol;
    
    Roles(String rol)
    {
        this.rol = rol;
    }
    
    public String toString()
    {
        return rol;
    }
    
    public static String listaRolesEmpleado()
    {
        return (TECNICO.toString()+" "+CAJERO.toString()+" "+POSTVENTA.toString()+" "+
        FINANCIERO.toString()+" "+ADMINISTRADOR.toString()+" "+COMERCIAL.toString());
    }
}
