package kr.or.ddit.mvc.controlller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVo;

public class MemberController {
	private  Scanner scan = new Scanner(System.in);
	private IMemberService service;
	
	//생성자
	public MemberController() {
		//service = new MemberServiceImpl(); 
		service = MemberServiceImpl.getInstance();
	}
	
	
	
	//시작메서드
	public void startMember(){
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 : insertMember(); break; //추가
			case 2 : updateMember(); break; //수정
			case 3 : deleteMember(); break; //삭제
			case 4 : displayMember(); break; //전체 자료 출력 
			case 5 : updateMember2(); break; //수정 
			case 0 : System.out.println("작업을 마칩니다."); return; //작업 끝 
			default : System.out.println("번호를 잘못 입력했습니다.");
			}
		}
	}
	
	
	//메뉴를 출력하고 선택한 작업번호를 반환하는 메서드 	
	public int displayMenu() {
		System.out.println();
		System.out.println(" == 작업 선택 == ");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 수정");
		System.out.println("3. 자료 삭제 ");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자료 수정 2");
		System.out.println("0. 작업 끝.");
		System.out.println("---------------------");
		System.out.println("원하는 작업번호>>");
		return scan.nextInt();
	}
	
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원정보를 입력하세요.");
		// 자료 추가에서 '회원ID'는 중복되지 않는다. 
		// (중복되면 다시 입력받는다.)
		int count = 0; //입력한 회원ID의 개수가 저장될 변수 
		
		String memId; //회원ID가 저장될 변수 
		do {
			System.out.println("회원ID>>");
			memId = scan.next();
			
			count = service.getMemberCount(memId);
			
			if(count>0) {
				System.out.println(memId + "는(은) 이미 존재하는 ID입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
			
		}while(count>0);
		
		System.out.println("비밀번호 >>");
		String memPass = scan.next();
		
		System.out.println("회원이름 >>");
		String memName = scan.next();
		
		System.out.println("전화번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();  //입력버퍼 비우기 
		System.out.println("주소 >>");
		String memAddr = scan.nextLine();
		
		// 입력한 데이터를 Vo객체에 저장한다. 
		MemberVo memVo = new MemberVo();
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.insertMember(memVo);
		if(cnt>0 ) {
			System.out.println("회원정보 추가 성공!!!");
		}else {
			System.out.println("회원 정보 추가 실패~~");
		}
	}
			
	//회원 정보를 삭제하는 메서드 
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원정보를 입력하세요.");
		System.out.println("삭제할 회원ID >>");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		if(cnt>0) {
			System.out.println("회원정보 삭제 성공!!!");
		}else {
			System.out.println("회원 정보 삭제 실패~~");
		}
	}

	//회원 정보를 수정하는 메서드  ==> 전체 항목 수정 
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID >>");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		if(count == 0) { // 없는 회원이면...
		   System.out.println(memId + "는(은) 없는 회원ID입니다.");
		   System.out.println("수정 작업을 마칩니다.");
		   return;
		}
		
		System.out.println();
		System.out.println("수정할 내용을 입력하세요");
		System.out.println("새로운 비밀번호 >>");
		String newMemPass = scan.next();
		
		System.out.println("새로운 이름 >>");
		String newMemName = scan.next();
		
		System.out.println("새로운 전화번호 >>");
		String newMemTel = scan.next();
		
		scan.nextLine();
		System.out.println("새로운 주소 >>");
		String newMemAddr = scan.nextLine();
		
		// 입력한 데이터를 Vo객체에 저장한다. 
		MemberVo memVo = new MemberVo();
		memVo.setMem_pass(newMemPass);
		memVo.setMem_name(newMemName);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);
		memVo.setMem_id(memId);
		
		int cnt = service.updateMember(memVo);
		if(cnt>0 ) {
			System.out.println("회원정보 수정 성공!!!");
		}else {
			System.out.println("회원정보 수정 실패~~");
		}
		
	}

	//회원 정보를 수정하는 메서드 ==> 원하는 항목만 선택해서 수정 
		private void updateMember2() {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.println("회원ID >>");
			String memId = scan.next();
			
			int count = service.getMemberCount(memId);
			if(count == 0) { // 없는 회원이면...
			   System.out.println(memId + "는(은) 없는 회원ID입니다.");
			   System.out.println("수정 작업을 마칩니다.");
			   return;
			}
			int num;
			String updateField = null;
			String updateTitle = null;
			
			do {
				System.out.println();
				System.out.println("수정할 항목을 선택하세요.");
				System.out.println("1.비밀번호 2. 회원이름 3.전화번호 4.회원주소 ");
				System.out.println("------------------------------------------------");
				System.out.println("수정항목 선택 >>");
				num = scan.nextInt();
				switch(num) {
				case 1 : updateField = "mem_pass";
						 updateTitle = "비밀번호"; break;
				case 2 : updateField = "mem_name";
						 updateTitle = "회원이름"; break;
				case 3 : updateField = "mem_tel";
						 updateTitle = "전화번호"; break;
				case 4 : updateField = "mem_addr";
						 updateTitle = "회원주소"; break;
				default : System.out.println("수정 항목을 잘못 선택했습니다.");
						  System.out.println("다시 선택하세요.");
				}
			}while(num < 1 || num > 5);
			
			
			System.out.println();
			System.out.println("새로운" + updateTitle + ">>");
			scan.nextLine();
			String updateData = scan.nextLine();
			
			//수정작업에 필요한 정보를 Map객체에 저장 
			Map<String,String> paramMap = new HashMap<String, String>();
			
			paramMap.put("memId", memId); //회원 ID
			paramMap.put("field", updateField); //수정할 컬럼명
			paramMap.put("data", updateData); //수정할 데이터 
			
			int cnt = service.updateMember2(paramMap);
			
			if(cnt>0 ) {
				System.out.println("회원정보 수정 성공!!!");
			}else {
				System.out.println("회원정보 수정 실패~~");
			}
			
		}
	//전체 회원 정보를 출력하는 메서드 
	private void displayMember() {
		List<MemberVo> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" ID   비밀번호   이름     전화번호    주소");
		System.out.println("--------------------------------------");
		
		if(memList == null || memList.size() == 0) {
			System.out.println("출력할 자료가 하나도 없습니다.");
		}else {
			for(MemberVo memVo : memList) {
			System.out.println(memVo.getMem_id() 
					+ "\t" + memVo.getMem_pass()
					+ "\t" + memVo.getMem_name()
					+ "\t" + memVo.getMem_tel()
					+ "\t" + memVo.getMem_addr());
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		
		new MemberController().startMember();
	}

}
