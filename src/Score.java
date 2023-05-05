public class Score {
    private static int score;

    public Score(int score){
        this.score = score;
    }

    public int ajoute(int valeur){
        score += valeur;
        return score;
    }

    public static int enleve(int valeur){
        score -= valeur;
        return score;
    }

    public int getScore(){
        return this.score;
    }

    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines score = new EnsembleChaines();
        score.ajouteChaine(0, 59, "Score actuel : "+this.score, "0xFFFFFF"); //59 car hauteur fenetre = 60
        return score;
    }
}
