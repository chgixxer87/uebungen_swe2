package lecture04.Calc_MVC.appClasses;

import javafx.scene.control.Button;
import lecture04.Calc_MVC.ServiceLocator;
import lecture04.Calc_MVC.abstractClasses.Model;

public class App_Model extends Model {
	
	private String current="";
	private long val1;
	private long val2;
	private String operator="";
	
	//business logik
    

    
    public App_Model() {
       
        
             
        ServiceLocator.getLogger().info("Application model initialized");//import der klasse nötig, da nicht gleiches package
        //mit getLogger wird der private static serviceLocator der Klasse ServiceLocator zurückgegeben. damit die rückgabe des
        //ServiceLocator objektes funktioniert, muss die klasse importiert werden (wie z.b. die java-klasse Logger
        //importieren, damit ein Logger objekt zurückgegeben werden kann)
    }
    
    protected long calculate (long num1, long num2, String operator){//private geht nicht. braucht
    	//mind. protected, damit obj model auf die methode zugreifen kann
		
		long result=0;
		
		if (operator.equals("+")){
			result=num1+num2;
		}
		
		if (operator.equals("-")){
			result=num1-num2;
		}
		
		if (operator.equals("X")){
			result=num1*num2;
		}
		if (operator.equals("/")){
			result=num1/num2;
		}
		if (operator.equals("=")){
			result=num1/num2;
		}
		
		return result;
		
		
	}
 
 
}
