package _1_2_Sorting;

public class Insertion extends AbstractSort {
	
	public static <T extends Comparable<? super T>> void sort(T[] a, int low, int hi) {
		for(int i = low; i < hi; i++) {
			for (int j = i; j > low; j--)
				if (less(a[j], a[j-1])) // less가 아니면 for문을 탈출하는 것이 낭비를 줄임
					exch(a,j,j-1);
		}
	}
	
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		sort(a,0,a.length);
	}
}
