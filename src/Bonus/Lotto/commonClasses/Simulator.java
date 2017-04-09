package Bonus.Lotto.commonClasses;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.appClasses.Bets;
import javafx.scene.control.Label;

public class Simulator extends Thread {

	      @Override
	      public void run() {
	  		try {
				while (true) {
					ServiceLocator.getServiceLocator().increaseJackpot();	
					Random generator = new Random();
					//the simulator creates a bet every 3 until 8 seconds
					int num = generator.nextInt(5000)+3000;

					LinkedList <Integer> randomNumbers = new LinkedList <Integer>();
					for(int i = 1; i <= 42; i++){
						randomNumbers.add(i);
					}
					Collections.shuffle(randomNumbers);

					LinkedList <Integer> randomAddNumbers = new LinkedList <Integer>();
					for(int i = 1; i <= 6; i++){
						randomAddNumbers.add(i);
					}
					Collections.shuffle(randomAddNumbers);		
					
					LinkedList <Integer> selected_numbers = new LinkedList <Integer>();
					for (int i = 0; i<6;i++){
						selected_numbers.add(randomNumbers.poll());
					}
					
					LinkedList <Integer> selected_AddNumbers = new LinkedList <Integer>();
					for (int i = 0; i<1;i++){
						selected_AddNumbers.add(randomAddNumbers.poll());
					}

					Bets bet = new Bets (selected_numbers,selected_AddNumbers);
					ServiceLocator.getServiceLocator().setBetForSim(bet);

					System.out.println(ServiceLocator.getServiceLocator().getBetListForSim());
				
					Thread.sleep(num);
				}
			} catch (InterruptedException e) {
			}
	      }
}