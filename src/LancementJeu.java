import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.Clip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;

public class LancementJeu extends Application {
    private Pane root;
    private Group caracteres;
    private GestionJeu gestionnaire;
    private Executable menu;
    private int hauteurTexte;
    private int largeurCaractere;
    private static Clip clip;
    private static Timeline timeline;
    public static void main(String[] args) {
        launch(args);
    }

    private void afficherCaracteres(){
        caracteres.getChildren().clear();
        int hauteur = (int) root.getHeight();
        for( ChainePositionnee c : gestionnaire.getChaines().chaines)
        {
            Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, c.c);
            t.setFont(Font.font ("Monospaced", 10));
            t.setFill(Color.web(c.getCouleur()));
            caracteres.getChildren().add(t);
        }
    }

    private void lancerAnimation() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent actionEvent) {
                            gestionnaire.jouerUnTour();
                            afficherCaracteres();
                        }
                    }),
                new KeyFrame(Duration.seconds(0.025))
                );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void stopAnimation(){
        timeline.stop();
    }

    private void lancerMusique(){
        try{
            File musique = new File("./fichiers_menus/musique_volumebas.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musique);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(200);
            clip.start();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void stopMusique(){
        clip.stop();
    }


    @Override
        public void start(Stage primaryStage){
            primaryStage.setTitle("IUTO Space Invader");
            caracteres = new Group();
            root= new AnchorPane(caracteres);
            gestionnaire = new GestionJeu();
            menu = new Executable();
            Text t=new Text("█");
            t.setFont(Font.font("Monospaced",10));
            hauteurTexte =(int) t.getLayoutBounds().getHeight();
            largeurCaractere = (int) t.getLayoutBounds().getWidth();

            Scene scene = new Scene(root,gestionnaire.getLargeur()*largeurCaractere,gestionnaire.getHauteur()*hauteurTexte);
            scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode()==KeyCode.LEFT)
                    gestionnaire.toucheGauche();
                if(key.getCode()==KeyCode.RIGHT)
                    gestionnaire.toucheDroite();
                if(key.getCode()==KeyCode.SPACE)
                    gestionnaire.toucheEspace();
            });
            Image image = new Image("file:./fichiers_menus/espace.jpg");
            BackgroundImage backImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
            );
            Background background = new Background(backImage);
            root.setBackground(background);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            lancerMusique();
            lancerAnimation();

        }
}
