package Bonus.Lotto.appClasses;

import java.util.Collections;
import java.util.LinkedList;

import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.abstractClasses.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;


public class App_Controller_bet extends Controller <App_Model, App_View_bet> {
	
	private LinkedList <Integer> chosenNumbers = new LinkedList <Integer>();
	private final int MAX_NUMBER = 6;
	private LinkedList <Integer> chosenAdditionalNumbers= new LinkedList <Integer>();
	private final int MAX_ADDITIONAL_NUMBER = 1;
	
	ServiceLocator serviceLocator;
	
	public App_Controller_bet(App_Model model, App_View_bet view) {
		super(model, view);
		serviceLocator = ServiceLocator.getServiceLocator();
	
        view.bet.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {  		
        		Bets bet = new Bets(chosenNumbers, chosenAdditionalNumbers);
        		int counter = serviceLocator.getBetCounter();       		
        		App_View_start.bets_summary[counter].setText(bet.toString());
        		App_View_start.bets_summary[counter].setStyle("-fx-text-fill: yellow;");
        		serviceLocator.setBet(bet);
        		System.out.println(serviceLocator.getBetList());
        		if(counter == 9){
        			App_View_start.buttons[0].setDisable(true);
        		}
        		serviceLocator.increaseJackpot();
        		App_View_start.updateJackpot();
        		App_View_start.updateTotalBets();
        		App_View_start.buttons[1].setDisable(false);
           		view.stop();
        	}
        });
        
        //removes all number selections and clears the linked lists for the selected numbers
        view.clear.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		clearAll();
        	}
        });
        
        view.random.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		Label label = new Label ("X");
        		label.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
        		setRandomNumbers();
        		setRandomAdditionalNumbers();
        		view.random.setDisable(true);
        		view.clear.setDisable(false);
        		view.bet.setDisable(false);
        	}
        });
        
        view.return_to_start.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
            	view.stop();//stop method of abstract view = stage.hide();
        	}
        });
        
        //event handling for the main numbers 1-42
        for (int i = 0; i<view.number.length;i++){
        view.number[i].setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		LottoButton btn = (LottoButton) event.getSource();   
        		String str= btn.getButtonString();
        		Label label = new Label ("X");
        		label.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
        		
        		if (btn.getStackPane().getChildren().size() == 1){
        			view.number[(Integer.parseInt(str))-1].getStackPane().getChildren().add(label);
        			chosenNumbers.add(Integer.parseInt(str));
        		}else{
        			//removes the red cross
        			btn.getStackPane().getChildren().remove(1);
        			int remove = Integer.parseInt(btn.getButtonString());
        			int index = chosenNumbers.indexOf(remove);
        			//removes the unchosen number from the linked list
        			chosenNumbers.remove(index);
        		}
        			
        		if (chosenNumbers.size() == MAX_NUMBER){
        			for (int i =0; i < view.number.length;i++){
        				if (!chosenNumbers.contains(Integer.parseInt(view.number[i].getButtonString()))){
        					view.number[i].setDisable(true);
        				}
        			}
        		
        		}else{
        			for (int i =0; i < view.number.length;i++){
        				if (!chosenNumbers.contains(Integer.parseInt(view.number[i].getButtonString()))){
        					view.number[i].setDisable(false);
        				}
        		    }
        	    }
        		
        		if (chosenNumbers.size()==MAX_NUMBER && chosenAdditionalNumbers.size() == MAX_ADDITIONAL_NUMBER){
        			view.bet.setDisable(false);
        		}else{
        			view.bet.setDisable(true);
        		}
        		
        		if (chosenAdditionalNumbers.isEmpty() && chosenNumbers.isEmpty()){
        			view.clear.setDisable(true);
        			view.random.setDisable(false);
        		}else{
        			view.clear.setDisable(false);
        			view.random.setDisable(true);
        		}
        	}
        });
    
}
        
        
        //event handling for the additional numbers 1-6
        for (int i = 0; i<view.additional_number.length;i++){
        view.additional_number[i].setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		LottoButton btn = (LottoButton) event.getSource();   
        		String str= btn.getButtonString();
        		Label label = new Label ("X");
        		label.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
        		
        		if (btn.getStackPane().getChildren().size() == 1){
        			view.additional_number[(Integer.parseInt(str))-1].getStackPane().getChildren().add(label);
        			chosenAdditionalNumbers.add(Integer.parseInt(str));
        		}else{
        			btn.getStackPane().getChildren().remove(1);
        			int remove = Integer.parseInt(btn.getButtonString());
        			int index = chosenAdditionalNumbers.indexOf(remove);
        			//removes the unchosen number from the linked list
        			chosenAdditionalNumbers.remove(index);
        		}
        		
        		if (chosenAdditionalNumbers.size() == MAX_ADDITIONAL_NUMBER){
        			for (int i =0; i < view.additional_number.length;i++){
        				if (!chosenAdditionalNumbers.contains(Integer.parseInt(view.additional_number[i].getButtonString()))){
        					view.additional_number[i].setDisable(true);
        				}
        			}
        		}else{
        			for (int i =0; i < view.additional_number.length;i++){
        				if (!chosenAdditionalNumbers.contains(Integer.parseInt(view.additional_number[i].getButtonString()))){
        					view.additional_number[i].setDisable(false);
        				}
        		    }
        	    }
        		
        		if (chosenAdditionalNumbers.size()==MAX_ADDITIONAL_NUMBER && chosenNumbers.size() == MAX_NUMBER){
        			view.bet.setDisable(false);
        		}else{
        			view.bet.setDisable(true);
        		}
        		
        		if (chosenAdditionalNumbers.isEmpty() && chosenNumbers.isEmpty()){
        			view.clear.setDisable(true);
        			view.random.setDisable(false);
        		}else{
        			view.clear.setDisable(false);
        			view.random.setDisable(true);
        		}
        	}

    });
    
}

        }
	
	private void setRandomNumbers(){
		LinkedList <Integer> randomNumbers = new LinkedList <Integer>();
		for(int i = 0; i < view.number.length; i++){
			randomNumbers.add(Integer.parseInt(view.number[i].getButtonString()));
		}
		Collections.shuffle(randomNumbers);
		for(int i = 0; i<6; i++){
			int randomNumber = randomNumbers.poll();
			chosenNumbers.add(randomNumber);
			Label label = new Label ("X");
    		label.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
			view.number[randomNumber-1].getStackPane().getChildren().add(label);
  		}
		for (int i =0; i < view.number.length;i++){
			if (!chosenNumbers.contains(Integer.parseInt(view.number[i].getButtonString()))){
				view.number[i].setDisable(true);
			}
	    }	
	}
	
	private void setRandomAdditionalNumbers(){
		LinkedList <Integer> randomAdditionalNumbers = new LinkedList <Integer>();
		for(int i = 0; i < view.additional_number.length; i++){
			randomAdditionalNumbers.add(Integer.parseInt(view.additional_number[i].getButtonString()));
		}
		Collections.shuffle(randomAdditionalNumbers);
		for(int i = 0; i<1; i++){
			int randomNumber = randomAdditionalNumbers.poll();
			chosenAdditionalNumbers.add(randomNumber);
			Label label = new Label ("X");
    		label.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
			view.additional_number[randomNumber-1].getStackPane().getChildren().add(label);
  		}
		for (int i =0; i < view.additional_number.length;i++){
			if (!chosenAdditionalNumbers.contains(Integer.parseInt(view.additional_number[i].getButtonString()))){
				view.additional_number[i].setDisable(true);
			}
		}	
	}
	
	private void clearAll(){
		for (int i = 0; i < view.number.length;i++){
			if(view.number[i].getStackPane().getChildren().size() == 2){
    			view.number[i].getStackPane().getChildren().remove(1);
			}
			view.number[i].setDisable(false);
		}
		
		for (int i = 0; i < view.additional_number.length;i++){
			if(view.additional_number[i].getStackPane().getChildren().size() == 2){
    			view.additional_number[i].getStackPane().getChildren().remove(1);
			}
			view.additional_number[i].setDisable(false);
		}
		
		view.clear.setDisable(true);
		chosenNumbers.clear();
		chosenAdditionalNumbers.clear();
		view.random.setDisable(false);
		view.bet.setDisable(true);
	}
}
