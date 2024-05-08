package com.example.analyticaldataquery.service;

import com.example.analyticaldataquery.model.Query;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.analyticaldataquery.model.UserActivityEvent;
import org.hibernate.query.Order;
import org.hibernate.query.SelectionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserActivityEventService {
    @Autowired
    private final EntityManager entityManager;

    public List<UserActivityEvent> searchByQuery(Query query) {
        RSQLVisitor<CriteriaQuery<UserActivityEvent>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        CriteriaQuery<UserActivityEvent> criteriaQuery = getCriteriaQuery(query.getQuery(), visitor);
        List<UserActivityEvent> resultList = ((org.hibernate.query.Query<UserActivityEvent>)entityManager.createQuery(criteriaQuery))
                .setOrder(Arrays.asList(Order.desc(UserActivityEvent.class, "eventId")))
                .setFirstResult(query.getPage()).setMaxResults(query.getPageSize())
                .getResultList();
        if (resultList == null || resultList.isEmpty()){
            return Collections.emptyList();
        }
        return resultList;
    }

    private <T> CriteriaQuery<T> getCriteriaQuery(String queryString, RSQLVisitor<CriteriaQuery<T>, EntityManager> visitor) {

        try {
            Node rootNode = new RSQLParser().parse(queryString);
            return rootNode.accept(visitor, entityManager);
        }catch (Exception e){
            log.error("An error happened while executing RSQL query", e);
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
