package ao3.br.rec.books.repository;

import java.util.List;

import ao3.br.rec.books.entity.User;

public interface UserRepositoryCustom {
	
    List<User> getUsersByBookId(int bookId);

}

