package zti.library.dto;

import zti.library.model.Author;
import zti.library.model.Book;
import lombok.Data;
import zti.library.model.Borrowed;
import zti.library.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<BorrowedDto> borrowedDto = new ArrayList<>();

    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setBorrowedDto(user.getBorrowed().stream().map(BorrowedDto::from).collect(Collectors.toList()));
        return userDto;
    }

}
