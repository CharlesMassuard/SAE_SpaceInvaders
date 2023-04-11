public class GestionJeu {
    
    private EnsembleChaines chaines;
    private int positionX;
    private Vaisseau vaisseau;
    private int score;
    private Projectile projectile;

    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        this.positionX = 0;
        this.vaisseau = new Vaisseau(positionX);
        this.score = 0;
        this.projectile = null;
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
        return laChaine;
    }

    public void jouerUnTour(){
        if(this.projectile != null){
            this.projectile.evolue();
        }
    }

}
