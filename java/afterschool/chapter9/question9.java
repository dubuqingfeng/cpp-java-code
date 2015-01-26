public class question9
{
	public static void main(String[] args) { 
		int n = 2;
		int m = 200;
		for (int i = n; i < m; i++) {  
        		int temp = (int)Math.sqrt(i);  
                   	if(i<=3){  
                		System.out.println(i);  
            		}  
            	for (int j = 2; j <= temp; j++) {  
                	if(i%j==0){  
                    		break;  
               		 }  
                	if(j>=temp){  
                    		System.out.println(i);  
                	}  
            	}  
        	}  
		//file
		File file = new File("prime.dat");
		if(!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOuputStream(file,true);
		
	} 
}
