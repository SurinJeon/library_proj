package library_proj.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library_proj.dao.UserDao;
import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;
import library_proj.util.JdbcUtil;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance = new UserDaoImpl();

	public static UserDaoImpl getInstance() {
		return instance;
	}

	private UserDaoImpl() {
	};

	@Override
	public List<User> selectUserByAll() {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				List<User> list = new ArrayList<User>();
				do {
					list.add(getUser(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private User getUser(ResultSet rs) throws SQLException {
		int userNo = 0;
		String userName = null;
		Date userBirth = null;
		String account = null;
		String tel = null;
		String phone = null;
		String address = null;
		
		try {
			userNo = rs.getInt("userno");
		} catch (SQLException e) {
		}
		
		try {
			userName = rs.getString("username");
		} catch (SQLException e) {
		}

		try {
			userBirth = rs.getDate("userbirth");
		} catch (SQLException e) {
		}
		
		try {
			account = rs.getString("account");
		} catch (SQLException e) {
		}
		
		try {
			tel = rs.getString("tel");
		} catch (SQLException e) {
		}
		
		try {
			phone = rs.getString("phone");
		} catch (SQLException e) {
		}
		
		try {
			address = rs.getString("address");
		} catch (SQLException e) {
		}
		
		return new User(userNo, userName, userBirth, account, tel, phone, address);
	}

	@Override
	public List<User> selectUserByNo(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where userno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserByName(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where username like ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, "%"+user.getUserName() +"%");

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserByPhone(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where phone like ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, user.getPhone());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserByAccount(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where account like ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, user.getAccount());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<User> selectUserByBirth(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where userbirth = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setTimestamp(1, new Timestamp(user.getUserBirth().getTime()));
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
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
	public List<User> selectUserByView(User user, Book book, RentalStatus rentalStatus) {
		String sql = "select userno, username, userbirth, address " + 
				"from vw_all where bookno = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
//			pstmt.setTimestamp(1, new Timestamp(user.getUserBirth().getTime()));
			pstmt.setString(1, book.getBookNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<User> list = new ArrayList<User>();
					do {
						list.add(getUser(rs));
					} while(rs.next());
					return list;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



/*
	private Object getUserByBook(ResultSet rs) throws SQLException {
		int userNo = rs.getInt("userno");
		String userName = rs.getString("username");
		Date userBirth = rs.getDate("userBirth");
		return new User(userNo, userName, userBirth);
	}
*/

	@Override
	public int insertUser(User user) {
		String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());
			pstmt.setString(2, user.getUserName());
			pstmt.setTimestamp(3, new Timestamp(user.getUserBirth().getTime()));
			pstmt.setString(4, user.getAccount());
			pstmt.setString(5, user.getTel());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getAddress());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUser(User user) {
		String sql = "update user set"
				+ " userno = ?,"
				+ " username = ?,"
				+ " userbirth = ?,"
				+ " account = ?,"
				+ " tel = ?,"
				+ " phone = ?,"
				+ " address = ?"
				+ " where userno = ?";

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());
			pstmt.setString(2, user.getUserName());
			pstmt.setTimestamp(3, new Timestamp(user.getUserBirth().getTime()));
			pstmt.setString(4, user.getAccount());
			pstmt.setString(5, user.getTel());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getAddress());
			pstmt.setInt(8, user.getUserNo());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(User user) {
		String sql = "delete from user where userno = ?;";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user.getUserNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> selectUserByAllForList() {
		String sql = "select userno, username, tel, phone from user";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				) {
			if (rs.next()) {
				List<User> list = new ArrayList<User>();
				do {
					list.add(getUserByList(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private User getUserByList(ResultSet rs) throws SQLException {
		
		int userNo = rs.getInt("userno");
		String userName = rs.getString("username");
		String tel = rs.getString("tel");
		String phone = rs.getString("phone");
		return new User(userNo, userName, tel, phone);
	}

	@Override
	public User selectUserByNoForDetail(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where userno = ?";
			try (Connection con = JdbcUtil.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					) {
				pstmt.setInt(1, user.getUserNo());
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return getUser(rs);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}


}
