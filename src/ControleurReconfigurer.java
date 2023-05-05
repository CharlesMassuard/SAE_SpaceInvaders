import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ControleurReconfigurer implements EventHandler<ActionEvent>{ 

    private MenuGagne appli;
    
    public ControleurReconfigurer(MenuGagne appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.reconfig();
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}