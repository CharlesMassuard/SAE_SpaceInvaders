import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ControleurRelancer implements EventHandler<ActionEvent>{ 

    private MenuGagne appli;
    
    public ControleurRelancer(MenuGagne appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.recommencer();
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}