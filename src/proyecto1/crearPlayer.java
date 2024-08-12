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

public class crearPlayer extends JFrame implements ActionListener {
    JLabel usuario = new JLabel("Usuario");
    JLabel contrasena = new JLabel("Contraseña");
    JTextField usuarioText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JLabel pregunta = new JLabel("Ya tienes cuenta?");
    JButton login = new JButton("Log in");
    JButton crear = new JButton("Crear Player");
    Login Log;

    public crearPlayer(Login Log) {
        this.Log = Log;
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.white);
        setTitle("Crear Player");
        setSize(400, 470);
        setResizable(false);

        // USUARIO
        usuario.setBounds(50, 60, 100, 50);
        usuario.setFont(new java.awt.Font("Segoe UI", 1, 18));
        add(usuario);
        usuarioText.setBounds(50, 110, 270, 40);
        add(usuarioText);

        // PASSWORD
        contrasena.setBounds(50, 150, 100, 50);
        contrasena.setFont(new java.awt.Font("Segoe UI", 1, 18));
        add(contrasena);
        passwordText.setBounds(50, 200, 270, 40);
        add(passwordText);

        // BOTON CREAR PLAYER
        crear.setBounds(100, 260, 150, 40);
        crear.setBackground(new java.awt.Color(255, 0, 51));
        crear.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        crear.setForeground(new java.awt.Color(255, 255, 255));
        crear.addActionListener(this);
        add(crear);

        // BOTON FRAME LOGIN
        pregunta.setBounds(50, 330, 150, 30);
        pregunta.setForeground(new java.awt.Color(153, 153, 153));
        pregunta.setFont(new java.awt.Font("Segoe UI", 1, 12));
        add(pregunta);
        login.setBounds(200, 330, 110, 30);
        login.setBackground(new java.awt.Color(153, 153, 153));
        login.setFont(new java.awt.Font("Segoe UI", 1, 12));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.addActionListener(this);
        add(login);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (login == evt.getSource()) {
            Log.setVisible(true);
            this.setVisible(false);
        }

        if (crear == evt.getSource()) {
            String cuenta = usuarioText.getText().toLowerCase();
            String password = new String(passwordText.getPassword());

            if (cuenta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                usuarioText.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                passwordText.requestFocus();
                return;
            }

            Usuario nuevo = new Usuario(cuenta, password);
            if (cuentasStratego.getInstance().agregarCuenta(nuevo.getCuenta(), nuevo.getPassword())) {
                JOptionPane.showMessageDialog(this, "Cuenta creada!");
                usuarioText.setText("");
                passwordText.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ya existe o no se pudo crear la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
