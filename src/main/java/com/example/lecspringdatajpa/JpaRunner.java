package com.example.lecspringdatajpa;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;  // JPA 핵심 클래스

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("whiteship");
        account.setPassword("hibernate");

        Study study = new Study();
        study.setName("Spring Data JPA");

        account.addStudy(study);

        // hibernate session을 사용하여 저장
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

        Account persistentAccount = session.load(Account.class, account.getId());
        persistentAccount.setUsername("keesun");
        System.out.println("==============================");
        System.out.println(persistentAccount.getUsername());
    }
}
