import java.util.Arrays;

public class Test extends Main {

	public static void main(String[] args) {
		long starttime=0;
		long finishtime=0;
		long starttimeModified=0;
		long finishtimeModified=0;
		long totalTimeTest = 0;
		long totalTimeTestModified= 0;
	

			System.out.println("-----------------------Start---The---Test-----------------------------");
			starttime = System.nanoTime();
			boolean result= levTest();
			finishtime = System.nanoTime();
			totalTimeTest = finishtime - starttime;
	    	System.out.println("---------Show the result of running method and compare it with expected result--------");
			System.out.println("Total Time:"+ totalTimeTest+ " nanoseconds");
			System.out.println("Is Accurate= " + (result?true:false));
			System.out.println("-----------------End--Test1-----------------------------");
		    starttimeModified = System.nanoTime();
			boolean resultModified= levTestModified();
			finishtimeModified = System.nanoTime();
			totalTimeTestModified = starttimeModified - finishtimeModified;
	     	System.out.println("---------Show the result of running 'Modified' method and compare it with expected result--------");
			System.out.println("Total Time:"+ totalTimeTestModified+ " nanoseconds");
			System.out.println("Is Accurate = " + (resultModified?true:false));
			System.out.println("----------------End--Test2------------------------------");

		}

	public static boolean levTest() {
		Levenshtein    levtest1;
		String [] Inputa = {"Haus", "Haus", "Haus", "Kartoffelsalat"};
		String [] Inputb = {"Maus", "Mausi", "Häuser", "Runkelrüben"};
		int [] ExpectedResult = {1,2,3,12};
		int [] Actualresults = new int[4];
		for(int i = 0; i < 4; i++)
			try{
			    levtest1 =new Levenshtein(Inputa[i], Inputb[i]);
			    Actualresults[i]=levtest1.ShowDist();
			    System.out.println("--------");
			    System.out.println(Inputa[i]+','+Inputb[i]+"=>"+ Actualresults[i]+"|"+"ExpectedResult="+ ExpectedResult[i]);
			    System.out.println("--------");
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
	
		return Arrays.equals(ExpectedResult, Actualresults);
		
	}
	public static boolean levTestModified() {
		Levenshtein    levtest2;
		String [] Inputa = {"Haus", "Haus", "Haus", "Kartoffelsalat"};
		String [] Inputb = {"Maus", "Mausi", "Häuser", "Runkelrüben"};
		int DistIn = 2;
		int [] ExpectedResultModified ={1,2,3,3};
		int [] ActualresultsModified = new int[4];
		for(int i = 0; i < 4; i++)
			try{
				levtest2 =new Levenshtein(Inputa[i].toString(), Inputb[i].toString());
			    ActualresultsModified[i]=levtest2.ShowDist(DistIn);
			    System.out.println("---------");
			    System.out.println(Inputa[i]+','+Inputb[i]+','+DistIn+"=>"+ ActualresultsModified[i]+"|"+"ExpectedResultModified="+ExpectedResultModified[i]);
			    System.out.println("---------");
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
		return Arrays.equals(ExpectedResultModified, ActualresultsModified);
	}
	}



