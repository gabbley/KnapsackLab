import java.awt.List;

public class Knapsack {

	public static int knapsackSum(int[] w, int n, int limit){
		int optimalSum = 0;
		
		if (limit == 0)
			optimalSum = 0;
		else
			while (limit > 0){
				optimalSum += w[n] + knapsackSum(w, n--, limit-1);
			}
		return optimalSum;
	}
	
	public static int knapsackSun(int[] w, int n, int limit, List<Integer> list){
		
	}
	
	public static void main (String[] args){
		
	}
	
}
