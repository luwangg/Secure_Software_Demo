package edu.hm.bugcoin.service.Customer;
/*
 * Created by shreaker on 14.10.16.
 * See: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.CustomerLevel;
import edu.hm.bugcoin.domain.CustomerState;

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

    List<Customer> getCustomers(CustomerLevel level);

    List<Customer> getCustomers(CustomerState state);

    List<Bankaccount> getBankAccounts(String nickname);

    List<Bankaccount> getBankAccounts(Customer customer);


    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------

    Customer addCustomer(Customer customer);

    Customer setCustomerLevel(Customer customer, CustomerLevel level) throws IllegalCustomerLevelException;

    public class IllegalCustomerLevelException extends Exception {
        public IllegalCustomerLevelException(String message) {
            super(message);
        }
    }

    Customer setCustomerState(Customer customer, CustomerState state);

    Bankaccount addBankAccount(Customer customer, long accountNumber);


    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------


}
