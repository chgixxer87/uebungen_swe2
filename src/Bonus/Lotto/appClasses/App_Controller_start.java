package Bonus.Lotto.appClasses;


import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller_start extends Controller<App_Model, App_View_start> {
    ServiceLocator serviceLocator;
    App_View_bet view2;
    App_View_goodluck view3;
    App_View_final view4;

    public App_Controller_start(App_Model model, App_View_start view) {
        super(model, view);
        
     // register ourselves to listen for button clicks (bet)
        view.buttons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                betClick();
            }


        });
        
     // register ourselves to listen for button clicks (simulate)
        view.buttons[1].setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
            	simulateClick();
        }
    });
        
     // register ourselves to listen for button clicks (exit)
        view.buttons[2].setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
            	Platform.exit();
        }
    });

        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
            
        //add 
        ServiceLocator sl = ServiceLocator.getServiceLocator();
		sl.getValueProperty().addListener( (observable, oldValue, newValue) -> {

		        // Move to the JavaFX thread
		        Platform.runLater(new Runnable() {
		            @Override public void run() {
		            	//adds the amount of every bet created by the simulator
		            	App_View_start.updateJackpot();
		            }
		        });
			}
        );
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
    }
    
	private void betClick() {
		
        Stage betStage = new Stage();
        betStage.initModality(Modality.APPLICATION_MODAL);
        
        //create the bet-view with a new bet-controller and the existing app_model
        view2 = new App_View_bet(betStage, model);
        new App_Controller_bet(model, view2); // -> super(model,view) -> from abstract Controller class

        view2.start();		
	}
	private void simulateClick() {

        Stage betStage = new Stage();
        betStage.initModality(Modality.APPLICATION_MODAL);

        view3 = new App_View_goodluck(betStage, model);
        
        new App_Controller_goodluck(model, view3); 

        view3.start();	
        model.initialize_goodluck();  
	}
	
}
