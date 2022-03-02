package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {
	HashSet<Integer> lottoSet = new HashSet<Integer>();

	Scanner sc = new Scanner(System.in);

	void start() {
		System.out.println("==========================");
		System.out.println("       Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("  1. Lotto 구입");
		System.out.println("  2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 : ");

		int input = Integer.parseInt(sc.nextLine());
		System.out.println();

		switch (input) {
		case 1:
			buy_lotto();
		case 2:
			System.out.println("감사합니다.");
			System.exit(0);
		}
	}
	
	void lotto_random() {
		while(lottoSet.size()<6) {
			int random = (int) (Math.random() * 45 + 1);
			lottoSet.add(random);
		}
	}

	void buy_lotto() {
		
		System.out.println(" Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int money = Integer.parseInt(sc.nextLine());
		System.out.println();

		if (money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
		} else if (money > 100000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
		} else {
			System.out.println("행운의 로또번호는 아래와 같습니다.");
			int lottoNum = money / 1000;
			int coin = money - (lottoNum * 1000);
			for (int i = 0; i < lottoNum; i++) {
				lotto_random();
				ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
				Collections.sort(lottoList); 
				System.out.println("로또번호" + (i+1) + " : " + lottoList);
				lottoSet.clear();
			}
			System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + coin + "원입니다.");
		}
		System.out.println();
		start();
	}

	public static void main(String[] args) {

		new Lotto().start();

	}
}