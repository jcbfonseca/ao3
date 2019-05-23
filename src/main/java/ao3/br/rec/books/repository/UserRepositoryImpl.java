package ao3.br.rec.books.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ao3.br.rec.books.entity.User;


@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
    public List<User> getUsersByBookId(int bookId){

		Query query = entityManager.createNativeQuery("SELECT * FROM User u, User_Book ub where u.user_id=ub.user_id and ub.book_id=?", User.class);

		query.setParameter(1, bookId);

		return query.getResultList();

	}

}