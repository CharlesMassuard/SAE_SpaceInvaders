public class Score {
    private int score;

    public Score(int score){
        this.score = score;
    }

    public int ajoute(int valeur){
        this.score += valeur;
        return score;
    }

    public int getScore(){
        return this.score;
    }

    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines score = new EnsembleChaines();
        score.ajouteChaine(0, 59, "Le score actuel est de : "+this.score, "0x000000"); //59 car hauteur fenetre = 60
        return score;
    }
}
