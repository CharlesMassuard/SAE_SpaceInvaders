public class NbrAliensEnVie {
    private int aliens;

    public NbrAliensEnVie(int aliens){
        this.aliens = aliens;
    }

    public int enleve(){
        this.aliens -= 1;
        return aliens;
    }

    public int getEnVie(){
        return this.aliens;
    }

    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines aliens = new EnsembleChaines();
        aliens.ajouteChaine(72, 59, "Nombre d'aliens en vie : "+this.aliens, "0xFFFFFF"); //59 car hauteur fenetre = 60
        return aliens;
    }
}
