package com.win.utility.service.impl;

import com.win.utility.model.Account;
import com.win.utility.model.enumeration.AccountType;
import com.win.utility.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import com.win.utility.dao.AccountDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao dao;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Account> getAllAccounts(){
        return dao.getAllAccounts();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Account> getAllAccounts(AccountType type){
        return dao.getAllAccountsSort(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void createAccount(String owner, double balance){
        dao.createAccount(owner, balance);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateBalance(int id, double balance){
        dao.updateBalance(id, balance);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAccount(int id){
        dao.deleteAccount(id);
    }
}
