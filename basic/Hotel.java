package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hotel {
	/*
	 * 문제) 호텔 객실을 관리하는 프로그램을 작성하시오.

   	조건1)  호텔 객식을 나타내는 Room클래스는 방번호(int), 방종류, 투숙객이름 필드로 구성되어 있고			     
           방번호와 방종류는 다음과 같다.
           - 201~209 : 싱글룸
           - 301~309 : 더블룸
           - 401~409 : 스위트룸

    조건2) 전체 객실 관리는 Map을 이용한다.
          (Map의 key값은 방번호로 하고 value값은 Room의 인스턴스로 한다.)
          실행 클래스의 생성자에서는 방번호와 방종류를 초기화한다.

	 */
	
	Scanner sc = new Scanner(System.in);
	 Map<Integer, Room> map = new HashMap<Integer,Room>();
	
	public static void main(String[] args) {
		new Hotel().start();

	}

	private void start() {
		
		for(int i = 1; i <= 9; i++) {
			map.put(200 + i,new Room(200 + i,"싱글룸","-"));		
		}
		
		for(int i = 1; i <= 9; i++) {
			map.put(300 + i,new Room(300 + i,"더블룸","-"));		
		}
		
		for(int i = 1; i <= 9; i++) {
			map.put(400 + i,new Room(400 + i,"스위트룸","-"));		
		}
		
		System.out.println("*********************************************");
		System.out.println(" 호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		while(true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: checkIn(); break;
			case 2: checkOut(); break;
			case 3: allRoom(); break;
			case 4: 
				System.out.println("업무를 종료합니다..");
				System.exit(0);
			default:
				System.out.println("번호를 잘못 선택했습니다.");
			}
		}
		
	}

	private void allRoom() {
		System.out.println("----------------------------------------------");
		System.out.println("                  현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println(" 방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");
		
		for(int i = 1; i <= 9; i++) {
			Room value = map.get(200+i);
			System.out.println(value.getRoomNum()
					+ "\t" + value.getRoomType()
					+ "\t" + value.getName());
		}

		for(int i = 1; i <= 9; i++) {
			Room value = map.get(300+i);
			System.out.println(value.getRoomNum()
					+ "\t" + value.getRoomType()
					+ "\t" + value.getName());
		}

		for(int i = 1; i <= 9; i++) {
			Room value = map.get(400+i);
			System.out.println(value.getRoomNum()
					+ "\t" + value.getRoomType()
					+ "\t" + value.getName());
		}
		
	}

	private void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("    체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.println("방번호 입력 >>");
		sc = new Scanner(System.in);
		int roomNum = sc.nextInt();
		if(map.containsKey(roomNum)) {
			if (!map.get(roomNum).getName().equals("-")) {
				Room value = map.remove(roomNum);
				if (roomNum >= 401) {
					map.put(roomNum, new Room(roomNum, "스위트룸", "-"));
				} else if (roomNum >= 301) {
					map.put(roomNum, new Room(roomNum, "더블룸", "-"));
				} else {
					map.put(roomNum, new Room(roomNum, "싱글룸", "-"));
				}
				System.out.println(value.getRoomNum() + "호 객실의 " +
							 value.getName() + "님 체크아웃을 완료하였습니다.");
				
			}else {
				System.out.println(roomNum + "호 객실에는 체크인 한 사람이 없습니다.");
			}
		}else {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}
		
	}

	private void checkIn() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("    체크인 작업");
		System.out.println("-----------------------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("-----------------------------------------------------------");
		System.out.print("방 번호 입력 >>");
		sc = new Scanner(System.in);
		int roomNum = sc.nextInt();
		if(map.containsKey(roomNum)) {
			System.out.println("누구를 체크인 하시겠습니까?");
			sc = new Scanner(System.in);
			String name = sc.next();
			Room value = map.get(roomNum);
			value.setName(name);
			map.put(roomNum, value);
			System.out.println("체크인이 완료되었습니다.");
		}else {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
		}
	}

	private int displayMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택>>");
		int choice = sc.nextInt();
		return choice;
	}

}

class Room{
	
	private int roomNum;
	private String roomType;
	private String name;
	
	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", name=" + name + "]";
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room(int roomNum, String roomType, String name) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.name = name;
	}
	
	
	
	
}