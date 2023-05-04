import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class ControleurQuitter implements EventHandler<ActionEvent>{ 

    private MenuPrincipal appli;
    
    public ControleurQuitter(MenuPrincipal appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.quitter();
    }
}