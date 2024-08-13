package proyecto1;

import javax.swing.JOptionPane;

public class cuentasStratego {
    private static cuentasStratego instance;
    private Usuario cuentas[];
    private Usuario actual;
    private int contador;
    private int contadorTotal;
    private int partidasTotal;
    private int heroes;
    private int villanos;

   
    private cuentasStratego() {
        cuentas = new Usuario[100];
        actual = null;
        contador = 0;
        contadorTotal = 0;
        partidasTotal = 0;
        heroes = 0;
        villanos = 0;
    }

    public void setUsuario(Usuario usuario){
        this.actual = usuario;
    }
    public Usuario getUsuario(){
        return actual;
    }
    public static cuentasStratego getInstance(){
        if (instance == null){
            instance = new cuentasStratego();
        }
        return instance;
    } 
    public Usuario[] getCuentas(){
        return cuentas;
    }
    public int getContador(){
        return contador;
    }
    public int getUsuariosActivos() {
        return contador;
    }
    public int getUsuariosHistoricos() {
        return contadorTotal;
    }
    public int getPartidas() {
        return partidasTotal;
    }
    public void incrementarPartidas() {
        partidasTotal++;
    }
    public int getHeroes() {
        return heroes;
    }
    public void incrementarHeroes() {
        heroes+=3;
    }
    public int getVillanos() {
        return villanos;
    }
    public void incrementarVillanos() {
        villanos+=3;
    }
    
public Usuario buscar(String cuenta) {
    for (Usuario usuario : cuentas) {
        if (usuario != null && usuario.getCuenta().equals(cuenta)) {
            return usuario;
        }
    }
    return null;
}
   
   
    public boolean agregarCuenta (String account, String password1){
        if (password1.length() != 5){
            JOptionPane.showMessageDialog(null, "La contraseña debe ser de 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (buscar(account)==null){
            for(int pos=0; pos < cuentas.length; pos++){
                if(cuentas[pos]==null){
                    cuentas[pos]= new Usuario(account,password1);
                   System.out.println("Cuenta agregada: " + account);
                   contador++;
                   contadorTotal++;
                    imprimirCuentas(); 
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario ya existente.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
     
    
    public boolean validarCuenta (String account, String password1){
        Usuario usuario = buscar(account);
        if (usuario != null && usuario.getPassword().equals(password1)){
            JOptionPane.showMessageDialog(null, "Ingreso a la cuenta exitoso.");
            this.actual = usuario;
            return true;
        }
        JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    
    public boolean cambiarPassword() {
    Usuario usuarioActual = getUsuario();

    if (usuarioActual != null) {
        String passwordActual = JOptionPane.showInputDialog(null, "Ingrese su contraseña actual:", "Cambiar Contraseña", JOptionPane.QUESTION_MESSAGE);
        
        if (passwordActual != null && usuarioActual.getPassword().equals(passwordActual)) {
            String nuevaPassword = JOptionPane.showInputDialog(null, "Ingrese la contraseña nueva:", "Cambiar Contraseña", JOptionPane.QUESTION_MESSAGE);
            
            if (nuevaPassword != null && nuevaPassword.length() == 5) {
                usuarioActual.setPassword(nuevaPassword);
                JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La nueva contraseña debe tener 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña actual incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "No hay usuario autenticado.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

    public boolean eliminarCuenta() {
    Usuario usuarioActual = getUsuario();

    if (usuarioActual != null) {
        int si = JOptionPane.showConfirmDialog(null, "¿Desea eliminar su cuenta?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (si == 0) {
            String passwordActual = JOptionPane.showInputDialog(null, "Ingrese su contraseña actual:", "Eliminar Cuenta", JOptionPane.QUESTION_MESSAGE);
            if (passwordActual != null && usuarioActual.getPassword().equals(passwordActual)) {
                for (int i = 0; i < cuentas.length; i++) {
                    if (cuentas[i] != null && cuentas[i].equals(usuarioActual)) {
                        cuentas[i] = null;
                        contador--; 
                        usuarioActual = null;
                        JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña actual incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "No hay usuario autenticado.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

     public String Ranking(){
         int numUsuariosActivos = 0;
        for (Usuario usuario : cuentas) {
            if (usuario != null) {
                numUsuariosActivos++;
            }
        }
        Usuario[] usuariosActivos = new Usuario[numUsuariosActivos];
        int index = 0;
        for (Usuario usuario : cuentas) {
            if (usuario != null) {
                usuariosActivos[index++] = usuario;
            }
        }

        
        for (int i = 0; i < usuariosActivos.length - 1; i++) {
            for (int j = 0; j < usuariosActivos.length - i - 1; j++) {
                if (usuariosActivos[j].getPuntos() < usuariosActivos[j + 1].getPuntos()) {
                    // Intercambiar usuarios
                    Usuario temp = usuariosActivos[j];
                    usuariosActivos[j] = usuariosActivos[j + 1];
                    usuariosActivos[j + 1] = temp;
                }
            }
        }

    
        String ranking = "";
        for (int i = 0; i < usuariosActivos.length; i++) {
            Usuario usuario = usuariosActivos[i];
            ranking += "Posicion: " + (i+1) + 
                " - Usuario: " + usuario.getCuenta() + 
                " - Puntos: " + usuario.getPuntos() + "\n";
        }
        return ranking;
    }
                
    public String Batallas(){
        return "Cantidad de Usuarios Activos: " + contador + 
                "\nCantidad de Usuarios Historicos: " + contadorTotal +
                "\nCantidad de Patidas Jugadas: " + partidasTotal +
                "\nCantidad de veces que ganaron los HEROES: " + heroes +
                "\nCantidad de veces que ganaron los VILLANOS: " + villanos;
    }
    
    public void imprimirCuentas() {
        System.out.println("Cuentas almacenadas:");
        for (Usuario usuario : cuentas) {
            if (usuario != null) {
                System.out.println(usuario);
            }
        }
    }
   
    public String getNombreUsuarioPorIndice(int indice) {
        if (indice >= 0 && indice < contador) {
            return cuentas[indice].getCuenta();
        }
        return null;
    }

    public Usuario getUsuarioPorNombre(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (cuentas[i].getCuenta().equalsIgnoreCase(nombre)) {
                return cuentas[i];
            }
        }
        return null;
    }

    public boolean usuarioExiste(String nombre) {
        return getUsuarioPorNombre(nombre) != null;
    }
}
