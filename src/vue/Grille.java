package vue;

import javax.swing.JPanel;

import Controleur.Fen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import java.awt.event.MouseEvent;

import java.awt.event.MouseAdapter;

import modele.Modele;
import modele.Sequence;
import javax.swing.JOptionPane;


public class Grille extends JPanel implements Vue{
    private final int tailleCases = 132;
    private final int tailleEspace = 10;

    private Sequence modele;

    private boolean perfectSeq;
    private BackGame bg;

    public Grille(){
        super();

        this.addMouseListener(mouseAdapter);
        perfectSeq =false;
       setPreferredSize(new Dimension(416,416));
     setBackground(null);
        

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*
        if(perfectSeq){
        g.setColor(Color.decode("#3ea6fa"));

        }
        else{
             g.setColor(Color.decode("#2b87d1"));
        g.fillRect(0, 0, getWidth(), getHeight());
        } */
       

        int[][] cases;
        if(modele!=null){
            cases = modele.getCases();

        }
        else{
            cases = new int[3][3];
        }

        paintCase(g,cases);
    }

    public void paintCase(Graphics g, int[][] cases){
        
        for(int i = 0 ; i< cases.length; i++){
            for(int i2 = 0 ; i2<cases[i].length ; i2++){
                
                if(cases[i][i2] == 1){
                    g.setColor(Color.WHITE);
                }
                else if(cases[i][i2] == -1){
                    g.setColor(Color.RED);
                }
                else{
                    g.setColor(Color.gray);

                }
                g.fillRect(i2*(tailleCases + tailleEspace), i*(tailleCases + tailleEspace), tailleCases, tailleCases);
            }
        }
    }

    public void animationSeq(Graphics g, ArrayList<String> seq){
        
         
        Thread th = new Thread() {
            public void run() {


                for(int i = 0 ; i<seq.size() ; i++){
                    String[] convert = seq.get(i).split("-");
                    int x = Integer.parseInt(convert[0]);
                    int y = Integer.parseInt(convert[1]);

        
                    g.setColor(Color.GREEN);
                    g.fillRect(x*(tailleCases + tailleEspace), y*(tailleCases + tailleEspace), 64, tailleCases);
        
                    try {
                        sleep(1000);

                    } catch (InterruptedException e) {
                       
                    }
        
                    g.setColor(Color.GRAY);
                    g.fillRect(x*(tailleCases + tailleEspace), y*(tailleCases + tailleEspace), tailleCases, tailleCases);    
                }
                
               
            }
        };
        th.start();
        
    }

    private MouseAdapter mouseAdapter = new MouseAdapter(){

        public void mousePressed(MouseEvent e){
            if(modele.getAnimationFini()){

            
                int clickX = (e.getX()/(tailleCases+tailleEspace));
                int clickY = (e.getY()/(tailleCases+tailleEspace));
                boolean test = modele.verifChoix(clickX, clickY);

                
                if( test && modele.endSeq()){
                    
                    modele.vueNextLvl();
                    modele.setAnimationFini();
                    Thread th = new Thread() {
                        public void run() {
                            try {
                                perfectSeq = true;
                                bg.flash(perfectSeq);
                                sleep(300);
                                perfectSeq = false;
                                bg.flash(perfectSeq);

                                sleep(1000);
                                modele.nextTurn();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    th.start();

                    
                    
                
                }
                else if(test && ! modele.endSeq()){
                    
                    modele.next();
                    

                }
                else{
                    
                    Object[] options= new Object[2];
            
                    options[0] ="RELANCER UNE PARTIE";
                    options[1] ="QUITTER";

                    int choix = JOptionPane.showOptionDialog(null,
                        "PERDUE !",
                        "GAME OVER",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                    );
                    if(choix == 0){
                        
                        modele.play();

                        
                    }
                    if(choix == 1){
                        fen.dispose();
                    }
                }
            }
        }
    };
    private Fen fen;
    public void addFen(Fen fen) {
        this.fen = fen;
    }

    @Override
    public void notifierChangement() {
        
        repaint();
        
        
    }

    @Override
    public void setModele(Modele m) {
        this.modele = ((Sequence)m);
        m.enregistrer(this);
        repaint();
    }

    public void setBg(BackGame b){
        this.bg = b;
    }
    
}
