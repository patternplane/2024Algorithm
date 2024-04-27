//결과 제출시 : 삭제
package _7_H_Hashing;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Main 
public class HashingHomework2 {
	static public void main(String[] args) throws Throwable {
		String str = getInput();
		int strLen = str.length();
		java.util.HashSet<String> subStrSet = new java.util.HashSet<String>();
		
		int answer = 0;
		String subStr;
		for (int i = 0; i < strLen; i++)
			for (int j = i + 1; j <= strLen; j++) {
				subStr = str.substring(i,j);
				if (!subStrSet.contains(subStr)) {
					subStrSet.add(subStr);
					answer++;
				}
			}
		
		System.out.println(answer);
	}
	
	static String getInput() throws Throwable {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		return br.readLine();
	}
}
