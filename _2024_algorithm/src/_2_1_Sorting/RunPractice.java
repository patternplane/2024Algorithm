package _2_1_Sorting;

public class RunPractice {
	public static void Run() {
		int[] testA = new int[] {5,1,6,7,2,9,5,2,3,5,1};
		int[] result = Counting.sort(testA, 10);
		for (int item : result)
			System.out.print(item + " ");
		System.out.println();
	}
}
