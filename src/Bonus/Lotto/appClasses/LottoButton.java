package Bonus.Lotto.appClasses;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

//dank der vererbung kann ein LottoButton wie ein normaler Button einer Pane hinzugefügt werden bzw. können alle methoden von der klasse java-button nun auch
//bei einem LottoButton verwendet werden
public class LottoButton extends Button {
	
	private Label label;
	//stackpane: kinder können gestapelt werden -> rotes kreuz setzen auf button, aber die zahl soll immernoch sichtbar sein
	//für jeden button wird eine solche stackpane als grafik gesetzt
	private StackPane sp;
	
	public LottoButton(Label label, StackPane sp){
		this.label = label;
		this.sp=sp;
		
	}
	
	public String getButtonString(){
		return this.label.getText();
	}
	
	public StackPane getStackPane(){
		return this.sp;
	}

}
