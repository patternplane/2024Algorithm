//결과 제출시 : 삭제
package _11_H_DivideAndConquer;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Solution
public class DivideAndConquer1 {
    int[] answer;
    int[][] arr;
    
    public int[] solution(int[][] arr) {
        this.arr = arr;
        this.answer = new int[2];
        
        compression(0,0,arr.length);
        
        return answer;
    }
    
    void compression(int sRow, int sCol, int size) {
        int value = arr[sRow][sCol];
        
        if (size > 1)
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    if (value != arr[sRow+i][sCol+j]) {
                        compression(sRow, sCol, size/2);
                        compression(sRow+size/2, sCol, size/2);
                        compression(sRow, sCol+size/2, size/2);
                        compression(sRow+size/2, sCol+size/2, size/2);
                        return;
                    }
        
        answer[value]++;
    }
}
