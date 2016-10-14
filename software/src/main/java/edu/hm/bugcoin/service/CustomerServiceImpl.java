package edu.hm.bugcoin.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import edu.hm.bugcoin.domain.Customer;

/**
 * Created by shreaker on 14.10.16.
 */
@Component
@Transactional
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomer(String name) {
        Assert.notNull(name, "Name must not be null");
        return customerRepository.findByName(name);
    }

    @Override
    public Customer findByNameQuery(String name) {
        Assert.notNull(name, "Name must not be null");
        return customerRepository.findByNameQuery(name);
    }
}
