package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import modele.Modele;
import modele.Sequence;

public class LabelGame extends JLabel implements Vue {



    private Sequence modele;

    public LabelGame(){
        
        super("LEVEL : ", SwingConstants.CENTER);
        setForeground(Color.WHITE);
        setFont(new Font("Dubai", Font.BOLD, 50));

    }

    @Override
    public void notifierChangement() {

        setText("LEVEL : "+(modele.getSeq().size()+1));
        
    }

    @Override
    public void setModele(Modele m) {
        this.modele = ((Sequence)m);
        m.enregistrer(this);

        
    }
    
}
