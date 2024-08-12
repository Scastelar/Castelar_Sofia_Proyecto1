
package proyecto1;


import java.util.Calendar;

public class Usuario {
    
    private String cuenta;
    private String password;
    private Calendar fechaCreacion;
    private int puntos;
    
    public Usuario (String cuenta, String password){
        this.cuenta = cuenta;
        this.password = password;
        fechaCreacion = Calendar.getInstance();
        puntos = 0;
    }
    public String getCuenta(){
        return cuenta;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
    public int getPuntos(){
        return puntos;
    }
    
    public String toString() {
        return "Cuenta: " + cuenta + 
               "\nFecha de Creación: " + fechaCreacion.getTime() + 
                "\nPuntos: " + puntos;
    }
}
     
   
    
   
                

