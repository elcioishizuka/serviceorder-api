package com.github.elcioishizuka.serviceorder.api.exceptionHandler;

import com.github.elcioishizuka.serviceorder.domain.exception.CustomerEmailAlreadyRegisteredException;
import com.github.elcioishizuka.serviceorder.domain.exception.CustomerNotFoundException;
import com.github.elcioishizuka.serviceorder.domain.exception.ServiceOrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    // This method will customize the ResponseStatusException eliminating the trace
    // Included also the @JsonInclude annotation in the Issue class to not show the null fields.
    @ExceptionHandler(CustomerEmailAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleCustomerEmailAlreadyRegisteredException(CustomerEmailAlreadyRegisteredException ex,
                                                                                WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Issue issue = new Issue();
        issue.setStatus(status.value());
        issue.setTitle(ex.getMessage());
        issue.setDateTime(OffsetDateTime.now());

        return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex,
                                                                                WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Issue issue = new Issue();
        issue.setStatus(status.value());
        issue.setTitle(ex.getMessage());
        issue.setDateTime(OffsetDateTime.now());

        return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ServiceOrderNotFoundException.class)
    public ResponseEntity<Object> handleServiceOrderNotFoundException(ServiceOrderNotFoundException ex,
                                                                      WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Issue issue = new Issue();
        issue.setStatus(status.value());
        issue.setTitle(ex.getMessage());
        issue.setDateTime(OffsetDateTime.now());

        return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request){

        List<Issue.ProblematicField> problematicFields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) error).getField();

            // Default message
//            String message = error.getDefaultMessage();

            // Customized messages from /resources/messages.properties
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            problematicFields.add(new Issue.ProblematicField(name, message));
        }

        Issue issue = new Issue();
        issue.setStatus(status.value());
        issue.setTitle("One or more fields are invalid. " +
                "Please correct the data and submit again");
        issue.setDateTime(OffsetDateTime.now());
        issue.setProblematicFields(problematicFields);

        return super.handleExceptionInternal(ex, issue, headers, status, request);

    }

}
