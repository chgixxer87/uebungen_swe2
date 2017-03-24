package lecture04.Calc_MVC.appClasses;

import java.util.Locale;
import java.util.logging.Logger;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//eigene klassen import
import lecture04.Calc_MVC.ServiceLocator;
import lecture04.Calc_MVC.abstractClasses.View;//verlangt import der eigenen view klasse. import von java-view-klasse = fehler (generic)
import lecture04.Calc_MVC.commonClasses.Translator;





public class App_View extends View<App_Model> {// -->mit generic App_model
	
	//controls definieren
    Menu menuFile;
    Menu menuFileLanguage;
    Menu menuHelp;
    
    //buttons
    final int NUMBERS = 10;
	Button [] numbers = new Button [NUMBERS];
	Button [] operators= new Button [6];
	
	Label message1 = new Label();
	Label message2 = new Label();
	Label message3 = new Label();
	Label equals = new Label(); 
	
  

	public App_View(Stage stage, App_Model model) {
        super(stage, model);
        stage.setTitle("Calculator MVC");
        
        ServiceLocator.getLogger().info("Application view initialized");
    }

	@Override
	protected Scene create_GUI() {
	    //ServiceLocator sl = ServiceLocator.getServiceLocator();  --> SL in der main initialisieren
	    Logger logger = ServiceLocator.getLogger();//unsere eigene static methode, nur nötig wenn extra ein translator obj für die methode
	    //gemacht werden soll
	    Translator t = ServiceLocator.getTranslator();//unsere eigene static methode, nur nötig wenn extra ein translator obj für die methode
	    //gemacht werden soll
	    
	    MenuBar menuBar = new MenuBar();
	    menuFile = new Menu(t.getString("program.menu.file")); // zuweisung der ausgewählten sprachen-property, "Datei" mit popup "sprache"
	    menuFileLanguage = new Menu(t.getString("program.menu.file.language"));//"Sprache" mit popups de und en
	    menuFile.getItems().add(menuFileLanguage);//zuweisung des popups "sprache" an "Datei"
	    
       for (Locale locale : ServiceLocator.getLocales()) {//unser eigener getter für die locales de und en. iteriert durch de und en
           MenuItem language = new MenuItem(locale.getLanguage());//getter der java klasse Locale. holt die popups de und en
           menuFileLanguage.getItems().add(language);//zuweisung der locales de und en (schleife) an "sprache"
           
           language.setOnAction( event -> {//click event hier in der view vom MenuItem (click auf de oder en); innerhalb der schleife?
        	   //--> wenn nicht geklickt, wird auch nicht abgearbeitet. schleife läuft also komplett 2x durch am anfang
				ServiceLocator.getConfiguration().setLocalOption("Language", locale.getLanguage());//configurator objekt vom SL holen und methode
				//setLocalOption der Configurator klasse aufrufen (String "Language" im cfg gespeichert, locale.getLanguage holt
				//das aktuell geklickte element de oder en und speichert es im cfg file unter Language)
				//-->initialisierung von configurator in der main, wie der SL (siehe splash model)
                ServiceLocator.setTranslator(new Translator(locale.getLanguage())); //bei jedem klick wird ein translator erstellt, d.h. das
                //transl objekt vom SL wird neu gesetzt und bei update text dann verwendet (de oder en)
                //das translator objekt nimmt einen String, den language code, entgegen
                //(bei uns de und en) --> siehe constructor der translator klasse
                updateTexts();
            });
        }
	    
       //normales menu erstellen mit den default werten. default translator noch initialisieren (in main: im default.cfg den
       //default wert für Language holen und damit den default translator erstellen)
        menuHelp = new Menu(t.getString("program.menu.help"));
	    menuBar.getMenus().addAll(menuFile, menuHelp); //menu fertig gestellt, ab jetzt nur noch programmspezifisch
	    
	    
		
		VBox root = new VBox(10);
		root.setPrefWidth(300);
		root.setPrefHeight(250);
		root.setAlignment(Pos.TOP_LEFT);
		root.getChildren().add(menuBar);
		
		//create 5x HBoxes and adds them to the Vbox
		final int CHILDREN = 5;
		HBox  []  hboxes = new HBox [CHILDREN];        
		for (int i =0; i<CHILDREN; i++){
			hboxes[i]= new HBox(10); //spacing 10
			root.getChildren().add(hboxes[i]);
			hboxes[i].setAlignment(Pos.CENTER);
			hboxes[i].setPrefHeight(50);
			hboxes[i].setPrefWidth(200);
		}
		
		//creates the buttons 0 - 9
		for (int i =0; i< numbers.length; i++){
			numbers[i]= new Button (Integer.toString(i));
		}
		
		//creates the operator buttons
		operators[0] = new Button ("+");
		operators[1] = new Button ("-");
		operators[2] = new Button ("/");
		operators[3] = new Button ("X"); //big letter X
		operators[4] = new Button ("=");
		operators[5] = new Button (".");
		
		
		//creates the operators
	
		//adds buttons to the hboxes (buttons as instance variables)
		hboxes[4].getChildren().addAll(numbers[0], operators [5], operators[4], operators[0]);
		hboxes[3].getChildren().addAll(numbers[1],numbers[2],numbers[3], operators[1] );
		hboxes[2].getChildren().addAll(numbers[4],numbers[5],numbers[6], operators[3] );
		hboxes[1].getChildren().addAll(numbers[7],numbers[8],numbers[9], operators[2] );
	
		hboxes[0].getChildren().addAll(equals, message1, message2, message3);
		
        Scene scene = new Scene(root);
       // scene.getStylesheets().add(
                //getClass().getResource("app.css").toExternalForm());
        return scene;
	}
	
	   protected void updateTexts() {//druch den klick kommt man in die methode create_GUI zum click action event
		   //die methode hier übersetzt alle GUI elemente mit der sprache des geklickten locales
	       Translator t = ServiceLocator.getTranslator();// transl obj vom SL wurde updatet durch den click event. das obj muss nur noch
	       //geholt werden
	        
	        // The menu entries
	       menuFile.setText(t.getString("program.menu.file"));
	       menuFileLanguage.setText(t.getString("program.menu.file.language"));
           menuHelp.setText(t.getString("program.menu.help"));
	        
	    }
}