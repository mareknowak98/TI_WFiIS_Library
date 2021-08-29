package zti.library.exception;

import java.text.MessageFormat;

public class BorrowedNotFoundException extends RuntimeException {
    public BorrowedNotFoundException(Long id){
        super(MessageFormat.format("Could not find borrowed record with id: {0}", id));
    }
}
