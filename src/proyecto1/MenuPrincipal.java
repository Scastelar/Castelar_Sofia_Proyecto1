
package proyecto1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


public class MenuPrincipal extends JFrame implements ActionListener  {
    JLabel titulo = new JLabel("MENU \nPRINCIPAL");
    JButton cerrarSesion = new JButton("Cerrar Sesion");
    JButton stratego = new JButton("Stratego - Marvel Heroes");
    JButton universoMarvel = new JButton("Universo Marvel");
    JButton configuracion = new JButton("Configuracion");
    JButton perfil = new JButton("Mi Perfil");
    
    MenuPrincipal(){
    this.getContentPane().setLayout(null);
    this.getContentPane().setBackground(new java.awt.Color(204, 204, 255));
    setTitle("Menu Principal");
    setSize(770,470);
    setResizable(false);
    
    //TITULO
    titulo.setBounds(40,0,200,130);
    titulo.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
    titulo.setForeground(new java.awt.Color(0, 51, 102));
    add(titulo);    
    
   //BOTON DE CERRAR SESION
   cerrarSesion.setBounds(40,300,150,50);
   cerrarSesion.setBackground(new java.awt.Color(255, 0, 51));
   cerrarSesion.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); 
   cerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
   cerrarSesion.addActionListener(this);
   add(cerrarSesion);
   
   //BOTON STRATEGO
    stratego.setBounds(360,40,300,70);
    stratego.setBackground(new java.awt.Color(0, 51, 102));
    stratego.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); 
    stratego.setForeground(new java.awt.Color(255, 255, 255));
    stratego.addActionListener(this);
    add(stratego);
    
    //BOTON CONFIGURACION
    configuracion.setBounds(410, 160, 200, 40);
    configuracion.setBackground(Color.white);
    configuracion.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); 
    configuracion.setForeground(new java.awt.Color(0, 51, 102));
    configuracion.addActionListener(this);
    add(configuracion);
    
    //BOTON MI PERFIL
    perfil.setBounds(410, 220, 200, 40);
    perfil.setBackground(Color.white);
    perfil.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); 
    perfil.setForeground(new java.awt.Color(0, 51, 102));
    perfil.addActionListener(this);
    add(perfil);
    
    //BOTON UNIVERSO MARVEL
    universoMarvel.setBounds(410, 280, 200, 40);
    universoMarvel.setBackground(Color.white);
    universoMarvel.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); 
    universoMarvel.setForeground(new java.awt.Color(0, 51, 102));
    universoMarvel.addActionListener(this);
    add(universoMarvel);
   
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    this.setLocationRelativeTo(this);
    this.setVisible(true);    
    }
    
    public void actionPerformed(ActionEvent evt){
      //CERRAR SESION
      if (cerrarSesion==evt.getSource()){
          new MenuInicial().setVisible(true);
            this.setVisible(false);
        }  
      
      //INICIAR PARTIDA 
      if (stratego==evt.getSource()){
         cuentasStratego gestor = cuentasStratego.getInstance();
            int contar=0;
            for (Usuario usuario : gestor.getCuentas()){
                if (usuario != null)
                    contar++;
            }
            if (contar>1){
               int si = JOptionPane.showConfirmDialog(null, "Iniciar nueva Partida?", "Nueva Partida", JOptionPane.YES_NO_CANCEL_OPTION);
               if (si == 0){
                    new Seleccionar().setVisible(true);
                    this.setVisible(false);
               }
            } else {
                JOptionPane.showMessageDialog(null, "No hay usuarios disponibles");
            } 
        }
      
      //CONFIGURACION
      if (configuracion==evt.getSource()){
            JOptionPane.showMessageDialog(null, "Modo Clasico Seleccionado");
        }

      //MI PERFIL
      if (perfil==evt.getSource()){
        new MiPerfil().setVisible(true);
        this.setVisible(false); 
      }
      
      //UNIVERSO MARVEL
      if (universoMarvel==evt.getSource()){
        new UniversoMarvel().setVisible(true);
        this.setVisible(false);
      }
    }
}