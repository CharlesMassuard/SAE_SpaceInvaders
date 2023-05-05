import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ControleurReprendre implements EventHandler<ActionEvent>{ 

    private MenuPause appli;
    
    public ControleurReprendre(MenuPause appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.reprendre();
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}