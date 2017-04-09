package Bonus.Lotto.appClasses;



import Bonus.Lotto.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App_Controller_goodluck extends Controller<App_Model, App_View_goodluck> {
	private App_View_final view4;

	public App_Controller_goodluck(App_Model model, App_View_goodluck view3) {
		super(model, view3);
		
        model.initializer.stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED){
                    	view3.stop(); 
                    	
                    	Stage betStage = new Stage();
                        betStage.initModality(Modality.APPLICATION_MODAL);

                        view4 = new App_View_final(betStage, model);
                        new App_Controller_final(model, view4); 
                    	view4.start();
                    }
                });
        
        // register ourselves to handle window-closing event
        view3.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        
	}

}
