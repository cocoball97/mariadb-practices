package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;


public class OrderDao {

	public void insert(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " insert" + "   into orders" + " values (null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getNumber());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getStatus());
			pstmt.setLong(4, vo.getPayment());
			pstmt.setString(5, vo.getShipping());
			pstmt.setLong(6, vo.getUserno());
			

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void insertBook(OrderBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " insert" + "   into orders" + " values (null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getQuantity());
			pstmt.setLong(3, vo.getPrice());
			pstmt.setLong(4, vo.getBookNo());
			pstmt.setLong(5, vo.getOrderNo());
			

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void deleteByNo(long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
		
			String sql =
				"delete" + 
				"  from orders" +
				" where no = ?";			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 뭐냐 이거
	public void deleteBooksByNo(long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
		
			String sql =
				"delete" + 
				"  from orders_book" +
				" where no = ?";			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public List<CartVo> findBooksByNoAndUserNo(Long no, Long userno) {
		List<CartVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql =
				"select id, userno" +
				"  from cart" +
				" where no = ? and userno = ?";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			pstmt.setLong(1, no);
			pstmt.setLong(2, userno);
			
			
			while(rs.next()) {
				Long userno = rs.getLong(1);
				
				CartVo vo = new CartVo();
				vo.setUserNo(userno);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.35.57:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
}
