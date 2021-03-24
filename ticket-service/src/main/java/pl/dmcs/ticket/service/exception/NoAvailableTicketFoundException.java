package pl.dmcs.ticket.service.exception;

public class NoAvailableTicketFoundException extends Exception {
    public NoAvailableTicketFoundException(String message) {
        super(message);
    }
}
