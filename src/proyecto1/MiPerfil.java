package proyecto1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class MiPerfil extends JFrame implements ActionListener {
    JLabel titulo = new JLabel("MI PERFIL");
    JLabel datos = new JLabel("Datos");
    JLabel logs = new JLabel("Logs");
    JLabel ultimoLog = new JLabel("Ultimos Partidos");
    JTextArea datosText = new JTextArea();
    JTextArea logsText = new JTextArea();
    JButton regresar = new JButton("Regresar");
    JButton cambiar = new JButton("Cambiar Contraseña");
    JButton eliminar = new JButton("Eliminar Cuenta");
    JScrollPane scroll = new JScrollPane();
    JScrollPane scroll2 = new JScrollPane();
    
    public MiPerfil(){
    this.getContentPane().setLayout(null);
    this.getContentPane().setBackground(new java.awt.Color(204, 204, 255));
    setTitle("Mi Perfil");
    setSize(770,470);
    setResizable(false);
    
    //TITULO
    titulo.setBounds(40,0,200,130);
    titulo.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
    titulo.setForeground(new java.awt.Color(0, 51, 102));
    add(titulo); 
    
    //BOTON REGRESAR
    regresar.setBounds(40,350,150,50);
    regresar.setBackground(new java.awt.Color(255, 0, 51));
    regresar.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); 
    regresar.setForeground(new java.awt.Color(255, 255, 255));
    regresar.addActionListener(this);
    add(regresar);
    
    //BOTON CAMBIAR CONTRASENA
    cambiar.setBounds(250,360,165,40);
    cambiar.setBackground(new java.awt.Color(0,51,102));
    cambiar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); 
    cambiar.setForeground(new java.awt.Color(255, 255, 255));
    cambiar.addActionListener(this);
    add(cambiar);
    
    //BOTON ELIMINAR CUENTA
    eliminar.setBounds(450,360,160,40);
    eliminar.setBackground(new java.awt.Color(255, 0, 51));
    eliminar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); 
    eliminar.setForeground(new java.awt.Color(255, 255, 255));
    eliminar.addActionListener(this);
    add(eliminar);
    
    //DATOS
    datos.setBounds(250, 0, 200, 130);
    datos.setBackground(new java.awt.Color(0, 51, 102));
    datos.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
    datos.setForeground(new java.awt.Color(0, 51, 102));
    add(datos);
    datosText.setBounds(250, 120, 200, 300);
    datosText.setEditable(false);
    datosText.setBackground(new java.awt.Color(204, 204, 255));
    datosText.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
    datosText.setForeground(new java.awt.Color(255, 255, 255));
    datosText.setLineWrap(true);
    datosText.setText("Hola 123");
    scroll.setViewportView(datosText);
    add(datosText);
    
    //LOGS
    logs.setBounds(500, 0, 200, 130);
    logs.setBackground(new java.awt.Color(0, 51, 102));
    logs.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
    logs.setForeground(new java.awt.Color(0, 51, 102));
    add(logs);
    ultimoLog.setBounds(500, 25, 200, 130);
    ultimoLog.setBackground(new java.awt.Color(0, 51, 102));
    ultimoLog.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); 
    ultimoLog.setForeground(new java.awt.Color(0, 51, 102));
    add(ultimoLog);
    logsText.setBounds(500, 120, 200, 300);
    logsText.setEditable(false);
    logsText.setBackground(new java.awt.Color(204, 204, 255));
    logsText.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
    logsText.setForeground(new java.awt.Color(255, 255, 255));
    logsText.setLineWrap(true);
    logsText.setText("No hay jugadas recientes.");
    scroll2.setViewportView(logsText);
    add(logsText);
        
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    this.setLocationRelativeTo(this);
    this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt){
       //REGRESAR 
       if (regresar==evt.getSource()){
            new MenuPrincipal().setVisible(true);
            this.setVisible(false);
        }
       //CAMBIAR CONTRASENA
       if (cambiar==evt.getSource()){
           cuentasStratego gestor = cuentasStratego.getInstance();
           boolean cambiar = gestor.cambiarPassword();
        }
       //ELIMINAR CUENTA
       if (eliminar==evt.getSource()){
            cuentasStratego gestor = cuentasStratego.getInstance();
            boolean eliminar = gestor.eliminarCuenta();
            if (eliminar) {
                new MenuInicial().setVisible(true);
                this.setVisible(false);
            }
        }
    }
    
    public void setVisible(boolean mostrar) {
        if (mostrar) {
            mostrarDatosUsuario();
        }
        super.setVisible(mostrar);
    }
     
    private void mostrarDatosUsuario() {
        cuentasStratego gestor = cuentasStratego.getInstance();
        Usuario usuarioActual = gestor.getUsuario();

        if (usuarioActual != null) {
            datosText.setText(usuarioActual.toString());
        } else {
            datosText.setText("No hay usuario autenticado.");
        }
    }
}