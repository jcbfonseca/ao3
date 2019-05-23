package ao3.br.rec.books.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ao3.br.rec.books.dto.UserDto;
import ao3.br.rec.books.service.UserService;
import ao3.br.rec.books.utils.Constants;

@RequestMapping("/user")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = Constants.GET_ALL_USERS_BY_BOOK_ID, method = RequestMethod.GET)
	public List<UserDto> getAllUsersByBookId(@PathVariable Integer book_id) {
		System.out.println("BookController.getAllBooksByUserId(" + book_id + ")");
		
		return userService.getUsersByBookId(book_id);
	}
	
}