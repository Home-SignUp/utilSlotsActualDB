package com.win.utility.dao;

import java.util.List;

import com.win.utility.model.Account;
import com.win.utility.model.enumeration.AccountType;
import org.springframework.orm.jpa.JpaTemplate;


public class AccountDao {

	private JpaTemplate template;

	public void setTemplate(JpaTemplate template) {
		this.template = template;
	}

    public List<Account> getAllAccounts(){
        List<Account> accounts = template.find("select a from Account a");
        return accounts;
    }

    public List<Account> getAllAccountsSort(AccountType type){
        List<Account> accounts = null;
        switch (type){
            case OWNER:
                accounts = template.find("SELECT a FROM Account a ORDER BY a.owner");
                break;
            case BALANCE:
                accounts = template.find("SELECT a FROM Account a ORDER BY a.balance");
                break;
            default:
                accounts = template.find("SELECT a FROM Account a ORDER BY a.id");
                break;
        }
        return accounts;
    }

	public void createAccount(String owner, double balance){
        Account account = new Account(owner, balance);
		template.persist(account);
	}

	public void updateBalance(int accountNumber, double balance){
		Account account = template.find(Account.class, accountNumber);
		if(account != null){
			account.setBalance(balance);
		}
		template.merge(account);
	}

	public void deleteAccount(int accountNumber){
		Account account = template.find(Account.class, accountNumber);
		if(account != null)
			template.remove(account);
	}
}