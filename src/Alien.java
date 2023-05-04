public class Alien{

    private double posX;
    private double posY;
    private int nbrTours = 0;
    private String direction = "E";

    public Alien(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }

    public EnsembleChaines getEnsembleChaine(){
        EnsembleChaines alien = new EnsembleChaines();
        alien.ajouteChaine(posX, posY,   " ▀▄     ▄▀ ", "0x008000");
        alien.ajouteChaine(posX, posY-1, " ▄█▀███▀█▄ ", "0x008000");
        alien.ajouteChaine(posX, posY-2, "█▀███████▀█", "0x008000");
        alien.ajouteChaine(posX, posY-3, "█ █▀▀▀▀▀█ █", "0x008000");
        alien.ajouteChaine(posX, posY-4, "   ▀▀ ▀▀   ", "0x008000");
        return alien;
    }

    public EnsembleChaines getEnsembleChaine2(){
        EnsembleChaines alien = new EnsembleChaines();
        alien.ajouteChaine( posX, posY,   "  ▄▀    ▀▄ ", "0x008000");
        alien.ajouteChaine(posX, posY-1, " ▄█▀███▀█▄ ", "0x008000");
        alien.ajouteChaine(posX, posY-2, "█▀███████▀█", "0x008000");
        alien.ajouteChaine(posX, posY-3, "█ █▀▀▀▀▀█ █", "0x008000");
        alien.ajouteChaine(posX, posY-4, "   ▀▀ ▀▀   ", "0x008000");
        return alien;
    }

    public double getPosX(){
        return this.posX;
    }

    public void ajouterTour(){
        this.nbrTours += 1;
    }

    public int getNbrTours(){
        return this.nbrTours;
    }

    public void changerDirection(){
        if(this.direction.equals("E")){
            this.direction = "O";
        } else {
            this.direction = "E";
        }
    }

    public boolean contient(int x, int y){
        return getEnsembleChaine().contient(x, y);
    }

    public void evolue(){
        if(getNbrTours() == 100){
            this.posY -= 1;
            this.nbrTours = 0;
            changerDirection();
        } else if(this.direction.equals("E")){
            this.posX += 0.1;
        } else {
            this.posX -= 0.1;
        }
    }
}