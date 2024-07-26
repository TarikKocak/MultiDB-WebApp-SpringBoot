package com.springboot.TestApp.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Transactional(readOnly = true)
    public List<Object[]> executeDynamicQuery(EntityManager entityManager, String query) {
        Query jpaQuery = entityManager.createNativeQuery(query);
        List<?> rawResult = jpaQuery.getResultList();


        List<Object[]> typedResult = new ArrayList<>();
        for (Object result : rawResult) {
            if (result instanceof Object[]) {
                typedResult.add((Object[]) result);
            } else {
                typedResult.add(new Object[]{result});
            }
        }

        return typedResult;
    }
}