public class Projectile {

    private double positionX, positionY;

    public Projectile(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines projectile = new EnsembleChaines();
        projectile.ajouteChaine(positionX, positionY, "█");
        return projectile;
    }

    public void evolue(){
        this.positionY += 0.2;
    }
    
}
