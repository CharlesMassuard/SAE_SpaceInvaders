public class Vaisseau{

    private double posX;

    public Vaisseau(double posX){
        this.posX = posX;
    }

    public void deplace(double vitesse){
        this.posX += vitesse;
    }

    public EnsembleChaines getEnsembleChaine(){
        EnsembleChaines vaisseau = new EnsembleChaines();
        vaisseau.ajouteChaine(posX, 0, "█████████████");
        vaisseau.ajouteChaine(posX, 1, "█████████████");
        vaisseau.ajouteChaine(posX, 2, "▄███████████▄");
        vaisseau.ajouteChaine(posX, 3, "     ███     ");
        vaisseau.ajouteChaine(posX, 4, "      ▄      ");
        return vaisseau;
    }

    public double positionCanon(){
        return this.posX+6;
    }

    public double getPosX(){
        return this.posX;
    }

    public void setPosX(int vitesse){
        this.posX += vitesse;
    }
}