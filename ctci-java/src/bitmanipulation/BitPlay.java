package bitmanipulation;

public class BitPlay {

	public static void swap(Integer i,Integer j,int start,int end)
	{		
		System.out.println("i="+Integer.toBinaryString(i));	
		System.out.println("j="+Integer.toBinaryString(j));	
		System.out.println("start="+start);	
		System.out.println("end="+end);	

		j<<=2;
		int temp=0xFFFF;
		int maskLength=end-start+1;

		temp<<=maskLength;

		for(int m=0;m<start;m++){
			temp<<=1;
			temp|=1;
		}

		
		Integer result=(i&temp)|j;
		System.out.println(Integer.toBinaryString(result));
	}
	
	
	public static int stringtoBinary(String input){
		//44.78
		
	return 44;	
		
	}


	public static void main(String[] args) {

		swap(98,5,2,4);
	}
}
