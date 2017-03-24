package Bonus.Lotto.splashScreen;

import Bonus.Lotto.abstractClasses.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class Splash_View extends View<Splash_Model> {
   
	private Label [] label;
	final int NUMBER_OF_LABELS = 2;

    public Splash_View(Stage stage, Splash_Model model) {
        super(stage, model);
        stage.initStyle(StageStyle.TRANSPARENT); // also undecorated (ohne Rand am Fenster) -> muss hier, nicht im css gemacht werden
    }

    @Override
    protected Scene create_GUI()  {
        BorderPane root = new BorderPane();
        root.setId("splash");
        
        label = new Label [NUMBER_OF_LABELS];
     
        label [0] = new Label ("Viel Glück beim Lotto");
        label [1] = new Label ("Good luck with the lottery");
        
        for (int i =0; i< label.length;i++){
        	label[i].setPrefHeight(90);
        }
        
        root.setTop(label[0]);
        root.setAlignment(label[0], Pos.CENTER);
        root.setBottom(label[1]);
        root.setAlignment(label[1], Pos.CENTER);
        
        Scene scene = new Scene(root, 900, 500);
        scene.getStylesheets().addAll(
                this.getClass().getResource("splash.css").toExternalForm());

        return scene;
    }
}
