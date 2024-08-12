
package proyecto1;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;


public class StrategoJuego extends JFrame implements ActionListener {
    JLabel titulo = new JLabel("STRATEGO");
    JLabel turno = new JLabel("");
    JTextArea personajeDatos = new JTextArea("");
    JPanel personajePanel = new JPanel(); 
    JPanel heroesEliminados = new JPanel(new GridLayout(1, 2));
    JPanel villanosEliminados = new JPanel(new GridLayout(1, 2));
    JButton retirar = new JButton("RETIRAR");
    JButton[][] buttons = new JButton[10][10];
    Personaje[][] personajes = new Personaje[10][10];
    Personaje personajeSeleccionado;
    //JPanel tablero = new JPanel();
    JButton botonSeleccionado;
    int botonFila;
    int botonCol;
    Personaje[] heroes = new Personaje[40];
    Personaje[] villanos = new Personaje[40];
    Random random = new Random();
    String turnoActual;
    cuentasStratego cuentas;
    
    public StrategoJuego(){
        setTitle("Stratego - Marvel Heroes");
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new java.awt.Color(204, 204, 255));
        titulo.setBounds(350, 0, 300, 90);
        titulo.setFont(new java.awt.Font("Trebuchet MS", 1, 40));
        titulo.setForeground(new java.awt.Color(0, 51, 102));
        add(titulo);
        
        //BOTON DE RETIRO
        retirar.setBounds(10,10,150,40);
        retirar.setBackground(Color.red);
        retirar.setForeground(Color.white);
        retirar.addActionListener(this);
        add(retirar);
        
        turno.setBounds(170, 470, 200, 100);
        turno.setText("Turno Actual: " + turnoActual);
        add(turno);
        
        Juego();
        cuentas = cuentasStratego.getInstance();
        
        
        //MOSTRAR FICHAS ELIMINADAS
        heroesEliminados.setBounds(620,80,200,200);
        heroesEliminados.setLayout(new FlowLayout());
        add(heroesEliminados);
        
        villanosEliminados.setBounds(620,300,200,200);
        villanosEliminados.setLayout(new FlowLayout());
        villanosEliminados.setBackground(Color.black);
        add(villanosEliminados);
        
        
        //MOSTRAR FICHA SELECCIONADA
        personajePanel.setBounds(20,100,120,140);
        personajePanel.setLayout(new FlowLayout());
        add(personajePanel);
        personajeDatos.setBounds(20, 250, 150, 300);
        personajeDatos.setBackground(new java.awt.Color(204, 204, 255));
        add(personajeDatos);
       
        setSize(870,650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }
    
    public void Juego() {
     JPanel tablero = new JPanel();
     tablero.setLayout(new GridLayout(10, 10));
     tablero.setBounds(170, 100, 400, 400);

     inicializarBotones(tablero);
     inicializarPersonajes();
     colocarPersonajes();
     arregloBandos();
     zonasProhibidas();
     configurarVisibilidadInicial();
     this.add(tablero);
    }

    public void actionPerformed(ActionEvent event){
       if (retirar==event.getSource()){
            int si = JOptionPane.showConfirmDialog(null, "Desea retirarse de la partida?", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
            if (si == 0){
            new MenuPrincipal().setVisible(true);
            this.setVisible(false);
           }
        }       
    }

    private void inicializarBotones(JPanel tablero) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.addActionListener(new Funciones());
                buttons[i][j] = button;
                buttons[i][j].setPreferredSize(new java.awt.Dimension(40, 40));
                tablero.add(button);  
            }
        }
    }

    private void inicializarPersonajes() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                personajes[i][j] = null;
            }
        }
    }

    private void colocarPersonajesAleatorios(Personaje[] heroes, Personaje[] villanos) {
        //HEROES
        for (int i = 4; i < heroes.length; i++) { 
            Personaje personaje = heroes[i];
            boolean colocado = false;
            while (!colocado) {
                int fila = random.nextInt(0, 4);
                int columna = encontrarPosicionDisponibleEnFila(fila);
                if (columna != -1) {
                    colocarFichaEnPosicion(personaje, fila, columna);
                    colocado = true; 
                } else {
                }
            }
        }
    
        //VILLANOS
        for (int i = 4; i < villanos.length; i++) {
            Personaje personaje = villanos[i];
            boolean colocado = false;
            while (!colocado) {
                System.out.println("Intentando colocar " + personaje.getName());
                int fila = random.nextInt(6, 10);
                int columna = encontrarPosicionDisponibleEnFila(fila);
                if (columna != -1) {
                    colocarFichaEnPosicion(personaje, fila, columna);
                    colocado = true; 
                } 
            }
        }
    }
    private void colocarPersonajes() {
    // COLOCAR TIERRA
    int columnaTierra = random.nextInt(8) + 1;
    colocarFichaEnPosicion(Personaje.Tierra, 0, columnaTierra);

    // COLOCAR NOVABLASTS
    colocarFichaEnPosicion(Personaje.NovaBlast, 1, columnaTierra); // Enfrente
    colocarFichaEnPosicion(Personaje.NovaBlast, 0, columnaTierra - 1); // Izquierda
    colocarFichaEnPosicion(Personaje.NovaBlast, 0, columnaTierra + 1); // Derecha

    // COLOCAR TIERRA OBTENIDA
    int columnaTierraObtenida = random.nextInt(8) + 1;
    colocarFichaEnPosicion(Personaje.TierraObtenida, 9, columnaTierraObtenida);

    // COLOCAR PUMPKINBOMBS
    colocarFichaEnPosicion(Personaje.PumpkinBomb, 8, columnaTierraObtenida); // Enfrente
    colocarFichaEnPosicion(Personaje.PumpkinBomb, 9, columnaTierraObtenida - 1); // Izquierda
    colocarFichaEnPosicion(Personaje.PumpkinBomb, 9, columnaTierraObtenida + 1); // Derecha

    //COLOCAR RANGO 2
    colocarFichasRango2(heroes, 2, 3); 
    colocarFichasRango2(villanos, 6, 7); 
}

private void colocarFichasRango2(Personaje[] bando, int filaInicio, int filaFin) {
    for (Personaje personaje : bando) {
        if (personaje != null) {
            try {
                if (personaje.getRango() == 2) {
                    boolean colocado = false;
                    while (!colocado) {
                        int fila = random.nextInt(filaFin - filaInicio + 1) + filaInicio;
                        int columna = random.nextInt(10);
                        if (personajes[fila][columna] == null) {
                            colocarFichaEnPosicion(personaje, fila, columna);
                            colocado = true;
                        }
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("Error al intentar colocar la ficha: " + personaje.getName() + ". Ficha con rango inválido.");
            }
        }
    }
}



    private void configurarVisibilidadInicial() {
        turnoActual = "Heroes";
    if (turnoActual.equals("Heroes")) {
        turnoActual = "Heroes";
        turno.setText("Turno Actual: " + turnoActual);
        ocultarFichasDelBandoContrario();
        mostrarFichasDelBandoActivo();
    } else {
        turnoActual = "Villanos";
        turno.setText("Turno Actual: " + turnoActual);
        ocultarFichasDelBandoContrario();
        mostrarFichasDelBandoActivo();
    }
}

    private int encontrarPosicionDisponibleEnFila(int fila) {
        for (int columna = 0; columna < 10; columna++) {
            if (personajes[fila][columna] == null) {
                return columna;
            }
        }
        return -1;
    }

    private void colocarFichaEnPosicion(Personaje personaje, int fila, int columna) {
    if (columna >= 0 && columna < 10) {
        if (personajes[fila][columna] == null) {
            personajes[fila][columna] = personaje;
            setButtonIcon(buttons[fila][columna], personaje.getName());
        } 
    }
}

 private class Funciones implements ActionListener {
     
    public void actionPerformed(ActionEvent event) {
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (event.getSource() == buttons[i][j]) {
                    if (personajeSeleccionado == null) {
                        seleccionarPersonaje(i, j);
                        botonSeleccionado.setBorder(BorderFactory.createLineBorder(Color.pink));
                                               
                        ImageIcon icono = new ImageIcon("src\\imagenes\\"+personajes[i][j].getName()+".png");
                        Image imagen = icono.getImage(); 
                        Image imagenEscalada = imagen.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);  
                        icono = new ImageIcon(imagenEscalada);
                        JLabel imagenLabel = new JLabel(icono);
                        buttons[i][j].setIcon(icono);
                        personajePanel.removeAll();
                        personajePanel.add(imagenLabel);
                        personajePanel.revalidate();
                        personajePanel.repaint();

                        String atributos = " Nombre: " +  personajes[i][j].getName() +
                                            "\n Bando: " +  personajes[i][j].getBando() +
                                            "\n Rango: " +  personajes[i][j].getRango();
                        personajeDatos.setText(atributos);
                        
                        buttons[i][j].revalidate();
                        buttons[i][j].repaint();
                        personajeDatos.revalidate();
                        personajeDatos.repaint();
                    } else {
                    if (buttons[i][j] == botonSeleccionado) {
                        resetSeleccion();
                        } else if (personajes[i][j] == null) {
                            moverPersonaje(i, j);
                            cambiarTurno();
                            System.out.println(turnoActual);
                        } else {
                            atacarPersonaje(i, j);
                            cambiarTurno();
                            System.out.println(turnoActual);
                            turno.setText("Turno Actual: " + turnoActual);
                        }
                    }
                }
            }
        }
    }
                

    public void seleccionarPersonaje(int i, int j) {
    
        if (personajes[i][j] != null && personajes[i][j].getRango() == 0) {
            botonSeleccionado = buttons[i][j];
        } else
        if (personajes[i][j] != null && personajes[i][j].getRango() > 0) {
            if (botonSeleccionado != null && botonSeleccionado != buttons[i][j]) {
                resetSeleccion();
            }
            personajeSeleccionado = personajes[i][j];
            botonSeleccionado = buttons[i][j];
            botonFila = i;
            botonCol = j;
        }
}
        

    private void moverPersonaje(int i, int j) {
       
            int nuevaFila = i;
            int nuevaCol = j;

            if (!esMovimientoValido(nuevaFila, nuevaCol)) {
                return;
            }

            buttons[nuevaFila][nuevaCol].setIcon(botonSeleccionado.getIcon());
            eliminarBotonIcon(botonSeleccionado);
            personajes[nuevaFila][nuevaCol] = personajeSeleccionado;
            personajes[botonFila][botonCol] = null;
            resetSeleccion();
        
    }

    private boolean esMovimientoValido(int nuevaFila, int nuevaCol) {
        
        if (Math.abs(nuevaFila - botonFila) > 1 || Math.abs(nuevaCol - botonCol) > 1) {
            if (personajeSeleccionado.getRango() != 2) {
                JOptionPane.showMessageDialog(null, "No puedes mover la ficha más de un espacio");
                return false;
            }
        }
        

        if (nuevaFila != botonFila && nuevaCol != botonCol) {
            JOptionPane.showMessageDialog(null, "No puedes mover la ficha en diagonal");
            return false;
        }

        if (personajeSeleccionado.getRango() == 2) {
            int dx = Math.abs(nuevaFila - botonFila);
            int dy = Math.abs(nuevaCol - botonCol);
            if (dx != 0 && dy != 0) {
                JOptionPane.showMessageDialog(null, "Solo puedes mover fichas de rango 2 horizontal o verticalmente");
                return false;
            }
        }

        return true;
    }

    private void atacarPersonaje(int i, int j) {
        if (personajes[i][j] == null) {
            JOptionPane.showMessageDialog(null, "No hay ficha en la posición objetivo.");
            return;
        }

        int difFila = Math.abs(i - botonFila);
        int difCol = Math.abs(j - botonCol);

        if (difFila > 1 || difCol > 1) {
            if (personajeSeleccionado.getRango() != 2) {
                JOptionPane.showMessageDialog(null, "Solo las fichas de rango 2 pueden atacar a fichas no adyacentes");
                return;
            }
        }

        if (difFila != 0 && difCol != 0) {
            JOptionPane.showMessageDialog(null, "No puedes atacar en diagonal");
            return;
        }

        if (Ganar()) {
            JOptionPane.showMessageDialog(null, "GANADOR: " + turnoActual);
            cuentas.incrementarPartidas();
            new MenuPrincipal().setVisible(true);
            setVisible(false);
            
            
        }
        if (personajeSeleccionado.getBando() != personajes[i][j].getBando()) {
            String resultado = resolverAtaque(i, j);
            JOptionPane.showMessageDialog(null, resultado);
            resetSeleccion();
        } 
        else {
            JOptionPane.showMessageDialog(null, "No puedes atacar a un personaje del mismo grupo");
        }
    }
    
    private boolean Ganar() {
        for (int i = 0; i < personajes.length; i++) {
            for (int j = 0; j < personajes[i].length; j++) {
                if (personajes[i][j] != null) {
                    if (personajes[i][j].getName().equals("Tierra") && personajes[i][j].getBando().equals("Heroes")) {
                        return false;
                    } else if (personajes[i][j].getName().equals("TierraObtenida") && personajes[i][j].getBando().equals("Villanos")) {
                        return false;
                    }
                }
            }
        }
        if (turnoActual.equals("Heroes")) {
            JOptionPane.showMessageDialog(null, "GANADOR: Villanos");
        } else {
            JOptionPane.showMessageDialog(null, "GANADOR: Heroes");
        }
        return true;
        
    }


    private String resolverAtaque(int i, int j) {
        if (personajeSeleccionado == null || personajes[i][j] == null) {
            return "Error: Una de las fichas es nula.";
        }

        String resultado = "";
        if (personajes[i][j].getName().equals("Tierra") || personajes[i][j].getName().equals("TierraObtenida")){
            Ganar();
            new MenuPrincipal().setVisible(true);
            setVisible(false);
            resultado = "Juego Completado";
            
        } else if (personajeSeleccionado.getRango()==3 && 
            (personajes[i][j].getName().equals("NovaBlast") || personajes[i][j].getName().equals("PumpkinBomb"))) {
            resultado = "GANADOR: " + personajeSeleccionado.getName() + "\nPERDEDOR: " + personajes[i][j].getName();
            AgregarBotonEliminado(personajes[i][j]);
            eliminarBotonIcon(buttons[i][j]);
            buttons[i][j].setBackground(UIManager.getColor("Button.background"));
            personajes[i][j] = null;
            
        } else if (personajeSeleccionado.getRango()!=3 && 
            (personajes[i][j].getName().equals("NovaBlast") || personajes[i][j].getName().equals("PumpkinBomb"))) {
            resultado = "GANADOR: " + personajes[i][j].getName() + "\nPERDEDOR: " + personajeSeleccionado.getName();
            AgregarBotonEliminado(personajes[botonFila][botonCol]);
            eliminarBotonIcon(buttons[botonFila][botonCol]);
            buttons[botonFila][botonCol].setBackground(UIManager.getColor("Button.background"));
            personajes[botonFila][botonCol]= null;
            
        } else if (personajeSeleccionado.getRango()!=3 && 
            (personajes[i][j].getName().equals("NovaBlast") || personajes[i][j].getName().equals("PumpkinBomb"))) {
            resultado = "GANADOR: " + personajes[i][j].getName() + "\nPERDEDOR: " + personajeSeleccionado.getName();
            AgregarBotonEliminado(personajes[botonFila][botonCol]);
            eliminarBotonIcon(buttons[botonFila][botonCol]);
            buttons[botonFila][botonCol].setBackground(UIManager.getColor("Button.background"));
            personajes[botonFila][botonCol] = null;
            
        } else if (personajeSeleccionado.getRango() == 1 && personajes[i][j].getRango() == 10) {
            resultado = "GANADOR: " + personajeSeleccionado.getName() + "\nPERDEDOR: " + personajes[i][j].getName();
            AgregarBotonEliminado(personajes[i][j]);
            eliminarBotonIcon(buttons[i][j]);
            buttons[i][j].setBackground(UIManager.getColor("Button.background"));
            personajes[i][j] = null;
            
        } else if (personajeSeleccionado.getRango() > personajes[i][j].getRango()) {
            resultado = "GANADOR: " + personajeSeleccionado.getName() + "\nPERDEDOR: " + personajes[i][j].getName();
            AgregarBotonEliminado(personajes[i][j]);
            eliminarBotonIcon(buttons[i][j]);
            buttons[i][j].setBackground(UIManager.getColor("Button.background"));
            personajes[i][j] = null;
            
        } else if (personajeSeleccionado.getRango() < personajes[i][j].getRango()) {
            resultado = "GANADOR: " + personajes[i][j].getName() + "\nPERDEDOR: " + personajeSeleccionado.getName();
            AgregarBotonEliminado(personajes[botonFila][botonCol]);
            eliminarBotonIcon(buttons[botonFila][botonCol]);
            buttons[botonFila][botonCol].setBackground(UIManager.getColor("Button.background"));
            personajes[botonFila][botonCol] = null;
        } 
            else {
            resultado = "EMPATE: Ambos personajes se han eliminado";
            AgregarBotonEliminado(personajes[i][j]);
            eliminarBotonIcon(buttons[i][j]);
            eliminarBotonIcon(buttons[botonFila][botonCol]);
            AgregarBotonEliminado(personajes[botonFila][botonCol]);
            buttons[i][j].setBackground(UIManager.getColor("Button.background"));
            buttons[botonFila][botonCol].setBackground(UIManager.getColor("Button.background"));
            personajes[i][j] = null;
            personajes[botonFila][botonCol] = null;
        }

        return resultado;
    }

    private void eliminarBotonIcon(JButton button) {
        button.setIcon(null);
    }
       
    private void AgregarBotonEliminado(Personaje ficha) {
       agregarFichaEliminada(ficha);
       
    }
     
    public void agregarFichaEliminada(Personaje fichaEliminada) {
    JLabel imagenLabel = new JLabel();
    ImageIcon icono = new ImageIcon("src\\imagenes\\" + fichaEliminada.getName() + ".png"); 

    Image imagen = icono.getImage();
    Image imagenEscalada = imagen.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    icono = new ImageIcon(imagenEscalada);
    imagenLabel.setIcon(icono);

    if (fichaEliminada.getBando().equals("Heroes")) {
        heroesEliminados.add(imagenLabel);
    } else if (fichaEliminada.getBando().equals("Villanos")) {
        villanosEliminados.add(imagenLabel);
    }

    heroesEliminados.revalidate();
    heroesEliminados.repaint();
    villanosEliminados.revalidate();
    villanosEliminados.repaint();
    }
  
    private void resetSeleccion() {
        if (botonSeleccionado != null) {
            botonSeleccionado.setBorder(UIManager.getBorder("Button.border"));
        }
        personajeSeleccionado = null;
        botonSeleccionado = null;
    }
    }

   private void setButtonIcon(JButton button, String imagePath) {
    ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/" + imagePath + ".png"));
    Image imagen = icono.getImage();
    Image imagenEscalada = imagen.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    button.setIcon(new ImageIcon(imagenEscalada));
    button.revalidate();
    button.repaint();
}
   
   private void ocultarFichasDelBandoContrario() {
        for (int i = 0; i < personajes.length; i++) {
            for (int j = 0; j < personajes[i].length; j++) {
                if (personajes[i][j] != null) {
                    if (turnoActual.equals("Heroes") && personajes[i][j].getBando().equals("Villanos")) {
                        buttons[i][j].setIcon(null);
                        buttons[i][j].setBackground(Color.lightGray);
                    } else if (turnoActual.equals("Villanos") && personajes[i][j].getBando().equals("Heroes")) {
                        buttons[i][j].setIcon(null);
                        buttons[i][j].setBackground(Color.lightGray);
                    }
                }
            }
        }
    }

   private void mostrarFichasDelBandoActivo() {
        for (int i = 0; i < personajes.length; i++) {
            for (int j = 0; j < personajes[i].length; j++) {
                if (personajes[i][j] != null) {
                    if (turnoActual.equals("Heroes") && personajes[i][j].getBando().equals("Heroes")) {
                        setButtonIcon(buttons[i][j], personajes[i][j].getName());
                        buttons[i][j].setBackground(UIManager.getColor("Button.background"));
                    } else if (turnoActual.equals("Villanos") && personajes[i][j].getBando().equals("Villanos")) {
                        setButtonIcon(buttons[i][j], personajes[i][j].getName());
                        buttons[i][j].setBackground(UIManager.getColor("Button.background"));
                    }
                }
            }
        }
    }

   public void cambiarTurno() {
    System.out.println("Cambiar turno: " + turnoActual);
    if (turnoActual.equals("Heroes")) {
        turnoActual = "Villanos";
    } else {
        turnoActual = "Heroes";
    }
    System.out.println("Nuevo turno: " + turnoActual);
    turno.setText("Turno Actual: " + turnoActual);
    ocultarFichasDelBandoContrario();
    mostrarFichasDelBandoActivo();
}
   
    private void arregloBandos(){
        //HEROES
        heroes[0] =  Personaje.Tierra;
        heroes[1] =  Personaje.NovaBlast;
        heroes[2] =  Personaje.NovaBlast;
        heroes[3] =  Personaje.NovaBlast;
        heroes[4] =  Personaje.DrStrange;
        heroes[5] =  Personaje.NovaBlast;
        heroes[6] =  Personaje.Elektra;
        heroes[7] =  Personaje.NightCrawler;
        heroes[8] =  Personaje.Gambit;
        heroes[9] =  Personaje.SpiderGirl;
        heroes[10] =  Personaje.IceMan;
        heroes[11] =  Personaje.Storm;
        heroes[12] =  Personaje.Phoenix;
        heroes[13] =  Personaje.Beast;
        heroes[14] =  Personaje.Daredevil;
        heroes[15] =  Personaje.BlackWidow;
        heroes[16] =  Personaje.EmmaFrost;
        heroes[17] =  Personaje.SheHulk;
        heroes[18] =  Personaje.GiantMan;
        heroes[19] =  Personaje.Colossus;
        heroes[20] =  Personaje.NovaBlast;
        heroes[21] =  Personaje.Blade;
        heroes[22] =  Personaje.Thing;
        heroes[23] =  Personaje.Punisher;
        heroes[24] =  Personaje.InvisibleWoman;
        heroes[25] =  Personaje.GhostRider;
        heroes[26] =  Personaje.Cyclops;
        heroes[27] =  Personaje.HumanTorch;
        heroes[28] =  Personaje.Thor;
        heroes[29] =  Personaje.SilverSurfer;
        heroes[30] =  Personaje.NovaBlast;
        heroes[31] =  Personaje.Hulk;
        heroes[32] =  Personaje.IronMan;
        heroes[33] =  Personaje.SpiderMan;
        heroes[34] =  Personaje.Wolverine;
        heroes[35] =  Personaje.Namor;
        heroes[36] =  Personaje.NickFury;
        heroes[37] =  Personaje.CapitanAmerica;
        heroes[38] =  Personaje.ProfessorX;
        heroes[39] =  Personaje.MrFantastic;

        //VILLANOS
       villanos[0] =  Personaje.TierraObtenida;
        villanos[1] =  Personaje.PumpkinBomb;
        villanos[2] =  Personaje.PumpkinBomb;
        villanos[3] =  Personaje.PumpkinBomb;
        villanos[4] =  Personaje.PumpkinBomb;
        villanos[5] =  Personaje.Leader;
        villanos[6] =  Personaje.Viper;
        villanos[7] =  Personaje.Electro;
        villanos[8] =  Personaje.MrSinister;
        villanos[9] =  Personaje.Sentinel1;
        villanos[10] =  Personaje.Sentinel2;
        villanos[11] =  Personaje.Ultron;
        villanos[12] =  Personaje.Sandman;
        villanos[13] =  Personaje.OmegaRed;
        villanos[14] =  Personaje.Lizard;
        villanos[15] =  Personaje.BLACKWIDOW;
        villanos[16] =  Personaje.Juggernaut;
        villanos[17] =  Personaje.Rhino;
        villanos[18] =  Personaje.Carnage;
        villanos[19] =  Personaje.Moleman;
        villanos[20] =  Personaje.PumpkinBomb;
        villanos[21] =  Personaje.Abomination;
        villanos[22] =  Personaje.BlackCat;
        villanos[23] =  Personaje.Sabretooth;
        villanos[24] =  Personaje.Thanos;
        villanos[25] =  Personaje.Deadpool;
        villanos[26] =  Personaje.Mysterio;
        villanos[27] =  Personaje.DrOctopus;
        villanos[28] =  Personaje.Mystique;
        villanos[29] =  Personaje.Onslaught;
        villanos[30] =  Personaje.PumpkinBomb;
        villanos[31] =  Personaje.RedSkull;
        villanos[32] =  Personaje.Bullseye;
        villanos[33] =  Personaje.Venom;
        villanos[34] =  Personaje.Apocalypse;
        villanos[35] =  Personaje.GreenGoblin;
        villanos[36] =  Personaje.Magneto;
        villanos[37] =  Personaje.Kingpin;
        villanos[38] =  Personaje.Galactus;
        villanos[39] =  Personaje.DrDoom; 
   
        colocarPersonajesAleatorios(heroes, villanos);
    }
    
   private void zonasProhibidas(){
        buttons[4][2].setEnabled(false);
        buttons[4][3].setEnabled(false);
        buttons[5][3].setEnabled(false);
        buttons[5][2].setEnabled(false);
        buttons[5][6].setEnabled(false);
        buttons[5][7].setEnabled(false);
        buttons[4][6].setEnabled(false);
        buttons[4][7].setEnabled(false);
        buttons[4][2].setBackground(Color.YELLOW);
        buttons[4][3].setBackground(Color.YELLOW);
        buttons[5][2].setBackground(Color.YELLOW);
        buttons[5][3].setBackground(Color.YELLOW);
        buttons[4][6].setBackground(Color.MAGENTA);
        buttons[4][7].setBackground(Color.MAGENTA);
        buttons[5][6].setBackground(Color.MAGENTA);
        buttons[5][7].setBackground(Color.MAGENTA);
    } 
    
}
