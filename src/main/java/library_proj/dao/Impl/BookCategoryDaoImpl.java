package library_proj.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_proj.dao.BookCategoryDao;
import library_proj.dto.BookCategory;
import library_proj.util.JdbcUtil;

public class BookCategoryDaoImpl implements BookCategoryDao {

	private static final BookCategoryDaoImpl instance = new BookCategoryDaoImpl();
	
	public static BookCategoryDaoImpl getInstance() {
		return instance;
	}
	
	private BookCategoryDaoImpl() {};
	
	@Override
	public List<BookCategory> selectBookCategoryByAll() {
		String sql = "select bookcategory, categoryname from bookcategory";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				List<BookCategory> list = new ArrayList<BookCategory>();
				do {
					list.add(getBookCategory(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BookCategory getBookCategory(ResultSet rs) throws SQLException {
		int bookCategory = rs.getInt("bookcategory");
		String categoryName = rs.getString("categoryname");
		
		return new BookCategory(bookCategory, categoryName);
	}

	@Override
	public BookCategory selectBookCategoryByNo(BookCategory bookcategory) {
		String sql = "select bookcategory, categoryname from bookcategory where bookcategory = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, bookcategory.getBookCategory());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getBookCategory(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertBookCategory(BookCategory bookcategory) {
		String sql = "insert into bookcategory values(?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, bookcategory.getBookCategory());
			pstmt.setString(2, bookcategory.getCategoryName());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBookCategory(BookCategory bookcategory) {
		String sql = "update bookcategory set bookcategory = ?, categoryname = ? where bookcategory = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, bookcategory.getBookCategory());
			pstmt.setString(2, bookcategory.getCategoryName());
			pstmt.setInt(3, bookcategory.getBookCategory());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBookCategory(BookCategory bookcategory) {
		String sql = "delete from bookcategory where bookcategory = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, bookcategory.getBookCategory());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
