package su.vfe.foodmanager.web;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import su.vfe.foodmanager.util.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {

    //TODO: Temporary stub for test NotFoundException
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void notFoundError() { }

    //TODO: Temporary stub for test MethodArgumentNotValidException
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void bindValidationError(HttpServletRequest req, MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        List<FieldError> details = result.getFieldErrors();

        for (FieldError fe : details) {
            System.out.println(fe.getField() + "     " + fe.getRejectedValue());
        }
    }
}