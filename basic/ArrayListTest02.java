package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest02 {

	/*
	 * 문제 5) 5명의 사람이름을 입력받아 ArrayList에 저장한 후 
	 * 이들 중 '김'씨 성의 이름을 모두 출력하시오.
	 * (입력은 Scanner 객체를 이용한다.)
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> name = new ArrayList<String>();
		
		for(int i = 0; i < 5; i++ ) {
			System.out.println(i + 1 + "번째 사람이름 입력>");
			String input = sc.next();
			 name.add(input);
		}
		System.out.println("김씨 성을 가진 사람들...");
		
		
		for(int i = 0; i < name.size(); i++) { 
			//1번째 방법 
			if(name.get(i).substring(0, 1).equals("김")) {//subString(0,1) 0이상~1미만 번째의 문자열
				System.out.println(name.get(i));
			}
			//2번째 방법 
			if(name.get(i).charAt(0)=='김') {// charAt 캐릭터형으로 반환 
				System.out.println(name.get(i));
			}
			//3번째 방법
			if(name.get(i).indexOf("김")==0) { //김의 위치가 첫번째 인 값  
				System.out.println(name.get(i));
			}
			if(name.get(i).startsWith("김")==true) {//  김으로 시작하는 값이면 true , true 생략 가능 
				System.out.println(name.get(i));
			}
			
		}
		
	}

}
