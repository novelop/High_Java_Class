package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.LprodVo;

public class JdbcToIbatisTest {
	/*
	 * Lprod테이블에 새로운데이터를 추가하기
	 * Lprodg_gu와 lrpod_nm은 직접 입력받아서 처리하고,
	 * lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1크게 한다. 
	 * 
	 * 그리고 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다. 
	 * (sql문이 저장되는 xml문서의 파일명 : jdbc.xml)
	 * 
	 */
	

	public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
		 SqlMapClient smc = null;
		
		try {
//			
//	         Charset charset = Charset.forName("utf-8");
//	         Resources.setCharset(charset);
//	         
//	         Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapconfig.xml");
//	          smc = SqlMapClientBuilder.buildSqlMapClient(rd);
//	         
//	         rd.close(); // 스트림 닫기
			
			smc = SqlMapClientFactory.getSqlMapClient();
	         
	        int nextId = (int) smc.queryForObject("jdbc.getMaxId");
	        nextId++;
	        
	        // 입력받은 lprod_gu가 이미 등록되어 있으면 
	        // 다시 입력 받아서 처리한다.
		    String gu;
		    int count = 0;
		    do {
		    	System.out.print("상품 분류 코드 입력: ");
		    	gu = scan.next();
		    	
		    	count = (int) smc.queryForObject("jdbc.getLprodCount");
		    	if(count>0) {
		    		System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다. ");
		    		System.out.println("다시 입력하세요");
		    	}
		    }while(count>0);
	        
	        System.out.print("상품 분류명 입력: ");
	        String nm = scan.next();
	        
	        //입력값을 Vo에 저장하기
	        LprodVo lvo = new LprodVo();
	        lvo.setLprod_id(nextId);
	        lvo.setLprod_gu(gu);
	        lvo.setLprod_nm(nm);
	        
	        Object obj = smc.insert("jdbc.insertLprod", lvo);
	        if(obj == null) {
	        	System.out.println("등록 성공!!!");
	        }else {
	        	System.out.println("등록 실패~~~");
	        }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
