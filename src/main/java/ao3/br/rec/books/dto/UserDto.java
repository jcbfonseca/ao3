package ao3.br.rec.books.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    Integer userId;
    String userName;
    List<BookDto> bookDtos= new ArrayList<>();
    public UserDto(Integer userId, String userName, List<BookDto> bookDtos) {
        this.userId = userId;
        this.userName = userName;
        this.bookDtos = bookDtos;
    }
    public UserDto() {
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public List<BookDto> getBookDtos() {
        return bookDtos;
    }
    public void setBookDtos(List<BookDto> bookDtos) {
        this.bookDtos = bookDtos;
    }
}
