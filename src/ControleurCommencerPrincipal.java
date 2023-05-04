import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ControleurCommencerPrincipal implements EventHandler<ActionEvent>{ 

    private MenuPrincipal appli;
    
    public ControleurCommencerPrincipal(MenuPrincipal appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.commencer();
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}