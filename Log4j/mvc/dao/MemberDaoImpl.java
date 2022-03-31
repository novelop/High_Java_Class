package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVo;


public class MemberDaoImpl implements IMemberDao {
	private static final Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	//1번 
	private static MemberDaoImpl dao;
	
	//2번
	private MemberDaoImpl() {}
	
    //3번 
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	
	

	@Override
	public int insertMember(Connection conn, MemberVo memVo) throws SQLException {
		String sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
				+ "		values(?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_id());
		pstmt.setString(2, memVo.getMem_pass());
		pstmt.setString(3, memVo.getMem_name());
		pstmt.setString(4, memVo.getMem_tel());
		pstmt.setString(5, memVo.getMem_addr());
		
		logger.info("PreparedStatement객체 생성");
		logger.info("실행sql문 : " + sql);
		logger.info("사용 데이터 : [" + memVo.getMem_id() + ", " 
		                           + memVo.getMem_pass() + ", "
		                           + memVo.getMem_name() +", " 
		                           + memVo.getMem_tel() + ", "
		                           + memVo.getMem_addr() + "]");
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업성공~~~");
		if(pstmt!=null) {
			pstmt.close();
			logger.info("PreparedStatement객체 반납.....");
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		String sql = "delete from mymember where mem_id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		logger.info("PreparedStatement객체 생성");
		logger.info("실행sql문 : " + sql);
		logger.info("사용 데이터 : [" + memId +  "]");
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업성공~~~");
		if(pstmt!=null) { 
			pstmt.close();
			logger.info("PreparedStatement객체 반납.....");
		}
		
		return cnt;
	}

	@Override
	public int updateMember(Connection conn, MemberVo memVo) throws SQLException {
		String sql = "update mymember "
				+ "   set mem_pass = ?,"
				+ "	  	  mem_name = ?,"
				+ "	  	  mem_tel = ?,"
				+ "	  	  mem_addr = ?"
				+ " where mem_id = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_pass());
		pstmt.setString(2, memVo.getMem_name());
		pstmt.setString(3, memVo.getMem_tel());
		pstmt.setString(4, memVo.getMem_addr());
		pstmt.setString(5, memVo.getMem_id());
		
		logger.info("PreparedStatement객체 생성");
		logger.info("실행sql문 : " + sql);
		logger.info("사용 데이터 : [" + memVo.getMem_id() + ", " 
		                           + memVo.getMem_pass() + ", "
		                           + memVo.getMem_name() +", " 
		                           + memVo.getMem_tel() + ", "
		                           + memVo.getMem_addr() + "]");
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업성공~~~");
		if(pstmt!=null) { 
			pstmt.close();
			logger.info("PreparedStatement객체 반납.....");
		}
		
		return cnt;
	}
	public int updateMember2(Connection conn, Map<String,String> paramMap ) throws SQLException{
		String sql = "update mymember "
				+ " set " + paramMap.get("field") + "= ? " // 컬럼명에 ? x
				+ " where mem_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, paramMap.get("data"));
		pstmt.setString(2, paramMap.get("memId"));
		
		logger.info("PreparedStatement객체 생성");
		logger.info("실행sql문 : " + sql);
		logger.info("사용 데이터 : [" + paramMap.get("data") + ", " 
		                           + paramMap.get("memId") +  "]");
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업성공~~~");
		if(pstmt!=null) { 
			pstmt.close();
			logger.info("PreparedStatement객체 반납.....");
		}
		
		return cnt;
	}
	
	
	@Override
	public List<MemberVo> getAllMember(Connection conn) throws SQLException {
		List<MemberVo> memList = null; //반환값이 저장될 변수  
		String sql = "select * from mymember";
		
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery(sql);
		 
		 logger.info("Statement객체 생성");
		 logger.info("실행sql문 : " + sql);
		 memList = new ArrayList<MemberVo>();
		 
		while(rs.next()) {
			MemberVo memVo = new MemberVo(); // 1개의 레코드가 저장될 변수 
			memVo.setMem_id(rs.getString("mem_id"));
			memVo.setMem_pass(rs.getString("mem_pass"));
			memVo.setMem_name(rs.getString("mem_name"));
			memVo.setMem_tel(rs.getString("mem_tel"));
			memVo.setMem_addr(rs.getString("mem_addr"));
			memList.add(memVo);
		}
		logger.info("작업성공~~~");	
		
		if(stmt!=null) {
			stmt.close();
			logger.info("Statement객체 반납.....");
		}
		if(rs!=null) {
			rs.close();
			logger.info("ResultSet객체 반납.....");
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(Connection conn, String memId) throws SQLException {
		String sql = "select count(*) cnt from mymember"
				+ "   where mem_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		logger.info("PreparedStatement객체 생성");
		logger.info("실행sql문 : " + sql);
		logger.info("사용 데이터 : [" + memId + "]");
		
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		
		logger.info("작업성공~~~");
		
		if(pstmt!=null) { 
			pstmt.close();
			logger.info("PreparedStatement객체 반납.....");
		}
		if(rs!=null) {
			rs.close();
			logger.info("ResultSet객체 반납.....");
		}
		
		return count;
	}
	
}
