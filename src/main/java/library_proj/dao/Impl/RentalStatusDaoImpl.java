package library_proj.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library_proj.dao.RentalStatusDao;
import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.util.JdbcUtil;

public class RentalStatusDaoImpl implements RentalStatusDao {
	private static RentalStatusDaoImpl instance = new RentalStatusDaoImpl();
	
	public static RentalStatusDaoImpl getInstance() {
		return instance;
	}
	
	private RentalStatusDaoImpl() {
	}

	@Override
	public List<RentalStatus> selectRentalStatusByAll() {
		String sql = "select rentalno, bookno, userno, rentaldate, userreturndate, delaydate from rentalstatus";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				List<RentalStatus> list = new ArrayList<RentalStatus>();
				do {
					list.add(getRentalStatus(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private RentalStatus getRentalStatus(ResultSet rs) throws SQLException {
		int rentalNo = 0;
		Book bookNo = null;
		User userNo = null;
		Date rentalDate = null;
		Date userReturnDate = null;
		int delayDate = 0;

		try {
			rentalNo = rs.getInt("rentalno");
		} catch (SQLException e) {
		}

		try {
			bookNo = new Book(rs.getString("bookno"));
		} catch (SQLException e) {
		}

		try {
			userNo = new User(rs.getInt("userno"));
		} catch (SQLException e) {
		}

		try {
			rentalDate = rs.getDate("rentaldate");
		} catch (SQLException e) {
		}

		try {
			userReturnDate = rs.getDate("userreturndate");
		} catch (SQLException e) {
		}

		try {
			delayDate = rs.getInt("delayDate");
		} catch (SQLException e) {
		}
		
		return new RentalStatus(rentalNo, bookNo, userNo, rentalDate, userReturnDate, delayDate);
	}

	@Override
	public List<RentalStatus> selectRentalStatusByUserNo(User user) {
		String sql = "select rentalno, bookno, booktitle, rentaldate, userreturndate, delaydate"
				+ " from vw_all where userno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, user.getUserNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					List<RentalStatus> list = new ArrayList<RentalStatus>();
					do {
						list.add(getRentalStatusForView(rs));
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
	public RentalStatus selectRentalStatusByBookNo(Book book) {
		String sql = "select rentalno, bookno, userno, rentaldate, userreturndate, delaydate"
				+ " from vw_all where bookno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, book.getBookNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					return getRentalStatusForView(rs);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	private RentalStatus getRentalStatusForView(ResultSet rs) {
		/*
		 * int rentalNo = 0;
		Book bookNo = null;
		User userNo = null;
		Date rentalDate = null;
		Date userReturnDate = null;
		int delayDate = 0;
		 */
		int rentalNo = 0;
		Book bookNo = null;
		User userNo = null;
		Date rentalDate = null;
		Date userReturnDate = null;
		int delayDate = 0;
		
		try {
			rentalNo = rs.getInt("rentalno");
		} catch (SQLException e) {
		}

		try {
			bookNo = new Book(rs.getString("bookno"));
			bookNo.setBookTitle(rs.getString("booktitle"));
		} catch (SQLException e) {
		}
		
		try {
			userNo = new User(rs.getInt("userno"));
		} catch (SQLException e) {
		}

		try {
			rentalDate = rs.getDate("rentaldate");
		} catch (SQLException e) {
		}

		try {
			userReturnDate = rs.getDate("userreturndate");
		} catch (SQLException e) {
		}

		try {
			delayDate = rs.getInt("delayDate");
		} catch (SQLException e) {
		}
		
		return new RentalStatus(rentalNo, bookNo, userNo, rentalDate, userReturnDate, delayDate);
	}

	@Override
	public int insertRentalStatus(RentalStatus rentalstatus) {
		String sql = "insert into rentalstatus values (null, ?, ?, ?, null, default(delaydate))";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, rentalstatus.getBookNo().getBookNo());
			pstmt.setInt(2, rentalstatus.getUserNo().getUserNo());
			pstmt.setTimestamp(3, new Timestamp(rentalstatus.getRentalDate().getTime()));
				
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateRentalStatus(RentalStatus rentalstatus) {
		String sql = "update rentalstatus "
				+ "set rentalno = ?, "
				+ "bookno = ?, "
				+ "userno = ?, "
				+ "rentaldate = ?, "
				+ "userreturndate = ?, "
				+ "delaydate = ? "
				+ "where rentalno = ?";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, rentalstatus.getRentalNo());
			pstmt.setString(2, rentalstatus.getBookNo().getBookNo());
			pstmt.setInt(3, rentalstatus.getUserNo().getUserNo());
			pstmt.setTimestamp(4, new Timestamp(rentalstatus.getRentalDate().getTime()));
			pstmt.setTimestamp(5, new Timestamp(rentalstatus.getUserReturnDate().getTime()));
			pstmt.setInt(6, rentalstatus.getDelayDate());
			pstmt.setInt(7, rentalstatus.getRentalNo());
				
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteRentalStatus(RentalStatus rentalstatus) {
		String sql = "delete from rentalstatus where rentalno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, rentalstatus.getRentalNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
