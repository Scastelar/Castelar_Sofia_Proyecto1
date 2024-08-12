package proyecto1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Seleccionar extends JFrame implements ActionListener {
    private JComboBox<String> usuariosComboBox;
    private JButton elegirOponenteButton;
    private JButton elegirBandoButton;
    private String usuarioSeleccionado;
    private String bandoSeleccionado;

    private static final String[] BANDOS = {"Héroes", "Villanos"};

    public Seleccionar() {
        setTitle("Seleccionar Oponente");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.white);

       
        String[] usuariosActivos = obtenerNombresUsuariosActivos();

       
        JLabel seleccionarUsuarioLabel = new JLabel("Selecciona un usuario:");
        seleccionarUsuarioLabel.setBounds(50, 30, 200, 30);
        seleccionarUsuarioLabel.setFont(new java.awt.Font("Segoe UI", 1, 16));
        add(seleccionarUsuarioLabel);

      
        usuariosComboBox = new JComboBox<>(usuariosActivos);
        usuariosComboBox.setBounds(50, 70, 270, 30);
        add(usuariosComboBox);

        // Botón para elegir oponente
        elegirOponenteButton = new JButton("Elegir Oponente");
        elegirOponenteButton.setBounds(50, 120, 250, 40);
        elegirOponenteButton.setBackground(new java.awt.Color(255, 0, 51));
        elegirOponenteButton.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        elegirOponenteButton.setForeground(Color.white);
        elegirOponenteButton.addActionListener(this);
        add(elegirOponenteButton);

      
        elegirBandoButton = new JButton("Elegir Bando");
        elegirBandoButton.setBounds(50, 180, 250, 40);
        elegirBandoButton.setBackground(new java.awt.Color(255, 0, 51));
        elegirBandoButton.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        elegirBandoButton.setForeground(Color.white);
        elegirBandoButton.addActionListener(this);
        add(elegirBandoButton);

        setVisible(true);
    }

    private String[] obtenerNombresUsuariosActivos() {
        int cantidadUsuariosActivos = cuentasStratego.getInstance().obtenerUsuariosActivos();
        String[] usuarios = new String[cantidadUsuariosActivos];
        for (int i = 0; i < cantidadUsuariosActivos; i++) {
            usuarios[i] = cuentasStratego.getInstance().obtenerNombreUsuarioPorIndice(i);
        }
        
        return usuarios;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == elegirOponenteButton) {
            usuarioSeleccionado = (String) usuariosComboBox.getSelectedItem();
            if (usuarioSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para jugar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "Oponente seleccionado: " + usuarioSeleccionado);
        }

        if (e.getSource() == elegirBandoButton) {
            if (bandoSeleccionado == null) {
                bandoSeleccionado = (String) JOptionPane.showInputDialog(this, "Selecciona tu bando:", "Selección de Bando", JOptionPane.PLAIN_MESSAGE, null, BANDOS, BANDOS[0]);
            }
            String bandoOponente = bandoSeleccionado.equals("Héroes") ? "Villanos" : "Héroes";
            JOptionPane.showMessageDialog(this, "Tu bando: " + bandoSeleccionado + "\nBando del oponente: " + bandoOponente);
            new StrategoJuego().setVisible(true);
            this.setVisible(false);
        }
    }
            
    
}

   

