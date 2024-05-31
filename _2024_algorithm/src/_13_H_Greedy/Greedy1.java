//결과 제출시 : 삭제
package _13_H_Greedy;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Solution
public class Greedy1 {
    public String solution(String number, int k) {
    	java.util.Stack<Integer> s = new java.util.Stack<Integer>();
       
        int mustDelete = k;
        for (char c : number.toCharArray()){
            int n = c - '0';
            while (mustDelete > 0 && !s.isEmpty() && s.peek() < n) {
                s.pop();
                mustDelete--;
            }
            s.push(n);
        }
        while (mustDelete-- > 0)
            s.pop();
       
        StringBuilder answer = new StringBuilder(number.length() - mustDelete);
        while (!s.isEmpty())
            answer.append(s.pop());
       
        return answer.reverse().toString();
    }
}
