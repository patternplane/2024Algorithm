package _2_1_Sorting;

public class RunPractice {
	public static void Run() {
		Integer[] testA = new Integer[] {179,208,306,93,859,984,55,9,271,33}; // {5,1,6,7,2,9,5,2,3,5,1}
		MergeTD.sort(testA);
		for (int item : testA)
			System.out.print(item + " ");
		System.out.println();
	}
}
