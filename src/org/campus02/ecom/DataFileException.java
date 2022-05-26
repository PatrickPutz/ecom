package org.campus02.ecom;

public class DataFileException extends Exception{

    public DataFileException() {
    }

    public DataFileException(String message) {
        super(message);
    }

    public DataFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFileException(Throwable cause) {
        super(cause);
    }

    public DataFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
