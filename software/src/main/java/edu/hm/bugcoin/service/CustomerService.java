package edu.hm.bugcoin.service;


import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;

import java.util.List;

/**
 * Created by shreaker on 14.10.16.
 * See: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
 */

/**
 * Interface to access the Customer- and Bankaccount-Interface in an user friendly way.
 */
public interface CustomerService {

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    Customer getCustomer(final String nickname);

    List<Customer> getCustomers(final String lastname, final String firstname);

    List<Bankaccount> getBankAccounts(final String nickname);

    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    Bankaccount addBankAccount(final Customer customer, final long accountNumber);

    Bankaccount updateAccountBalance(final long accountNumber, final float newBalance);
    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------


}
