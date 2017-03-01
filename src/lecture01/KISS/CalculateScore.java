package lecture01.KISS;

	//original code in package ch.fhnw.richards.lecture01.kissExercises;
import java.util.Random;

/**
 * In a game, you collect six objects, which are numbered 1, 2 and 3. The number of objects you
 * collect gives you points, based on which object it is, and how many you have.
 * 
 * In this example, the method calcScore() takes an array of integers (1, 2, or 3) and calculates
 * the total score. The main method creates a random array, calls the method and prints the score.
 */
public class CalculateScore {
	
	//teständerung für git

    public static void main(String[] args) {
        int numObjects = 6;

        Random rand = new Random();
        int[] objects = new int[numObjects];
        for (int i = 0; i < numObjects; i++)
            objects[i] = rand.nextInt(3) + 1;

        int score = calcScore(objects);

        System.out.print("Objects: ");
        for (int i = 0; i < numObjects; i++)
            System.out.print(objects[i] + " ");
        System.out.println("     Score: " + score);
    }

    public static int calcScore(int[] objects) {
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        
        int object1 =1;
        int object2 =2;
        int object3 =3;

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == 1) {
                counter1++;
            }
            if (objects[i] == 2) {
                counter2++;
            }
            if (objects[i] == 3) {
                counter3++;
            }
        }
        
        int ScoreCounter1= calcScoreforCounter (counter1, object1);
        int ScoreCounter2= calcScoreforCounter (counter2, object2);
        int ScoreCounter3= calcScoreforCounter (counter3, object3);
        int totalScore = ScoreCounter1 + ScoreCounter2 + ScoreCounter3;
        
        return totalScore;
    }
    
    public static int calcScoreforCounter (int counter, int object) {
    	int ScoreForCounter =0;
    	
       	if(counter<3){
    		ScoreForCounter=0;
       	}
    	
    	if (counter ==3){
    		ScoreForCounter=object;
    	}
    		
    	if (counter ==4){
    		ScoreForCounter=object+1;
    	}
    			
    	if (counter==5){
    		ScoreForCounter=object+2;
    	}
    		
    	return ScoreForCounter;
    }
}

