package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
 * 문제) set을 활용하여 숫자 야구 게임 프로그램을 작성하시오. 
 *      컴퓨터의 숫자는 난수를 이용해서 구한다. 
 *      (스트라이크는 S, 볼은 B로 나타낸다.)
 * 예시)
 *      컴퓨터 난수 ==> 9 5 7
 *      
 * 실행예시)
 *      숫자입력 : 3 5 6 
 *      3 5 6 => 1S 0B
 *      숫자입력 : 7 8 9
 *      7 8 9 => 0S 2B
 *      숫자입력 : 9 7 5 
 *      9 7 5 => 1S 2B
 *      숫자입력 : 9 5 7 
 *      9 5 7 => 3S 0B
 *      
 *      축하합니다...
 *      당신은 4번째만에 맞췄습니다. 
 */

public class BaseballTest {

	public static void main(String[] args) {
        
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		while(a.size() < 3) {
			a.add((int)(Math.random() *(9-1+1) + 1));
		}
		System.out.println(a);
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Integer> num = new ArrayList<Integer>();
		for(int i=0; i<3; i++) {
			System.out.print("숫자 입력 : ");
			num.add(Integer.parseInt(sc.next())); 
		}
	    System.out.println(num);
	    
	     //
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i = 0; i < 3; i++) {
        	hs.add(a.get(i));
           boolean is = hs.add(num.get(0));
           if(is == false) {
        	   
           }
        }
        
        boolean is2 = hs.add(num.get(0));
        boolean is3 = hs.add(num.get(0));
         
	}

}
