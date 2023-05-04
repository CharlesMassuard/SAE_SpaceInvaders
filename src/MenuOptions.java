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
import javafx.scene.layout.GridPane;
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

public class MenuOptions extends Application{

    private TextField nbrAliens;

    @Override
    public void init(){
        // cette méthode est utilisée pour initialiser les éléments 
        // non graphiques et événetuellement graphiques autres que la Scène et le Stage
        this.nbrAliens = new TextField();
    }

    private GridPane gridPane(){
        GridPane pane = new GridPane();
        pane.add(new Label("Combien d'aliens souhaitez-vous combattre ?"), 0, 0, 5, 1);
        pane.add(nbrAliens, 1, 0);
        pane.setHgap(50);
        pane.setVgap(20);  
        return pane;
    }

    private HBox root(){
        HBox pane = new HBox(10);
        VBox vbox = new VBox(50);
        //Mise en place de l'arrière plan
        Image image = new Image("file:./fichiers_menus/back.jpg");
        BackgroundImage backImage = new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, true, true)
        );
        //musique
        Background background = new Background(backImage);
        pane.setBackground(background);
        try{
            File musique = new File("./fichiers_menus/musique_volumebas.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musique);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e){
            System.out.println(e);
        }
        vbox.getChildren().addAll(gridPane());
        vbox.setPrefWidth(400);
        HBox.setMargin(vbox, new Insets(30));
        pane.getChildren().add(vbox);
        return pane;
    }

    @Override
    public void start(Stage stage){     
        Scene scene =new Scene(root());
        stage.setTitle("Space Invaders - Choix des options");
        stage.setScene(scene);
        stage.show();
    }
}
