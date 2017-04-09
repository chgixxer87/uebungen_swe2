package Bonus.Lotto.appClasses;

import java.util.Locale;

import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.abstractClasses.View;
import Bonus.Lotto.commonClasses.Translator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App_View_bet extends View<App_Model>{
	
	Menu menuFileLanguage;
    Menu menuRules;
	
	Button bet;
	Button return_to_start;
	Button clear;
	Button random;
	
	LottoButton []number;
	
	LottoButton [] additional_number;

	HBox []hb;

	public App_View_bet(Stage stage, App_Model model) {
		super(stage, model);
        stage.setTitle("Lotto");
        
        ServiceLocator.getServiceLocator().getLogger().info("Application view (bet) initialized");
	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Translator t = sl.getTranslator();
	    
	    /**create controls and set their properties*/ 
	    bet = new Button (t.getString("program.bet.bet"));
	    bet.setId("bet");
	    bet.setDisable(true);
	    return_to_start = new Button (t.getString("program.bet.return"));
	    return_to_start.setId("return");
	    clear = new Button (t.getString("program.bet.clear"));
	    clear.setDisable(true);
	    clear.setId ("clear");
	    random = new Button (t.getString("program.bet.random"));
	    random.setId("random");

	    number = new LottoButton[42];
	    
	    additional_number = new LottoButton [6];
	    
	    //each LottoButton sets a stackPane as graphic. each stackPane sets the label of its number ("1" to "42") and later a red "X" when the user selects a number by clicking on the button
	    ////dank der vererbung kann ein LottoButton wie ein normaler Button einer Pane hinzugefügt werden bzw. können alle methoden von der klasse java-button nun auch
	  //bei einem LottoButton verwendet werden
	    for(int i=0; i<number.length;i++){
	    	Label label = new Label (Integer.toString(i+1));
	    	StackPane sp = new StackPane();
	    	sp.getChildren().add(label);
	    	number[i] = new LottoButton(label,sp);
	    	number[i].setGraphic(sp);
	    	number[i].setMinHeight(45);
	    	number[i].setMinWidth(45);
	    	number[i].setStyle("-fx-font-size: 13px; -fx-font-style: italic;-fx-background-color: white;");
	    }
	    
	    for(int i=0; i<additional_number.length;i++){
	    	Label label = new Label (Integer.toString(i+1));
	    	StackPane sp = new StackPane();
	    	sp.getChildren().add(label);
	    	additional_number[i] = new LottoButton(label,sp);
	    	additional_number[i].setGraphic(sp);
	    	additional_number[i].setMinHeight(45);
	    	additional_number[i].setMinWidth(45);
	    }

	    //create menu
	    MenuBar menuBar = new MenuBar();
	    menuFileLanguage = new Menu(t.getString("program.menu.file.language"));
	    menuRules = new Menu(t.getString("program.menu.rules"));
	    menuBar.getMenus().addAll(menuFileLanguage, menuRules);
	    
	    //(sizing (and ohter) of all controls done by css)
	    //creating root
	    VBox root = new VBox();
		root.setPrefWidth(420);
		root.setPrefHeight(700);
		root.getChildren().add(menuBar);
		
		
		/**creating all children of root and add their own childern*/
		final int NUMBER_OF_HBOX = 7;
		hb = new HBox[NUMBER_OF_HBOX];

		//create all HBoxes of root
		int buttonCount=0;
		
		//puts all number buttons into the 7 HBoxes
		for(int i =0; i<NUMBER_OF_HBOX;i++){
			hb[i]= new HBox();
			hb[i].setPrefWidth(420);
			hb[i].setPrefHeight(50);
			hb[i].setSpacing(10);
				for(int count =0; count<6;count++){
					hb[i].getChildren().add(number[buttonCount]);
					buttonCount++;
				}
		}
		
		//create HBox for additional_numbers
		HBox add_number = new HBox();
		add_number.setPrefWidth(420);
		add_number.setPrefHeight(50);
		add_number.setSpacing(10);
		for(int count =0; count<6;count++){
			additional_number[count].setStyle("-fx-background-radius: 5em; -fx-min-width: 45px;-fx-min-height: 45px;-fx-max-width: 45px;-fx-max-height: 45px;"
					+ " -fx-background-color: yellow;");
			add_number.getChildren().add(additional_number[count]);
		}
		
		for (int i = 0; i<hb.length; i++){
			root.getChildren().add(hb[i]);
			root.setMargin(hb[i], new Insets(0,0,10,10));
		}
		
		root.getChildren().add(add_number);
		root.setMargin(add_number, new Insets(0,0,10,10));

		//create last Box of the root
		VBox vb1= new VBox();
		vb1.setPrefWidth(420);
		vb1.setPrefHeight(160);
		vb1.setSpacing(40);
		root.setMargin(vb1, new Insets(50,0,0,10));
		vb1.getChildren().add(bet);
		vb1.getChildren().add(clear);
		vb1.getChildren().add(random);
		vb1.getChildren().add(return_to_start);
		
		root.getChildren().add(vb1);
		
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
	    
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("app_bet.css").toExternalForm());
        
        return scene;
	}

	private void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();

	    // The menu entries
	    menuFileLanguage.setText(t.getString("program.menu.file.language"));
        menuRules.setText(t.getString("program.menu.rules"));
        
        bet.setText(t.getString("program.bet.bet"));
        clear.setText(t.getString("program.bet.clear"));
        random.setText(t.getString("program.bet.random"));
	    return_to_start.setText(t.getString("program.bet.return"));
	}

}
