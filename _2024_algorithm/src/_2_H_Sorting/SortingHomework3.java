// 결과 제출시 : 삭제
package _2_H_Sorting;

// 결과 제출시 : 학번 이름 기재하기

// 결과 제출시 : 클래스 명은 Main
//            RadixSorter2가 아닌 RadixSorter로
public class SortingHomework3 {
	public static void main(String[] args) throws Exception {
		int N;
		int[] xValues;
		int[] indexes;
		final int RadixSortValueOffset = -1000000000;
		
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		xValues = new int[N];
		indexes = new int[N];
		int idx = 0;
		for (String item : br.readLine().split(" ")) {
			xValues[idx] = Integer.parseInt(item) - RadixSortValueOffset;
			indexes[idx] = idx++;
		}
		
		RadixSorter2.sortIndex(indexes, xValues);
		
		int[] lessItemCnt = new int[xValues.length];
		lessItemCnt[0] = 0;
		for (int rank = 1; rank < N; rank++) {
			if (xValues[indexes[rank-1]] != xValues[indexes[rank]]) 
				lessItemCnt[rank] = lessItemCnt[rank-1] + 1;
			else
				lessItemCnt[rank] = lessItemCnt[rank-1];
		}
		
		for (int rank = 0; rank < N; rank++)
			xValues[indexes[rank]] = lessItemCnt[rank];
			
		StringBuilder resultStr = new StringBuilder();
		for (int i = 0; i < N; i++)
			resultStr.append(xValues[i]).append(' ');
		
		System.out.print(resultStr.toString());
	}
}

class RadixSorter2{
	public static void sortIndex(int[] idxArray, int[] dataArray) {
		int exp = 1;
		int[] idxs = idxArray;
		int[] aux = new int[idxs.length];
		int[] c = new int[10];
		int m = dataArray[idxArray[0]];
		for (int idx : idxArray)
			if (m < dataArray[idx])
				m = dataArray[idx];
		
		int[] temp;
		while (m/exp != 0 && exp > 0) {
			for (int i = 0; i < c.length; i++)
				c[i] = 0;
			for (int i = 0; i < idxs.length; i++)
				c[dataArray[idxs[i]]/exp%10]++;
			for (int i = 1; i < c.length; i++)
				c[i] += c[i-1];
			for (int i = idxs.length - 1; i >= 0; i--)
				aux[--c[dataArray[idxs[i]]/exp%10]] = idxs[i];
			
			temp = idxs;
			idxs = aux;
			aux = temp;
			exp *= 10;
		}
		
		if (idxs != idxArray)
			for (int i = 0;i < idxs.length; i++)
				idxArray[i] = idxs[i];
	}
}
