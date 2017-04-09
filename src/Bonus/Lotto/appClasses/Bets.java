package Bonus.Lotto.appClasses;

import java.util.Collections;
import java.util.LinkedList;

import Bonus.Lotto.ServiceLocator;
import Bonus.Lotto.commonClasses.Translator;

public class Bets {
	
	private LinkedList <Integer> normalBet = new LinkedList<Integer>();
	private LinkedList <Integer> additionalBet = new LinkedList<Integer>();
	
	public Bets(LinkedList <Integer> normalBet, LinkedList <Integer> additionalBet){
		Collections.sort(normalBet);
		this.normalBet = normalBet;
		Collections.sort(additionalBet);
		this.additionalBet = additionalBet;
	}
	
	public String toString(){
		ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Translator tl = sl.getTranslator();
	    String number = tl.getString("program.bet.betClass.numbers")+ this.normalBet.toString();
	    String add_number = tl.getString("program.bet.betClass.add_numbers")+this.additionalBet.toString();
		return number+", "+add_number;
	}

}

