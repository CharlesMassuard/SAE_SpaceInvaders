public class Score {
    private int score;

    public Score(int score){
        this.score = 0;
    }

    public int ajoute(int valeur){
        this.score += valeur;
        return score;
    }

    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines score = new EnsembleChaines();
        score.ajouteChaine(55, 5, "Le score actuel est de : "+this.score);
        return score;
    }
}
