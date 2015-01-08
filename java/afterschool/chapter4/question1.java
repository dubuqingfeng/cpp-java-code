import java.util.Scanner; 
public class question1
{
	public static void main(String args[]){
		//Declare and initialize an array.
		float j[] = new float[5];
		Scanner scanner = new Scanner(System.in);
		System.out.println("test");
		for(int i = 0;i < 5;i++){ 
			j[i] = scanner.nextFloat();
		}
		float sum = 0;
		for(int i = 0;i < 5;i++){
			sum = sum + j[i];
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat( "0.0 "); 
		//System.out.println(df.format(sum));
		System.out.println(df.format(sum/5));
	}
}
