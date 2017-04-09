package Bonus.Lotto.appClasses;

import Bonus.Lotto.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class App_Controller_final extends Controller<App_Model, App_View_final> {

	public App_Controller_final(App_Model model, App_View_final view4) {
		super(model, view4);
		
        // register ourselves to handle window-closing event
        view4.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
	}

}
