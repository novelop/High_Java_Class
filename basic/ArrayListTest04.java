package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest04 {

	public static void main(String[] args) {
		//5명 별명을 입력받아 ArrayList에 저장하고 이 별명 중에 제일 긴 별명을 출력하시오. (단, 별명의 길이는 같아도 됨.)
		Scanner sc = new Scanner(System.in);
		ArrayList<String> aliasList = new ArrayList<String>();

		for (int i = 0; i < 5; i++) {
			System.out.println(i + 1 + "번째 별명 입력>");
			String input = sc.nextLine(); //nextLine() 띄어쓰기 가능 
			aliasList.add(input);
		}
		
		//제일 긴 별명의 길이가 저장될 변수 선언 
		//          ==> 첫번째 별명의 길이로 초기화한다.
		int maxLength = aliasList.get(0).length();
		for(int i = 0 ; i < aliasList.size(); i++) {
			if(maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들...");
		for(String alias : aliasList) {
			if(maxLength == alias.length()) {
				System.out.println(alias);				
			}
		}
		

	}

}
