import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ControleurDebut implements EventHandler<ActionEvent>{ 

    private MenuOptions appli;
    
    public ControleurDebut(MenuOptions appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.debut();
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}