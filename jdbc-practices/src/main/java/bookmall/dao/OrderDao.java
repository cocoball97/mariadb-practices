package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;


public class OrderDao {

	public int insert(OrderVo vo) {
		int count = 0;

		try (
				Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement(
						"insert into orders (number, name, status, payment, shipping, user_no) "
								+ "select ?, a.name, ?, ?, ?, ? "
								+ "from user a "
								+ "where a.no = ?");
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
				
		) {
			pstmt1.setString(1, vo.getNumber());
			pstmt1.setString(2, vo.getStatus());
			pstmt1.setLong(3, vo.getPayment());
			pstmt1.setString(4, vo.getShipping());
			pstmt1.setLong(5, vo.getUserno());
			pstmt1.setLong(6, vo.getUserno());

			count = pstmt1.executeUpdate();

			ResultSet rs = pstmt2.executeQuery();
			vo.setNo(rs.next() ? rs.getLong(1) : null);
			rs.close();
		} catch (SQLException e) {
			System.out.println("order error1:" + e);
		}

		return count;
	}
	
	public int insertBook(OrderBookVo vo) {
		int count = 0;

		try (
				Connection conn = getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement(
						"insert into orders_book (title, quantity, price, book_no, order_no) "
								+ "select a.title, ?, ?, ?, ? "
								+ "from book a "
								+ "where a.no = ?"
			);
				
				PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
		) {			
			pstmt1.setLong(1, vo.getQuantity());
			pstmt1.setLong(2, vo.getPrice());
			pstmt1.setLong(3, vo.getBookNo());
			pstmt1.setLong(4, vo.getOrderNo());
			pstmt1.setLong(5, vo.getBookNo());
			

			count = pstmt1.executeUpdate();

		} catch (SQLException e) {
			System.out.println("order error2:" + e);
		}

		return count;
	}

	public int deleteByNo(Long no) {
		int count = 0;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from orders where no =?");
		) {
			pstmt.setLong(1, no);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("order error3:" + e);
		}
		
		return count;	
	}	
	

	public int deleteBooksByNo(Long order_no) {
		int count = 0;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from orders_book where order_no =?");
		) {
			pstmt.setLong(1, order_no);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("order error4:" + e);
		}
		
		return count;	
	}	
	
	
	public List<OrderBookVo> findBooksByNoAndUserNo(Long No, Long user_no) {
		List<OrderBookVo> result = new ArrayList<>();
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"select title, quantity, price, book_no, order_no "
					+ "from orders_book a join orders b on a.order_no = b.no " 
				    + "where a.order_no = ? and b.user_no = ? ");
		) {
			pstmt.setLong(1, No);
			pstmt.setLong(2, user_no);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString(1);
				Long quantity = rs.getLong(2);
				Long price = rs.getLong(3);
				Long book_no = rs.getLong(4);
				Long order_no = rs.getLong(5);
			
				
				OrderBookVo vo = new OrderBookVo();
				vo.setBookTitle(title);
				vo.setQuantity(quantity);
				vo.setPrice(price);
				vo.setBookNo(book_no);
				vo.setOrderNo(order_no);
				
				result.add(vo);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("order error5:" + e);
		}
		
		return result;
	} 
	
	public OrderVo findByNoAndUserNo(Long No, Long user_No) {
		OrderVo vo = null;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select no, number, name, status, payment, shipping, user_no from orders where no = ? and user_no = ? order by no desc");
		) {
			pstmt.setLong(1, No);
			pstmt.setLong(2, user_No);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String number = rs.getString(2);
				String name = rs.getString(3);
				String status = rs.getString(4);
				Long payment = rs.getLong(5);
				String shipping = rs.getString(6);
				Long user_no = rs.getLong(7);
				
				vo = new OrderVo();
				vo.setNo(no);
				vo.setNumber(number);
				vo.setName(name);
				vo.setStatus(status);
				vo.setPayment(payment);
				vo.setShipping(shipping);
				vo.setUserNo(user_no);
				
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("order error6:" + e);
		}
		
		return vo;
	} 
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.35.165:3306/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}


}
