package library_proj.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library_proj.dto.Book;
import library_proj.dto.BookCategory;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.util.JdbcUtil;

public class ReturnService {

	public void transReturn(Book book) {
		String upRentalSql = "update rentalstatus set userreturndate = curdate() where bookno = ?";
		String upBookSql = "update book set isRented = 1 where bookno = ?";

		Connection con = null;
		PreparedStatement sPstmt = null;
		PreparedStatement uPstmt = null;

		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);

			sPstmt = con.prepareStatement(upRentalSql);
			sPstmt.setString(1, book.getBookNo());
			sPstmt.executeUpdate();

			uPstmt = con.prepareStatement(upBookSql);
			uPstmt.setString(1, book.getBookNo());
			uPstmt.executeUpdate();
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
