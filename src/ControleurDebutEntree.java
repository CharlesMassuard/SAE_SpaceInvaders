import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ControleurDebutEntree implements EventHandler<KeyEvent>{ 

    private Executable appli;
    
    public ControleurDebutEntree(Executable appli){
        this.appli = appli;
    }

    @Override
    public void handle(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)){
            if(this.appli.commencer()){
                Node n = (Node) e.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
            }
        }
    }
          
}