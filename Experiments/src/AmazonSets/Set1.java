package AmazonSets;

import java.util.Arrays;

public class Set1 {
	private static int coinSum(int n){
		if(n<5) return 0;
		int diff =  (n/2+n/3+n/4)-n;
		diff+=coinSum(n/2)+coinSum(n/3)+coinSum(n/4);
		return diff;
		
		
		

	}
	/*
	 *  1,2,4,3,9,5,10,6,8,7
	    0,0,0,1,0,1,0 ,2,2,3
	//array recomposition
   /*
    * 1   == 0
    * 1,2 ==0	
    * 1,2,4 ==0
    * 1,2,3,4 == 1
    * 1,2,3,4,9 ==0
    * 1,2,3,4,5,9 == 1
    * 1,2,3,4,5,9,10 == 0
    * 1,2,3,4,5,6,9,10 == 2 
    * 1,2,3,4,5,6,8,9,10 == 2
    * 1,2,3,4,5,6,7,8,9,10 ==3   
	/*
	 * 
5,9,3,6,10,7,1,8,4,2
0,0,2,1, 0,2,6,2,6,8
[1-10]
5  => 0
5,9,  => 0
3, 5, 9 => 2
3,5,6,9 => 1,
3,5,6,9,10 => 0,
3,5,6,7,9,10 => 2
1,3,5,6,7,9,10 => 6       10-6 =4 
1,3,5,6,7,8,9,10 => 2     10-2 =8 > 4,2 = 8
1,3,4,5,6,7,8,9,10 => 6   10-6 = 4;4>2 ==4
1,2,3,4,5,6,7,8,9,10 =>8   2

                 *
1,3,4,5,6,7,8,9,10,2
              *
1,3,5,6,7,8,9,10,4,2
             * 
1,3,5,6,7,9,10,8,4,2
           *
3,5,6,7,9,10,1,8,4,2

0,0,2,1, 0,2,6,2,6,8

*/
static int [] original={5,9,3,6,10,7,1,8,4,2};
static int [] sorted = {1,2,3,4,5,6,7,8,9,10};
static int [] shifted= {0,0,2,1,0,2,6,2,6,8};
private static void recompose(){
	int end = 9;
	while(end>0){
		int start=end-shifted[end];
		int temp = sorted[start];
		for(int i=start;i<end;i++){
			sorted[i]=sorted[i+1];
		}
		sorted[end] = temp;
		end--;
	}
	System.out.println(Arrays.toString(sorted));
	
	
}
}
