package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.BoardBean;

public class qnaDAO {
	// 1,2 단계 디비연결
		public Connection getConnection() throws Exception{
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			Connection con = ds.getConnection();
			return con;
		}
		
		// int count=getBoardCount() 메서드 정의 
		public int getBoardCount() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				// 1,2 디비연결
				con = getConnection();
				// 3 sql 구문 
				String sql = "SELECT count(*) FROM qna";
				pstmt=con.prepareStatement(sql);
				// 4 실행 => 결과저장
				rs=pstmt.executeQuery();
				// 5 결과 => 다음행으로 이동  열을 가져와서 count변수 저장
				if(rs.next()) {
					count=rs.getInt("count(*)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return count;
		}
		
		//검색어
		public int getBoardCount(String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int count=0;
			try {
				// 1,2 디비연결
				con=getConnection();
				// 3 sql 구문 
//				String sql="select count(*) from board where subject like '%검색어%' ";
				String sql="select count(*) from qna where subject like ? ";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				// 4 실행 => 결과저장
				rs=pstmt.executeQuery();
				// 5 결과 => 다음행으로 이동  열을 가져와서 count변수 저장
				if(rs.next()) {
					count=rs.getInt("count(*)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return count;
		}
		
//		List bbList=bdao.getBoardList(startRow,pageSize);
		// 제네릭 List에 모든형을 저장하지 않고 BoardBean형만 저장하겠다
		public List<qnaBean> getBoardList(int startRow,int pageSize) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<qnaBean> qbList = new ArrayList<qnaBean>();
			try {
				//1,2 디비연결
				con = getConnection();
				//3 sql
				String sql="select * from qna order by re_ref desc, re_seq asc limit ?,?";			
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				//4 실행 결과저장
				rs=pstmt.executeQuery();
				//5 결과 다음행으로 이동 게시판 하나 BoardBean 객체생성 저장
				//   BoardBean 배열 한칸에 저장 
				while(rs.next()) {
					qnaBean qb=new qnaBean();
					qb.setNum(rs.getInt("num"));
					qb.setName(rs.getString("name"));
					qb.setPass(rs.getString("pass"));
					qb.setSubject(rs.getString("subject"));
					qb.setContent(rs.getString("content"));
					qb.setDate(rs.getTimestamp("date"));
					qb.setReadcount(rs.getInt("readcount"));
					// file
					qb.setFile(rs.getString("file"));
					// 답글
					qb.setRe_ref(rs.getInt("re_ref"));
					qb.setRe_lev(rs.getInt("re_lev"));
					qb.setRe_seq(rs.getInt("re_seq"));

					qbList.add(qb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return qbList;
		}
		
		//검색어
		public List<qnaBean> getBoardList(int startRow,int pageSize,String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<qnaBean> qbList=new ArrayList<qnaBean>();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql
//				String sql="select * from board where subject like '%검색어%' order by num desc limit ?,?";
				String sql="select * from qna where subject like ? order by num desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 실행 결과저장
				rs=pstmt.executeQuery();
				//5 결과 다음행으로 이동 게시판 하나 BoardBean 객체생성 저장
				//   BoardBean 배열 한칸에 저장 
				while(rs.next()) {
					qnaBean qb=new qnaBean();
					qb.setNum(rs.getInt("num"));
					qb.setName(rs.getString("name"));
					qb.setPass(rs.getString("pass"));
					qb.setSubject(rs.getString("subject"));
					qb.setContent(rs.getString("content"));
					qb.setDate(rs.getTimestamp("date"));
					qb.setReadcount(rs.getInt("readcount"));
					qb.setFile(rs.getString("file"));

					
					qbList.add(qb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return qbList;
		}
		
		// BoardBean bb=  getBoard(num);
		public qnaBean getBoard(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			qnaBean qb=new qnaBean();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql 구문 준비
				String sql="select * from qna where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 sql 실행 => 결과 저장
				rs=pstmt.executeQuery();
				//5 결과 다음행 데이터 있으면 bb바구니에 rs 가져온내용 저장
				if(rs.next()) {
					qb.setNum(rs.getInt("num"));
					qb.setName(rs.getString("name"));
					qb.setPass(rs.getString("pass"));
					qb.setSubject(rs.getString("subject"));
					qb.setContent(rs.getString("content"));
					qb.setDate(rs.getTimestamp("date"));
					qb.setReadcount(rs.getInt("readcount"));
					// file
					qb.setFile(rs.getString("file"));
					//답글
					qb.setRe_ref(rs.getInt("re_ref"));
					qb.setRe_lev(rs.getInt("re_lev"));
					qb.setRe_seq(rs.getInt("re_seq"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return qb;
		}
		
		// bdao.updateReadcount(num);
		public void updateReadcount(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				//1,2 단계 디비연결
				con=getConnection();
				// 3sql 구문
				String sql="update qna set readcount=readcount+1 where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				// 4 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}
		
		// insertBoard(bb) 메서드 만들고 호출
		public void insertBoard(qnaBean qb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			PreparedStatement pstmt2=null;
			try {
				// 1,2 단계 디비연결 호출
				con=getConnection();
				// num구하기 최대num값 번호 +1
				int num=0;
				//3단계 sql 구문 준비
				String sql2="select max(num) from qna";
				pstmt2=con.prepareStatement(sql2);
				//4단계 sql 실행 결과 => 저장
				rs=pstmt2.executeQuery();
				//5단계 저장된 결과 다음행(첫행) 이동 데이터 있으면 num= max(num)+1
				if(rs.next()){
					num=rs.getInt("max(num)")+1;
				}
				
				// 3단계 sql
				String sql="insert into qna(num,name,pass,subject,content,date,readcount,file,re_ref,re_lev,re_seq) values(?,?,?,?,?,?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, qb.getName());
				pstmt.setString(3, qb.getPass());
				pstmt.setString(4, qb.getSubject());
				pstmt.setString(5, qb.getContent());
				pstmt.setTimestamp(6, qb.getDate());
				pstmt.setInt(7, qb.getReadcount());
				//file
				pstmt.setString(8, qb.getFile());
				//답글
				pstmt.setInt(9, num); // num기준글번호 => 그룹번호 re_ref 일치
				pstmt.setInt(10, 0); // 들여쓰기 re_lev 0
				pstmt.setInt(11, 0); // 그룹 안에 순서 re_seq 0번째
				// 4단계 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(pstmt2!=null) 	try {	pstmt2.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}
		
		public void reInsertBoard(qnaBean qb) {
			// 내장객체 선언
			Connection con=null;
			PreparedStatement pstmt2=null;
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			try {
				// 1,2 단계 디비연결 호출
				con=getConnection();
				// num구하기 최대num값 번호 +1
				int num=0;
				//3단계 sql 구문 준비
				String sql2="select max(num) from qna";
				pstmt2=con.prepareStatement(sql2);
				//4단계 sql 실행 결과 => 저장
				rs=pstmt2.executeQuery();
				//5단계 저장된 결과 다음행(첫행) 이동 데이터 있으면 num= max(num)+1
				if(rs.next()){
					num=rs.getInt("max(num)")+1;
				}
			// 답글을 달고자하는 글밑에 답글이 달려있으면
			// 내밑에 답글의 순서 재배치(순서+1) 한칸씩 아래로 배치
			// 조건 : 같은 그룹이면서 답글을 달고자하는 글순서값 보다 큰거 찾기 
			// 수정 : 순서값 +1 을 해서 한칸 아래로 배치
				sql2="update qna set re_seq=re_seq+1 where re_ref=? and re_seq>?";
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setInt(1, qb.getRe_ref());
				pstmt2.setInt(2, qb.getRe_seq());
				pstmt2.executeUpdate();
				
				// 3단계 sql
//				String sql="insert into board(num,name,pass,subject,content,date,readcount,file) values(?,?,?,?,?,?,?,?)";
				String sql="insert into qna(num,name,pass,subject,content,date,readcount,file,re_ref,re_lev,re_seq) values(?,?,?,?,?,?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, qb.getName());
				pstmt.setString(3, qb.getPass());
				pstmt.setString(4, qb.getSubject());
				pstmt.setString(5, qb.getContent());
				pstmt.setTimestamp(6, qb.getDate());
				pstmt.setInt(7, qb.getReadcount());
				pstmt.setString(8,qb.getFile());
				//답글
				pstmt.setInt(9, qb.getRe_ref()); //re_ref 가져온 그룹번호 그대로 사용
				pstmt.setInt(10, qb.getRe_lev()+1);  // re_lev 가져온 들여쓰기 re_lev+1
				pstmt.setInt(11, qb.getRe_seq()+1);  // re_seq 가져온 그룹안에 순서 re_seq +1
				
				// 4단계 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(rs!=null) 	        try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt2!=null) 	try {	pstmt2.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}//메서드
		
		
		// int check=   numCheck(num,pass)
		public int numCheck(int num,String pass) {
			// 내장객체 선언
					Connection con=null;
					ResultSet rs=null;
					PreparedStatement pstmt=null;
			int check=-1;
			try {
				//1,2 디비연결 메서드 호출
				con=getConnection();
				// 3 sql  board 조건 num=?
				String sql="select * from qna where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				// 4 rs 저장 실행결과
				rs=pstmt.executeQuery();
				// 5 rs 다음행 이동 데이터 있으면 true  num있음
				//                  pass 비교 맞으면 check=1
				//                                틀리면 check=0
				//                                          false  num없음 check=-1 
				if(rs.next()) {
					if(pass.equals(rs.getString("pass"))) {
						check=1;
					}else {
						check=0;
					}
				}else {
					check=-1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 //  사용한 내장객체(기억장소) 해제
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(rs!=null) 	        try {	rs.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return check;
		}
		// bdao.updateBoard(bb);
		public void updateBoard(qnaBean qb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql update
				String sql="update qna set subject=?, content=?, file=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, qb.getSubject());
				pstmt.setString(2, qb.getContent());
				// file
				pstmt.setString(3, qb.getFile());
				pstmt.setInt(4, qb.getNum());
				// 4 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}
		
		// bdao.deleteBoard(num);
				public void deleteBoard(int num) {
					Connection con=null;
					PreparedStatement pstmt=null;
					try {
						//1,2 디비연결
						con=getConnection();
						//3 sql update
						String sql="delete from qna where num=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, num);
						// 4 실행
						pstmt.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
						if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
					}
				}
				
}
