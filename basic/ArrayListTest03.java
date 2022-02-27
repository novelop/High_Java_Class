package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest03 {
	public static void main(String[] args) {
		/*
		 * 문제1) 5명 별명을 입력받아 ArrayList에 저장하고 이 별명 중에 제일 긴 별명을 출력하시오. (단, 별명의 길이는 모두 다르게 입력한다.)
		 * 
		 * 문제2) 5명 별명을 입력받아 ArrayList에 저장하고 이 별명 중에 제일 긴 별명을 출력하시오. (단, 별명의 길이는 같아도 됨.)
		 */
		Scanner sc = new Scanner(System.in);
		ArrayList<String> name = new ArrayList<String>();

		for (int i = 0; i < 5; i++) {
			System.out.println(i + 1 + "번째 별명 입력>");
			String input = sc.nextLine(); //nextLine() 띄어쓰기 가능 
			name.add(input);
		}
		
		//제일 긴 별명이 저장될 변수 선언
		//   ==> List의 첫번째 자료로 초기화 한다. 
		String maxAlias = name.get(0);
		
		for(int i = 1; i < name.size(); i++) {
			if(maxAlias.length() < name.get(i).length()) {
				maxAlias = name.get(i);
			}
		}
		
		System.out.println("제일 긴 별명 : " + maxAlias);
		
		
		
		
		
		
//		int max = 0;
//		for (String str : name) {
//			if (str.length() > max) {
//				max = 0;
//				for (int i = 0; i < str.length(); i++) {
//					max += 1;
//				}
//			}
//		}
//		System.out.println("별명 중에 제일 긴 별명");
//		for (String str : name) {
//			if (str.length() == max) {
//				System.out.println(str);
//			}
//		}
//		
		

	}
}
