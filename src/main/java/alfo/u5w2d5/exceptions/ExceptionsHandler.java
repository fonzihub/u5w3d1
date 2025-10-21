package alfo.u5w2d5.exceptions;

import alfo.u5w2d5.payloads.ErrorsPayload;
import alfo.u5w2d5.payloads.ErrorsWithListDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationErrors(ValidationException ex) {
        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsMessages());
    }

    @ExceptionHandler(UnauthorizedExceptions.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsPayload handleUnauthorizedException(UnauthorizedExceptions ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class) // Tra le parentesi indico quale eccezione dovrà gestire questo metodo
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsPayload handleNotFound(ChangeSetPersister.NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class) // Tutte le eccezioni che non sono BadRequestException o NotFoundException vengono gestite da questo handler
    public ErrorsPayload handleServerError(Exception ex) {
        ex.printStackTrace(); // E' importante avere il print dello stack trace per sapere dove intervenire per fixare il bug
        return new ErrorsPayload("C'è stato un errore generico, giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}
