public class GestionJeu {
    
    private EnsembleChaines chaines;
    private int positionX;
    private Vaisseau vaisseau;
    private boolean lancer;

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
        getChaines();
    }

    public EnsembleChaines getChaines(){
        if(this.lancer){
            EnsembleChaines projectile = new EnsembleChaines();
            projectile.union(new Projectile(vaisseau.positionCanon(), 10).getEnsembleChaines());
            System.out.println(vaisseau.positionCanon());
            this.lancer = false;
            return projectile;
        }
        EnsembleChaines dessinVaisseau = new EnsembleChaines();
        dessinVaisseau.union(this.vaisseau.getEnsembleChaine());;
        return dessinVaisseau;
    }

    public void jouerUnTour(){
        getChaines();
    }

}
