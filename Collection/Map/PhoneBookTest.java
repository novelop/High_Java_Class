package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {
	
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;
	
	// 생성자
	public PhoneBookTest() {
		phoneBookMap = new HashMap<String, Phone>();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneStart();
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneStart() {
		System.out.println();
		System.out.println("**********************************");
		System.out.println("        전화번호 관리 프로그램");
		System.out.println("**********************************");
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 :
				insert(); break;
			case 2 :
				update(); break;
			case 3 :
				delete(); break;
			case 4 :
				search(); break;
			case 5 :
				displayAll(); break;
			case 0 :
				System.out.println("프로그램을 종료합니다."); 
				return;
			default :
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 선택하세요.");
			}
		}
		
	}
	

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("----------------------------------");
		System.out.print("번호 입력 >> ");
		int num = scan.nextInt();
		
		return num;
	}

	// 새로운 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 여부를 검사한다.
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return; // return을 안쓰려면 else를 써야함
		}
		
	    System.out.print("전화번호 >> ");
	    String tel = scan.next();
	    
	    scan.nextLine(); // 입력 버퍼 비우기
	    System.out.print("주소 >> ");
//	    String addr = scan.next();
	    String addr = scan.nextLine();
	    
	    // Scanner객체의 입력 메서드의 특징
	    // next(), nextInt(), nextDouble().... 등
	    // ==> 사이띄기, tab키, enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
	    // nextLine()
	    // ==> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 enter키를 누르면 enter키까지 읽어간다.
	    // Scanner는 입력한 값이 입력버퍼에 먼저 저장된 후에 차례로 꺼내와서 처리된다.
	    
	    phoneBookMap.put(name, new Phone(name, tel, addr));
	    
//	    Phone p = new Phone(name, addr, tel);
//	    PhoneBookMap.put(name, p);
	    
	    System.out.println(name + "씨 전화번호 등록 완료!!!");
		
	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		// 입력한 사람의 이름이 전화번호 정보에 없으면...
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		System.out.print("새로운 전화번호 입력 >> ");
		String newTel = scan.next();
		
		System.out.print("새로운 주소 >> ");
		String newAddr = scan.next();
		
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		System.out.println(name + "씨의 전화번호 정보를 수정완료!!");
		
	}
	
	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요...");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨 전화번호 정보 삭제 완료!!!");	
	}
	
	// 전화번호 정보를 검색하는 메서드
	private void search() {
		 System.out.println();
		 System.out.println("검색할 전화번호 정보를 입력하세요...");
		 System.out.print("이름 >> ");
		 String name =  scan.next();
		 
		 if(!phoneBookMap.containsKey(name)) {
				System.out.println(name + "씨는 등록되지 않은 사람입니다.");
				System.out.println("검색 작업을 마칩니다.");
				return;
		 }
		
		 Phone p = phoneBookMap.get(name);
		 System.out.println();
		 System.out.println(name + "씨의 전화번호 정보");
		 System.out.println("================================");
		 System.out.println("이름 : " + p.getName());
		 System.out.println("전화번호 : " + p.getTel());
		 System.out.println("주소 : " + p.getAddr());
		 System.out.println("================================");
		 
	}
	
	// 전화번호 정보 전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		
		// 모든 key값 가져오기
		Set<String> phoneKeySet = phoneBookMap.keySet();
		
		System.out.println("----------------------------------");
		System.out.println("  번호    이름    전화번호     주소");
		System.out.println("----------------------------------");
		if(phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int cnt = 0; // 번호 출력용 변수
			for(String name : phoneKeySet) {
				cnt++; // 번호 증가
				Phone p = phoneBookMap.get(name);
				System.out.println("   " + cnt + "    " + name + "    " + p.getTel() + "     " + p.getAddr());
			}
		}
		System.out.println("----------------------------------");
		System.out.println("출력 끝...");
	}
	
}

class Phone{
	private String name;
	private String tel;
	private String addr;
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}	
	
}