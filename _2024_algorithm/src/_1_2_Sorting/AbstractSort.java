package _1_2_Sorting;

public abstract class AbstractSort {
	static public <T extends Comparable<? super T>> void sort(T[] a) {}
	
	static public <T extends Comparable<? super T>> boolean less(T a, T b) {
		return a.compareTo((T) b) < 0;
	}
	
	static public <T extends Comparable<? super T>> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	static public <T extends Comparable<? super T>> void show(T[] a) {
		for(T item : a)
			System.out.print(item + " ");
		System.out.println();
	}
	
	static public <T extends Comparable<? super T>> boolean isSorted(T[] a) {
		for (int i = 0; i < a.length - 1; i++)
			if (!less(a[i], a[i+1]))
				return false;
		return true;
	}
}
