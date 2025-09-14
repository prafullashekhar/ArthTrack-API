package com.prafull.ArthTrack.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.prafull.ArthTrack.security.ThreadLocalUserContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

@Aspect
@Component
public class HibernateFilterAspect {
    @PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* com.prafull.ArthTrack.service..*(..)) || " +
        "execution(* org.springframework.data.repository.Repository..*(..))")
        // "execution(* org.springframework.data.repository.Repository+.*(..))")
    public void applyFilter() {
        Long userId = ThreadLocalUserContext.getCurrentUserId();
        if (userId != null) {
            Session session = entityManager.unwrap(Session.class);
            if (session.getEnabledFilter("userFilter") == null) {
                session.enableFilter("userFilter").setParameter("userId", userId);
            }
        }
    }
}
