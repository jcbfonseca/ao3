package ao3.br.rec.books.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ao3.br.rec.books.entity.Book;

@Repository
@Transactional(readOnly = true)
public class BookRepositoryImpl implements BookRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Book> getBooksByUserId(int userId) {

		Query query = entityManager.createNativeQuery("SELECT * FROM Book b, User_Book ub where b.book_id=ub.book_id and ub.user_id=?", Book.class);

		query.setParameter(1, userId);

		return query.getResultList();

	}

}