import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.Slider;
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
import javafx.beans.value.ObservableValue;
import javafx.scene.input.*;

//MENU PRINCIPAL RECREE, MAINTENANT L'EXECUTABLE
public class Executable extends Application{

        private static Clip clip;
        private static TextField nbrAliens;
        private static TextField nbrVagues;

        public static Clip getClip(){
            return clip;
        }

        @Override
        public void init(){
        // cette méthode est utilisée pour initialiser les éléments 
        // non graphiques et événetuellement graphiques autres que la Scène et le Stage
            this.nbrAliens = new TextField("12");
            this.nbrVagues = new TextField("5");
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

        private GridPane parametres(){
            GridPane pane = new GridPane();
            Text texte = new Text("Choisissez entre 6 à 24 aliens à combatttre : ");
            texte.setFont(Font.font("Dyuthi", FontWeight.NORMAL, 20));
            texte.setFill(Color.WHITE); //couleur texte
            texte.setStrokeWidth(0.4); //Taille bordure
            texte.setStroke(Color.BLACK); //couleur bordure
            Text texteVagues = new Text("Choisissez le nombre de vagues d'aliens à affronter : ");
            texteVagues.setFont(Font.font("Dyuthi", FontWeight.NORMAL, 20));
            texteVagues.setFill(Color.WHITE); //couleur texte
            texteVagues.setStrokeWidth(0.4); //Taille bordure
            texteVagues.setStroke(Color.BLACK); //couleur bordure
            nbrAliens.setOnKeyReleased(new ControleurDebutEntree(this));
            nbrVagues.setOnKeyReleased(new ControleurDebutEntree(this));
            pane.add(texte, 0, 0, 100, 1);
            pane.add(nbrAliens, 50, 0, 1, 10);
            pane.add(texteVagues, 0, 10, 2, 1);
            pane.add(nbrVagues, 50, 10, 2, 1);
            return pane;
        }
    
        private HBox root(){
            HBox pane = new HBox(10);
            VBox vbox = new VBox(50);
            //Mise en place de l'arrière plan
            Image image = new Image("file:./fichiers_menus/espace.gif");
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
            vbox.getChildren().addAll(borderPane(), parametres(), boutons());
            vbox.setPrefWidth(400);
            HBox.setMargin(vbox, new Insets(30));
            pane.getChildren().add(vbox);
            return pane;
        }

    public void quitter(){
        Platform.exit();
    }

    public boolean commencer(){
        try{
        String nbrAliensRentre = nbrAliens.getText();
        String nbrVaguesRentre = nbrVagues.getText();
        Integer nbrAliensRentreInteger = Integer.parseInt(nbrAliensRentre);
        Integer nbrVaguesInteger = Integer.parseInt(nbrVaguesRentre);
            if(nbrAliensRentreInteger>=6 && nbrAliensRentreInteger<=24 && nbrVaguesInteger>=1){
                clip.stop();
                LancementJeu menu = new LancementJeu(nbrAliensRentreInteger, nbrVaguesInteger);
                Stage stage = new Stage();
                menu.start(stage);
                return true;
            } else {
                ErreurParametres menu = new ErreurParametres();
                Stage stage = new Stage();
                menu.start(stage);
            }
        } catch (Exception e){
            ErreurParametres menu = new ErreurParametres();
            Stage stage = new Stage();
            menu.start(stage);
        }
        return false;
    }

    @Override
    public void start(Stage stage){              
        Scene scene =new Scene(root());
        stage.setTitle("Space Invaders - Menu Principal");
        stage.setScene(scene);
        stage.setResizable(false);
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
        stage.show();
    }
}