package com.example;

import com.example.config.AppConfig;
import com.example.entity.Account;
import com.example.dao.AccountDAO;
import com.example.service.BankService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        BankService bankService = context.getBean(BankService.class);
        AccountDAO accountDAO = context.getBean(AccountDAO.class);

        // Create two demo accounts (only first time)
        Account a1 = new Account("Alice", 5000);
        Account a2 = new Account("Bob", 2000);
        accountDAO.saveAccount(a1);
        accountDAO.saveAccount(a2);

        System.out.println("\nBefore transfer:");
        System.out.println(accountDAO.getAccount(a1.getId()));
        System.out.println(accountDAO.getAccount(a2.getId()));

        try {
            bankService.transferMoney(a1.getId(), a2.getId(), 1000);
        } catch (Exception e) {
            System.out.println("⚠️ Transaction failed: " + e.getMessage());
        }

        System.out.println("\nAfter transfer:");
        System.out.println(accountDAO.getAccount(a1.getId()));
        System.out.println(accountDAO.getAccount(a2.getId()));

        context.close();
    }
}
