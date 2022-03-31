package kr.or.ddit.mvc.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVo;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberServiceImpl implements IMemberService {
	
	private static MemberServiceImpl service;

	private IMemberDao dao;
	private SqlMapClient smc = SqlMapClientFactory.getSqlMapClient();
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
		int cnt = 0;
		try {
			cnt = dao.insertMember(smc, memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = dao.deleteMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVo memVo) {
		int cnt = 0;
		try {
			cnt = dao.updateMember(smc, memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		int cnt = 0;
		try {
			cnt = dao.updateMember2(smc, paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVo> getAllMember() {
		List<MemberVo> memList = null;
		try {
			memList = dao.getAllMember(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int count = 0;
		try {
			count = dao.getMemberCount(smc,memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	

}
