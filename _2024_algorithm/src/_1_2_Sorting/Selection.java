package _1_2_Sorting;

public class Selection extends AbstractSort {
	public static <T> void sort(Comparable<T>[] a) {
		for (int i = 0; i < a.length -1 ; i++) {
			int minIdx = i;
			for (int j = i+1; j < a.length; j++) {
				if (less(a[j], a[minIdx]))
					minIdx = j;
			}
			exch(a,i,minIdx);
		}
	}
}
