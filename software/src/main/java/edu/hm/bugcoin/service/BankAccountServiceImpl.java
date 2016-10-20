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

    private final static long  ACCOUNT_NR_CUSTOMER_BEGIN = 200000;
    private final static long  ACCOUNT_NR_VOUCHER = 100000;

    @Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private TransactionRepository transactionRepository;


    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------

    @Override public long getNewAccountNr() {
        long newAccountNr;
        try {
            newAccountNr = findMaxAccountNr();
            if(newAccountNr == ACCOUNT_NR_VOUCHER){
                newAccountNr = ACCOUNT_NR_CUSTOMER_BEGIN;
            }else{
                newAccountNr++;
            }
        } catch (NoAccountFoundException e) {
            newAccountNr = ACCOUNT_NR_CUSTOMER_BEGIN;
        }
        return newAccountNr;
    }

    private long findMaxAccountNr() throws NoAccountFoundException {
        List<Bankaccount> accounts = bankAccountRepository.findAll();
        if(accounts == null){
            throw new NoAccountFoundException();
        }
        final long INVALID_ACCOUNT_NR_FOR_SEARCH = -1;
        long accountNrMax = INVALID_ACCOUNT_NR_FOR_SEARCH;
        long currentAccountNr;
        for (Bankaccount account : accounts) {
            currentAccountNr = account.getAccountNumber();
            if(currentAccountNr > accountNrMax){
                accountNrMax = currentAccountNr;
            }
        }
        return accountNrMax;
    }

    private class NoAccountFoundException extends Exception {
    }

    @Override
    public long getAccountNrVoucher(){
        return ACCOUNT_NR_VOUCHER;
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
