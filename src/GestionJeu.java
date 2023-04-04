public class GestionJeu {
    
    private EnsembleChaines chaines;
    private int positionX;
    private Vaisseau vaisseau;
    private boolean lancer;
    private Projectile projectile;

    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        this.positionX = 0;
        this.vaisseau = new Vaisseau(positionX);
        this.lancer = false;
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
        this.lancer = true;
    }

    public EnsembleChaines getChaines(){
        EnsembleChaines laChaine = new EnsembleChaines();
        laChaine.union(this.vaisseau.getEnsembleChaine());;
        if(this.lancer){
            laChaine.union(new Projectile(vaisseau.positionCanon(), 10).getEnsembleChaines());
            this.lancer = false;
        }
        return laChaine;
    }

    public void jouerUnTour(){
    }

}
