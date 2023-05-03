import java.util.List;
import java.util.ArrayList;

public class GestionJeu {
    
    private EnsembleChaines chaines;
    private int positionX;
    private Vaisseau vaisseau;
    private Score score;
    private Projectile projectile;
    private List<Alien> lesAliens;

    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        this.positionX = 0;
        this.vaisseau = new Vaisseau(positionX);
        this.score = new Score(0);
        this.lesAliens = new ArrayList<>();
        this.projectile = null;
        ajouterAliens(12);
        System.out.println(chaines.contient(0, 0));
    }

    public void ajouterAliens(int nbrAliens){
        double posX = 2;
        double posY = getHauteur()-10;
        for(int i=0; i<nbrAliens; ++i){
            lesAliens.add(new Alien(posX, posY));
            posX+=15;
            if(lesAliens.size()%6==0){ //si plus de 6 aliens sur une ligne, passage à la ligne suivante
                posX = 2;
                posY -= 7;
            }
        }
    }

    public int getHauteur(){
        return 60;
    }

    public int getLargeur(){
        return 100;
    }

    public void toucheGauche(){
        if(vaisseau.getPosX() > 0){ //ne va pas vers la gauche si le vaisseau touche le bord
            vaisseau.setPosX(-1);
        }
    }

    public void toucheDroite(){
        if(vaisseau.getPosX() < this.getLargeur()-13){ //ne va pas vers la droite si le vaisseau touche le bord (largeur du vaisseau = 13)
            vaisseau.setPosX(1);
        }
    }

    public void toucheEspace(){
        this.projectile = new Projectile(vaisseau.positionCanon(), 4);
    }

    public EnsembleChaines getChaines(){
        EnsembleChaines laChaine = new EnsembleChaines();
        laChaine.union(this.vaisseau.getEnsembleChaine());
        // laChaine.union(this.score.getEnsembleChaines());
        if(this.projectile != null){
            laChaine.union(this.projectile.getEnsembleChaines());
        }
        laChaine.union(this.score.getEnsembleChaines());
        for(Alien alien : this.lesAliens){
            if(alien.getNbrTours()%2==0){
                laChaine.union(alien.getEnsembleChaine());
            } else {
                laChaine.union(alien.getEnsembleChaine2());
            }
        }
        return laChaine;
    }

    public void jouerUnTour(){
        if(this.projectile != null){
            this.projectile.evolue();
        }
        score.ajoute(1);
        for(Alien alien : this.lesAliens){
            alien.evolue();
            alien.ajouterTour();
        }
    }

}
