package ao3.br.rec.books.utils;

public interface Constants {
    static final String GET_USER_BY_ID = "/getUser/{userId}";
    static final String GET_ALL_USERS_BY_BOOK_ID = "/userbook/{book_id}";
    
    static final String DELETE_USER_BOOK = "/userbook/{book_id}";

    static final String GET_ALL_BOOKS = "/";
    static final String SAVE_BOOK = "/";
    static final String GET_BOOK_BY_ID = "/getBook/{bookId}";
    static final String GET_ALL_BOOKS_BY_USER_ID = "/booksByUserId";

    static final String SAVE_USER_BOOK = "/userbook/{book_id}";
    static final String GET_USER_BOOK = "/userbook/{book_id}";
}
