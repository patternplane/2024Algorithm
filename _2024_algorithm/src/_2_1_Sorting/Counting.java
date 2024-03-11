package _2_1_Sorting;

public class Counting {
	static public int[] sort(int[] a,int K) {
		int[] b = new int[a.length];
		int[] c = new int[K];
		int N = a.length;
		
		for (int i = 0; i < N; i++) c[a[i]]++;
		for (int i = 1; i < K; i++) c[i] += c[i-1];
		for (int i = N - 1; i >= 0; i--) b[--c[a[i]]] = a[i];
		
		return b;
	}
}
