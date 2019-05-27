package ao3.br.rec.books.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ao3.br.rec.books.dto.BookDto;
import ao3.br.rec.books.dto.UserBookDTO;
import ao3.br.rec.books.entity.User;

@Service
public interface BookService {
	public BookDto getBookById(Integer bookId);

	public void saveBook(BookDto bookDto, User user);
	
	public void deleteUserBook(int userId, int bookdId);


	public UserBookDTO saveUserBook(UserBookDTO userBookDto, User user);
	
	public UserBookDTO getUserBook(int bookId, User user);

	public List<BookDto> getBooksByUserId(int userId);
}
