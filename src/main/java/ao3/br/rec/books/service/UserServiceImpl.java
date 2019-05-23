package ao3.br.rec.books.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ao3.br.rec.books.converter.BookConverter;
import ao3.br.rec.books.converter.UserBookConverter;
import ao3.br.rec.books.converter.UserConverter;
import ao3.br.rec.books.dto.UserDto;
import ao3.br.rec.books.entity.User;
import ao3.br.rec.books.entity.UserBook;
import ao3.br.rec.books.repository.UserRepository;

@Component
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto getUserById(Integer userId) {
		System.out.println("UserServiceImpl.getUserById()");
		return UserConverter.entityToDto(userRepository.getOne(userId));
	}

	@Override
	public void saveUser(UserDto userDto) {
		System.out.println("--> UserServiceImpl.saveUser()");

		User u = UserConverter.dtoToEntity(userDto);
		userRepository.save(u);
	}

	@Override
	public List<UserDto> getAllUsersDTO() {
		System.out.println("UserServiceImpl.getAllUsersDTO()");
		return userRepository.findAll().stream().map(UserConverter::entityToDto).collect(Collectors.toList());
	}
	@Override
	public List<User> getAllUsers() {
		System.out.println("UserServiceImpl.getAllUsers()");
		return userRepository.findAll();
	}
	public List<UserDto> getUsersByBookId(int bookId) {
		System.out.println("UserServiceImpl.getUsersByBookId()");
		return userRepository.getUsersByBookId(bookId).stream().map(UserConverter::entityToDto).collect(Collectors.toList());
		
	}

}
