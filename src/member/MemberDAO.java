package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	// ��񿬰� �޼���
	public Connection getConnection() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con = ds.getConnection();
		return con;
	}
	
	// insertMember(mb) �޼��� ����(���ϰ� ����)
	public void insertMember(MemberBean mb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "INSERT INTO member(id,pass,name,email,mobile,phone,postcode,address,detailAddress) VALUES(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getPass());
			pstmt.setString(3, mb.getName());
			pstmt.setString(4, mb.getEmail());
			pstmt.setString(5, mb.getMobile());
			pstmt.setString(6, mb.getPhone());
			pstmt.setString(7, mb.getPostcode());
			pstmt.setString(8, mb.getAddress());
			pstmt.setString(9, mb.getDetailAddress());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch (Exception ex) {}
			if(con != null)   try {con.close();} catch (Exception ex) {}
		}
		
	
	} //�޼���
	
	
	// int check = mdao.userCheck(id,pass);
	public int userCheck(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int check = -1;
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pass.equals(rs.getString("pass"))) {
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null)    try {rs.close();} catch (Exception ex) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(con != null)   try { con.close(); } catch (SQLException ex) {}
		}
		
		return check;
	}
	
	public MemberBean getMember(String id) {
		MemberBean mb = new MemberBean();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setEmail(rs.getString("email"));
				mb.setMobile(rs.getString("mobile"));
				mb.setPhone(rs.getString("phone"));
				mb.setPostcode(rs.getString("postcode"));
				mb.setAddress(rs.getString("address"));
				mb.setDetailAddress(rs.getString("detailAddress"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null)    try {rs.close();} catch (Exception ex) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(con != null)   try { con.close(); } catch (SQLException ex) {}
		}
		
		return mb;
	}
	
	public void updateMember(MemberBean mb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "UPDATE member SET name=?,email=?,mobile=?,phone=?,postcode=?,address=?,detailAddress=? WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getName());
			pstmt.setString(2, mb.getEmail());
			pstmt.setString(3, mb.getMobile());
			pstmt.setString(4, mb.getPhone());
			pstmt.setString(5, mb.getPostcode());
			pstmt.setString(6, mb.getAddress());
			pstmt.setString(7, mb.getDetailAddress());
			pstmt.setString(8, mb.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(con != null)   try { con.close(); } catch (SQLException ex) {}
		}
	}
	
	public int idDupcheck(String id) {
		int check=-1;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//1,2 ��񿬰�
			con=getConnection();
			//3 sql ���� �غ�  id=?
			String sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4 sql���� ��� ����
			rs=pstmt.executeQuery();
			// 5 ���������� �̵� ������  ������ ������ check=1
			//                                             ������ check=-1
			if(rs.next()) {
				check=1; // ���̵� ����, ���̵� �ߺ�
			}else {
				check=-1; // ���̵� ����, ���̵� ��밡��
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch (SQLException ex) {}
			if(pstmt!=null) try {pstmt.close();}catch (SQLException ex) {}
			if(con!=null) try {con.close();}catch (SQLException ex) {}
		}
		return check;
	}
	
	// ���̵�üũ
	public int idCheck(String id) {
		String lengthRegex = "[a-z][a-z0-9]{5,14}"; // ����
		String engLowerRegex = "[a-z]"; // ������ �ҹ���
		String numRegex = "[0-9]"; // ����
		
		int count = 0;
		
		  if(Pattern.matches(lengthRegex, id)) {
		        count += Pattern.compile(engLowerRegex).matcher(id).find() ? 1 : 0;
		        count += Pattern.compile(numRegex).matcher(id).find() ? 1 : 0;
		    }
		    return count;
	}
	
	// ��й�ȣ üũ
	public int passCheck(String pass) {
		String lengthRegex = "[A-Za-z0-9!@#$%]{8,16}"; // ����
		String engUpperRegex = "[A-Z]"; // ������ �빮��
	    String engLowerRegex = "[a-z]"; // ������ �ҹ���
	    String numRegex = "[0-9]"; // ����
	    String specRegex = "[!@#$%]"; // Ư������
	    
	    int count = 0;
	    
	    if(Pattern.matches(lengthRegex, pass)) {
	    	count += Pattern.compile(engUpperRegex).matcher(pass).find() ? 1 : 0;
	        count += Pattern.compile(engLowerRegex).matcher(pass).find() ? 1 : 0;
	        count += Pattern.compile(numRegex).matcher(pass).find() ? 1 : 0;
	        count += Pattern.compile(specRegex).matcher(pass).find() ? 1 : 0;
	    }
	    return count;
	}
	
	
	
} //Ŭ����
