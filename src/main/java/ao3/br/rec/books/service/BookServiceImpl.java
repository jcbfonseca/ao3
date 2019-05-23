package ao3.br.rec.books.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao3.br.rec.books.converter.BookConverter;
import ao3.br.rec.books.converter.UserBookConverter;
import ao3.br.rec.books.dto.BookDto;
import ao3.br.rec.books.dto.UserBookDTO;
import ao3.br.rec.books.entity.Book;
import ao3.br.rec.books.entity.User;
import ao3.br.rec.books.entity.UserBook;
import ao3.br.rec.books.repository.BookRepository;
import ao3.br.rec.books.repository.UserBookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserBookRepository userBookRepository;

	@Override
	public BookDto getBookById(Integer bookId) {
		System.out.println("BookServiceImpl.getBookById()");
		Book b = bookRepository.getOne(bookId);
		return BookConverter.entityToDto(b);
	}

	
	@Override
	public void saveBook(BookDto bookDto, User u) {
		System.out.println("BookServiceImpl.saveBook()");
		
		Book bookTitle = bookRepository.findByTitle(bookDto.getTitle());
		System.out.println(bookTitle);
		
		if(bookTitle != null) {
			System.out.println(bookTitle.getTitle());
			bookDto.setId(bookTitle.getId());
		}

		Book bookSaved = bookRepository.save(BookConverter.dtoToEntity(bookDto));
		UserBook ub = new UserBook();
		ub.setUser(u);
		ub.setUser_id(u.getUser_id());
		ub.setBook(bookSaved);
		ub.setBook_id(bookSaved.getId());
		
		userBookRepository.saveAndFlush(ub);
	}

	@Override
	public List<BookDto> getAllBooks() {
		System.out.println("BookServiceImpl.getAllBooks()");
		return bookRepository.findAll().stream().map(BookConverter::entityToDto).collect(Collectors.toList());
	}

	@Override
	public List<BookDto> getBooksByUserId(int userId) {
		System.out.println("BookServiceImpl.getAllBooks()");
		return bookRepository.getBooksByUserId(userId).stream().map(BookConverter::entityToDto).collect(Collectors.toList());
	}
	
	public UserBookDTO getUserBook(int bookId, User user) {
		System.out.println("BookId = " + bookId);

		UserBook ubValue = null;
		List<UserBook> ubs = userBookRepository.findAll();
		for (UserBook ub : ubs) {
			if(ub.getBook_id() == bookId && ub.getUser_id().intValue() == user.getUserId().intValue()) {
				ubValue = ub;
				break;
			}
		}
		
		return UserBookConverter.entityToDto(ubValue);
	}

		
	@Override
	public UserBookDTO saveUserBook(UserBookDTO userBookDto, User user) {
		System.out.println("BookServiceImpl.saveBook()");

		UserBook ub = UserBookConverter.dtoToEntity(userBookDto);
		ub.setUser_id(user.getUserId());
		ub.setUser(user);
		UserBook userBookSaved = userBookRepository.saveAndFlush(ub);
		
		return UserBookConverter.entityToDto(userBookSaved);
	}

}
