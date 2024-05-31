//결과 제출시 : 삭제
package _13_H_Greedy;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Solution
 public class Greedy2 {
    public int solution(int[][] routes) {
        
        RadixSorter_routes(routes, 30000);
        
        int end = routes[0][1];
        int answer = 1;
        for (int i = 1; i < routes.length; i++) {
            if (end < routes[i][0]) {
                end = routes[i][1];
                answer++;
            }
            else if (end > routes[i][1])
                end = routes[i][1];
        }
        
        return answer;
    }
    
    private void RadixSorter_routes(int[][] routes, int maxValue) {
        int cPositionOffset = 9;
        int[] c = new int[19];
        
        int[][] temp;
        int[][] a = routes;
        int[][] aux = new int[routes.length][routes[0].length];
        
        int r = 1;
        
        while (maxValue/r != 0) {
            for (int i = 0; i < c.length; i++)
                c[i] = 0;
            for (int i = 0; i < a.length; i++)
                c[a[i][0]/r%10+cPositionOffset]++;
            for (int i = 1; i < c.length; i++)
                c[i] += c[i-1];
            for (int i = a.length - 1; i >= 0; i--)
                aux[--c[a[i][0]/r%10+cPositionOffset]] = a[i];
            
            temp = a;
            a = aux;
            aux = temp;
            r *= 10;
        }
        
        if (a != routes)
            for (int i = 0; i < a.length; i++)
                routes[i] = a[i];
    }
}
