package su.vfe.foodmanager.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import su.vfe.foodmanager.util.exception.NotFoundException;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {

    //TODO: Temporary stub for test NotFoundException
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void notFoundError() { }
}