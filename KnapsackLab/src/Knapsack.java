
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Knapsack</h1>Executes the modified Knapsack problem.
 * 
 * @author Gabby Baniqued
 */

public class Knapsack {

	//field for output file
	private static String outputFile = "knapsack.txt";

	/**
	 * Executes simplified Knapsack problem.
	 * 
	 * @param w 
	 * 	array of weights
	 * @param n
	 * 	index to traverse array w
	 * @param limit
	 * 	max capacity of knapsack
	 * 
	 * @return optimal summed capacity of bag
	 */
	public static int knapsackSum(int[] w, int n, int limit) {
		int optimalSum = 0;
		int useSum = 0;
		int dontUseSum = 0;

		if (n < 0)
			return 0;

		if (w[n] <= limit)
			useSum += knapsackSum(w, n - 1, limit - w[n]) + w[n];
		dontUseSum += knapsackSum(w, n - 1, limit); 
		if (useSum > dontUseSum)
			optimalSum += useSum;
		else
			optimalSum += dontUseSum;

		return optimalSum;
	}

	/**
	 * Executes Knapsack problem while keeping track of weights.
	 * 
	 * @param w 
	 * 	array of weights
	 * @param n
	 * 	index to traverse array w
	 * @param limit
	 * 	max capacity of knapsack
	 * @param list
	 * 	list of item weights added to knapsack
	 * 
	 * @return optimalSum
	 * 	return highest capacity of knapsack with given weights
	 */
	public static int knapsackSum(int[] w, int n, int limit, ArrayList<Integer> list) {
		int optimalSum = 0;
		int useSum = 0;
		int dontUseSum = 0;
		ArrayList<Integer> listUse = new ArrayList<Integer>();
		ArrayList<Integer> listDontUse = new ArrayList<Integer>();

		if (n < 0)
			return 0;

		if (w[n] <= limit){
			listUse.add(w[n]);
			useSum += knapsackSum(w, n - 1, limit - w[n], listUse) + w[n];
		}
		else
			useSum += 0;

		dontUseSum += knapsackSum(w, n - 1, limit, listDontUse);

		if (useSum > dontUseSum) {
			list.addAll(listUse);
			optimalSum += useSum;
		} else {
			list.addAll(listDontUse);
			optimalSum += dontUseSum;
		}

		return optimalSum;

	}

	/**
	 * Opens file for reading
	 * 
	 * @param filename
	 * 	name of file
	 * 
	 * @return Scanner
	 * 	file to be read as Scanner Object
	 */
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

	/**
	 * Allows writing to file
	 * 
	 * @param filename
	 * 	name of file
	 * 
	 * @throws FileNotFoundException
	 * 	if file not found
	 * 
	 * @return PrintWriter
	 * 	Object able to write to given file
	 */
	public static PrintWriter writeToFile(String filename) throws FileNotFoundException {

		File f = new File(filename);
		PrintWriter output = null;
		try {
			output = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			PrintWriter out = new PrintWriter(outputFile);
			out.println("Unable to Open File");
			return null;
		}
		return output;
	}

	/**
	 * Opens all files and organizes weights prior to sending through knapsackSum methods
	 * 
	 * @throws FileNotFoundException
	 * 	if file not found
	 * 
	 * @param fileOfFiles
	 * 	Scanner that consists of other files
	 */
	public static void organizeFile(Scanner fileOfFiles) throws FileNotFoundException {
		ArrayList<Integer> w;
		int limit = 0;
		PrintWriter out;

		try {
			out = writeToFile(outputFile);
		} catch (FileNotFoundException e) {
			out = new PrintWriter(new File(outputFile));
			e.printStackTrace();
		}
		
		out.print("test");

		while (fileOfFiles.hasNext()) { // prints name of file
			w = new ArrayList<Integer>();
			String filename = fileOfFiles.nextLine();
			System.out.print(filename + " ");
			Scanner file = openFile(filename); // opens file from file

			if (file.hasNextInt()) {
				limit = file.nextInt();
				System.out.print(" limit: " + limit + "    ");
			}

			while (file.hasNextInt()) {
				int weight = file.nextInt();
				w.add(weight);
				System.out.print(weight + ", ");
			}

			printKnapsack(listToArr(w), limit, out);
			System.out.println("\n");

		}

	}

	/**
	 * Sends weights through knapsackSum methods and prints out optimal sums
	 * and array of used weights
	 * 
	 * @param w
	 * 	array of weights
	 * 
	 * @param limit
	 * 	capacity of knapsack
	 * 
	 * @param out
	 * 	Object able to write to given file
	 */
	public static void printKnapsack(int[] w, int limit, PrintWriter out) {
		System.out.print("\nSimplified: ");
		System.out.print("\nOptimal Sum: " + knapsackSum(w, w.length - 1, limit)+"\n");
		
		System.out.print("\nNot the Simplified: ");
		ArrayList<Integer> weights = new ArrayList<Integer>();
		System.out.print("\nOptimal Sum: " + knapsackSum(w, w.length - 1, limit, weights));
		System.out.println("\n"+ printWeights(weights));
	}

	/**
	 * Adds elements of an ArrayList of Integer to an array of int
	 * 
	 * @param arr
	 * 	ArrayList of Integer objects
	 * 
	 * @return int[]
	 * 	corresponding array with elements added
	 */
	public static int[] listToArr(ArrayList<Integer> arr) {
		int[] w = new int[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			w[i] = arr.get(i);
		}
		return w;
	}
	
	/**
	 * Prints weights of object
	 * 
	 * @param weights
	 * 	ArrayList of weights
	 * 
	 * @return String
	 * 	weights and objects to print
	 */
	public static String printWeights(ArrayList<Integer> weights){
		String s = "";
		for (int i = 0; i<weights.size(); i++){
			s += "1 watermelon of weight " + weights.get(i) + "\n";
		}
		return s;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1) {
			System.out.println("Not enough files provided");
			System.exit(1);
		}

		Scanner in = openFile(args[0]);
		if (in == null)
			System.exit(1);
		organizeFile(in);
	}


}
