import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class ControleurOk implements EventHandler<ActionEvent>{ 

    private ErreurParametres appli;
    
    public ControleurOk(ErreurParametres appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}