package Bonus.Lotto.appClasses;

import Bonus.Lotto.abstractClasses.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App_View_final extends View<App_Model>{

	public App_View_final(Stage stage, App_Model model) {
		super(stage, model);
		stage.setTitle("Lotto");
	}
	
	

	@Override
	protected Scene create_GUI() {
		BorderPane root = new BorderPane();
		root.setPrefHeight(600);
		root.setPrefWidth(600);
		
		Label label = new Label("!!!!");
		label.setStyle("-fx-font-size: 50px; -fx-font-style: oblique; -fx-text-fill: red;");
		
		root.setBottom(label);
		
		root.setAlignment(label, Pos.CENTER);
		
		root.setMargin(label, new Insets(0,0,40,0));
		
		root.setStyle("-fx-background-color: gray;");
		
        Scene scene = new Scene(root);

        return scene;
	}

}
