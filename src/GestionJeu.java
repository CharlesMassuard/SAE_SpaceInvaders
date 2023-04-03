public class GestionJeu {
    
    private EnsembleChaines chaines;
    private int positionX;
    private int positionY;

    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        chaines.ajouteChaine(positionX, positionY, "@@");
        this.positionX = 0;
        this.positionY = 30;
    }

    public int getHauteur(){
        return 60;
    }

    public int getLargeur(){
        return 100;
    }

    public void toucheGauche(){
        System.out.println("GAUCHEEE");
        this.positionY -= 1;
    }

    public void toucheDroite(){
        System.out.println("DROITEEE");
        this.positionX += 1;
    }

    public void toucheEspace(){
        System.out.println("Appui sur la touche espace");
    }

    public EnsembleChaines getChaines(){
        return this.chaines;
    }

    public void jouerUnTour(){
    }

}
