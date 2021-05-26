package com.mercadolivre.config.validacao.uniqueValue;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String atributo;
    private Class<?> entidadeDominio;
    @PersistenceContext
    private EntityManager em;


    @Override
    public void initialize(UniqueValue params) {
        atributo = params.fieldName();
        entidadeDominio = params.domainClass();
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("SELECT 1 FROM  " + entidadeDominio.getName() + " WHERE "
                + atributo + "=:value");
        System.out.println(value);
        query.setParameter("value", value);
        List<?> resultQuery = query.getResultList();
        Assert.state(resultQuery.size() <= 1, "foi encontrado mais de um: " + entidadeDominio
                + " com o atributo: " + atributo + " igual a: " + value);
        return resultQuery.isEmpty();
    }

}
