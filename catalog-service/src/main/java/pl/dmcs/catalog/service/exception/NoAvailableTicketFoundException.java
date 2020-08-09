package pl.dmcs.catalog.service.exception;

public class NoAvailableTicketFoundException extends Exception {
    public NoAvailableTicketFoundException(String message) {
        super(message);
    }
}
