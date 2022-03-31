package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVo;

public interface IBoardService {

	public int insertBoard(BoardVo boardVo);
	public int updateBoard(BoardVo boardVo);
	public int updateCountBoard(String boardNo);
	public List<BoardVo> selectSearchBoard(String serach);
	public List<BoardVo> selectAllBoard();
	public BoardVo selectOneBoard(String boardNo);
	public int deleteBoard(String boardNo);
}
