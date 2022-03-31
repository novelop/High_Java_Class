package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVo;

public interface IBoardDao {
		
	public int insertBoard(Connection conn, BoardVo boardVo) throws SQLException;
	public int updateBoard(Connection conn, BoardVo boardVo) throws SQLException;
	public int updateCountBoard(Connection conn, String boardNo) throws SQLException;
	public List<BoardVo> selectSearchBoard(Connection conn, String serach) throws SQLException;
	public List<BoardVo> selectAllBoard(Connection conn) throws SQLException;
	public BoardVo selectOneBoard(Connection conn, String boardNo) throws SQLException;
	public int deleteBoard(Connection conn, String boardNo) throws SQLException;
}
