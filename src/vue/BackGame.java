package vue;

import javax.swing.JPanel;



import java.awt.BorderLayout;
import java.awt.Color;



public class BackGame extends JPanel{


    public BackGame(){
        super();
        setLayout(new BorderLayout());
        setBackground(Color.decode("#2b87d1"));
    }
    public void addComposant(Vue v){
        if(v instanceof LabelGame){
            
            add((LabelGame)v, BorderLayout.SOUTH);
        }
        else if(v instanceof Grille){
            JPanel pan = new JPanel();
            pan.add((Grille)v);
            pan.setBackground(null);
       
            add(pan, BorderLayout.CENTER);
        }
    }
    public void flash(boolean b){
        if(b){
            setBackground(Color.decode("#3ea6fa"));
        }
        else{
            setBackground(Color.decode("#2b87d1"));
        }
    }


    
}
