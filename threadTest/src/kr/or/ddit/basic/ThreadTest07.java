package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 *  컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 *  컴퓨터의 가위 바위 보는 난수를 이용해서 구하고, 
 *  사용자의 가위 바위 보는 showInputDialog()를 이용해서 입력 받는다.
 *  
 *  입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 *  5초 안에 입력이 없으면 게임에 진것으로 처리한다.
 *  
 *  5초 안에 입력이 있으면 승패를 구해서 출력한다. 
 *  
 *  결과 예시)
 *  1) 5초안에 입력이 없을 때 
 *   - 결 과 -
 *  시간 초과로 당신이 졌습니다. 
 *  
 *  2) 5초안에 입력이 있을 때 
 *   - 결 과 - 
 *  컴퓨터 : 가위 
 *  당 신 : 바위 
 *  결 과 : 당신이 이겼습니다. 
 */
public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new InputGame();
		Thread th2 = new Count();
		th1.start();
		th2.start();
	}
}

class InputGame extends Thread {
	public static boolean inputCheck = false;

	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("'가위','바위', '보' 중에 입력하세요.");
		inputCheck = true;
		int num = (int)(Math.random() * 3 + 1);
		String com = null;
		switch(num) {
		case 1: com = "가위"; break;
		case 2: com = "바위"; break;
		case 3: com = "보"; break;
		}
		System.out.println("- 결과 -");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당 신 : " + str);
		System.out.print("결 과 : ");
		
		if (str.equals("가위") || str.equals("바위") || str.equals("보")) {
			if (com.equals(str)) {
				System.out.print("비겼습니다.");
			} else if (com.equals("가위") && str.equals("보") || com.equals("바위") && str.equals("가위")
					|| com.equals("보") && str.equals("바위")) {
				System.out.println("당신이 졌습니다.");
			} else {
				System.out.println("당신이 이겼습니다.");
			}
		}else {
			System.out.println("잘못입력했습니다.");
			return;
		}
		
		
	}
}

class Count extends Thread {
	@Override
	public void run() {
		for(int i=5 ; i>=1; i--) {
			if(InputGame.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("- 결 과 -");
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}
