//결과 제출시 : 삭제
package _7_H_Hashing;

import java.util.HashSet;

//결과 제출시 : 학번 이름 기재하기

//결과 제출시 : 클래스 명은 Solution 
public class HashingHomework1 {
	public boolean solution(String[] phone_book) {
    	HashSet<String> phoneSet = new HashSet<String>();
        for (String s : phone_book)
            phoneSet.add(s);
    	for (String s : phone_book)
    		for (int i = 1; i < s.length(); i++)
    			if (phoneSet.contains(s.substring(0, i)))
    				return false;
    	
        return true;
    }
}
