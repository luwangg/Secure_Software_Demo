package edu.hm.bugcoin.service;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import edu.hm.bugcoin.domain.Customer;

/**
 * Created by shreaker on 14.10.16.
 */
public interface CustomerRepository extends Repository<Customer, Long>{


    Customer findByName(String name);

    @Query(value = "select * from customer", nativeQuery = true)
    Customer findByNameQuery(String name);

}
