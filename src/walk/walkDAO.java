package walk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class walkDAO {
	// 1,2 단계 디비연결
		public Connection getConnection() throws Exception{
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			Connection con = ds.getConnection();
			return con;
		}
		
		public void insertBoard(walkBean wb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				// 1,2 단계 디비연결 호출
				con = getConnection();
				
				// 3단계 sql
				String sql="INSERT INTO walk(pass,pname,price,factory,image) VALUES(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, wb.getPass());
				pstmt.setString(2, wb.getPname());
				pstmt.setString(3, wb.getPrice());
				pstmt.setString(4, wb.getFactory());
				pstmt.setString(5, wb.getImage());
				
				// 4단계 실행
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}
		
		public int getBoardCount() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				// 1,2 디비연결
				con = getConnection();
				// 3 sql 구문 
				String sql = "SELECT count(*) FROM walk";
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
					int count = 0;
					
					try {
						// 1,2 디비연결
						con=getConnection();
						// 3 sql 구문 
//						String sql="select count(*) from board where subject like '%검색어%' ";
						String sql="select count(*) from walk where pname like ? ";
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
				
		
		public List<walkBean> getBoardList(int startRow,int pageSize) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<walkBean> wbList = new ArrayList<walkBean>();
			try {
				//1,2 디비연결
				con = getConnection();
				//3 sql
				String sql = "SELECT * FROM walk ORDER BY num DESC limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				//4 실행 결과저장
				rs=pstmt.executeQuery();
				//5 결과 다음행으로 이동 게시판 하나 BoardBean 객체생성 저장
				//   BoardBean 배열 한칸에 저장 
				while(rs.next()) {
					walkBean wb = new walkBean();
					wb.setNum(rs.getInt("num"));
					wb.setPass(rs.getString("pass"));
					wb.setPname(rs.getString("pname"));
					wb.setPrice(rs.getString("price"));
					wb.setFactory(rs.getString("factory"));
					wb.setImage(rs.getString("image"));
					
					wbList.add(wb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return wbList;
		}

		public List<walkBean> getBoardList(int startRow,int pageSize,String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<walkBean> wbList = new ArrayList<walkBean>();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql
//				String sql="select * from board where subject like '%검색어%' order by num desc limit ?,?";
				String sql="select * from walk where pname like ? order by num desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 실행 결과저장
				rs=pstmt.executeQuery();
				//5 결과 다음행으로 이동 게시판 하나 BoardBean 객체생성 저장
				//   BoardBean 배열 한칸에 저장 
				while(rs.next()) {
					walkBean wb = new walkBean();
					wb.setNum(rs.getInt("num"));
					wb.setPass(rs.getString("pass"));
					wb.setPname(rs.getString("pname"));
					wb.setPrice(rs.getString("price"));
					wb.setFactory(rs.getString("factory"));
					wb.setImage(rs.getString("image"));
					
					wbList.add(wb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return wbList;
		}
		
		public walkBean getBoard(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			walkBean wb=new walkBean();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql 구문 준비
				String sql="select * from walk where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 sql 실행 => 결과 저장
				rs=pstmt.executeQuery();
				//5 결과 다음행 데이터 있으면 bb바구니에 rs 가져온내용 저장
				if(rs.next()) {
					wb.setNum(rs.getInt("num"));
					wb.setPass(rs.getString("pass"));
					wb.setPname(rs.getString("pname"));
					wb.setPrice(rs.getString("price"));
					wb.setFactory(rs.getString("factory"));
					wb.setImage(rs.getString("image"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//마무리 // insert에서 사용한 내장객체(기억장소) 해제
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return wb;
		}
		
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
				String sql="select * from walk where num=?";
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
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return check;
		}
		
		
		public void updateBoard(walkBean wb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql update
				String sql="update walk set pname=?,price=?,factory=?,image=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, wb.getPname());
				pstmt.setString(2, wb.getPrice());
				pstmt.setString(3, wb.getFactory());
				pstmt.setString(4, wb.getImage());
				pstmt.setInt(5, wb.getNum());
				// 4 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}
		public void deleteBoard(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql update
				String sql="delete from walk where num=?";
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
