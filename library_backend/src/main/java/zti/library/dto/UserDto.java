package zti.library.dto;

import zti.library.model.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<BorrowedDto> borrowedDto = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();


    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setBorrowedDto(user.getBorrowed().stream().map(BorrowedDto::from).collect(Collectors.toList()));
        userDto.setRoles(user.getRoles());
        return userDto;
    }

}