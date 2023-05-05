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
public class MenuGagne extends Application{

    private BorderPane borderPane(){
        BorderPane pane = new BorderPane();
        Text titre = new Text("VOUS AVEZ GAGNÉ !");
        titre.setFont(Font.font("Dyuthi", FontWeight.BOLD, 80));
        titre.setFill(Color.WHITE); //couleur texte
        pane.setTop(titre);
        pane.setAlignment(titre, Pos.CENTER);
        return pane;
    }


    private HBox boutons(){
        Executable menuP = new Executable();
        HBox pane = new HBox();
        Button start = new Button("Relancer une partie");
        Button reconfig = new Button("Reconfigurer une nouvelle partie");
        Button quitter = new Button("Quitter le jeu");
        pane.setSpacing(200);
        pane.getChildren().addAll(start, reconfig, quitter);
        pane.setAlignment(Pos.CENTER);
        start.setOnAction(new ControleurRelancer(this));
        reconfig.setOnAction(new ControleurReconfigurer(this));
        quitter.setOnAction(new ControleurQuitter(menuP));
        return pane;
    }

    private HBox root(){
        HBox pane = new HBox(10);
        VBox vbox = new VBox(50);
        //Mise en place de l'arrière plan
        Image image = new Image("file:./fichiers_menus/space.gif");
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

    public void recommencer(){
        LancementJeu.stopMusique();
        LancementJeu executable = new LancementJeu();
        Stage stage = new Stage();
        Executable.getClip().stop();
        executable.start(stage);
    }

    public void reconfig(){
        MenuOptions menu = new MenuOptions();
        Stage stage = new Stage();
        Executable.getClip().stop();
        menu.start(stage);
    }

    @Override
    public void start(Stage stage){              
        Scene scene =new Scene(root());
        stage.setTitle("Space Invaders - Gagné");
        stage.setScene(scene);
        stage.show();
    }
}