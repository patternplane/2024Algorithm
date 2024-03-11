package _2_1_Sorting;

public class RunPractice {
	public static void Run() {
		int[] testA = new int[] {179,208,306,93,859,984,55,9,271,33}; // {5,1,6,7,2,9,5,2,3,5,1}
		int[] result = Radix.sort(testA);
		for (int item : result)
			System.out.print(item + " ");
		System.out.println();
	}
}
