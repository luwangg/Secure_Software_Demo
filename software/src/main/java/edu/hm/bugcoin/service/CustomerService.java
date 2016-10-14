package edu.hm.bugcoin.service;


import edu.hm.bugcoin.domain.Customer;

/**
 * Created by shreaker on 14.10.16.
 */
public interface CustomerService {

    Customer getCustomer(String firstName);

    Customer findByNameQuery(String name);

}
