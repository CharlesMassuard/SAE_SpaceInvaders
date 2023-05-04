public class Vaisseau{

    private int posX;

    public Vaisseau(int posX){
        this.posX = posX;
    }

    public void deplace(double vitesse){
        this.posX += vitesse;
    }

    public EnsembleChaines getEnsembleChaine(){
        EnsembleChaines vaisseau = new EnsembleChaines();
        vaisseau.ajouteChaine(posX, 0, "███████████████", "0x000000");
        vaisseau.ajouteChaine(posX, 1, " █████████████", "0x000000");
        vaisseau.ajouteChaine(posX, 2, " █████████████", "0x000000");
        vaisseau.ajouteChaine(posX, 3, " ▄███████████▄", "0x000000");
        vaisseau.ajouteChaine(posX, 4, "      ███     ", "0x000000");
        vaisseau.ajouteChaine(posX, 5, "       ▄      ", "0x000000");
        return vaisseau;
    }

    public int positionCanon(){
        return this.posX+6;
    }

    public double getPosX(){
        return this.posX;
    }

    public void setPosX(int vitesse){
        this.posX += vitesse;
    }
}