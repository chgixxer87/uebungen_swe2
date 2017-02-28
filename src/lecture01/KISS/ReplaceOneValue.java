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
public class ReplaceOneValue implements Cloneable {

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

        //ArrayList<Integer> NewIntarray = new ArrayList<Integer>(MAXVALUES);
        ArrayList<Integer> NewIntarray = (ArrayList<Integer>) OldIntarray.clone();

        // get the current ints ->dank clone nicht nötig
       /** NewIntarray.add(0, OldIntarray.get(0));
        NewIntarray.add(1, OldIntarray.get(1));
        NewIntarray.add(2, OldIntarray.get(2));*/

        // create a new random int
        Random rn = new Random();
        int randomValue = rn.nextInt(10) + 1;

        // replace the old int, set in the new one
        NewIntarray.set(index, randomValue);
       /** NewIntarray.remove(index);
        NewIntarray.add(index, randomValue);*/
        
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
        	//recursive call of replaceOneValue until the arrays arent equal anymore
        	//the return statement needs a array and replaceOneValue creates a new array. without return: nothing happens
        	//and it returns always the value in line 81...even if the arrays are equal (semantic error, we want 2 different arrays)
        	return replaceOneValue(NewIntarray,index);
        }
        
        return NewIntarray;

        /**int newInt1 = NewIntarray.get(0);
        int newInt2 = NewIntarray.get(1);
        int newInt3 = NewIntarray.get(2);

        // clear the intarray
        NewIntarray.removeAll(NewIntarray);

        // check if the int's are equal
        boolean bool1;
        if (newInt1 != newInt2) {
            bool1 = false;
        } else {
            bool1 = true;
        }

        boolean bool2;
        if (newInt1 != newInt3) {
            bool2 = false;
        } else {
            bool2 = true;
        }
        boolean bool3;
        if (newInt2 != newInt3) {
            bool3 = false;
        } else {
            bool3 = true;
        }

        boolean bool4 = false;

        bool4 = bool1 == false && bool2 == false && bool3 == false;

        // if the int's are not equal, set them
        if (bool4 == true) {

            NewIntarray.add(0, newInt1);
            NewIntarray.add(1, newInt2);
            NewIntarray.add(2, newInt3);

            // if the int's are equal, run again
        } else {

            NewIntarray = replaceOneValue(OldIntarray, index);
        }

        return NewIntarray;*/
    }
}
