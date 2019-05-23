package ao3.br.rec.books.repository;

import java.util.List;

import ao3.br.rec.books.entity.Book;

public interface BookRepositoryCustom {

    List<Book> getBooksByUserId(int userId);

}