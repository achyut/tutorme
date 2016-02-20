package uta.edu.tutorme.exceptions;

/**
 * Created by ananda on 2/18/16.
 */
public class InconsistentSizeException extends Exception {
    public InconsistentSizeException() {
    }

    public InconsistentSizeException(String detailMessage) {
        super(detailMessage);
    }

    public InconsistentSizeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InconsistentSizeException(Throwable throwable) {
        super(throwable);
    }
}
