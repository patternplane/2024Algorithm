package _1_2_Sorting;

import java.util.Arrays;

public class MainClass {

	public static void main(String[] args) {
		Integer[] test = new Integer[] {1,6,3,2,7,5,4,9,8,12,11,10};
		
		Selection sort1 = new Selection();
		Insertion sort2 = new Insertion();
		Shell sort3 = new Shell();
		
		Integer[] copy;
		copy = Arrays.copyOf(test, test.length);
		sort1.sort(copy);
		sort1.show(copy);
		
		copy = Arrays.copyOf(test, test.length);
		sort2.sort(copy);
		sort2.show(copy);
		
		copy = Arrays.copyOf(test, test.length);
		sort3.sort(copy);
		sort3.show(copy);
	}

}
