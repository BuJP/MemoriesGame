package modele;

import java.util.ArrayList;

import vue.Vue;
import java.util.Random;

public class Sequence implements Modele {
    private int[][] cases;
    private ArrayList<String> sequence;
    private ArrayList<Vue> vues;
    private int indexSeq;
    private boolean annimationFini;

    public Sequence(){
        init();
        vues = new ArrayList<>();
    }

    public void init(){
        cases = new int[3][3];
        sequence = new ArrayList<>();
        indexSeq = 0;
        
        generateCase();
        
        

        annimationFini = false;
    }



    public void animationSeq(){

        Thread th = new Thread() {
            public void run() {
                annimationFini = false;
                for(int i = 0 ; i<sequence.size() ; i++){
                    String[] convert = sequence.get(i).split("-");
                    int x = Integer.parseInt(convert[0]);
                    int y = Integer.parseInt(convert[1]);

                    cases[y][x] = 1;
                    vues.get(0).notifierChangement();
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cases[y][x] = 0;
                    vues.get(0).notifierChangement();


                }
              
                annimationFini = true;
               
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    public void animationSeqChoix(int x, int y, boolean test){

        Thread th = new Thread() {
            public void run() {
                if(test){
                    cases[y][x] = 1;
                }
                else{
                    cases[y][x] = -1;
                }
                
                vues.get(0).notifierChangement();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                cases[y][x] = 0;
                vues.get(0).notifierChangement();
                
                

            }
        };
        th.start();
    }

    public void generateCase(){
        
		Random random = new Random();
        String value;
        do{
            value = (random.nextInt(3 + 0) + 0)+"-"+(random.nextInt(3 + 0)+0);

        } while((sequence.size() != 0) && value.equals(sequence.get(sequence.size()-1)));
		sequence.add(value);
    }

    public boolean verifChoix(int x, int y){
        boolean test =  sequence.get(indexSeq).equals(x+"-"+y);
        animationSeqChoix(x, y, test);
        
        
        return test;
    }

    @Override
    public void enregistrer(Vue v) {
        vues.add(v);
    }

    public int[][] getCases() {
        return cases;
    }
    public boolean getAnimationFini() {
        return annimationFini;
    }
    public ArrayList<String> getSeq() {
        
        return sequence;
    }

    public boolean endSeq(){
        
        return indexSeq == sequence.size()-1;
    }
    public void setAnimationFini(){
        annimationFini = !annimationFini;
    }
    public void next() {
        indexSeq++;
    }

    public int getIndex() {
        return indexSeq;
    }

    public void vueNextLvl(){
        
        vues.get(1).notifierChangement();
    }

    public void nextTurn() {
  
        


       

        generateCase();
        animationSeq();
        indexSeq = 0;
    }

    public void play() {
        Thread th = new Thread() {
            public void run() {

                
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                cases = new int[3][3];
                sequence = new ArrayList<>();
                vues.get(1).notifierChangement();
                indexSeq = 0;
                
                generateCase();
                animationSeq();
                
            }
        };
        th.start();

    }
    
}
