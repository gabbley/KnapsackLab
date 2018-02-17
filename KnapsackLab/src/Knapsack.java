
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack {
	
	private String outputFile = "output.txt";

	public static int knapsackSum(int[] w, int n, int limit){
		int optimalSum = 0;
		int useSum = 0;
		int dontUseSum = 0;
		
		if (n<0)
			optimalSum = 0;
		else if (w[n] < limit)
				useSum += knapsackSum(w, n-1, limit-w[n]) + w[n];
				dontUseSum += knapsackSum(w, n-1, limit);
				if (useSum>dontUseSum)
					optimalSum += useSum;
				else
					optimalSum += dontUseSum;
		
		return optimalSum;
	}
	
	public static int knapsackSum(int[] w, int n, int limit, ArrayList<Integer> list){
		int optimalSum = 0;
		int useSum = 0;
		int dontUseSum = 0;
		ArrayList<Integer> listUse = new ArrayList<Integer>();
		ArrayList<Integer> listDontUse = new ArrayList<Integer>();
		
		if (n<0)
			optimalSum = 0;
		else if (w[n] < limit)
				listUse.add(w[n]);
				useSum += knapsackSum(w, n-1, limit-w[n], listUse) + w[n];
				listUse.add(w[n-1]);
				dontUseSum += knapsackSum(w, n-1, limit, listDontUse);
				if (useSum>dontUseSum)
					optimalSum += useSum;
				else
					optimalSum += dontUseSum;
				
		
		
		return optimalSum;
	}
	
	public static Scanner openFile(String filename) {

		File f = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		return input;
	}
//
//	public static PrintWriter canBeOpened(String filename, int part) {
//
//		File f = new File(filename);
//		PrintWriter output = null;
//		try {
//			output = new PrintWriter(f);
//		} catch (FileNotFoundException e) {
//			PrintWriter out = new PrintWriter(outputFile);
//			out.println("Part" + part + ": Unable to Open File");
//			return null;
//		}
//		return output;
//	}
	
	public static void main (String[] args){
		
	}
	
}
