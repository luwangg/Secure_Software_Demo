package edu.hm.bugcoin.service;
/*
 * Created by shreaker on 14.10.16.
 * See: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import java.util.List;


/**
 * Interface to access the Customer- and Bankaccount-Interface in an user friendly way.
 */
public interface CustomerService {

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------

    Customer getCustomer(String nickname);

    List<Customer> getCustomers(String lastname, String firstname);

    List<Bankaccount> getBankAccounts(String nickname);


    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------

    Customer addCustomer(Customer customer);

    Bankaccount addBankAccount(Customer customer, long accountNumber);

    Bankaccount updateAccountBalance(long accountNumber, float newBalance);


    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------


}
