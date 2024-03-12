package _2_1_Sorting;

public class MergeBase extends _1_2_Sorting.AbstractSort {
	protected static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int low, int mid, int hi) {
		for (int i = low; i < hi; i++)
			aux[i] = a[i];
		
		int k = low;
		int i = low;
		int j = mid;
		
		while (k < hi) {
			if (i >= mid) a[k++] = aux[j++];
			else if (j >= hi) a[k++] = aux[i++];
			else if (less(aux[j],aux[i])) a[k++] = aux[j++];
			else a[k++] = aux[i++];
		}
	}
	
	protected static <T extends Comparable<? super T>> void Halfmerge(T[] a, T[] aux, int low, int mid, int hi) {
		for (int i = low; i < mid; i++)
			aux[i] = a[i];
		
		int k = low;
		int i = low;
		int j = mid;
		
		while (k < hi) {
			if (i >= mid) a[k++] = a[j++];
			else if (j >= hi) a[k++] = aux[i++];
			else if (less(a[j],aux[i])) a[k++] = a[j++];
			else a[k++] = aux[i++];
		}
	}
}
