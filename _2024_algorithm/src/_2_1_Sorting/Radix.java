package _2_1_Sorting;

public class Radix {
	public static int[] sort(int[] a) {
		int M = a[0];
		for (int item : a)
			if (M < item)
				M = item;
		int[] c = new int[10];
		int[] b = new int[a.length];
		int exp = 1;
		
		while (M/exp > 0) {
			for (int i = 0; i < 10; i++) c[i] = 0;
			for (int i = 0; i < a.length; i++) c[a[i]/exp%10]++;
			for (int i = 1; i < 10; i++) c[i] += c[i-1];
			for (int i = a.length-1; i >= 0; i--) b[--c[a[i]/exp%10]] = a[i];
			for (int i = 0; i < a.length; i++) a[i] = b[i];
			exp *= 10;
		}
		
		return b;
	}
}
