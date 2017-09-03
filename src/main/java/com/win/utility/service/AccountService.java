package com.win.utility.service;


import com.win.utility.model.Account;
import com.win.utility.model.enumeration.AccountType;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    List<Account> getAllAccounts(AccountType type);

    void createAccount(String owner, double balance);

    void updateBalance(int id, double balance);

    void deleteAccount(int id);

}
