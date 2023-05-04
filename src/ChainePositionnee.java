public class ChainePositionnee{
    double x,y;
    String c, couleur;
    public ChainePositionnee(double a,double b, String d, String couleur){x=a; y=b; c=d; this.couleur=couleur;}

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public String getCouleur(){
        return this.couleur;
    }

    @Override
    public String toString(){
        return "("+getX()+","+getY()+")";
    }
}
