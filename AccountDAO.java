package com.example.dao;

import com.example.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Account getAccount(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    public void updateAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
    }

    public void saveAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.save(account);
    }
}
