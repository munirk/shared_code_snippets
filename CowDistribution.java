// Problem:
// A farmer has 25 cows numbered from 1 to 25 such that cow-1 gives 1 gallon of
// milk,  cow 2- gives 2-gallon milk and the so on up to cow 25 gives
// 25 gallons of milk. These cows are to be distribute equally among 5 sons,
// such that the milk from the shared cows should be to equal quantity for each
// son.


// Author: Munir Khan <munir.z.khan@gmail.com>
// Note:
// - number of cows is the square of number of sons
// - the code below can generate multiple solutions
// - number of solutions can be specified
// - maximum number of solution is the factorial of number of sons

// Sample output for numOfDesiredSolutions = 2
//	1	2	3	4	5	
//	10	6	7	8	9	
//	14	15	11	12	13	
//	18	19	20	16	17	
//	22	23	24	25	21	
//	=============
//
//	1	2	3	4	5	
//	10	6	7	8	9	
//	14	15	11	12	13	
//	17	18	19	20	16	
//	23	24	25	21	22	
//	=============


public class CowDistribution {
	private static final int SONS = 5;

	public void nextPermutation(int[] nums) {
		int firstSmaller;
		boolean smallerFound = false;
		for (firstSmaller = nums.length - 1; !smallerFound && firstSmaller >= 0; firstSmaller--) {
			if (firstSmaller > 0 && nums[firstSmaller - 1] < nums[firstSmaller]) {
				smallerFound = true;
			}
		}

		if (smallerFound) {
			int nextBigger;
			for (nextBigger = nums.length - 1; nums[nextBigger] <= nums[firstSmaller]; nextBigger--)
				;

			int temp = nums[nextBigger];
			nums[nextBigger] = nums[firstSmaller];
			nums[firstSmaller] = temp;
		}

		int reversibleCount = nums.length - firstSmaller - 1;
		int end = nums.length - 1;
		for (int i = 0; i < reversibleCount / 2; i++) {
			int temp = nums[firstSmaller + i + 1];
			nums[firstSmaller + i + 1] = nums[end - i];
			nums[end - i] = temp;
		}
	}

	public int[][] getSolution(int numSons, int[] rotations) {
		if (rotations == null || rotations.length != numSons)
			return null;

		int[][] result = new int[numSons][numSons];

		for (int i = 0; i < numSons; i++) {
			int shift = rotations[i];
			for (int j = 0; j < numSons; j++) {
				result[i][(j + shift) % numSons] = i * numSons + j + 1;
			}
		}
		return result;
	}

	public static void printResult(int[][] solution) {
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[i].length; j++) {
				System.out.print(solution[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		CowDistribution sol = new CowDistribution();

		int[] rotations = new int[SONS];
		for (int i = 0; i < rotations.length; i++) {
			rotations[i] = i;
		}

		int numOfDesiredSolutions = 2;

		for (int i = 0; i < numOfDesiredSolutions; i++) {
			int[][] solution = sol.getSolution(rotations.length, rotations);
			printResult(solution);
			System.out.println("=============\n");
			sol.nextPermutation(rotations);
		}
	}
}
