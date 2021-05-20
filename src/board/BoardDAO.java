package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
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
				String sql = "SELECT count(*) FROM board";
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
				String sql="select count(*) from board where subject like ? ";
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
		public List<BoardBean> getBoardList(int startRow,int pageSize) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<BoardBean> bbList=new ArrayList<BoardBean>();
			try {
				//1,2 디비연결
				con = getConnection();
				//3 sql
				String sql = "SELECT * FROM board ORDER BY num DESC limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				//4 실행 결과저장
				rs=pstmt.executeQuery();
				//5 결과 다음행으로 이동 게시판 하나 BoardBean 객체생성 저장
				//   BoardBean 배열 한칸에 저장 
				while(rs.next()) {
					BoardBean bb=new BoardBean();
					bb.setNum(rs.getInt("num"));
					bb.setName(rs.getString("name"));
					bb.setPass(rs.getString("pass"));
					bb.setSubject(rs.getString("subject"));
					bb.setContent(rs.getString("content"));
					bb.setDate(rs.getTimestamp("date"));
					bb.setReadcount(rs.getInt("readcount"));
					// file
					bb.setFile(rs.getString("file"));

					bbList.add(bb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return bbList;
		}
		
		//검색어
		public List<BoardBean> getBoardList(int startRow,int pageSize,String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<BoardBean> bbList=new ArrayList<BoardBean>();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql
//				String sql="select * from board where subject like '%검색어%' order by num desc limit ?,?";
				String sql="select * from board where subject like ? order by num desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 실행 결과저장
				rs=pstmt.executeQuery();
				//5 결과 다음행으로 이동 게시판 하나 BoardBean 객체생성 저장
				//   BoardBean 배열 한칸에 저장 
				while(rs.next()) {
					BoardBean bb=new BoardBean();
					bb.setNum(rs.getInt("num"));
					bb.setName(rs.getString("name"));
					bb.setPass(rs.getString("pass"));
					bb.setSubject(rs.getString("subject"));
					bb.setContent(rs.getString("content"));
					bb.setDate(rs.getTimestamp("date"));
					bb.setReadcount(rs.getInt("readcount"));
					bb.setFile(rs.getString("file"));
					
					bbList.add(bb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return bbList;
		}
		
		// BoardBean bb=  getBoard(num);
		public BoardBean getBoard(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			BoardBean bb=new BoardBean();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql 구문 준비
				String sql="select * from board where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 sql 실행 => 결과 저장
				rs=pstmt.executeQuery();
				//5 결과 다음행 데이터 있으면 bb바구니에 rs 가져온내용 저장
				if(rs.next()) {
					bb.setNum(rs.getInt("num"));
					bb.setName(rs.getString("name"));
					bb.setPass(rs.getString("pass"));
					bb.setSubject(rs.getString("subject"));
					bb.setContent(rs.getString("content"));
					bb.setDate(rs.getTimestamp("date"));
					bb.setReadcount(rs.getInt("readcount"));
					// file
					bb.setFile(rs.getString("file"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return bb;
		}
		
		// bdao.updateReadcount(num);
		public void updateReadcount(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				//1,2 단계 디비연결
				con=getConnection();
				// 3sql 구문
				String sql="update board set readcount=readcount+1 where num=?";
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
		public void insertBoard(BoardBean bb) {
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
				String sql2="select max(num) from board";
				pstmt2=con.prepareStatement(sql2);
				//4단계 sql 실행 결과 => 저장
				rs=pstmt2.executeQuery();
				//5단계 저장된 결과 다음행(첫행) 이동 데이터 있으면 num= max(num)+1
				if(rs.next()){
					num=rs.getInt("max(num)")+1;
				}
				
				// 3단계 sql
				String sql="insert into board(num,name,pass,subject,content,date,readcount,file) values(?,?,?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, bb.getName());
				pstmt.setString(3, bb.getPass());
				pstmt.setString(4, bb.getSubject());
				pstmt.setString(5, bb.getContent());
				pstmt.setTimestamp(6, bb.getDate());
				pstmt.setInt(7, bb.getReadcount());
				//file
				pstmt.setString(8, bb.getFile());
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
				String sql="select * from board where num=?";
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
		public void updateBoard(BoardBean bb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql update
				String sql="update board set subject=?, content=?, file=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, bb.getSubject());
				pstmt.setString(2, bb.getContent());
				// file
				pstmt.setString(3, bb.getFile());
				pstmt.setInt(4, bb.getNum());
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
						String sql="delete from board where num=?";
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
