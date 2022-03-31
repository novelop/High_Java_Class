package kr.or.ddit.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVo;
import kr.or.ddit.util.DBUtil3;

public class MemberServiceImpl implements IMemberService {
	private static final Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	private static MemberServiceImpl service;

	private IMemberDao dao;
	
	//생성자 
	private MemberServiceImpl(){
	   //dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}

	//3번 
	public static MemberServiceImpl getInstance() {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}

	
	@Override
	public int insertMember(MemberVo memVo) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			
			cnt = dao.insertMember(conn, memVo);
			logger.info("insert작업 성공!!");
		} catch (SQLException e) {
			logger.error("insert작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();
			logger.info("Connection객체 반납....");} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			cnt = dao.deleteMember(conn, memId);
			logger.info("delete작업 성공!!");
		} catch (SQLException e) {
			logger.error("delete작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close(); logger.info("Connection객체 반납....");} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVo memVo) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			cnt = dao.updateMember(conn, memVo);
			logger.info("update작업 성공!!");
		} catch (SQLException e) {
			logger.error("update작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close(); logger.info("Connection객체 반납....");} catch(SQLException e) {}
		}
		return cnt;
	}
	
	@Override
	public int updateMember2( Map<String,String> paramMap) {
		Connection conn = null;
		int cnt = 0; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			cnt = dao.updateMember2(conn, paramMap);
			logger.info("update작업 성공!!");
		} catch (SQLException e) {
			logger.error("update작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close(); logger.info("Connection객체 반납....");} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<MemberVo> getAllMember() {
		Connection conn = null;
		List<MemberVo> memList = null; //반환값 변수 
		try {
			conn = DBUtil3.getConnection();
			memList = dao.getAllMember(conn);
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
		   count = dao.getMemberCount(conn, memId);
		} catch (SQLException e) {
			count = 0;
		}
		return count;
	}

}
