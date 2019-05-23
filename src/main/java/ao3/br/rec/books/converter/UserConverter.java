package ao3.br.rec.books.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ao3.br.rec.books.dto.BookDto;
import ao3.br.rec.books.dto.UserDto;
import ao3.br.rec.books.entity.User;
import ao3.br.rec.books.entity.UserBook;

public class UserConverter {
	public static User dtoToEntity(UserDto userDto) {
		User user = new User(userDto.getUserName(), null);
		user.setUserId(userDto.getUserId());
		
		Set<UserBook> ubs = new HashSet<UserBook>();
		
		System.out.println(userDto.getBookDtos());
		System.out.println(userDto.getBookDtos().size());
		
		for (BookDto b : userDto.getBookDtos()) {
			ubs.add(new UserBook(user, BookConverter.dtoToEntity(b)));
		}
		user.setBooks(ubs);
		return user;
	}

	public static UserDto entityToDto(User user) {
		UserDto userDto = new UserDto(user.getUserId(), user.getUserName(), null);
		List<BookDto> booksDto = new ArrayList<BookDto>();
		for (UserBook ub : user.getBooks()) {
			booksDto.add(BookConverter.entityToDto(ub.getBook()));
		}
		userDto.setBookDtos(booksDto);
		
		return userDto;
	}
}
