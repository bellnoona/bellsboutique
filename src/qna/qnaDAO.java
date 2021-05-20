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
				String sql = "SELECT count(*) FROM qna";
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
				String sql="select count(*) from qna where subject like ? ";
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
		public List<qnaBean> getBoardList(int startRow,int pageSize) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<qnaBean> qbList = new ArrayList<qnaBean>();
			try {
				//1,2 ��񿬰�
				con = getConnection();
				//3 sql
				String sql="select * from qna order by re_ref desc, re_seq asc limit ?,?";			
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
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
					// ���
					qb.setRe_ref(rs.getInt("re_ref"));
					qb.setRe_lev(rs.getInt("re_lev"));
					qb.setRe_seq(rs.getInt("re_seq"));

					qbList.add(qb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return qbList;
		}
		
		//�˻���
		public List<qnaBean> getBoardList(int startRow,int pageSize,String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<qnaBean> qbList=new ArrayList<qnaBean>();
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql
//				String sql="select * from board where subject like '%�˻���%' order by num desc limit ?,?";
				String sql="select * from qna where subject like ? order by num desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
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
				//������ // insert���� ����� ���尴ü(������) ����
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
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql ���� �غ�
				String sql="select * from qna where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 sql ���� => ��� ����
				rs=pstmt.executeQuery();
				//5 ��� ������ ������ ������ bb�ٱ��Ͽ� rs �����³��� ����
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
					//���
					qb.setRe_ref(rs.getInt("re_ref"));
					qb.setRe_lev(rs.getInt("re_lev"));
					qb.setRe_seq(rs.getInt("re_seq"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
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
				//1,2 �ܰ� ��񿬰�
				con=getConnection();
				// 3sql ����
				String sql="update qna set readcount=readcount+1 where num=?";
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
		public void insertBoard(qnaBean qb) {
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
				String sql2="select max(num) from qna";
				pstmt2=con.prepareStatement(sql2);
				//4�ܰ� sql ���� ��� => ����
				rs=pstmt2.executeQuery();
				//5�ܰ� ����� ��� ������(ù��) �̵� ������ ������ num= max(num)+1
				if(rs.next()){
					num=rs.getInt("max(num)")+1;
				}
				
				// 3�ܰ� sql
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
				//���
				pstmt.setInt(9, num); // num���ر۹�ȣ => �׷��ȣ re_ref ��ġ
				pstmt.setInt(10, 0); // �鿩���� re_lev 0
				pstmt.setInt(11, 0); // �׷� �ȿ� ���� re_seq 0��°
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
		
		public void reInsertBoard(qnaBean qb) {
			// ���尴ü ����
			Connection con=null;
			PreparedStatement pstmt2=null;
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			try {
				// 1,2 �ܰ� ��񿬰� ȣ��
				con=getConnection();
				// num���ϱ� �ִ�num�� ��ȣ +1
				int num=0;
				//3�ܰ� sql ���� �غ�
				String sql2="select max(num) from qna";
				pstmt2=con.prepareStatement(sql2);
				//4�ܰ� sql ���� ��� => ����
				rs=pstmt2.executeQuery();
				//5�ܰ� ����� ��� ������(ù��) �̵� ������ ������ num= max(num)+1
				if(rs.next()){
					num=rs.getInt("max(num)")+1;
				}
			// ����� �ް����ϴ� �۹ؿ� ����� �޷�������
			// ���ؿ� ����� ���� ���ġ(����+1) ��ĭ�� �Ʒ��� ��ġ
			// ���� : ���� �׷��̸鼭 ����� �ް����ϴ� �ۼ����� ���� ū�� ã�� 
			// ���� : ������ +1 �� �ؼ� ��ĭ �Ʒ��� ��ġ
				sql2="update qna set re_seq=re_seq+1 where re_ref=? and re_seq>?";
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setInt(1, qb.getRe_ref());
				pstmt2.setInt(2, qb.getRe_seq());
				pstmt2.executeUpdate();
				
				// 3�ܰ� sql
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
				//���
				pstmt.setInt(9, qb.getRe_ref()); //re_ref ������ �׷��ȣ �״�� ���
				pstmt.setInt(10, qb.getRe_lev()+1);  // re_lev ������ �鿩���� re_lev+1
				pstmt.setInt(11, qb.getRe_seq()+1);  // re_seq ������ �׷�ȿ� ���� re_seq +1
				
				// 4�ܰ� ����
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(rs!=null) 	        try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt2!=null) 	try {	pstmt2.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
		}//�޼���
		
		
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
				String sql="select * from qna where num=?";
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
		public void updateBoard(qnaBean qb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql update
				String sql="update qna set subject=?, content=?, file=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, qb.getSubject());
				pstmt.setString(2, qb.getContent());
				// file
				pstmt.setString(3, qb.getFile());
				pstmt.setInt(4, qb.getNum());
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
						String sql="delete from qna where num=?";
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
