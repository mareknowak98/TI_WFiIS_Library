package zti.library.dto;

import zti.library.model.Author;
import lombok.Data;

@Data
public class AuthorDto {
    private Long id;
    private String author;

    public static AuthorDto from(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setAuthor(author.getAuthor());
        return authorDto;
    }
}
