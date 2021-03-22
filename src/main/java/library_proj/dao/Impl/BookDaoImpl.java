package library_proj.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_proj.dao.BookDao;
import library_proj.dto.Book;
import library_proj.dto.BookCategory;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.util.JdbcUtil;

public class BookDaoImpl implements BookDao {
	
	private static BookDaoImpl instance = new BookDaoImpl();
	
	public static BookDaoImpl getInstance() {
		return instance;
	}
	
	private BookDaoImpl() {};

	@Override
	public List<Book> selectBookByAll() {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange from book";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				List<Book> list = new ArrayList<Book>(); 
				do {
					list.add(getBook(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Book getBook(ResultSet rs) throws SQLException {
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
			count = rs.getInt("count");
		} catch (SQLException e) {
		}
		
		try {
			rentalRange = rs.getInt("rentalrange");
		} catch (SQLException e) {
		}
		
		return new Book(bookNo, bookTitle, isRented, bookCategory, count, rentalRange);
	}

	@Override
	public List<Book> selectBookByNo(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where bookno like ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> selectBookByNoForRent(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where bookno like ? and isRented = 1";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public List<Book> selectBookByTitle(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where booktitle like?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookTitle());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Book> selectBookByTitleForRent(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where booktitle like ? and isRented = 1";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookTitle());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public List<Book> selectBookByCategory(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where bookcategory like ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, book.getBookCategory().getBookCategory());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Book> selectBookByCategoryForRent(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, count, rentalrange"
				+ " from book"
				+ " where bookcategory like ? and isRented = 1";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, book.getBookCategory().getBookCategory());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBook(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public int insertBook(Book book) {
//		new Book(bookNo, bookTitle, isRented, bookCategory, count, rentalRange)
		String sql ="insert into book"
				+ " values (?, ?, ?, ?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setInt(3, book.getIsRented());
			pstmt.setInt(4, book.getBookCategory().getBookCategory());
			pstmt.setInt(5, book.getCount());
			pstmt.setInt(6, book.getRentalRange());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBook(Book book) {
		String sql = "update book set bookno = ?, booktitle = ?, isRented = ?, bookcategory = ?, count = ?, rentalrange = ? where bookno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setInt(3, book.getIsRented());
			pstmt.setInt(4, book.getBookCategory().getBookCategory());
			pstmt.setInt(5, book.getCount());
			pstmt.setInt(6, book.getRentalRange());
			pstmt.setString(7, book.getBookNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBook(Book book) {
		String sql ="delete from book"
				+ " where bookno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Book> selectBookByUserNo(User user) {
		String sql = "select bookno, booktitle, rentalno, delaydate, rentaldate from vw_all where userno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, user.getUserNo());
			try(ResultSet rs= pstmt.executeQuery();
					){
				if(rs.next()) {
					List<Book> list = new ArrayList<Book>();
					do {
						list.add(getBookUser(rs));
					} while(rs.next());
					return list;
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Book getBookUser(ResultSet rs) {
		String bookNo = null;
		String bookTitle = null;
		RentalStatus rentalstatus = null;

		try {
			bookNo = rs.getString("bookno");
		} catch (SQLException e) {
		}

		try {
			bookTitle = rs.getString("booktitle");
		} catch (SQLException e) {
		}
		
		try {
			rentalstatus = new RentalStatus(rs.getInt("rentalno"));
		} catch (SQLException e) {
		}
		
		try {
			rentalstatus.setDelayDate(rs.getInt("delaydate"));
		} catch (SQLException e) {
		}
		
		try {
			rentalstatus.setRentalDate(rs.getDate("rentaldate"));
		} catch (SQLException e) {
		}
		return new Book(bookNo, bookTitle, rentalstatus);
	}

	@Override
	public Book selectBookByNoForDetail(Book book) {
		String sql = "select bookno, booktitle, isRented, bookcategory, categoryname, count, rentalrange"
				+ " from vw_book"
				+ " where bookno = ?";
//		String sql = "select bookno, booktitle, isRented, bookcategory, categoryname, count, rentalrange"
//				+ " from vw_book"
//				+ " where bookno = ? and isRented = 1";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getBookDetail(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
