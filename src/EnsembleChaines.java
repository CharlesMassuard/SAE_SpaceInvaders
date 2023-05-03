import java.util.ArrayList;
public class EnsembleChaines {
    ArrayList<ChainePositionnee> chaines;
    public EnsembleChaines(){chaines= new ArrayList<ChainePositionnee>(); }

    public void ajouteChaine(double x, double y, String c){
        chaines.add(new ChainePositionnee(x,y,c));}

    public void union(EnsembleChaines e){
        for(ChainePositionnee c : e.chaines)
            chaines.add(c);
    }

    public boolean contient(int x, int y){
        for(ChainePositionnee chaine : this.chaines){
            if(chaine.x == x && chaine.y == y){
                return true;
            }
        }
        return false;
    }

}
