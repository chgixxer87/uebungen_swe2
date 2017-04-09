package Bonus.Lotto.appClasses;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class JackpotTest {
	
	int jackpot = 12000000;
	int [] random;
	int [] bets;
	LinkedList <Integer> numbers;
	int four_correct = 4;
	int five_correct= 2;
	int six_correct = 1;
	
	@Before
	public void prepare(){
		
		random = new int [6];
		numbers = new LinkedList <Integer>();
		bets = new int [6];
		
		for(int i = 1; i<=42;i++){
			numbers.add(i);
		}
		
		Collections.shuffle(numbers);
		
		for (int i =0; i<6;i++){
			random[i] = numbers.poll();
			System.out.println("Lottozahlen: "+random[i]);
		}
		
		Scanner read= new Scanner(System.in);
		
		int counter =0;
		
		while (counter <6){
			System.out.println("geben sie die zahl ein: " );
			bets[counter] = read.nextInt();
			counter++;
		}
		
		for (int i = 0; i<bets.length; i++){
			System.out.println(bets[i]);
			
		}
		
		
		
		
	}
	
	

	@Test
	public void test() {
		
		try {
		
			//win for four numbers
			assertEquals(3000000, calculate_four(), 0);
			
			//win for five numbers
			assertEquals(6000000, calculate_five(), 0);
			
			assertEquals(jackpot, calculate_six(), 0);
			
			
			
		} catch (Exception e){
			fail();
		}
		
	
	}


	private double calculate_six() {
		// TODO Auto-generated method stub
		return jackpot;
	}



	private double calculate_five() {
		double win = jackpot / five_correct;
		
		return win;
	}



	private double calculate_four() {
		
		double win = jackpot / four_correct;
		
		return win;
	}

}
