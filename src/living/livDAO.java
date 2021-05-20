package living;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class livDAO {
	// 1,2 �ܰ� ��񿬰�
		public Connection getConnection() throws Exception{
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			Connection con = ds.getConnection();
			return con;
		}
		
		public void insertBoard(livBean lb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				// 1,2 �ܰ� ��񿬰� ȣ��
				con = getConnection();
				
				// 3�ܰ� sql
				String sql="INSERT INTO liv(pass,pname,price,factory,image) VALUES(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, lb.getPass());
				pstmt.setString(2, lb.getPname());
				pstmt.setString(3, lb.getPrice());
				pstmt.setString(4, lb.getFactory());
				pstmt.setString(5, lb.getImage());
				
				// 4�ܰ� ����
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
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
				// 1,2 ��񿬰�
				con = getConnection();
				// 3 sql ���� 
				String sql = "SELECT count(*) FROM liv";
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
					int count = 0;
					
					try {
						// 1,2 ��񿬰�
						con=getConnection();
						// 3 sql ���� 
//						String sql="select count(*) from board where subject like '%�˻���%' ";
						String sql="select count(*) from liv where pname like ? ";
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
				
		
		public List<livBean> getBoardList(int startRow,int pageSize) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<livBean> lbList = new ArrayList<livBean>();
			try {
				//1,2 ��񿬰�
				con = getConnection();
				//3 sql
				String sql = "SELECT * FROM liv ORDER BY num DESC limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
				while(rs.next()) {
					livBean lb = new livBean();
					lb.setNum(rs.getInt("num"));
					lb.setPass(rs.getString("pass"));
					lb.setPname(rs.getString("pname"));
					lb.setPrice(rs.getString("price"));
					lb.setFactory(rs.getString("factory"));
					lb.setImage(rs.getString("image"));
					
					lbList.add(lb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return lbList;
		}

		public List<livBean> getBoardList(int startRow,int pageSize,String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<livBean> lbList = new ArrayList<livBean>();
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql
//				String sql="select * from board where subject like '%�˻���%' order by num desc limit ?,?";
				String sql="select * from liv where pname like ? order by num desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
				while(rs.next()) {
					livBean lb = new livBean();
					lb.setNum(rs.getInt("num"));
					lb.setPass(rs.getString("pass"));
					lb.setPname(rs.getString("pname"));
					lb.setPrice(rs.getString("price"));
					lb.setFactory(rs.getString("factory"));
					lb.setImage(rs.getString("image"));
					
					lbList.add(lb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return lbList;
		}
		
		public livBean getBoard(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			livBean lb=new livBean();
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql ���� �غ�
				String sql="select * from liv where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 sql ���� => ��� ����
				rs=pstmt.executeQuery();
				//5 ��� ������ ������ ������ bb�ٱ��Ͽ� rs �����³��� ����
				if(rs.next()) {
					lb.setNum(rs.getInt("num"));
					lb.setPass(rs.getString("pass"));
					lb.setPname(rs.getString("pname"));
					lb.setPrice(rs.getString("price"));
					lb.setFactory(rs.getString("factory"));
					lb.setImage(rs.getString("image"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return lb;
		}
		
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
				String sql="select * from liv where num=?";
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
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return check;
		}
		
		
		public void updateBoard(livBean lb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql update
				String sql="update liv set pname=?,price=?,factory=?,image=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, lb.getPname());
				pstmt.setString(2, lb.getPrice());
				pstmt.setString(3, lb.getFactory());
				pstmt.setString(4, lb.getImage());
				pstmt.setInt(5, lb.getNum());
				// 4 ����
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
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql update
				String sql="delete from liv where num=?";
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
