package library_proj.dto;

public class Book {

	private String bookNo;
	private String bookTitle;
	private int isRented;
	private BookCategory bookCategory;
	private int count;
	private int rentalRange;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String bookNo) {
		this.bookNo = bookNo;
	}

	public Book(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Book(String bookNo, String bookTitle, int isRented, BookCategory bookCategory, int count, int rentalRange) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.isRented = isRented;
		this.bookCategory = bookCategory;
		this.count = count;
		this.rentalRange = rentalRange;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getIsRented() {
		return isRented;
	}

	public void setIsRented(int isRented) {
		this.isRented = isRented;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRentalRange() {
		return rentalRange;
	}

	public void setRentalRange(int rentalRange) {
		this.rentalRange = rentalRange;
	}

	@Override
	public String toString() {
		return String.format("Book [bookNo=%s, bookTitle=%s, isRented=%s, bookCategory=%s, count=%s, rentalRange=%s]",
				bookNo, bookTitle, isRented, bookCategory, count, rentalRange);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookCategory == null) ? 0 : bookCategory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookCategory == null) {
			if (other.bookCategory != null)
				return false;
		} else if (!bookCategory.equals(other.bookCategory))
			return false;
		return true;
	}
	
	
	
}
