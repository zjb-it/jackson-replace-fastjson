package jackson.replaces.fastjson.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author zjb
 */
public class ParseException extends RuntimeException {
    private JsonProcessingException processingException;

    public ParseException(JsonProcessingException processingException) {
        this.processingException = processingException;
    }

    public ParseException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        processingException.printStackTrace();
    }

    @Override
    public String toString() {
        return processingException.toString();
    }

    @Override
    public String getMessage() {
        return processingException.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return processingException.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return processingException.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return processingException.initCause(cause);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        processingException.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        processingException.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return processingException.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return processingException.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        processingException.setStackTrace(stackTrace);
    }
}
