package edu.hm.bugcoin.service.Customer;


import edu.hm.bugcoin.domain.CustomerLevel;
import edu.hm.bugcoin.domain.CustomerState;
import org.springframework.data.repository.Repository;
import edu.hm.bugcoin.domain.Customer;

import java.util.List;

/**
 * Created by shreaker on 14.10.16.
 * See: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
 */

/**
 * Queries related to the Customer-Table.
 */
public interface CustomerRepository extends Repository<Customer, Long>{

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    Customer findByNickname(String nickname);

    Customer findByEmail(String email);

    List<Customer> findByLastnameAndFirstname(String lastname, String firstname);

    List<Customer> findByLastname(String lastname);

    List<Customer> findByLevel(CustomerLevel level);

    List<Customer> findByState(CustomerState state);

    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------

    Customer saveAndFlush(Customer account);


    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------


}
