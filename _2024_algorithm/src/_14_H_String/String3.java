//결과 제출시 : 삭제
package _14_H_String;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Solution
public class String3 {
	public int solution(String dartResult) {
		char[] dr = dartResult.toCharArray();

		int score = 0;
		int lastScore = 0;
		int totalScore = 0;

		for (int start = 0, end; start < dr.length;) {
			end = start;

			while (Character.isDigit(dr[end]))
				end++;
			score = charAtoInt(dr, start, end);

			if (dr[end] == 'D')
				score *= score;
			else if (dr[end] == 'T')
				score *= score * score;
			end++;

			if (end < dr.length && !Character.isDigit(dr[end])) {
				if (dr[end] == '*') {
					score *= 2;
					totalScore += lastScore;
				} 
				else if (dr[end] == '#')
					score = -score;
				end++;
			}

			start = end;
			totalScore += score;
			lastScore = score;
		}

		return totalScore;
	}

	int charAtoInt(char[] array, int s, int e) {
		int result = 0;
		for (; s < e; s++) {
			result *= 10;
			result += array[s] - '0';
		}
		return result;
	}
}
