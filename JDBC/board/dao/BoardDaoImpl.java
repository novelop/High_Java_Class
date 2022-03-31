package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVo;


public class BoardDaoImpl implements IBoardDao {
	
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {
		
	}
	
	public static BoardDaoImpl getInstance() {
		if(dao==null) dao = new BoardDaoImpl();
		return dao;
	}
	

	@Override
	public int insertBoard(Connection conn, BoardVo boardVo) throws SQLException {
		String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_date, board_cnt, board_content)"
				+ "	values(board_seq.nextVal,?,?,sysdate,0,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardVo.getBoard_title());
		pstmt.setString(2, boardVo.getBoard_writer());
		pstmt.setString(3, boardVo.getBoard_content());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int updateBoard(Connection conn, BoardVo boardVo) throws SQLException {
		String sql = "update jdbc_board"
				+ "	  set board_title = ?,"
				+ "	      board_content = ?,"
				+ "		  board_date = sysdate"
				+ "   where board_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardVo.getBoard_title());
		pstmt.setString(2, boardVo.getBoard_content());
		pstmt.setString(3, boardVo.getBoard_no());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public List<BoardVo> selectSearchBoard(Connection conn, String search) throws SQLException {
		List<BoardVo> boardList = null;
		String sql = "select board_no, board_title, board_writer, board_cnt"
				+ "   from jdbc_board"
				+ "   where board_title like '%'|| ? ||'%'"
				+ "	  order by board_no desc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, search);
		
		ResultSet rs = pstmt.executeQuery();
		
		boardList = new ArrayList<BoardVo>();
		
		while(rs.next()) {
			BoardVo boardVo = new BoardVo(); // 1개의 레코드가 저장될 변수 
			boardVo.setBoard_no(rs.getString("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_cnt(rs.getString("board_cnt"));
			
			boardList.add(boardVo);
		}
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		
		return boardList;
		
	}

	@Override
	public List<BoardVo> selectAllBoard(Connection conn) throws SQLException {
		List<BoardVo> boardList = null;
		String sql = "select board_no, board_title, board_writer, board_cnt"
				+ "   from jdbc_board"
				+ "	  order by board_no desc";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		boardList = new ArrayList<BoardVo>();
		
		while(rs.next()) {
			BoardVo boardVo = new BoardVo(); // 1개의 레코드가 저장될 변수 
			boardVo.setBoard_no(rs.getString("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_cnt(rs.getString("board_cnt"));
			
			boardList.add(boardVo);
		}
		
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
		
		return boardList;
	}

	@Override
	public BoardVo selectOneBoard(Connection conn,String boardNo) throws SQLException {
		String sql = "select board_no, board_title, board_writer, to_char(board_date, 'YYYY-MM-DD') board_date, board_content, board_cnt"
				+ "   from jdbc_board"
				+ "   where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		
		ResultSet rs = pstmt.executeQuery();
	
		BoardVo boardVo = null; 
		
		while(rs.next()) {
			boardVo = new BoardVo(); // 1개의 레코드가 저장될 변수 
			boardVo.setBoard_no(rs.getString("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_date(rs.getString("board_date"));
			boardVo.setBoard_content(rs.getString("board_content"));
			boardVo.setBoard_cnt(rs.getString("board_cnt"));
		}
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		
		return boardVo;
	}

	@Override
	public int deleteBoard(Connection conn, String boardNo) throws SQLException {
		String sql = "delete from jdbc_board where board_no = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int updateCountBoard(Connection conn, String boardNo) throws SQLException {
		String sql = "update jdbc_board"
				+ "	  set board_cnt = board_cnt + 1"
				+ "   where board_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

}
