package zti.library.dto;

import lombok.Data;
import zti.library.model.Category;

@Data
public class CategoryDto {
    private Long id;
    private String category;

    public static CategoryDto from(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategory(category.getCategory());
        return categoryDto;
    }
}
