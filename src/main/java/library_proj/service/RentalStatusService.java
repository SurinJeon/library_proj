package library_proj.service;

import java.util.List;

import library_proj.dao.RentalStatusDao;
import library_proj.dao.Impl.RentalStatusDaoImpl;
import library_proj.dto.Book;
import library_proj.dto.RentalStatus;
import library_proj.dto.User;

public class RentalStatusService {

	private RentalStatusDao daoRental = RentalStatusDaoImpl.getInstance();
	
	public List<RentalStatus> showRentalBooks(User user){
		return daoRental.selectRentalStatusByUserNo(user);
	}
	public RentalStatus showUserByBookTitle(Book book) {
		return daoRental.selectRentalStatusByBookNo(book);
	}
	
	public RentalStatus showUserByBookTitleBookView(Book book) {
		return daoRental.selectRentalStatusByBookNoBookView(book);
	}
	
	public int updateRentalStatusLogIn() {
		return daoRental.updateRentalStatusLogIn();
	}

	public RentalStatus showUserByBookTitleNoView(Book book) {
		return daoRental.selectRentalStatusByBookNoBookView(book);
	}
}
