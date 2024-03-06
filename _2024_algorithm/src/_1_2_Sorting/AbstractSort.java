package _1_2_Sorting;

public abstract class AbstractSort {
	static public <T> void sort(Comparable<T>[] a) {}
	
	static public <T> boolean less(Comparable<T> a, Comparable<T> b) {
		return a.compareTo((T) b) < 0;
	}
	
	static public <T> void exch(Comparable<T>[] a, int i, int j) {
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	static public <T> void show(Comparable<T>[] a) {
		for(Comparable<T> item : a)
			System.out.print(item + " ");
		System.out.println();
	}
	
	static public <T> boolean isSorted(Comparable<T>[] a) {
		for (int i = 0; i < a.length - 1; i++)
			if (!less(a[i], a[i+1]))
				return false;
		return true;
	}
}
