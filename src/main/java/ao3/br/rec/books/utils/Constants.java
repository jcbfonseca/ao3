package ao3.br.rec.books.utils;

public interface Constants {
    static final String GET_USER_BY_ID = "/getUser/{userId}";
    static final String GET_ALL_USERS = "/getAllUsers";
    static final String SAVE_USER = "/saveUser";
    static final String GET_ALL_USERS_BY_BOOK_ID = "/getAllUsersByBookId";

    static final String GET_BOOK_BY_ID = "/getBook/{bookId}";
    static final String GET_ALL_BOOKS = "/getAllBooks";
    static final String GET_ALL_BOOKS_BY_USER_ID = "/getAllBooksByUserId";
    static final String SAVE_BOOK = "/saveBook";

    static final String SAVE_USER_BOOK = "/saveUserBook/{book_id}";
    static final String GET_USER_BOOK = "/getUserBook/{book_id}";
}
