package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVo;



public class BoardController {
	private  Scanner scan = new Scanner(System.in);
	private IBoardService service;
	
	public BoardController() {
		service = BoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}

	
	private void BoardStart() {
		String title = null;
		int choice = -1;
		while(true) {
			if(choice!=3) title = null;
			choice = displayMenu(title);
			switch(choice) {
			case 1 : insertBoard(); break; //게시글 추가
			case 2 : readBoard(); break; //게시글 보기
			case 3 : title = searchBoard(); break; //게시글 검색
			case 0 : System.out.println("작업을 마칩니다."); return; //작업 끝 
			default : System.out.println("번호를 잘못 입력했습니다.");
			}
		}
		
	}
	
	private String searchBoard() {
		System.out.println("검색작업");
		System.out.println("------------------------------");
		System.out.print("검색할 제목 입력 :");
		scan.nextLine();
		String title = scan.nextLine();
		
		//List<BoardVo> boardList = service.selectSearchBoard(title);

//		System.out.println();
//		System.out.println("----------------------------------------------");
//		System.out.println(" No	        제 목            작성자 	조회수   ");
//		System.out.println("----------------------------------------------");
//		if(boardList == null || boardList.size() == 0) {
//			List<BoardVo> allBoardList = service.selectAllBoard();
//			for(BoardVo boardVo : allBoardList) {
//				System.out.println(boardVo.getBoard_no() 
//						+ "\t" + boardVo.getBoard_title()
//						+ "\t" + boardVo.getBoard_writer()
//						+ "\t" + boardVo.getBoard_cnt());
//				}
//		}else {
//			for(BoardVo boardVo : boardList) {
//			System.out.println(boardVo.getBoard_no() 
//					+ "\t" + boardVo.getBoard_title()
//					+ "\t" + boardVo.getBoard_writer()
//					+ "\t" + boardVo.getBoard_cnt());
//			}
//		}
		
		return title;
	}


	private void readBoard() {
		System.out.println("보기를 원하는 게시글 번호 입력>");
		String boardNo = scan.next();
		
		BoardVo boardVo = new BoardVo();
		boardVo = service.selectOneBoard(boardNo);
		if(boardVo == null) {
			System.out.println("존재하지 않는 게시글 번호 입니다.");
			return;
		}
		
		System.out.println(boardNo + "번글 내용");
		System.out.println("---------------------------------------");
		System.out.println("-제 목 : " +boardVo.getBoard_title());
		System.out.println("-작성자 : " + boardVo.getBoard_writer());
		System.out.println("-내 용 : " + boardVo.getBoard_content());
		System.out.println("-작성일 : " + boardVo.getBoard_date());
		System.out.println("-조회수 : " +boardVo.getBoard_cnt());
		System.out.println("---------------------------------------");
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >>");
		int num = scan.nextInt();
		switch(num) {
		case 1: updateBoard(boardNo); break;
		case 2: deleteBoard(boardNo); break;
		case 3: return;
		
		}
		
		
	}


	private void deleteBoard(String boardNo) {
		
		int cnt = service.deleteBoard(boardNo);
		
		if(cnt>0 ) {
			System.out.println("게시글 삭제 성공!!!");
		}else {
			System.out.println("게시글 삭제 실패~~");
		}
	}


	private void updateBoard(String boardNo) {
		System.out.println("수정작업하기");
		System.out.println("-------------------------");
		System.out.print("-제 목 :");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("-내 용 :");
		String content = scan.nextLine();
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		boardVo.setBoard_no(boardNo);
		
		
		int cnt = service.updateBoard(boardVo);
		if(cnt>0 ) {
			System.out.println("게시글 수정 성공!!!");
		}else {
			System.out.println("게시글 수정 실패~~");
		}
	}


	private void insertBoard() {
		System.out.println("새글 작성하기");
		System.out.println("-----------------------");
		System.out.print("- 제 목:");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("- 작성자:");
		
		String writer = scan.nextLine();
		System.out.print("- 내 용:");
		
		String content = scan.nextLine();
		
		// 입력한 데이터를 Vo객체에 저장한다. 
			BoardVo boardVo = new BoardVo();
			boardVo.setBoard_title(title);
			boardVo.setBoard_writer(writer);
			boardVo.setBoard_content(content);
			
			int cnt = service.insertBoard(boardVo);
			if(cnt>0 ) {
				System.out.println("게시글 추가 성공!!!");
			}else {
				System.out.println("게시글 추가 실패~~");
			}
		
	}


	private int displayMenu(String title) {
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		if(title==null || "".equals(title)) {
			boardList = service.selectAllBoard();
		}else {
			boardList = service.selectSearchBoard(title);
		}
		
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("----------------------------------------------");
		
		if(boardList == null || boardList.size() == 0) {
			System.out.println("출력할 자료가 하나도 없습니다.");
		}else {
			for(BoardVo boardVo : boardList) {
			System.out.println(boardVo.getBoard_no() 
					+ "\t" + boardVo.getBoard_title()
					+ "\t" + boardVo.getBoard_writer()
					+ "\t" + boardVo.getBoard_cnt());
			}
		}
		System.out.println("----------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성    2. 게시글보기    3. 검색    0. 작업끝");
		int choice = scan.nextInt();
		return choice;
	}


	public static void main(String[] args) {
	  new BoardController().BoardStart();

	}


}
