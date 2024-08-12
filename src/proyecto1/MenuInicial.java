
package proyecto1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MenuInicial extends JFrame implements ActionListener{
    JLabel titulo = new JLabel("MENU INICIAL");
    JLabel imagen = new JLabel();
    JButton login = new JButton("Login");
    JButton crearPlayer = new JButton("Crear Player");
    JButton salir = new JButton("Salir");
   
    public MenuInicial(){
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new java.awt.Color(204, 204, 255));
        setTitle("Menu Inicial");
        setSize(770,470);
        setResizable(false);
        
        //IMAGEN
        imagen.setBounds(200, 0, 570, 470);
        SetImageLabel(imagen, "src\\imagenes\\Stratego_ Marvel Heroes.jpg");
        add(imagen);
        
        //TITULO
        titulo.setBounds(20,0,200,170);
        titulo.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 51, 102));
        add(titulo);
        
        //BOTON LOGIN
        login.setBounds(20,150,150,50);
        login.setBackground(new java.awt.Color(0, 51, 102));
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        //BOTON CREAR PLAYER
        crearPlayer.setBounds(20,220,150,50);
        crearPlayer.setBackground(new java.awt.Color(0, 51, 102));
        crearPlayer.setForeground(Color.white);
        crearPlayer.addActionListener(this);
        add(crearPlayer);
        
        //BOTON SALIR
        salir.setBounds(20,290,150,50);
        salir.setBackground(new java.awt.Color(0, 51, 102));
        salir.setForeground(Color.white);
        salir.addActionListener(this);
        add(salir);
         
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt){
       if (login==evt.getSource()){
            new Login().setVisible(true);
            this.setVisible(false);
       }
       if (crearPlayer==evt.getSource()){
            new crearPlayer(new Login()).setVisible(true);
            this.setVisible(false);
       }
       if (salir==evt.getSource()){
            JOptionPane.showMessageDialog(null,"Salida Exitosa");
            this.dispose();
            System.exit(0);
       }
           
    }
    
     private void SetImageLabel(JLabel labelName, String root){
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }
    
    public static void main(String[] args) {
        MenuInicial Stratego = new MenuInicial();
    }
    
}
