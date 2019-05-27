package ao3.br.rec.books.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    Integer userId;
    String userName;
    List<UserBookDTO> userBookDtos= new ArrayList<>();
    public UserDto(Integer userId, String userName, List<UserBookDTO> bookDtos) {
        this.userId = userId;
        this.userName = userName;
        this.userBookDtos = bookDtos;
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
    public List<UserBookDTO> getUserBookDtos() {
        return userBookDtos;
    }
    public void setUserBookDtos(List<UserBookDTO> bookDtos) {
        this.userBookDtos = bookDtos;
    }
}
