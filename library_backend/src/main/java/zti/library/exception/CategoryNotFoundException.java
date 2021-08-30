package zti.library.exception;

import java.text.MessageFormat;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id){
        super(MessageFormat.format("Could not find category with id: {0}", id));
    }
}

