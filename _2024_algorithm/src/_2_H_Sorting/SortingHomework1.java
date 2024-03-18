// 결과 제출시 : 삭제
package _2_H_Sorting;

// 결과 제출시 : 학번 이름 기재하기

// 결과 제출시 : 클래스 명은 Solution
public class SortingHomework1 {
	public int[] solution(int[] numbers) {
		
		int[] combinations = new int[(numbers.length*(numbers.length - 1))/2];
		for (int i = 0, k = 0; i < numbers.length - 1; i++)
			for (int j = i+1; j < numbers.length; j++)
				combinations[k++] = numbers[i] + numbers[j];
		RadixSorter.sort(combinations);
		
		int answerLength = 0;
		for (int i = 0, lastestN = -1; i < combinations.length; i++)
			if (combinations[i] != lastestN) {
				lastestN = combinations[i];
				combinations[answerLength++] = lastestN;
			}
		
		int[] answer = new int[answerLength];
		for (int i = 0; i < answerLength; i++)
			answer[i] = combinations[i];

     return answer;
 }
}

class RadixSorter{
	static public void sort(int[] origin) {
		int exp = 1;
		int[] a = origin;
		int[] aux = new int[a.length];
		int[] c = new int[10];
		int m = a[0];
		for (int item : a)
			if (m < item)
				m = item;
		
		int[] temp;
		while (m/exp != 0) {
			for (int i = 0; i < c.length; i++)
				c[i] = 0;
			for (int i = 0; i < a.length; i++)
				c[a[i]/exp%10]++;
			for (int i = 1; i < c.length; i++)
				c[i] += c[i-1];
			for (int i = a.length - 1; i >= 0; i--)
				aux[--c[a[i]/exp%10]] = a[i];
			
			temp = a;
			a = aux;
			aux = temp;
			exp *= 10;
		}
		
		if (a != origin)
			for (int i = 0;i < a.length; i++)
				origin[i] = a[i];
	}
}