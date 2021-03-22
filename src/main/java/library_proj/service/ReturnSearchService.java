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

public class ReturnSearchService {

	public Book transReturnSearch(Book book) {
		String selectRentSql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from vw_all where bookno = ?";
		String updateRentSql = "update rentalstatus r left join book b on r.bookno = b.bookno left join user u on r.userno = u.userno"
				+ " set r.delaydate = curdate() - (r.rentaldate + b.rentalrange)" 
				+ " where b.bookno = ?";
		Connection con = null;
		PreparedStatement sPstmt = null;
		PreparedStatement uPstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);
			
			sPstmt = con.prepareStatement(selectRentSql);
			sPstmt.setString(1, book.getBookNo());
			rs = sPstmt.executeQuery();
				
			
			uPstmt = con.prepareStatement(updateRentSql);
			uPstmt.setString(1, book.getBookNo());
			uPstmt.executeUpdate();
			con.commit();
			
			if(rs.next()) {
				return getBookDetail(rs);
			}
			
			
			
		} catch (SQLException e) {
			rollbackUtil(con);
		}
		
		return null;
		
	}
	
	public void rollbackUtil(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private Book getBookDetail(ResultSet rs) {
		String bookNo = null;
		String bookTitle = null;
		int isRented = 0;
		BookCategory bookCategory = null;
		int count = 0;
		int rentalRange = 0;
		

		try {
			bookNo = rs.getString("bookno");
		} catch (SQLException e) {
		}

		try {
			bookTitle = rs.getString("booktitle");
		} catch (SQLException e) {
		}

		try {
			isRented = rs.getInt("isrented");
		} catch (SQLException e) {
		}
		
		try {
			bookCategory = new BookCategory(rs.getInt("bookcategory"));
		} catch (SQLException e) {
		}
		
		try {
			bookCategory.setCategoryName(rs.getString("categoryname"));
		} catch (Exception e) {
		}
	
		try {
			count = rs.getInt("count");
		} catch (SQLException e) {
		}
		
		try {
			rentalRange = rs.getInt("rentalrange");
		} catch (SQLException e) {
		}
		
		return new Book(bookNo, bookTitle, isRented, bookCategory, count, rentalRange);
	}
}
