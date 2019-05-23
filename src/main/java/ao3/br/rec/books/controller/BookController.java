package ao3.br.rec.books.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import ao3.br.rec.books.dto.BookDto;
import ao3.br.rec.books.dto.UserBookDTO;
import ao3.br.rec.books.entity.User;
import ao3.br.rec.books.service.BookService;
import ao3.br.rec.books.utils.Constants;

@RequestMapping("/book")
@RestController
public class BookController {
	@Autowired
	BookService bookService;

	//REST - POST
	@RequestMapping(value = Constants.SAVE_BOOK, method = RequestMethod.POST)
	public void saveBook(@RequestBody BookDto bookDto) {
		System.out.println("---> BookController.saveBook()");
		
		Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
		bookService.saveBook(bookDto, (User)details);
	}
	
	//REST - PATCH
	@RequestMapping(value = Constants.SAVE_USER_BOOK, method = RequestMethod.PATCH, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserBookDTO saveUserBook(@PathVariable Integer book_id, @RequestBody String body) {
		
		Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		UserBookDTO ubDto = bookService.getUserBook(book_id, (User)details);
		
		if(body.contains("startDate")) {
			ubDto.setStartDate(new Date());
		} else if(body.contains("endDate")) {
			ubDto.setEndDate(new Date());
		}  else if(body.contains("star")) {
			
			try {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Integer> map = mapper.readValue(body, Map.class);
				int v = map.get("star");
				ubDto.setStar(v);
				
			} catch (Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return bookService.saveUserBook(ubDto, (User)details);
	}
	
	//REST - GET
	@RequestMapping(value = Constants.GET_USER_BOOK, method = RequestMethod.GET)
	public UserBookDTO getUserBook(@PathVariable Integer book_id) {
		System.out.println("BookController.getUserBook()");

		Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		return bookService.getUserBook(book_id, (User)details);
	}
	
	
	//TODO - depois migrar para deixar correto de acordo com o padrao REST
	@RequestMapping(value = Constants.GET_ALL_BOOKS_BY_USER_ID, method = RequestMethod.GET)
	public List<BookDto> getAllBooksByUserId() {
		System.out.println("BookController.getAllBooksByUserId()");
		
		Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
		List<BookDto> books = bookService.getBooksByUserId(((User)details).getUserId());
		
		return books;
	}

	

}