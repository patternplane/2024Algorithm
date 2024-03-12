package _2_1_Sorting;

public class MergeTD extends MergeBase {
	static final int CUTOFF = 7;
	
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		T[] aux = (T[]) new Comparable[a.length];
		for (int i = 0; i < a.length; i++)
			aux[i] = a[i]; 
		sort(a,aux,0,a.length);
	}
	
	private static <T extends Comparable<? super T>> void sort(T[] a, T[] aux, int low, int hi) {
		// 성능개선 (1/3) : 정렬크기가 충분히 줄어들면 재귀보다는 직접 정렬
		if(hi - low <= CUTOFF) {
			_1_2_Sorting.Insertion.sort(a,low,hi);
			return;
		}
		
		int mid = (low+hi)/2;
		// 성능개선 (3/3)
		sort(aux,a,low,mid);
		sort(aux,a,mid,hi);
		
		// 성능개선 (2/3) : 이미 병합이 된 것과 동일하면 병합 생략
		/*
		 * if (a[mid-1].compareTo(a[mid]) <= 0) return;
		 */
		
		// 성능개선 (3/3) : aux와 a 배열의 역할을 교대함으로써 aux에 a를 복사하는 오버헤드 제거
		mergeWithoutCopy(a,aux,low,mid,hi); // merge(a,aux,low,mid,hi); mergeWithoutCopy
		
		// 기타 개선 : 공간복잡도를 절반만 사용하여 병합도 가능. Halfmerge(a,aux,low,mid,hi);
	}
}
