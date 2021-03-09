package library_proj.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library_proj.dao.ManagerDao;
import library_proj.dto.Manager;
import library_proj.util.JdbcUtil;

public class ManagerDaoImpl implements ManagerDao {
	/* singleton pattern */
	private static final ManagerDaoImpl instance = new ManagerDaoImpl();
	
	public static ManagerDaoImpl getInstance() {
		return instance;
	}
	
	private ManagerDaoImpl() {};

	@Override
	public List<Manager> selectManagerByAll() {
		String sql = "select mngaccount, passwd from manager";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				List<Manager> list = new ArrayList<Manager>(); 
				do {
					list.add(getManager(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Manager getManager(ResultSet rs) throws SQLException {
		String mngAccount = rs.getString("mngaccount");
		String passwd = rs.getString("passwd");
		return new Manager(mngAccount, passwd);
	}

	@Override
	public Manager selectManagerByNo(Manager manager) {
		String sql = "select mngaccount, passwd from manager where mngaccount = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, manager.getMngAccount());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getManager(rs);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertManager(Manager manager) {
		String sql = "insert into manager values (?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, manager.getMngAccount());
			pstmt.setString(2, manager.getPasswd());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateManager(Manager manager) {
		String sql = "update manager set mngaccount = ?, passwd = ? where mngaccount = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, manager.getMngAccount());
			pstmt.setString(2, manager.getPasswd());
			pstmt.setString(3, manager.getMngAccount());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteManager(Manager manager) {
		String sql = "delete from manager where mngaccount = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, manager.getMngAccount());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
