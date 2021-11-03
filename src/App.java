import Controleur.Fen;
import modele.Sequence;
import vue.BackGame;
import vue.Grille;
import vue.LabelGame;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        
        Sequence s = new Sequence();
        
        Grille g = new Grille();
        g.setModele(s);

        BackGame bg = new BackGame();
        bg.addComposant(g);

        g.setBg(bg);

        LabelGame label = new LabelGame();
        label.setModele(s);
        bg.addComposant(label);

        Fen fen = new Fen((Sequence)s);
        fen.ajout(bg);

        g.addFen(fen);

        s.animationSeq();
    }
}
