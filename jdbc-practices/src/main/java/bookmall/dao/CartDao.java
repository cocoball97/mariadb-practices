package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {

	public int insert(CartVo vo) {

		int count = 0;

		try (Connection conn = getConnection();
				
				PreparedStatement pstmt1 = conn.prepareStatement(
						"insert into cart (title, quantity, price, book_no, user_no) "
						+ "select a.title, ?, a.price, ?, ? "
						+ "from book a "
						+ "where a.no = ?");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
		) {
			pstmt1.setObject(1, vo.getQuantity());
			pstmt1.setObject(2, vo.getBookNo());
			pstmt1.setObject(3, vo.getUserNo());
			pstmt1.setObject(4, vo.getBookNo());

			count = pstmt1.executeUpdate();

			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();
		} catch (SQLException e) {
			System.out.println("cart error1:" + e);
		}

		return count;
	}


	public int deleteByNo(Long no) {
		int count = 0;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from cart where no =?");
		) {
			pstmt.setLong(1, no);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("cart error2:" + e);
		}
		
		return count;	
	}	
	
	public int deleteByUserNoAndBookNo(Long user_no, Long no) {
		int count = 0;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from cart where user_no = ? and no = ?");
		) {
			pstmt.setLong(1, user_no);
			pstmt.setLong(2, no);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("cart error3:" + e);
		}
		
		return count;	
	}	
	
	public List<CartVo> findByUserNo(Long No) {
		List<CartVo> result = new ArrayList<>();
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select no, title, quantity, price, book_no, user_no from cart where user_no = ?");
		) {
			pstmt.setLong(1, No);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long quantity = rs.getLong(3);
				Long price = rs.getLong(4);
				Long book_no = rs.getLong(5);
				Long user_no = rs.getLong(6);
				
				CartVo vo = new CartVo();
				vo.setNo(no);
				vo.setBookTitle(title);
				vo.setQuantity(quantity);
				vo.setPrice(price);
				vo.setBookNo(book_no);
				vo.setUserNo(user_no);
				
				result.add(vo);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("cart error4:" + e);
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