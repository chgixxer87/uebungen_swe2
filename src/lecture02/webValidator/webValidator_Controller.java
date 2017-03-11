package lecture02.webValidator;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class webValidator_Controller {
	
	private webValidator_Model model;
	private webValidator_View view;
	
	//constructor
	webValidator_Controller(webValidator_Model model, webValidator_View view) {
        this.model = model;
        this.view = view;
        
        view.txtWeb.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                validateWeb(newValue);
            }            
        });
        
        
        view.txtPort.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
               //validatePort(newValue);
            }            
        });
     
    }
	
	 private void validateWeb(String newValue) {
	        boolean valid = false;
	        
	        System.out.println(newValue);
	        
	        if(newValue.length()> 3 && newValue.substring(0 , newValue.length()).equals("www")){
	        	String[] addressParts = newValue.split("@"); // split Methode der java-klasse String
	        	 if (addressParts.length == 2 && !addressParts[0].isEmpty()) {
	        		 String[] domainParts = addressParts[1].split("\\.");
	        		 if (domainParts.length >= 2) {
		                    valid = true;
		                    for (String s : domainParts) {
		                        if (s.length() < 2) {
		                            valid = false;
		                            break;
		                        }
		                    }                
		                }
	        	 }
	        	
	        	
	        }
	        
	       /** if (newValue.charAt(newValue.length()-1) == 'w' && ) {
	            String[] addressParts = newValue.split("@"); // split Methode der java-klasse String
	            if (addressParts.length == 2 && !addressParts[0].isEmpty() && addressParts[0].length() ==3) {
	                String[] domainParts = addressParts[1].split("\\.");
	                if (domainParts.length >= 2) {
	                    valid = true;
	                    for (String s : domainParts) {
	                        if (s.length() < 2) {
	                            valid = false;
	                            break;
	                        }
	                    }                
	                }
	            }
	        }*/
	        
	        if (valid) {
	            view.txtWeb.setStyle("-fx-text-inner-color: green;");
	        } else {
	            view.txtWeb.setStyle("-fx-text-inner-color: red;");
	        }
	    }
}
