package library_proj.dao;

import java.util.List;

import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;

public interface RentalStatusDao {

	// join부분을 view로 만들어야됨 (잊지말기...)
	List<RentalStatus> selectRentalStatusByAll();
	List<RentalStatus> selectRentalStatusByUserNo(User user);
	int insertRentalStatus(RentalStatus rentalstatus);
	int updateRentalStatus(RentalStatus rentalstatus);
	int deleteRentalStatus(RentalStatus rentalstatus);
	RentalStatus selectRentalStatusByBookNo(Book book);
	RentalStatus selectRentalStatusByBookNoBookView(Book book);
	
}
