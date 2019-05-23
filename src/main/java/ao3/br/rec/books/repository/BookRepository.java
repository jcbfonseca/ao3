package ao3.br.rec.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ao3.br.rec.books.entity.Book;

@Component
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom{
	
	public Book findByTitle(String title);
}

