package com.example.service;

import com.example.dao.AccountDAO;
import com.example.entity.Account;
import com.example.entity.TransactionRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void transferMoney(int fromId, int toId, double amount) {
        Account fromAcc = accountDAO.getAccount(fromId);
        Account toAcc = accountDAO.getAccount(toId);

        if (fromAcc.getBalance() < amount) {
            throw new RuntimeException("❌ Insufficient balance in sender account!");
        }

        fromAcc.setBalance(fromAcc.getBalance() - amount);
        toAcc.setBalance(toAcc.getBalance() + amount);

        accountDAO.updateAccount(fromAcc);
        accountDAO.updateAccount(toAcc);

        // Record the transaction
        Session session = sessionFactory.getCurrentSession();
        TransactionRecord tx = new TransactionRecord(fromId, toId, amount);
        session.save(tx);

        System.out.println("✅ Transaction completed successfully!");
    }
}
