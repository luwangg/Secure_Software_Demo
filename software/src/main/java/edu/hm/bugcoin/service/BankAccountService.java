package edu.hm.bugcoin.service;

import edu.hm.bugcoin.domain.Bankaccount;

/**
 * Created by shreaker on 18.10.16.
 */
public interface BankAccountService {

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------

    long getNewAccountNr();

    Bankaccount getAccount(long accountNumber);

    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------

    Bankaccount updateAccountBalance(long accountNumber, float newBalance);

    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------

}
