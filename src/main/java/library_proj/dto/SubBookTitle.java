package library_proj.dto;

public class SubBookTitle extends Book{

	private String bookTitle;

	public SubBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
		super.setBookTitle(bookTitle);
	}

}
