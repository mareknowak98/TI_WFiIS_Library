package zti.library.exception;

import java.text.MessageFormat;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id){
        super(MessageFormat.format("Could not find author with id: {0}", id));
    }

}
