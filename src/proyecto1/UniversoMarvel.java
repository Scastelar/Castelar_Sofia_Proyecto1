
package proyecto1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


public class UniversoMarvel extends JFrame implements ActionListener{
    JLabel titulo = new JLabel("UNIVERSO MARVEL");
    JLabel ranking = new JLabel("Ranking");
    JLabel batallas = new JLabel("Batallas");
    JButton regresar = new JButton("Regresar");
    JTextArea rankingTxt = new JTextArea();
    JTextArea batallasTxt = new JTextArea();
    JScrollPane scroll = new JScrollPane();
    JScrollPane scroll2 = new JScrollPane();
    
    public UniversoMarvel(){
    this.getContentPane().setLayout(null);
    this.getContentPane().setBackground(new java.awt.Color(204, 204, 255));
    setTitle("Universo Marvel");
    setSize(770,470);
    setResizable(false);
    
    //TITULO
    titulo.setBounds(20,0,250,130);
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
    
    //RANKING
    ranking.setBounds(300, 0, 200, 100);
    ranking.setBackground(new java.awt.Color(0, 51, 102));
    ranking.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
    ranking.setForeground(new java.awt.Color(0, 51, 102));
    add(ranking);
    rankingTxt.setBounds(300, 90, 400, 110);
    rankingTxt.setEditable(false);
    rankingTxt.setBackground(new java.awt.Color(204, 204, 255));
    rankingTxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    rankingTxt.setForeground(new java.awt.Color(255, 255, 255));
    rankingTxt.setLineWrap(true);
    rankingTxt.setText("Hola 123");
    scroll.setViewportView(rankingTxt);
    add(rankingTxt);
    
    //BATALLAS
    batallas.setBounds(300, 160, 200, 100);
    batallas.setBackground(new java.awt.Color(0, 51, 102));
    batallas.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
    batallas.setForeground(new java.awt.Color(0, 51, 102));
    add(batallas);
    batallasTxt.setBounds(300, 250, 400, 110);
    batallasTxt.setEditable(false);
    batallasTxt.setBackground(new java.awt.Color(204, 204, 255));
    batallasTxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    batallasTxt.setForeground(new java.awt.Color(255, 255, 255));
    batallasTxt.setLineWrap(true);
    batallasTxt.setText("Hola 123");
    scroll2.setViewportView(batallasTxt);
    add(batallasTxt);
    
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    this.setLocationRelativeTo(this);
    this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt){
       if (regresar==evt.getSource()){
            new MenuPrincipal().setVisible(true);
            this.setVisible(false);
        } 
    }
    
    public void setVisible(boolean mostrar) {
        if (mostrar) {
            mostrarDatos();
        }
        super.setVisible(mostrar);
    }
     
    private void mostrarDatos() {
        cuentasStratego gestor = cuentasStratego.getInstance();
        Usuario usuarioActual = gestor.getUsuario();
    
        if (usuarioActual != null) {
            rankingTxt.setText(gestor.Ranking());
            batallasTxt.setText(gestor.Batallas());
        } 
    }
}