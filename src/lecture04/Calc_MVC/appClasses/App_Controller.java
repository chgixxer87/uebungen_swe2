package lecture04.Calc_MVC.appClasses;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;
import lecture04.Calc_MVC.ServiceLocator;
import lecture04.Calc_MVC.abstractClasses.Controller;


public class App_Controller extends Controller<App_Model, App_View> {
    
	
	private String current="";
	private long val1;
	private long val2;
	private String operator="";

    public App_Controller(App_Model model, App_View view) {
        super(model, view); //model und view erstellen
        
        // register ourselves to listen for button clicks for numbers
        for (int i =0; i < view.numbers.length;i++){
        
        view.numbers[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	buttonClick( ((Button)event.getSource()).getText() ); //takes the String of the current clicked number
            }
        });
        }
        
        // register ourselves to listen for button clicks for operators
        for (int i =0; i < view.operators.length;i++){
        
        view.operators[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	buttonClick( ((Button)event.getSource()).getText() ); //takes the String of the current clicked number
            }
        });
        }
        
        
       
    // register ourselves to handle window-closing event
    view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            Platform.exit();
        }
    });
        
        //ServiceLocator.init();        
        ServiceLocator.getLogger().info("Application controller initialized");
    }
    
    
    private void buttonClick(String str){ //decision of calling processNumbers oder processOperators
    	for (int i = 0; i < view.numbers.length; i++){
    		if (view.numbers[i].getText().equals(str)){//--> view ist ein Objekt von der abstract class View und wird beim abstract Controller erstellt
    			//bzw als instanzvariable gesetzt. auf dieses objekt kann die variable numbers angewendet werden (oder eine methode von App_View
    			//z.b. createGUI)
    			//--> view ist eine instanz der klasse View, welche vererbt wird an App_view
    			//alternative: numbers static machen, damit keine instanz nötig ist, um die variable aufzurufen
    			
    			processNumbers(str);
    			
    		}else{
    			processOperators(str);
    		}
    	}
    	
    	
    }
    
    private void processNumbers (String str){
    	
    	//hier wird 1. zahl eingegeben
    	if (view.message2.getText().equals("")){
    		current+=str;
    		view.message1.setText(current);
    		//service
    		//ServiceLocator.getLogger1().info("1. zahl wird eingegeben");
    		}
    	
    	//hier wird zweite zahl eingegeben
    	if (!view.message2.getText().equals("")){
			current+=str;
			//ServiceLocator.getLogger3().info("2. zahl wird eingegeben, logger 3");
			//ServiceLocator.getLogger2().info("2. zahl wird eingegeben, logger 2");
			view.message3.setText(current);
			}
    }
    
    private void processOperators (String str){
    	if (!view.message1.getText().equals("") && view.message3.getText().equals("")){
    		current=str;
    		view.message2.setText(current);
    		current = "";
    		//ServiceLocator.getLogger1().info("operator wurde eingegeben");
    		}
    		
    		if (!view.message1.getText().equals("") && !view.message3.getText().equals("")){
    			val1 = Long.parseLong(view.message1.getText());
    			val2 = Long.parseLong(view.message3.getText());
    			operator = view.message2.getText();
    			val1 = model.calculate(val1, val2, operator);
    			view.message1.setText(Long.toString(val1));
    			
    			if (str.equals("=")){
    				view.equals.setText(str);
    				view.message2.setText("");
    				view.message3.setText("");
    			}else{
    			view.message2.setText(str);
    			view.message3.setText("");
    			current = "";
    			//ServiceLocator.getLogger1().info("operator wurde eingegeben");
    		}	
    		}
    		
    		if (view.message1.getText().equals("")){
    			//info wird nicht ausgegeben, da tiefer als min level warning
    			//ServiceLocator.getLogger2().info("zuerst zahl eingeben");
    			//ab warning und höher wird ausgegeben, da min level = warning
    			//ServiceLocator.getLogger2().warning("zuerst zahl eingeben");
    		}
    	
    	
    }

}
