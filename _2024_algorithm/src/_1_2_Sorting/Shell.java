package _1_2_Sorting;

public class Shell extends AbstractSort {
	public static <T> void sort(Comparable<T>[] a) {
		int h = 1;
		while (h < a.length/3)
			h = h*3 + 1;
		
		while (h > 0) {
			for (int i = h; i < a.length; i++)
				for (int j = i; j >= h; j -= h) 
					if (less(a[j], a[j-h])) // less가 아니면 for문을 탈출하는 것이 낭비를 줄임
						exch(a,j,j-h);
			h /= 3;
		}
	}
}
