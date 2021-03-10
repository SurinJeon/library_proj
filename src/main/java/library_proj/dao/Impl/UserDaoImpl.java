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
import library_proj.dto.User;
import library_proj.util.JdbcUtil;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance = new UserDaoImpl();
	
	public static UserDaoImpl getInstance() {
		return instance;
	}
	
	private UserDaoImpl(){};
	
	
	@Override
	public List<User> selectUserByAll() {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if (rs.next()) {
				List<User> list = new ArrayList<User>();
				do {
					list.add(getUser(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private User getUser(ResultSet rs) throws SQLException {
		int userNo = rs.getInt("userno");
		String userName = rs.getString("username");
		Date userBirth = rs.getDate("userbirth");
		String account = rs.getString("account");
		String tel = rs.getString("tel");
		String phone = rs.getString("phone");
		String address = rs.getString("address");
		return new User(userNo, userName, userBirth, account, tel, phone, address);
	}

	@Override
	public List<User> selectUserByNo(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where userno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
				pstmt.setInt(1, user.getUserNo());

				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {
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
	public List<User> selectUserByName(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where username = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
				pstmt.setString(1, user.getUserName());

				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {
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
	public List<User> selectUserByPhone(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where phone = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
				pstmt.setString(1, user.getPhone());

				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {
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
	public List<User> selectUserByAccount(User user) {
		String sql = "select userno, username, userbirth, account, tel, phone, address from user where account = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
				pstmt.setString(1, user.getAccount());

				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {
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
	public int insertUser(User user) {
		String sql ="insert into book"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, user.getUserNo());
			pstmt.setString(2, user.getUserName());
			pstmt.setTimestamp(3, new Timestamp(user.getUserBirth().getTime()));
			pstmt.setString(4, "account");
			pstmt.setString(5, "tel");
			pstmt.setString(6, "phone");
			pstmt.setString(7, "address");
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
