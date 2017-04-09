package Bonus.Lotto.appClasses;

import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.abstractClasses.View;
import Bonus.Lotto.commonClasses.Translator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App_View_goodluck extends View<App_Model>{

	protected App_View_goodluck(Stage stage, App_Model model) {
		super(stage, model);
		stage.setTitle("Lotto");
	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Translator tl = sl.getTranslator();
		
		
		BorderPane root = new BorderPane();
		root.setPrefHeight(600);
		root.setPrefWidth(600);
		
		Label label = new Label(tl.getString("program.gl_view.label"));
		label.setStyle("-fx-font-size: 50px; -fx-font-style: oblique; -fx-text-fill: red;");
		
		root.setBottom(label);
		
		root.setAlignment(label, Pos.CENTER);
		
		root.setMargin(label, new Insets(0,0,40,0));
		
		root.setStyle("-fx-background-color: yellow;");
		
        Scene scene = new Scene(root);

        return scene;
	}

}
