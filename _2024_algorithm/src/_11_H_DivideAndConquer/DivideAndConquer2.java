//결과 제출시 : 삭제
package _11_H_DivideAndConquer;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 HW2
public class DivideAndConquer2 {
	static int n, k;
	static int[] selection;
	static int selected;
	
	public static void main(String[] args) throws Throwable {
		
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		n = Integer.parseInt(inputs[0]);
		k = Integer.parseInt(inputs[1]);

		selected = 0;
		selection = new int[k + 1];
		makeCombination();
	}
	
	static void makeCombination() {
		if (k <= 0)
			return;
		
		k--;
		selected++;
		for (int i = selection[selected - 1] + 1; i <= n - k; i++) {
			selection[selected] = i;
			if (k > 0)
				makeCombination();
			else {
				System.out.print("[");
				for (int idx = 1; idx <= selected; idx++) {
					System.out.print(selection[idx]);
					if (idx != selection.length - 1)
						System.out.print(",");
				}
				System.out.print("] ");
			} 
		}
		k++;
		selected--;
	}
}
