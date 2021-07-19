package br.com.zupacademy.ane.proposta.validacao;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String attribute;
    private Class<?> klazz;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        attribute = constraintAnnotation.fieldName();
        klazz = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from " + klazz.getName() + " where " + attribute + "=:value");
        query.setParameter("value", value);
        List<?> lista = query.getResultList();
        Assert.isTrue(lista.size()<=1, "Foi encontrado mais de uma "+klazz+" com o atributo "+attribute);
        return lista.isEmpty();
    }
}
