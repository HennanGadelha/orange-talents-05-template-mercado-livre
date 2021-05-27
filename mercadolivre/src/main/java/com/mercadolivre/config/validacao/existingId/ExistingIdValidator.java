package com.mercadolivre.config.validacao.existingId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistingIdValidator implements ConstraintValidator<ExistingID, Object> {

    private String atributo;
    private Class<?> entidadeDominio;
    @PersistenceContext
    private EntityManager em;


    @Override
    public void initialize(ExistingID params) {
        atributo = params.fieldName();
        entidadeDominio = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value != null){
            Query query = em.createQuery("SELECT 1 FROM  " + entidadeDominio.getName() + " WHERE "
                    + atributo + "=:value");
            query.setParameter("value", value);
            List<?> resultQuery = query.getResultList();
            boolean entidadeExistente = !resultQuery.isEmpty();
            return entidadeExistente;

        }

        return true;

    }


}
