//결과 제출시 : 삭제
package _14_H_String;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Solution
public class String3 {
	public int solution(String dartResult) {

		ScoreData scoreData = new ScoreData();
		
		int newScore;
		char bonus;
		char option;
		
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("([0-9]*)([SDT])([*#])?");
		java.util.regex.Matcher matcher = pattern.matcher(dartResult);
		
		while (matcher.find()) {
			newScore = Integer.parseInt(matcher.group(1));
			bonus = matcher.group(2).charAt(0);
			option = (matcher.group(3) != null ? matcher.group(3).charAt(0) : '\0');
			
			scoreData.takeNextScore(newScore);
			scoreData.takeBonus(bonus);
			scoreData.takeOption(option);
			scoreData.applyCurrentScore();
		}

		return scoreData.totalScore;
	}
}

class ScoreData {
	public int score = 0;
	public int lastScore = 0;
	public int totalScore = 0;
	
	public void takeNextScore(int score) {
		this.score = score;
	}
	
	public void takeBonus(char bonus) {
		if (bonus == 'D')
			this.score *= this.score;
		else if (bonus == 'T')
			this.score *= this.score * this.score;
	}
	
	public void takeOption(char option) {
		if (option == '*') {
			this.score *= 2;
			this.totalScore += this.lastScore;
		}
		else if (option == '#')
			this.score = -this.score;
	}
	
	public void applyCurrentScore() {
		this.totalScore += this.score;
		this.lastScore = this.score;
	}
}
