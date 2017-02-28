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
    	
    	
    	//array which takes Button objects
    	Button [] buttons = new Button [NUMBER_OF_BUTTONS];
    	
        for (int i =0; i< buttons.length;i++){
        	buttons[i]= new Button();
        }

        int[] numbers = getRandomNumbers();

        setButtons(buttons, numbers);

        HBox root = new HBox(btn1, btn2, btn3, btn4, btn5, btn6);
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

    public void setButtons(Button [] buttons, int[] numbers) {

        switch (numbers[0]) {

        case 1:
            one.setText("One");
            break;
        case 2:
            one.setText("Two");
            break;
        case 3:
            one.setText("Three");
            break;
        case 4:
            one.setText("Four");
            break;
        case 5:
            one.setText("Five");
            break;
        case 6:
            one.setText("Six");
            break;

        }

        switch (numbers[1]) {

        case 1:
            two.setText("One");
            break;
        case 2:
            two.setText("Two");
            break;
        case 3:
            two.setText("Three");
            break;
        case 4:
            two.setText("Four");
            break;
        case 5:
            two.setText("Five");
            break;
        case 6:
            two.setText("Six");
            break;

        }

        switch (numbers[2]) {

        case 1:
            three.setText("One");
            break;
        case 2:
            three.setText("Two");
            break;
        case 3:
            three.setText("Three");
            break;
        case 4:
            three.setText("Four");
            break;
        case 5:
            three.setText("Five");
            break;
        case 6:
            three.setText("Six");
            break;

        }

        switch (numbers[3]) {

        case 1:
            four.setText("One");
            break;
        case 2:
            four.setText("Two");
            break;
        case 3:
            four.setText("Three");
            break;
        case 4:
            four.setText("Four");
            break;
        case 5:
            four.setText("Five");
            break;
        case 6:
            four.setText("Six");
            break;

        }

        switch (numbers[4]) {

        case 1:
            five.setText("One");
            break;
        case 2:
            five.setText("Two");
            break;
        case 3:
            five.setText("Three");
            break;
        case 4:
            five.setText("Four");
            break;
        case 5:
            five.setText("Five");
            break;
        case 6:
            five.setText("Six");
            break;

        }

        switch (numbers[5]) {

        case 1:
            six.setText("One");
            break;
        case 2:
            six.setText("Two");
            break;
        case 3:
            six.setText("Three");
            break;
        case 4:
            six.setText("Four");
            break;
        case 5:
            six.setText("Five");
            break;
        case 6:
            six.setText("Six");
            break;

        }

    }
}

