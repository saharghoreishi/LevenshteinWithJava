
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
    	 try
 	   {
    	InputOutputWithaskingQ();
 	   }
 	    catch(java.lang.NumberFormatException e)
 	    	 {
 	                System.err.println(e.getMessage());
 	                InputOutputWithaskingQ();
 	    	 }
 	    catch(Exception e)
    	 {
                System.err.println(e.getMessage());
              
    	 }
    }
    public static void InputOutputWithaskingQ() throws Exception

    {
    	 Scanner scanner = new Scanner(System. in);
    	 System.out.print("Enter first string string : ");
	     String inputStringone =( scanner. nextLine().replaceAll(" ", ""));
	     System.out.print("Enter second string string : ");
	     String inputStringTwo =( scanner. nextLine().replaceAll(" ", ""));
	     Levenshtein lev = new Levenshtein(inputStringone, inputStringTwo);
	     System.out.print("If you want to choose modified version press 1 else press 2: ");
	       int choose =Integer.parseInt(scanner.nextLine());
	       if(choose==1){
	    	   System.out.print("Please Enter MaxDist value: ");
	    	   int maxDist=Integer.parseInt(scanner.nextLine());
	    	   System.out.println(inputStringone+','+inputStringTwo+','+maxDist+"=>"+lev.ShowDist(maxDist));
	           }else if(choose==2){System.out.println(inputStringone+','+inputStringTwo+"=> "+lev.ShowDist());}
		 scanner.close();
	  
    	}
}

class Levenshtein {
	
	private String a=null;
	private String b=null;
	private int[][] dis ;
    int deletion =0;
	int insertion =0;
	int substitution =0;
	Levenshtein (String Token1, String Token2) {
		this.a = Token1;
		this.b = Token2;
		dis = new int[ a.length() + 1 ][ b.length() + 1 ];
	}
	private  void FillFirstRow()
	{
		   for( int i = 0; i < a.length() + 1; i++ ) {
		        dis[ i ][ 0 ] = i;
		     
		    }
	}
	private  void FillFirstColumn()
	{
		  for(int j = 0; j < b.length() + 1; j++) {
		        dis[ 0 ][ j ] = j;
		       
		    }
	}
	private  void FillfirstRowandColumnWithThread()
	{
		 Thread thread1 = new Thread(new Runnable() {
	            @Override
	            public void run() {FillFirstRow();}});
	        thread1.start();
	        Thread thread2 = new Thread(new Runnable() {
	            @Override
	            public void run() {FillFirstColumn();}});
	        thread2.start();
	    
	}
	private  int FillMatrix(int Dist)
	{ 
		for( int i = 1; i < a.length() + 1; i++ ) 
		{ 
          for( int j = 1; j < b.length() + 1; j++ )
            {
             deletion = dis[ i - 1 ][ j ] + 1;
             insertion = dis[ i ][ j - 1 ] + 1;
             substitution = dis[ i - 1 ][ j - 1 ]+(a.charAt(i-1) == b.charAt(j-1)? 0:1);  
             dis[ i ][ j ] = Math.min( Math.min( deletion, insertion ), substitution );
             if(dis[ i  ][ j ]> Dist+1 && dis[ i - 1 ][ j - 1 ]>Dist+1)
 				return Dist+1;
            }
		}
          return  dis[ a.length() ][ b.length() ];
	}
	private  int FillMatrix()
	{ 
		for( int i = 1; i < a.length() + 1; i++ ) 
		{
          for( int j = 1; j < b.length() + 1; j++ )
            {
        	    deletion = dis[ i - 1 ][ j ] + 1;
                insertion = dis[ i ][ j - 1 ] + 1;
                substitution = dis[ i - 1 ][ j - 1 ]+(a.charAt(i-1) == b.charAt(j-1)? 0:1);  
               dis[ i ][ j ] = Math.min( Math.min( deletion, insertion ), substitution );
	        }
		}
		return  dis[ a.length() ][ b.length() ];
	}
    int ShowDist(int Dist)
	{
    	if(a.length()==0||b.length()==0){return a.length()+b.length() ;}
    	else{
    		FillfirstRowandColumnWithThread();
	    	return  FillMatrix(Dist);
    	}

	}
	int ShowDist()
	{
		if(a.length()==0||b.length()==0){return a.length()+b.length() ;}
    	else{
    		FillfirstRowandColumnWithThread();
		return  FillMatrix();
    	}
	}

}