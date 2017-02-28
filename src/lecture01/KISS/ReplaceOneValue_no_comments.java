package lecture01.KISS;
import java.util.ArrayList;
import java.util.Random;

/**
 * This program contains a method, replaceOneValue(), taken from a real program, and it desperately
 * needs improved. Can you figure out what the method does, and replace it with a better
 * implementation?
 * 
 * The main method creates the initial parameters required by the method, prints them, calls the
 * method, and prints the result.
 */

public class ReplaceOneValue_no_comments {

	public static void main(String[] args) {
		  
		        // Create initial array holding three integer values from 1 to 10 inclusive.
		        ArrayList<Integer> oldArray = new ArrayList<>();
		        for (int i = 0; i < 3; i++)
		            oldArray.add((int) (Math.random() * 10 + 1));

		        // Print the initial array, pick a random value from 0 to 2, and call the method
		        printArray("                    Initial values: ", oldArray);
		        int randomIndex = (int) (Math.random() * 3);
		        ArrayList<Integer> newArray = replaceOneValue(oldArray, randomIndex);

		        // Print the initial array and the new array
		        printArray("Initial values should be unchanged: ", oldArray);
		        printArray("                        New values: ", newArray);

		    }

		    private static void printArray(String msg, ArrayList<Integer> array) {
		        System.out.print(msg);
		        for (int i = 0; i < array.size(); i++)
		            System.out.print(array.get(i) + ", ");
		        System.out.println();
		    }

		    public static ArrayList<Integer> replaceOneValue(ArrayList<Integer> OldIntarray, int index) {

		        final int MAXVALUES = 3;

		        ArrayList<Integer> NewIntarray = (ArrayList<Integer>) OldIntarray.clone();

		        // create a new random int
		        Random rn = new Random();
		        int randomValue = rn.nextInt(10) + 1;

		        // replace the old int, set in the new one
		        NewIntarray.set(index, randomValue);
		        
		        int i=0;
		        boolean done=false;
		        while (i<MAXVALUES && done !=true){
		        	if (OldIntarray.get(i)==NewIntarray.get(i)){
		        		i++;
		        	}else{
		        		done =true;
		        	}	
		        }
		        
		        /**if the int values of both arrays are all equal, run again until they arent equal*/
		        if (!done){
		        	System.out.println(index);
		        	return replaceOneValue(NewIntarray,index);
		        }else{
		        	return NewIntarray;
		        }
		    }
}
