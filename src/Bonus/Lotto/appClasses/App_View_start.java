package Bonus.Lotto.appClasses;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//import own classes
import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.abstractClasses.View;
import Bonus.Lotto.commonClasses.Simulator;
import Bonus.Lotto.commonClasses.Translator;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View_start extends View<App_Model> {
	
    Menu menuFileLanguage;
    Menu menuRules;
    
    static Label jackpot;
    static Label bets;
    
    static Label [] bets_summary;
    private final int MAX_BETS =10;
    
    static Button [] buttons;

	public App_View_start(Stage stage, App_Model model) {
        super(stage, model);
        stage.setTitle("Lotto");
        ServiceLocator.getServiceLocator().getLogger().info("Application view (start) initialized");
        Simulator sim = new Simulator();
        sim.start();
    }

	@Override
	protected Scene create_GUI() {
        ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Translator t = sl.getTranslator();
	    
	    /**create controls and set their properties*/
	    MenuBar menuBar = new MenuBar();
	   
	    menuFileLanguage = new Menu(t.getString("program.menu.file.language"));
	    menuRules = new Menu(t.getString("program.menu.rules"));
	    jackpot = new Label("Jackpot: "+sl.getJackpot());
	    jackpot.setId("jackpot");
	    jackpot.setMinWidth(600);
	    bets_summary = new Label [MAX_BETS];
	    
	    final int B_NUMBER = 3;
	    buttons = new Button [B_NUMBER];
	    buttons[0]= new Button (t.getString("program.start.bet"));
	    buttons[1] = new Button (t.getString("program.start.simulate"));
	    buttons[1].setDisable(true);
	    buttons[2] = new Button (t.getString("program.start.exit"));
	    for (int i =0; i<B_NUMBER;i++){
	    	buttons[i].setPrefWidth(150);
	    	buttons[i].setPrefHeight(40);
	    }
	    
	    bets= new Label (t.getString("program.start.totalBets")+ ", total: "+sl.getTotalCHF());
	    bets.prefWidth(330);
	    bets.setPrefHeight(30);
	    
	    for (int i = 0; i<MAX_BETS;i++){
	    	bets_summary[i] = new Label();
	    	bets_summary[i].setId("label_bet");
	    }
	   
	   /**begin the translation of the chosen language*/
       for (Locale locale : sl.getLocales()) {
           MenuItem language = new MenuItem(locale.getLanguage());
           menuFileLanguage.getItems().add(language);
           language.setOnAction( event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
                sl.setTranslator(new Translator(locale.getLanguage()));
                updateTexts();
            });
        }
       
       for (Locale locale : sl.getLocales()) {
           MenuItem rules = new MenuItem(locale.getLanguage());
           menuRules.getItems().add(rules);
           rules.setOnAction( event -> {
        	   	  Desktop desk = Desktop.getDesktop();
        	 
        	   		  //path noch verallgemeinern / testen ob auf anderem pc funktioniert       	   		  
        	   		if(locale.getLanguage().equals("de")){
        	   			try {
							desk.open(new File("C:/Users/Joel/git/uebungen_swe2/src/Bonus/Lotto/Regeln.txt"));
						} catch (IOException e) {
							e.printStackTrace();
						}
        	   		 }else{
        	   			try {
							desk.open(new File("C:/Users/Joel/git/uebungen_swe2/src/Bonus/Lotto/rules.txt"));
						} catch (IOException e) {
							e.printStackTrace();
						}
        	   		 }		
            });
        }
	    
	    menuBar.getMenus().addAll(menuFileLanguage, menuRules);
	    
	    //sizing (and ohter) of all controls done by css
	    BorderPane root = new BorderPane();
		root.setPrefWidth(900);
		root.setPrefHeight(500);
		
		
		//creating all children of root and add their own childern
		VBox vb1= new VBox();
		vb1.setPrefWidth(900);
		vb1.setPrefHeight(90);
		vb1.getChildren().add(menuBar);
		vb1.getChildren().add(jackpot);
		
		VBox vb2= new VBox();
		vb2.setPrefWidth(200);
		vb2.setPrefHeight(410);
		vb2.setSpacing(50);
		vb2.getChildren().addAll(buttons[0], buttons[1], buttons[2]);
		
		for(int i=0; i<B_NUMBER;i++){
			vb2.setMargin(buttons[i], new Insets(20,20,20,20));
		}
		
		VBox vb3= new VBox();
		vb3.setPrefWidth(730);
		vb3.setPrefHeight(420);
		vb3.getChildren().add(bets);
		vb3.setMargin(bets, new Insets(20,0,0,0));
		for (int i = 0; i<MAX_BETS; i++){
			vb3.getChildren().add(bets_summary[i]);
		}

		root.setTop(vb1);
		root.setLeft(vb2);
		root.setCenter(vb3);
		
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("app_start.css").toExternalForm());
        return scene;
	}
	
	   protected void updateTexts() {
		   ServiceLocator sl = ServiceLocator.getServiceLocator();
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();

	       // The menu entries
	       menuFileLanguage.setText(t.getString("program.menu.file.language"));
           menuRules.setText(t.getString("program.menu.rules"));
           
           buttons[0].setText(t.getString("program.start.bet"));
   	       buttons[1].setText(t.getString("program.start.simulate"));
   	       buttons[2].setText(t.getString("program.start.exit"));
   	       
   	   	       for (int i = 0; i < sl.getBetCounter();i++){
   	   	    	   bets_summary[i].setText(sl.getBetList().get(i).toString());
   	   	       } 
   	       
   	       
   	       bets.setText(t.getString("program.start.totalBets")+ ", total: "+sl.getTotalCHF());
	    }
	   
	   public static void updateJackpot(){
		   ServiceLocator sl = ServiceLocator.getServiceLocator(); 
		   jackpot.setText("Jackpot: "+sl.getJackpot());
	   }
	   
	   static void updateTotalBets(){
		   ServiceLocator sl = ServiceLocator.getServiceLocator(); 
		   Translator t = ServiceLocator.getServiceLocator().getTranslator();
		   bets.setText(t.getString("program.start.totalBets")+ ", total: "+sl.getTotalCHF());
	   }
}