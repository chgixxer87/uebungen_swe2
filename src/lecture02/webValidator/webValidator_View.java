package lecture02.webValidator;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class webValidator_View {
    private webValidator_Model model;
    private Stage stage;

    TextField txtWeb = new TextField();
    TextField txtPort =new TextField();

    webValidator_View(Stage stage, webValidator_Model model) {
        this.stage = stage;
        this.model = model;
        
        stage.setTitle("Web Validator");
        
        GridPane root = new GridPane();
        root.add(txtWeb, 0, 0);
        root.add(txtPort, 2, 0);

        Scene scene = new Scene(root);
        stage.setScene(scene);;
    }
    
    public void start() {
        stage.show();
    }
    
    /**
     * Stopping the view - just make it invisible
     */
    public void stop() {
        stage.hide();
    }
    
    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
        return stage;
    }
}
