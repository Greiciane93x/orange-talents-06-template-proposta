package br.com.zupacademy.ane.proposta.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsById, Long> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsById constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " =:value");
        query.setParameter("value", value);
        return !query.getResultList().isEmpty();
    }
}


