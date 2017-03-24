package Bonus.Lotto.appClasses;


import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
    ServiceLocator serviceLocator;

    public App_Controller(App_Model model, App_View view) {
        super(model, view);
        
     // register ourselves to listen for button clicks (bet)
        /**view.buttons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                betClick();
            }
        });*/
        
     // register ourselves to listen for button clicks (simulate)
        /**view.buttons[1].setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
            	simulateClick();
        }
    });*/
        
     // register ourselves to listen for button clicks (exit)
        /**view.buttons[1].setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
            	Platform.exit();
        }
    });*/

        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
    }
    
    public void buttonClick() {
        model.incrementValue(); //hier wird die model-variable geholt vom abstrakten Controller und auf dem model die methode
        //incrementValue angewendet. die methode ist im App_model implementiert. der aufruf vom abstrakten model funktioniert aber
        //wegen der vererbung
        String newText = Integer.toString(model.getValue());        

       // view.lblNumber.setText(newText);        
    }
}
