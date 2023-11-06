package com.example.api.infra.expections;

import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DomainException{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(EntityNotFoundException exception){
        var erro = new NotFound(exception);
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors().stream().map(ErrosDto::new).toList();
        return ResponseEntity.badRequest().body(erros);
    }

    private record ErrosDto(String field, String defaultMessage){
        public ErrosDto(FieldError field){
            this(field.getField(), field.getDefaultMessage());
        }
    }

    private record NotFound(String message){
        public NotFound(EntityNotFoundException exception){
            this(exception.getMessage());
        }
    }

}
