package clothes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class cloDAO {
	// 1,2 �ܰ� ��񿬰�
		public Connection getConnection() throws Exception{
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			Connection con = ds.getConnection();
			return con;
		}
		
		public void insertBoard(cloBean cb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				// 1,2 �ܰ� ��񿬰� ȣ��
				con = getConnection();
				
				// 3�ܰ� sql
				String sql="INSERT INTO clo(pass,pname,price,factory,image) VALUES(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, cb.getPass());
				pstmt.setString(2, cb.getPname());
				pstmt.setString(3, cb.getPrice());
				pstmt.setString(4, cb.getFactory());
				pstmt.setString(5, cb.getImage());
				
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
				String sql = "SELECT count(*) FROM clo";
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
						String sql="select count(*) from clo where pname like ? ";
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
				
		
		public List<cloBean> getBoardList(int startRow,int pageSize) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<cloBean> cbList = new ArrayList<cloBean>();
			try {
				//1,2 ��񿬰�
				con = getConnection();
				//3 sql
				String sql = "SELECT * FROM clo ORDER BY num DESC limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1);
				pstmt.setInt(2, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
				while(rs.next()) {
					cloBean cb = new cloBean();
					cb.setNum(rs.getInt("num"));
					cb.setPass(rs.getString("pass"));
					cb.setPname(rs.getString("pname"));
					cb.setPrice(rs.getString("price"));
					cb.setFactory(rs.getString("factory"));
					cb.setImage(rs.getString("image"));
					
					cbList.add(cb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return cbList;
		}

		public List<cloBean> getBoardList(int startRow,int pageSize,String search) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<cloBean> cbList = new ArrayList<cloBean>();
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql
//				String sql="select * from board where subject like '%�˻���%' order by num desc limit ?,?";
				String sql="select * from clo where pname like ? order by num desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 ���� �������
				rs=pstmt.executeQuery();
				//5 ��� ���������� �̵� �Խ��� �ϳ� BoardBean ��ü���� ����
				//   BoardBean �迭 ��ĭ�� ���� 
				while(rs.next()) {
					cloBean cb = new cloBean();
					cb.setNum(rs.getInt("num"));
					cb.setPass(rs.getString("pass"));
					cb.setPname(rs.getString("pname"));
					cb.setPrice(rs.getString("price"));
					cb.setFactory(rs.getString("factory"));
					cb.setImage(rs.getString("image"));
					
					cbList.add(cb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return cbList;
		}
		
		public cloBean getBoard(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			cloBean cb=new cloBean();
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql ���� �غ�
				String sql="select * from clo where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 sql ���� => ��� ����
				rs=pstmt.executeQuery();
				//5 ��� ������ ������ ������ bb�ٱ��Ͽ� rs �����³��� ����
				if(rs.next()) {
					cb.setNum(rs.getInt("num"));
					cb.setPass(rs.getString("pass"));
					cb.setPname(rs.getString("pname"));
					cb.setPrice(rs.getString("price"));
					cb.setFactory(rs.getString("factory"));
					cb.setImage(rs.getString("image"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//������ // insert���� ����� ���尴ü(������) ����
				if(rs!=null) 	    try {	rs.close(); } catch (SQLException ex) { }
				if(pstmt!=null) 	try {	pstmt.close(); } catch (SQLException ex) { }
				if(con!=null) 	    try {	con.close(); } catch (SQLException ex) { }
			}
			return cb;
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
				String sql="select * from clo where num=?";
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
		
		
		public void updateBoard(cloBean cb) {
			Connection con=null;
			PreparedStatement pstmt=null;
			
			try {
				//1,2 ��񿬰�
				con=getConnection();
				//3 sql update
				String sql="update clo set pname=?,price=?,factory=?,image=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, cb.getPname());
				pstmt.setString(2, cb.getPrice());
				pstmt.setString(3, cb.getFactory());
				pstmt.setString(4, cb.getImage());
				pstmt.setInt(5, cb.getNum());
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
				String sql="delete from clo where num=?";
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
