public class Projectile {

    private double positionX, positionY;

    public Projectile(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines projectile = new EnsembleChaines();
        projectile.ajouteChaine(positionX,positionY, "█", "0x9C9C9C");
        projectile.ajouteChaine(positionX,positionY-1, "-", "0xFF6600");
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
