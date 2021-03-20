package library_proj.service;

import java.util.List;

import library_proj.dao.UserDao;
import library_proj.dao.Impl.UserDaoImpl;
import library_proj.dto.User;

public class UserService {

	private UserDao daoUser = UserDaoImpl.getInstance();
	
	public List<User> showUsers(){

		List<User> list = daoUser.selectUserByAllForList();
		list.stream().forEach(System.out::println);
		return daoUser.selectUserByAllForList();
	}
	
	
	public List<User> showUsersByUserNo(User user){
//		List<User> list = daoUser.selectUserByNo(user);
		return daoUser.selectUserByNo(user);
		
	}
	
	public List<User> showUsersByUserName(User user){
		return daoUser.selectUserByName(user);
	}
	
	public List<User> showUsersByUserAccount(User user){
		return daoUser.selectUserByAccount(user);
	}
	
	public List<User> showUsersByUserPhone(User user){
		return daoUser.selectUserByPhone(user);
	}
	
	public User showUserByUserNoForDetail(User user) {
		return daoUser.selectUserByNoForDetail(user);
	}
}
