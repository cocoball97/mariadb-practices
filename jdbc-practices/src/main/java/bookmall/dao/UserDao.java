package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.UserVo;

public class UserDao {

	public int insert(UserVo vo) {

		int count = 0;

		try (Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement("insert into user values (null, ?, ?, ?, ?)");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
		) {
			pstmt1.setString(1, vo.getName());
			pstmt1.setString(2, vo.getPhone());
			pstmt1.setString(3, vo.getEmail());
			pstmt1.setString(4, vo.getPassword());

			count = pstmt1.executeUpdate();

			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();
		} catch (SQLException e) {
			System.out.println("user error1:" + e);
		}

		return count;
	}
	
	public int deleteByNo(Long no) {
		int count = 0;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from user where no = ?");
		) {
			pstmt.setLong(1, no);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("user error2:" + e);
		}
		
		return count;	
	}	


	public List<UserVo> findAll() {
		List<UserVo> result = new ArrayList<>();
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select no, name, phone, email, password from user order by no desc");
		) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
				UserVo vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setEmail(email);
				vo.setPassword(password);
				
				result.add(vo);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("user error3:" + e);
		}
		
		return result;
	} 
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.35.241:3306/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
}