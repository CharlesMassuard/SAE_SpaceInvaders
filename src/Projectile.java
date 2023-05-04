public class Projectile {

    private double positionX, positionY;

    public Projectile(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines projectile = new EnsembleChaines();
        projectile.ajouteChaine(positionX,positionY, "█", "0x000000");
        return projectile;
    }

    public double getPositionX(){
        return this.positionX;
    }

    public double getPositionY(){
        return this.positionY;
    }

    public void evolue(){
        this.positionY += 0.5;
    }
    
}
