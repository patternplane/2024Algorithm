package _2_1_Sorting;

public class MergeBU extends MergeBase {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		T[] temp;
		T[] arr = a;
		T[] aux = (T[]) new Comparable[a.length];
		int n = 1;
		while (n <= a.length) {
			for (int i = 0; i < a.length; i += 2*n)
				mergeWithoutCopy(aux,arr,i,i+n,Math.min(a.length, i+2*n));
			n *= 2;
			temp = arr;
			arr = aux;
			aux = temp;
		}
		if (a != arr)
			System.arraycopy(aux, 0, a, 0, aux.length);
	}
}
