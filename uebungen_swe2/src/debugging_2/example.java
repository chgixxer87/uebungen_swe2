package debugging_2;


public class example {
	

	public static void main(String[] args) {
		
        int[] numsOut;
        int value= 0;
        
        int [] nums = new int [5];
        
        for (int i =0; i<nums.length;i++){
        	nums[i]= value+1;
        }

        

        // Create the second array
        int lengthNumsOut = nums.length / 2; // error
        numsOut = new int[lengthNumsOut];

        // Process the first array into the second
        for (int i = 0; i < lengthNumsOut; i++) {
            numsOut[i] = nums[i] + nums[nums.length - i]; // error
        }

        // Print the result
        System.out.println(numsOut); // error
    }

	}


