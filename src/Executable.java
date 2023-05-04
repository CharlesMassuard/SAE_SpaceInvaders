import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import java.io.File;
import javax.sound.sampled.Clip;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;

//MENU PRINCIPAL RECREE, MAINTENANT L'EXECUTABLE
public class Executable extends Application{

        private static Clip clip;

        public static Clip getClip(){
            return clip;
        }

        @Override
        public void init(){
        // cette méthode est utilisée pour initialiser les éléments 
        // non graphiques et événetuellement graphiques autres que la Scène et le Stage
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

        private BorderPane borderPane(){
            BorderPane pane = new BorderPane();
            Text titre = new Text("SPACE INVADERS");
            titre.setFont(Font.font("Dyuthi", FontWeight.BOLD, 80));
            titre.setFill(Color.WHITE); //couleur texte
            titre.setStrokeWidth(3); //Taille bordure
            titre.setStroke(Color.BLACK); //couleur bordure
            pane.setTop(titre);
            pane.setAlignment(titre, Pos.CENTER);
            return pane;
        }
    
    
        private HBox boutons(){
            HBox pane = new HBox();
            Button start = new Button("Commencer une partie");
            Button quitter = new Button("Quitter le jeu");
            pane.setSpacing(200);
            pane.getChildren().addAll(start, quitter);
            pane.setAlignment(Pos.CENTER);
            quitter.setOnAction(new ControleurQuitter(this));
            start.setOnAction(new ControleurCommencerPrincipal(this));
            return pane;
        }
    
        private HBox root(){
            HBox pane = new HBox(10);
            VBox vbox = new VBox(50);
            //Mise en place de l'arrière plan
            Image image = new Image("file:./fichiers_menus/loop-hyper-loop.gif");
            BackgroundImage backImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
            );
            Background background = new Background(backImage);
            pane.setBackground(background);
            //Vue
            vbox.setStyle("-fx-background-color:transparent");
            vbox.getChildren().addAll(borderPane(), boutons());
            vbox.setPrefWidth(400);
            HBox.setMargin(vbox, new Insets(30));
            pane.getChildren().add(vbox);
            return pane;
        }

    public void quitter(){
        Platform.exit();
    }

    public void commencer(){
        MenuOptions menu = new MenuOptions();
        Stage stage = new Stage();
        menu.start(stage);
    }

    @Override
    public void start(Stage stage){              
        Scene scene =new Scene(root());
        stage.setTitle("Space Invaders - Menu Principal");
        stage.setScene(scene);
        stage.show();
    }
}