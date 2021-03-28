package library_proj.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import library_proj.dto.Book;
import library_proj.dto.User;
import library_proj.util.JdbcUtil;

public class RentalService {

	public void transRental(User user, Book book) {
		String bookSql = "insert into rentalstatus values (null, ?, ?, curdate(), null, default(delaydate))";
		String rentalstatusSql = "update book set isRented = 0 where bookno = ?";
		
		Connection con = null;
		PreparedStatement bPstmt = null;
		PreparedStatement rPstmt = null;
		
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			bPstmt = con.prepareStatement(bookSql);
			bPstmt.setString(1, book.getBookNo());
			bPstmt.setInt(2, user.getUserNo());
			bPstmt.executeUpdate();
			
			rPstmt = con.prepareStatement(rentalstatusSql);
			rPstmt.setString(1, book.getBookNo());
			rPstmt.executeUpdate();
			
			con.commit();
			
		} catch (SQLException e) {
			rollbackUtil(con);
		}
		
	}

	public void rollbackUtil(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
