package library_proj.dto;

public class subBookTitle extends Book{

	private String bookTitle;

	public subBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
		super.setBookTitle(bookTitle);
	}

}
