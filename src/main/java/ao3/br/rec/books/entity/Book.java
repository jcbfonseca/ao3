package ao3.br.rec.books.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="book_sequence")
	@SequenceGenerator(name="book_sequence", sequenceName="book_seq")
	private Integer book_id;
	
	@Column(unique=true)
	private String title;
	
	@Column
	private String authors;

	@Column
	private String imgLink;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    private Set<UserBook> users;	
	
	
	public Book() {
		this.users = new HashSet<UserBook>();
	}

	public Book(Integer id, String title, String authors, String link) {
		super();
		this.book_id = id;
		this.title = title;
		this.authors = authors;
		this.imgLink = link;
		this.users = new HashSet<UserBook>();
	}

	public Integer getId() {
		return book_id;
	}

	public void setId(Integer id) {
		this.book_id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public Set<UserBook> getUsers() {
		return users;
	}

	public void setUsers(Set<UserBook> users) {
		this.users = users;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
}
