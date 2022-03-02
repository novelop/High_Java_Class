package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {

	/*
	 *  문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고, 
	 *      Map을 이용하여 정보를 관리하는 프로그램을 작성하시오.
	 *      
	 *      전화번호 정보는 Map에 저장하여 관리한다.
	 *      (key값은 입력받은 사람의 '이름'으로 하고, 
	 *       value값은 'Phone클래스의 인스턴스'로 한다.)
	 *       
	 *       아래 메뉴의 기능을 모두 작성하시오.
	 *       (삭제,검색 기능은 ==> '이름'을 입력받아 처리한다.)
	 *       
	 *   메뉴 예시) 
	 *      1. 전화번호 등록
	 *      2. 전화번호 수정        ==> '이름'을 입력 받아 처리한다. 
	 *      3. 전화번호 삭제
	 *      4. 전화번호 검색 
	 *      5. 전화번호 전체 출력
	 *      0. 프로그램 종료   
	 * -----------------------------------
	 * 실행에시) 
	 *   	1. 전화번호 등록
	 *      2. 전화번호 수정        
	 *      3. 전화번호 삭제
	 *      4. 전화번호 검색 
	 *      5. 전화번호 전체 출력
	 *      0. 프로그램 종료   
	 *     번호 입력 >> 1 
	 * ------------------------------------
	 *     새롭게 등록할 전화번호 정보를 입력하세요.
	 *     이름 >> 홍길동 
	 *     전화번호 >> 010-1111-1111
	 *     주소 >> 대전시 중구 오류동 
	 *     
	 *     '홍길동'전화번호 정보 등록 완료!!
	 * ------------------------------------
	 * 		1. 전화번호 등록
	 *      2. 전화번호 수정        
	 *      3. 전화번호 삭제
	 *      4. 전화번호 검색 
	 *      5. 전화번호 전체 출력
	 *      0. 프로그램 종료   
	 * ------------------------------------    
	 *     번호 입력 >> 1
	 *  새롭게 등록할 전화번호 정보를 입력하세요.
	 *     이름 >> 홍길동 
	 *  '홍길동'은 이미 등록된 사람입니다.
	 * ------------------------------------    
	 *      1. 전화번호 등록
	 *      2. 전화번호 수정        
	 *      3. 전화번호 삭제
	 *      4. 전화번호 검색 
	 *      5. 전화번호 전체 출력
	 *      0. 프로그램 종료   
	 * ------------------------------------    
	 *      번호입력 >> 5
	 *      
	 *  번호 이름    전화번호   주소
	 *  1   홍길동   010-1111-1111 대전시 중구 오류동 
	 *  
	 *  
	 *     
	 */
	
	Scanner sc = new Scanner(System.in);
	private HashMap<String, Phone> map = new HashMap<String, Phone>();
	
	Phone[] p;
	
	public static void main(String[] args) {
		
		new PhoneBookTest().start();

	}

	private void start() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: insertPhone(); break;
			case 2: update(); break;
			case 3: delete(); break;
			case 4: select(); break;
			case 5: allPhone(); break;
			case 0: 
				System.out.println("프로그램을 종료합니다..");
				System.exit(0);
			default:
				System.out.println("번호를 잘못 선택했습니다.");
			}
		}
		
	}
	
	private void select() {
		System.out.println("검색할 전화번호부의 이름을 입력해주세요.");
		sc = new Scanner(System.in);
		String name =sc.next();
		if (map.containsKey(name)) {
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String key = it.next();
				if(key.equals(name)) {
					Phone value = map.get(key);
					System.out.println("이름 : " + value.getName() 
							+ "\n전화번호 : " + value.getPon() 
							+ "\n주소 : " + value.getAddr());
				}
			}
		}else {
			System.out.println("해당되는 데이터가 없습니다.");
			return;
		}
		
	}

	private void delete() {
		System.out.println("삭제할 전화번호부의 이름을 입력해주세요.");
		 sc = new Scanner(System.in);
		String name =sc.next();
		if (map.containsKey(name)) {
			map.remove(name);
			System.out.println(name + "의 데이터가 삭제되었습니다.");
		}else {
			System.out.println("해당되는 데이터가 없습니다.");
			return;
		}
		
	}

	private void update() {
		System.out.println("수정할 전화번호부의 이름을 입력해주세요.");
		sc = new Scanner(System.in);
		String name =sc.next();
		if (map.containsKey(name)) {
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String key = it.next();
				if(key.equals(name)) {
					  p = new Phone[1];
					  System.out.print("전화번호 >>");
					  sc = new Scanner(System.in);
					  String phone = sc.nextLine();
					  System.out.print("주소 >>");
					  String adress = sc.nextLine();
				      p[0]= new Phone(name, phone, adress);
					  map.put(name, p[0]);
					System.out.println("전화번호부가 수정되었습니다.");
				}
			}
		}else {
			System.out.println("해당되는 데이터가 없습니다.");
			return;
		}
		
	}

	private void allPhone() {
		System.out.println("번호\t이름\t전화번호\t\t주소");
		int count = 1;
		for(Phone value : map.values()) {
			System.out.println(count 
					+ "\t" + value.getName() 
					+ "\t" + value.getPon() 
					+ "\t" + value.getAddr());
			count++;
		}
		
	}

	private void insertPhone() {
		p = new Phone[1];
		System.out.println("---------------------------------");
	    System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
	    System.out.print("이름 >>");
	    sc = new Scanner(System.in);
	    String name =sc.nextLine();
	    if(map.containsKey(name)) {
	    	System.out.println(name + "는 이미있는 사람입니다.");
	    	return;
	    }
	    System.out.print("전화번호 >>");
	    sc = new Scanner(System.in);
	    String phone = sc.nextLine();
	    System.out.print("주소 >>");
	    String adress = sc.nextLine();
		p[0]= new Phone(name, phone, adress);
	    map.put(name, p[0]);
	}

	private int displayMenu() {
		System.out.println("------------------------------------ ");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
	    System.out.println("0. 프로그램 종료");
	    System.out.println("------------------------------------ ");
	    System.out.print("번호 입력 >>");
	    int num = sc.nextInt();
	    return num;
	}
	

}
class Phone{
	private String name;
	private String addr;
	private String pon;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPon() {
		return pon;
	}
	public void setPon(String pon) {
		this.pon = pon;
	}
	@Override
	public String toString() {
		return "Phone [name=" + name + ", addr=" + addr + ", pon=" + pon + "]";
	}
	public Phone(String name, String pon, String addr) {
		super();
		this.name = name;
		this.pon = pon;
		this.addr = addr;
	}
	
	
	
}