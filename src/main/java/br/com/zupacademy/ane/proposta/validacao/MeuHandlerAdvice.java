package br.com.zupacademy.ane.proposta.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class MeuHandlerAdvice {

    /**
     * padronizei de acordo com o padrão zup para treinar
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorStandard> handle(MethodArgumentNotValidException methodArgumentNotValidException){
        Collection<String> messages = new ArrayList<>();
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
            messages.add(message);
        });
        ErrorStandard errorStandard = new ErrorStandard(messages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorStandard);

    }
}
