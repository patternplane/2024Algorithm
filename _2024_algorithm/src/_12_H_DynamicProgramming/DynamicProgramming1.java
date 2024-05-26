//결과 제출시 : 삭제
package _12_H_DynamicProgramming;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Solution
public class DynamicProgramming1 {
	public int solution(int[][] triangle) {
        int max = 0;
        for (int floor = 1; floor < triangle.length; floor++)
            for (int room = 0; room < triangle[floor].length; room++){
                if (room == 0)
                    triangle[floor][room] += triangle[floor-1][room];
               else if (room == triangle[floor].length - 1)
                   triangle[floor][room] += triangle[floor-1][room-1];
                else
                    triangle[floor][room] += Math.max(triangle[floor-1][room-1], triangle[floor-1][room]);
               if (max < triangle[floor][room])
                   max = triangle[floor][room];
            }
       
        return max;
    }
}
