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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_sequence")
	@SequenceGenerator(name="user_sequence", sequenceName="user_seq")
	private Integer user_id;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
//	@ManyToMany	(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<Book> books = new LinkedList<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserBook> books;	
	

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer userId) {
		this.user_id = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Set<UserBook> getBooks() {
		return books;
	}

	public void setBooks(Set<UserBook> books) {
		this.books = books;
	}

	public User() {
		this.books = new HashSet<UserBook>();
	}

	public User(String userName, Set<UserBook> books) {
		this.userName = userName;
		this.books = books;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
}

