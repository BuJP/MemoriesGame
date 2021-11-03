package Controleur;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Sequence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Fen extends JFrame {
    JPanel game;
    JPanel menu;

    private JButton play;
    private JButton option;
    private JButton leave;
    private Sequence modele;

    public Fen(Sequence modele){
       // super();
        setTitle("arrg");

        this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initMenu();
        add(menu);

        this.modele = modele;
		this.setVisible(true);
        //setLayout(new BorderLayout());
        
        game = new JPanel();
        game.setLayout(new BorderLayout());

       // add(game);
        
    }

    public void ajout(JPanel v){
        game.add(v);
    }

    public void initMenu(){
        menu = new JPanel();
        menu.setBackground(Color.decode("#2b87d1"));
        menu.setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
        menu.setSize(new Dimension(500,500));

        play = new JButton("Play");
        play.setPreferredSize(new Dimension(500,100));
        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                menu.setVisible(false); 
                add(game);
                modele.play();
    
            }
        });
        option = new JButton("Option");
        option.setPreferredSize(new Dimension(500,100));
        leave = new JButton("Leave");
        leave.setPreferredSize(new Dimension(500,100));
        leave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
               dispose();
    
            }
        });

        menu.add(play);
        menu.add(leave);

    }

}
