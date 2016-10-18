package edu.hm.bugcoin.service;

import edu.hm.bugcoin.domain.Bankaccount;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by shreaker on 18.10.16.
 */
@Component
@Transactional
public class BankAccountServiceImpl implements BankAccountService{

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------
    private final BankAccountRepository bankAccountRepository;

    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository = bankAccountRepository;
    }

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    @Override
    public long getNewAccountNr() {
        return findMaxAccountNr() + 1;
    }

    private long findMaxAccountNr(){
        List<Bankaccount> bankaccounts = bankAccountRepository.findAll();
        long bankAccountNrMax = -1;
        long currentAccountNr;
        for (Bankaccount bankAccount : bankaccounts) {
            currentAccountNr = bankAccount.getAccountnumber();
            if(currentAccountNr > bankAccountNrMax){
                bankAccountNrMax = currentAccountNr;
            }
        }
        return bankAccountNrMax;
    }

    @Override
    public Bankaccount getAccount(long accountNumber) {
        return bankAccountRepository.findByAccountnumber(accountNumber);
    }
    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    @Override
    public Bankaccount updateAccountBalance(final long accountNumber, final float newBalance) {
        Assert.notNull(accountNumber, "Accountnumber must not be null");
        Assert.notNull(newBalance, "NewBalance must not be null");
        Bankaccount bankaccount = bankAccountRepository.findByAccountnumber(accountNumber);
        bankaccount.setBalance(newBalance);
        bankAccountRepository.saveAndFlush(bankaccount);
        return null;
    }

    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------


}
