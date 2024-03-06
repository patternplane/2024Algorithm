package _1_2_Sorting;

public class Insertion extends AbstractSort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		for(int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--)
				if (less(a[j], a[j-1])) // less가 아니면 for문을 탈출하는 것이 낭비를 줄임
					exch(a,j,j-1);
		}
	}
}
