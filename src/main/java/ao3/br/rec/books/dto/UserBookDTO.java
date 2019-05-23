package ao3.br.rec.books.dto;

import java.util.Date;

public class UserBookDTO {

    private Integer user_id;

    private Integer book_id;	
	
	private UserDto user;

	private BookDto book;

	private Date startDate;
	
	private Date endDate;
	
	private int progress;
	
	private int star;

	private String opinion;
	
	public UserBookDTO() {
		
	}
	
	public UserBookDTO(Long id, UserDto user, BookDto book, Date startDate, Date endDate, int progress,
			int star, String opinion) {
		super();
		this.user = user;
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
		this.progress = progress;
		this.star = star;
		this.opinion = opinion;
	}
	
   
	
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public BookDto getBook() {
		return book;
	}
	public void setBook(BookDto book) {
		this.book = book;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int score) {
		this.star = score;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}
