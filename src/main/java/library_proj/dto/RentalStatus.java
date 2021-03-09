package library_proj.dto;

import java.util.Date;

public class RentalStatus {

	private int rentalNo;
	private Book bookNo;
	private User userNo;
	private Date rentalDate;
	private Date userReturnDate;
	private Date delayDate;

	public RentalStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RentalStatus(int rentalNo) {
		this.rentalNo = rentalNo;
	}

	public RentalStatus(int rentalNo, Book bookNo, User userNo, Date rentalDate, Date userReturnDate, Date delayDate) {
		this.rentalNo = rentalNo;
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.rentalDate = rentalDate;
		this.userReturnDate = userReturnDate;
		this.delayDate = delayDate;
	}

	public int getRentalNo() {
		return rentalNo;
	}

	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}

	public Book getBookNo() {
		return bookNo;
	}

	public void setBookNo(Book bookNo) {
		this.bookNo = bookNo;
	}

	public User getUserNo() {
		return userNo;
	}

	public void setUserNo(User userNo) {
		this.userNo = userNo;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getUserReturnDate() {
		return userReturnDate;
	}

	public void setUserReturnDate(Date userReturnDate) {
		this.userReturnDate = userReturnDate;
	}

	public Date getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(Date delayDate) {
		this.delayDate = delayDate;
	}

	@Override
	public String toString() {
		return String.format(
				"RentalStatus [rentalNo=%s, bookNo=%s, userNo=%s, rentalDate=%s, userReturnDate=%s, delayDate=%s]",
				rentalNo, bookNo, userNo, rentalDate, userReturnDate, delayDate);
	}

	
}
