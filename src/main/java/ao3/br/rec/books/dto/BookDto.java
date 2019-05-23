package ao3.br.rec.books.dto;

public class BookDto {

	private Integer id;

	private String title;

	private String authors;

	private String imgLink;

	private byte[] cover;

	public BookDto() {
		
	}

	public BookDto(Integer id, String title, String authors, String imgLink, byte[] cover) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.imgLink = imgLink;
		this.cover = cover;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}

}
