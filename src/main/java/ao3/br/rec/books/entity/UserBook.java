package ao3.br.rec.books.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(UserBookPK.class)
public class UserBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column//(name = "user_id", insertable = false, updatable = false)
    private Integer user_id;

    @Id
    @Column//(name = "book_id", insertable = false, updatable = false)
    private Integer book_id;	
	
    @ManyToOne
	//@JoinColumn(name="user_id")
	private User user;

	@ManyToOne
	//@JoinColumn(name="book_id")
	private Book book;

	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
//	
	@Column
	private int progress;
//	
	@Column
	private Integer star=0;
	
//
	@Column
	private String opinion;
	
	public UserBook() {
		
	}
	
	public UserBook(User u, Book b) {
		this.user = u;
		this.book = b;
	}
	
	
	public User getUser() {
		return user;
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

	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
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

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

}

