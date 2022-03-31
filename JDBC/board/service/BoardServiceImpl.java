package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVo;
import kr.or.ddit.util.DBUtil3;


public class BoardServiceImpl implements IBoardService {

	private static BoardServiceImpl service;

	private IBoardDao dao;
	
	//생성자 
	private BoardServiceImpl(){
	   //dao = new MemberDaoImpl();
		dao = BoardDaoImpl.getInstance();
	}

	//3번 
	public static BoardServiceImpl getInstance() {
		if(service==null) service = new BoardServiceImpl();
		return service;
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.insertBoard(conn, boardVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.updateBoard(conn, boardVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateCountBoard(String boardNo) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.updateCountBoard(conn, boardNo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<BoardVo> selectSearchBoard(String serach) {
		List<BoardVo> boardList = null;
		Connection conn = null;
		try {
			conn = DBUtil3.getConnection();
			boardList = dao.selectSearchBoard(conn, serach);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public List<BoardVo> selectAllBoard() {
		List<BoardVo> boardList = null;
		Connection conn = null;
		try {
			conn = DBUtil3.getConnection();
			
			boardList = dao.selectAllBoard(conn);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public BoardVo selectOneBoard(String boardNo) {
		BoardVo boardVo = null;
		Connection conn = null;
		try {
			conn = DBUtil3.getConnection();
			
			int cnt = dao.updateCountBoard(conn, boardNo);
			if(cnt == 0) {
				return null;
			}
			
			boardVo = dao.selectOneBoard(conn, boardNo);
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return boardVo;
	}

	@Override
	public int deleteBoard(String boardNo) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.deleteBoard(conn, boardNo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}

}
