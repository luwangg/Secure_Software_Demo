package edu.hm.bugcoin.service;
/*
 * Created by shreaker on 18.10.16.
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class BankAccountServiceImpl implements BankAccountService
{

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------

    @Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private TransactionRepository transactionRepository;


    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------

    @Override public long getNewAccountNr() {
        return findMaxAccountNr() + 1;
    }

    private long findMaxAccountNr(){
        List<Bankaccount> bankaccounts = bankAccountRepository.findAll();
        long bankAccountNrMax = -1;
        long currentAccountNr;
        for (Bankaccount bankAccount : bankaccounts) {
            currentAccountNr = bankAccount.getAccountNumber();
            if(currentAccountNr > bankAccountNrMax){
                bankAccountNrMax = currentAccountNr;
            }
        }
        return bankAccountNrMax;
    }

    @Override public Bankaccount getAccount(long accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }

    @Override public float getBalance(final long accountNumber)
    {
        // find all transactions by the bank account
        final Bankaccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        final List<Transaction> transactions =
                transactionRepository.findByTargetAccountOrSourceAccount(account, account);

        // calculate the balance from all transactions
        float balance = 0.0f;
        for (final Transaction transaction : transactions)
        {
            if (transaction.getSourceAccount().equals(account))
                balance -= transaction.getAmount();
            else
                balance += transaction.getAmount();
        }

        return balance;
    }


    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------


}
