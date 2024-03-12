package _2_1_Sorting;

public class MergeTD extends MergeBase {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		T[] aux = (T[]) new Comparable[a.length];
		sort(a,aux,0,a.length);
	}
	
	private static <T extends Comparable<? super T>> void sort(T[] a, T[] aux, int low, int hi) {
		if(hi - low <= 1)
			return;
		
		int mid = (low+hi)/2;
		sort(a,aux,low,mid);
		sort(a,aux,mid,hi);
		merge(a,aux,low,mid,hi); // Halfmerge(a,aux,low,mid,hi);
	}
}
