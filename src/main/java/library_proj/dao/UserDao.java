package library_proj.dao;

import java.util.List;

import library_proj.dto.User;

public interface UserDao {

	List<User> selectUserByAll();
	User selectUserByNo(User user);
	List<User> selectUserByName();
	List<User> selectUserByPhone();
	List<User> selectUserByAccount();
	int insertUser(User user);
	int updateUser(User user);
	int deleteUser(User user);
	
}
