package library_proj.dao;

import java.util.List;

import library_proj.dto.User;

public interface UserDao {

	List<User> selectUserByAll();
	List<User> selectUserByNo(User user);
	List<User> selectUserByName(User user);
	List<User> selectUserByPhone(User user);
	List<User> selectUserByAccount(User user);
	int insertUser(User user);
	int updateUser(User user);
	int deleteUser(User user);
	
}
