//결과 제출시 : 삭제
package _14_H_String;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Main
public class String1 {
	public static void main(String[] args) {

		int n;
		String[] array;

		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		try {
			n = Integer.parseInt(br.readLine());
			array = new String[n];
			for (int i = 0; i < n; i++)
				array[i] = br.readLine();
		} catch (Exception e) {
			System.out.println("Input Error");
			return;
		}

		radix_String(array);

		StringBuilder str = new StringBuilder();
		String lastStr = null;
		for (String s : array)
			if (lastStr == null || lastStr.compareTo(s) != 0) {
				str.append(s).append('\n');
				lastStr = s;
			}

		System.out.print(str);
	}

	static void radix_String(String[] origin) {
		String[] a = origin;
		String[] aux = new String[origin.length];
		String[] temp;
		int[] c = new int[1 + 26];
		int idx = 0;
		int maxLen = 0;
		for (String s : origin)
			if (maxLen < s.length())
				maxLen = s.length();

		while (idx < maxLen) {
			for (int i = 0; i < c.length; i++)
				c[i] = 0;
			for (String s : a) {
				if (idx < s.length())
					c[s.charAt(s.length() - 1 - idx) - 'a' + 1]++;
				else
					c[0]++;
			}
			for (int i = 1; i < c.length; i++)
				c[i] += c[i - 1];
			for (int i = a.length - 1; i >= 0; i--) {
				if (idx < a[i].length())
					aux[--c[a[i].charAt(a[i].length() - 1 - idx) - 'a' + 1]] = a[i];
				else
					aux[--c[0]] = a[i];
			}

			temp = a;
			a = aux;
			aux = temp;
			idx++;
		}

		if (a != origin)
			for (int i = 0; i < a.length; i++)
				origin[i] = a[i];
	}
}
