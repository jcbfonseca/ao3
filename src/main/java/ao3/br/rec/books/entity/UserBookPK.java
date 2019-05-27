package ao3.br.rec.books.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserBookPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private int user_id;

	@Column(name = "book_id")
	private int book_id;


	public UserBookPK() {

	}

	public UserBookPK(int userId, int bookId) {
		this.user_id = userId;
		this.book_id = bookId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

}