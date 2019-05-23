package ao3.br.rec.books.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ao3.br.rec.books.dto.UserDto;
import ao3.br.rec.books.entity.User;

@Service
public interface UserService {
	UserDto getUserById(Integer userId);

	void saveUser(UserDto userDto);

	public List<UserDto> getAllUsersDTO();
	
	public List<User> getAllUsers();
	
	public List<UserDto> getUsersByBookId(int bookId);
}
