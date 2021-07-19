package br.com.zupacademy.ane.proposta.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint (validatedBy ={CpfOuCnpfValidator.class})
@Target({ElementType.FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
@Documented
public @interface CpfOuCnpj {

    String message() default "CPF/CNPJ inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
