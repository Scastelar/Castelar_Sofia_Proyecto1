
package proyecto1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Login extends JFrame implements ActionListener{
    JLabel usuario = new JLabel("Usuario");
    JLabel contrasena = new JLabel("Contraseña");
    JTextField usuarioText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JLabel pregunta = new JLabel("No tienes cuenta?");
    JButton login = new JButton("Log in");
    JButton crear = new JButton("Crear Player");
    
    public Login(){
    this.getContentPane().setLayout(null);
    this.getContentPane().setBackground(Color.white);
    setTitle("Log in");
    setSize(400,470);
    setResizable(false);   
    
    //USUARIO
    usuario.setBounds(50, 60, 100, 50);
    usuario.setFont(new java.awt.Font("Segoe UI", 1, 18));
    add(usuario);
    usuarioText.setBounds(50, 110, 270, 40);
    add(usuarioText);
    
    //PASSWORD
    contrasena.setBounds(50, 150, 100, 50);
    contrasena.setFont(new java.awt.Font("Segoe UI", 1, 18));
    add(contrasena);
    passwordText.setBounds(50, 200, 270, 40);
    add(passwordText);
    
    //BOTON LOGIN
    login.setBounds(100,260,150,40);
    login.setBackground(new java.awt.Color(255, 0, 51));
    login.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); 
    login.setForeground(new java.awt.Color(255, 255, 255));
    login.addActionListener(this);
    add(login);
    
    //BOTON FRAME CREAR PLAYER
    pregunta.setBounds(50, 330, 100, 30);
    pregunta.setForeground(new java.awt.Color(153, 153, 153));
    pregunta.setFont(new java.awt.Font("Segoe UI", 1, 12));
    add(pregunta);
    crear.setBounds(160, 335, 110, 20);
    crear.setBackground(new java.awt.Color(153, 153, 153));
    crear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    crear.setForeground(new java.awt.Color(255, 255, 255));
    crear.addActionListener(this);
    add(crear);
    
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    this.setLocationRelativeTo(this);
    this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt){
       if (login==evt.getSource()){
            String cuenta = usuarioText.getText().toLowerCase();
            String password = new String(passwordText.getPassword());

            if ((usuarioText.getText()).isEmpty()==true){
                JOptionPane.showMessageDialog(null, "Ingrese el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                usuarioText.requestFocus();
                return;
            }

           if(cuentasStratego.getInstance().validarCuenta(cuenta, password)){
                new MenuPrincipal().setVisible(true);
                this.dispose();
            } else {
               usuarioText.requestFocus();
            }
       }
       
       if (crear==evt.getSource()){
           new crearPlayer(this).setVisible(true);
           this.setVisible(false);
       }
    }
}   