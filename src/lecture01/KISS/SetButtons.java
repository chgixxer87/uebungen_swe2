package lecture01.KISS;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This application contains six buttons. The text in each button has to be set according to the
 * values from an array, which range from 1 to 6. These values are used to set the text in the
 * buttons. The original program set images, but the idea is the same.
 * 
 * The code you should improve:
 * 
 * - How the buttons are declared and initialized
 * 
 * - The contents of the method setButtons()
 */
public class SetButtons extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	final int NUMBER_OF_BUTTONS = 6;
    	String [] strArray = {"one","two","three","four","five","six"};
    	
    	
    	
    	//array which takes Button objects
    	Button [] buttons = new Button [NUMBER_OF_BUTTONS];
    	
        for (int i =0; i< buttons.length;i++){
        	buttons[i]= new Button();
        }
        

        int[] numbers = getRandomNumbers();

        setButtons(buttons, numbers, strArray);
        
        //give the buttons array (filled now with value through the method setButtons) to the HBox
        HBox root = new HBox(buttons);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KISS example: SetButtons");
        primaryStage.show();
    }
    
    //
    private int[] getRandomNumbers() {
    	final int RANDOM_ARRAY = 6;
        int[] numbers = new int[RANDOM_ARRAY];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 6 + 1);
        }
        return numbers;
    }

    public void setButtons(Button [] buttons, int[] numbers, String [] strArray) {

    	for (int i=0; i<buttons.length;i++){
    		//example i = 0; value 5 is saved on index 0 in the numbers array
    		//-->the string "five" is saved on index 4 of the String array, so you have to decrement the String index with 1
    		buttons[i].setText(strArray[numbers[i]-1]);
    	}

    }
}

