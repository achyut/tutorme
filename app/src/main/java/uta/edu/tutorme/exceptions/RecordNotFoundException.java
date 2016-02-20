package uta.edu.tutorme.exceptions;

/**
 * Created by ananda on 2/18/16.
 */
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public RecordNotFoundException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RecordNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
