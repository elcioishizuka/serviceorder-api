package com.github.elcioishizuka.serviceorderapi.exceptionHandler;

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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

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
        issue.setDateTime(LocalDateTime.now());
        issue.setProblematicFields(problematicFields);

        return super.handleExceptionInternal(ex, issue, headers, status, request);

    }




}
