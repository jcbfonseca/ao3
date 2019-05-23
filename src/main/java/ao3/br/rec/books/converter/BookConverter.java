package ao3.br.rec.books.converter;

import ao3.br.rec.books.dto.BookDto;
import ao3.br.rec.books.entity.Book;

public class BookConverter {
	public static Book dtoToEntity(BookDto dto) {
		
		Book book = new Book();
		book.setId(dto.getId());
		book.setAuthors(dto.getAuthors());
		book.setImgLink(dto.getImgLink());
		book.setTitle(dto.getTitle());
		
		return book;
	}

	public static BookDto entityToDto(Book book) {
		BookDto dto = new BookDto();
		dto.setId(book.getId());
		dto.setAuthors(book.getAuthors());
		dto.setImgLink(book.getImgLink());
		dto.setTitle(book.getTitle());

		return dto;
	}
}
