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
	// 1,2 �ܰ� ��񿬰�
		public Connection getConnection() throws Exception{
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			Connection con = ds.getConnection();
			return con;
		}
		
		// int count=getBoardCount() �޼��� ���� 
		public int getBoardCount() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				// 1,2 ��񿬰�
				con = getConnection();
				// 3 sql ���� 
				String sql = "SELECT count(*) FROM board";
				pstmt=con.prepareStatement(sql);
				// 4 ���� => �������
				rs=pstmt.executeQuery();
				// 5 ��� => ���������� �̵�  ���� �����ͼ� count���� ����
				if(rs.next()) {
					count=rs.getInt("count(*)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return count;
		}
		
		//�˻���
		public int getBoardCount(String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int count=0;
			try {
				// 1,2 ��񿬰�
				con=getConnection();
				// 3 sql ���� 
//				String sql="select count(*) from board where subject like '%�˻���%' ";
				String sql="select count(*) from board where subject like ? ";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				// 4 ���� => �������
				rs=pstmt.executeQuery();
				// 5 ��� => ���������� �̵�  ���� �����ͼ� count���� ����
				if(rs.next()) {
					count=rs.getInt("count(*)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return count;
		}
		
//		List bbList=bdao.getBoardList(startRow,pageSize);
		// ���׸� List�� ������� �������� �ʰ� BoardBean���� �����ϰڴ�
		public List<BoardBean> getBoardList(int startRow,int pageSize) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<BoardBean> bbList=new ArrayList<BoardBean>();
			try {
				//1,2 ��񿬰�
				con = getConnection();
				//3 sql
				String sql = "SELECT * FROM board ORDER BY num DESC limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
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
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return bbList;
		}
		
		//�˻���
		public List<BoardBean> getBoardList(int startRow,int pageSize,String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<BoardBean> bbList=new ArrayList<BoardBean>();
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql
//				String sql="select * from board where subject like '%�˻���%' order by num desc limit ?,?";
				String sql="select * from board where subject like ? order by num desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
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
				//������ // insert���� ����� ���尴ü(������) ����
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
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql ���� �غ�
				String sql="select * from board where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 sql ���� => ��� ����
				rs=pstmt.executeQuery();
				//5 ��� ������ ������ ������ bb�ٱ��Ͽ� rs �����³��� ����
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
				//������ // insert���� ����� ���尴ü(������) ����
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
				//1,2 �ܰ� ��񿬰�
				con=getConnection();
				// 3sql ����
				String sql="update board set readcount=readcount+1 where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				// 4 ����
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}
		
		// insertBoard(bb) �޼��� ����� ȣ��
		public void insertBoard(BoardBean bb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			PreparedStatement pstmt2=null;
			try {
				// 1,2 �ܰ� ��񿬰� ȣ��
				con=getConnection();
				// num���ϱ� �ִ�num�� ��ȣ +1
				int num=0;
				//3�ܰ� sql ���� �غ�
				String sql2="select max(num) from board";
				pstmt2=con.prepareStatement(sql2);
				//4�ܰ� sql ���� ��� => ����
				rs=pstmt2.executeQuery();
				//5�ܰ� ����� ��� ������(ù��) �̵� ������ ������ num= max(num)+1
				if(rs.next()){
					num=rs.getInt("max(num)")+1;
				}
				
				// 3�ܰ� sql
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
				// 4�ܰ� ����
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(pstmt2!=null) 	try {	pstmt2.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}
		// int check=   numCheck(num,pass)
		public int numCheck(int num,String pass) {
			// ���尴ü ����
					Connection con=null;
					ResultSet rs=null;
					PreparedStatement pstmt=null;
			int check=-1;
			try {
				//1,2 ��񿬰� �޼��� ȣ��
				con=getConnection();
				// 3 sql  board ���� num=?
				String sql="select * from board where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				// 4 rs ���� ������
				rs=pstmt.executeQuery();
				// 5 rs ������ �̵� ������ ������ true  num����
				//                  pass �� ������ check=1
				//                                Ʋ���� check=0
				//                                          false  num���� check=-1 
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
				//������ //  ����� ���尴ü(������) ����
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
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql update
				String sql="update board set subject=?, content=?, file=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, bb.getSubject());
				pstmt.setString(2, bb.getContent());
				// file
				pstmt.setString(3, bb.getFile());
				pstmt.setInt(4, bb.getNum());
				// 4 ����
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
						//1,2 ��񿬰�
						con=getConnection();
						//3 sql update
						String sql="delete from board where num=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, num);
						// 4 ����
						pstmt.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
						if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
					}
				}
				
}
