import java.util.ArrayList;
public class EnsembleChaines {
    ArrayList<ChainePositionnee> chaines;
    public EnsembleChaines(){chaines= new ArrayList<ChainePositionnee>(); }

    public void ajouteChaine(double x, double y, String c, String couleur){
        chaines.add(new ChainePositionnee(x,y,c,couleur));}

    public void union(EnsembleChaines e){
        for(ChainePositionnee c : e.chaines)
            chaines.add(c);
    }

    public boolean contient(int x, int y ){
        for(ChainePositionnee c : chaines)
            if(c.x <= x && c.x < x + c.c.length() && x<c.x+c.c.length() && c.y == y){
                return true;
            }
        return false;
    }

}
