package ao3.br.rec.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ao3.br.rec.books.entity.User;

@Component
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
	

}

