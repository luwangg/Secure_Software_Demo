package edu.hm.bugcoin.service;
/*
 * Created by shreaker on 18.10.16.
 */

import edu.hm.bugcoin.domain.Bankaccount;
import org.springframework.data.jpa.repository.Query;


/**
 *
 */
public interface BankAccountService {

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------

    long getNewAccountNr();

    long getAccountNrVoucher();

    Bankaccount getAccount(long accountNumber);

    float getBalance(long accountNumber);


    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------

}
