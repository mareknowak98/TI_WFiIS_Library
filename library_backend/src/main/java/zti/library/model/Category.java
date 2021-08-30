package zti.library.model;

import lombok.Data;
import zti.library.dto.CategoryDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_category_id")
    )
    private List<Book> booksInCategory = new ArrayList<>();

    public Category(){ }

    public static Category from(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategory(categoryDto.getCategory());
        return category;
    }

    public Category(String category){
        this.category = category;
    }

    public void addCategoryToBook(Book book){ booksInCategory.add(book); }
    public void removeCategoryFromBook(Book book){
        booksInCategory.remove(book);
    }

}
