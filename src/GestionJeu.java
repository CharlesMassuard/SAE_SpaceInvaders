import java.util.List;
import java.util.ArrayList;
import javafx.application.Platform;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javafx.stage.Stage;
import java.lang.Thread;

public class GestionJeu {
    
    private EnsembleChaines chaines;
    private int positionX;
    private Vaisseau vaisseau;
    private Score score;
    private Projectile projectile;
    private List<Alien> lesAliens;
    private NbrAliensEnVie nbrAliensEnVie;
    private int nbrAliens;
    private int nbrVagues;
    private int nbrVaguesPasses;

    public GestionJeu(){
        this.chaines = new EnsembleChaines();
        this.positionX = 0;
        this.vaisseau = new Vaisseau(positionX);
        this.score = new Score(0);
        this.lesAliens = new ArrayList<>();
        this.projectile = null;
        this.nbrAliens = LancementJeu.getNbrAliens();
        this.nbrVagues = LancementJeu.getNbrVagues();
        this.nbrVaguesPasses = 1;
        this.nbrAliensEnVie = new NbrAliensEnVie(nbrAliens);
        ajouterAliens(nbrAliens);
    }

    public void ajouterAliens(int nbrAliens){
        int posX = 2;
        int posY = getHauteur()-10;
        for(int i=0; i<nbrAliens; ++i){
            lesAliens.add(new Alien(posX, posY));
            posX+=15;
            if(lesAliens.size()%6==0){ //si plus de 6 aliens sur une ligne, passage à la ligne suivante
                posX = 2;
                posY -= 7;
            }
        }
    }

    public int getHauteur(){
        return 60;
    }

    public int getLargeur(){
        return 100;
    }

    public void toucheGauche(){
        if(vaisseau.getPosX() > 0){ //ne va pas vers la gauche si le vaisseau touche le bord
            vaisseau.setPosX(-1);
        }
    }

    public void toucheDroite(){
        if(vaisseau.getPosX() < this.getLargeur()-13){ //ne va pas vers la droite si le vaisseau touche le bord (largeur du vaisseau = 13)
            vaisseau.setPosX(1);
        }
    }

    public void toucheEspace(){
        this.projectile = new Projectile(vaisseau.positionCanon(), 4);
        try{
            File musique = new File("./fichiers_menus/bruitlaser.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musique);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void pauseMenu(){
        LancementJeu.stopAnimation();
        LancementJeu.stopMusique();
        MenuPause pauseMenu = new MenuPause();
        Stage stage = new Stage();
        pauseMenu.start(stage);
    }

    public static void reprendre(){
        LancementJeu.lancerMusique();
        LancementJeu.lancerAnimation();
    }

    public EnsembleChaines nbrVagues(){
        EnsembleChaines vagues = new EnsembleChaines();
        vagues.ajouteChaine(45, 59, "Vague "+nbrVaguesPasses+"/"+nbrVagues, "0xFFFFFF");
        return vagues;
    }

    public EnsembleChaines getChaines(){
        this.chaines.union(this.vaisseau.getEnsembleChaine());
        this.chaines.union(this.score.getEnsembleChaines());
        this.chaines.union(this.nbrAliensEnVie.getEnsembleChaines());
        this.chaines.union(nbrVagues());
        if(this.projectile != null){
            this.chaines.union(this.projectile.getEnsembleChaines());
        }
        for(Alien alien : this.lesAliens){
            if(alien.getNbrTours()%2==0){
                this.chaines.union(alien.getEnsembleChaine());
            } else {
                this.chaines.union(alien.getEnsembleChaine2());
            }
        }
        return this.chaines;
    }

    public void jouerUnTour(){
        if(nbrAliensEnVie.getEnVie() != 0){
            ArrayList<Projectile> ballesReussies = new ArrayList<>();
            ArrayList<Alien> aliensTouches = new ArrayList<>();
            if(this.projectile != null){
                if(this.projectile.getPositionY() > this.getHauteur()){
                    this.projectile = null;
                } else {
                    this.projectile.evolue();
                }
            }
            for(Alien alien : this.lesAliens){
                if(this.projectile != null){
                    if(alien.contient((int) this.projectile.getPositionX(), (int) this.projectile.getPositionY())){
                        try{
                            File musique = new File("./fichiers_menus/alienmeurt.wav");
                            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musique);
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInput);
                            clip.start();
                        } catch (Exception e){
                            System.out.println(e);
                        }
                        ballesReussies.add(this.projectile);
                        aliensTouches.add(alien);
                        score.ajoute(100);
                        nbrAliensEnVie.enleve();
                    }
                }
                if(alien.getPosY()<10 || this.score.getScore()<-100){ //perdu
                    MenuPerdu partieGagne = new MenuPerdu();
                    Stage stage = new Stage();
                    partieGagne.start(stage);
                    LancementJeu.stopAnimation();
                    break;
                }
                alien.evolue();
                alien.ajouterTour();  
            }
            if(aliensTouches.size() != 0){
                this.lesAliens.removeAll(aliensTouches);
            }
            if(ballesReussies.size() != 0){
                this.projectile = null;
            }
            this.chaines = new EnsembleChaines();
        } else {
            if(nbrVaguesPasses >= nbrVagues){
                try{
                    File musique = new File("./fichiers_menus/victoire.wav");
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musique);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                } catch (Exception e){
                    System.out.println(e);
                }
                MenuGagne partieGagne = new MenuGagne(this.score.getScore());
                Stage stage = new Stage();
                partieGagne.start(stage);
                LancementJeu.stopAnimation();
            } else {
                try{
                    File musique = new File("./fichiers_menus/ding_fin_vagues.wav");
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musique);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                } catch (Exception e){
                    System.out.println(e);
                }
                score.ajoute(300);
                nbrVaguesPasses ++;
                if(nbrAliens+3 < 24){
                    nbrAliens += 3;
                }
                this.nbrAliensEnVie = new NbrAliensEnVie(nbrAliens);
                ajouterAliens(nbrAliens);
            }
        }
    }
}
