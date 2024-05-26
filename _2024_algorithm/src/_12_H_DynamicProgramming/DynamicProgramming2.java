//결과 제출시 : 삭제
package _12_H_DynamicProgramming;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 HW2
public class DynamicProgramming2 {
	public static void main(String[] args) {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        String a = null;
        String b = null;
        try {
            a = br.readLine();
            b = br.readLine();
        } catch (Exception e) {
            System.out.println("Input Error");
        }
       
        int[][] maxlen = new int[a.length()+1][b.length()+1];
        boolean[][] fromLeft = new boolean[a.length()+1][b.length()+1];
        
        for (int aChar = 1; aChar <= a.length(); aChar++)
            for (int bChar = 1; bChar <= b.length(); bChar++) {
                if (a.charAt(aChar-1) == b.charAt(bChar-1))
                    maxlen[aChar][bChar] = 1 + maxlen[aChar-1][bChar-1];
                else if (maxlen[aChar-1][bChar] >= maxlen[aChar][bChar-1])
                    maxlen[aChar][bChar] = maxlen[aChar-1][bChar];
                else {
                    maxlen[aChar][bChar] = maxlen[aChar][bChar-1];
                    fromLeft[aChar][bChar] = true;
                }
            }

        StringBuilder sameStr = new StringBuilder();
        for (int AChar = a.length(), BChar = b.length(); AChar > 0 && BChar > 0;) {
            if (a.charAt(AChar-1) == b.charAt(BChar-1)) {
                sameStr.append(a.charAt(AChar-1));
                AChar--;
                BChar--;
            }
            else if (fromLeft[AChar][BChar])
                BChar--;
            else
                AChar--;
        }
        
        System.out.println(sameStr.reverse().toString());
        System.out.println(maxlen[a.length()][b.length()]);
    }
}
