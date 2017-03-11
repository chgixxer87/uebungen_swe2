package lecture02.webValidator;

import javafx.application.Application;
import javafx.stage.Stage;

public class webValidator extends Application {
	
	 private webValidator_View view;
	 private webValidator_Controller controller;
	 private webValidator_Model model;
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		model = new webValidator_Model();
        view = new webValidator_View(primaryStage, model);
        controller = new webValidator_Controller(model, view);

        // Display the GUI after all initialization is complete
        view.start();
		
	}
	
	public static void main(String[] args) {
		launch (args);
	}

}

	