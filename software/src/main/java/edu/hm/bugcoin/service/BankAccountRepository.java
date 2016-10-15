package edu.hm.bugcoin.service;


import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by shreaker on 14.10.16.
 * See: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
 */

/**
 * Queries related to the Bankaccount-Table.
 */
public interface BankAccountRepository extends Repository<Bankaccount, Long>{

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    Bankaccount findByAccountnumber(long accountnumber);

    List<Bankaccount> findByCustomer(Customer customer);

    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    Bankaccount saveAndFlush(Bankaccount account);



    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------

}
