package library_proj.dao;

import java.util.List;

import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;

public interface UserDao {

	List<User> selectUserByAll();
	List<User> selectUserByNo(User user);
	List<User> selectUserByName(User user);
	List<User> selectUserByPhone(User user);
	List<User> selectUserByAccount(User user);
	List<User> selectUserByBirth(User user);
	List<User> selectUserByView(User user, Book book, RentalStatus rentalStatus);
	int insertUser(User user);
	int updateUser(User user);
	int deleteUser(User user);
	
}
