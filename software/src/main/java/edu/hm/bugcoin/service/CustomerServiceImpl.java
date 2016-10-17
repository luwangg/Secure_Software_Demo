package edu.hm.bugcoin.service;

import edu.hm.bugcoin.domain.Bankaccount;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import edu.hm.bugcoin.domain.Customer;

import java.util.List;

/**
 * Created by shreaker on 14.10.16.
 * See: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
 */
@Component
@Transactional
public class CustomerServiceImpl implements CustomerService{

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------
    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;

    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------
    public CustomerServiceImpl(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository){
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    @Override
    public List<Customer> getCustomers(String lastname, String firstname) {
        Assert.notNull(lastname, "Name must not be null");
        Assert.notNull(firstname, "Name must not be null");
        return customerRepository.findByLastnameAndFirstname(lastname, firstname);
    }

    @Override
    public Customer getCustomer(String nickname) {
        Assert.notNull(nickname, "Nickname must not be null");
        return customerRepository.findByNickname(nickname);
    }

    @Override
    public List<Bankaccount>  getBankAccounts(String nickname) {
        Assert.notNull(nickname, "Nickname must not be null");
        Customer customer = customerRepository.findByNickname(nickname);
        return bankAccountRepository.findByCustomer(customer);
    }


    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    @Override
    public Bankaccount addBankAccount(final Customer customer, final long accountNumber) {
        Assert.notNull(customer, "Customer must not be null");
        Assert.notNull(accountNumber, "Accountnumber must not be null");
        final float INITIAL_ACCOUNT_BALANCE = 0;
        Bankaccount bankaccount = new Bankaccount(customer, accountNumber, INITIAL_ACCOUNT_BALANCE);
        return bankAccountRepository.saveAndFlush(bankaccount);
    }

    @Override
    public Bankaccount updateAccountBalance(final long accountNumber, final float newBalance) {
        Assert.notNull(accountNumber, "Accountnumber must not be null");
        Assert.notNull(newBalance, "NewBalance must not be null");
        Bankaccount bankaccount = bankAccountRepository.findByAccountnumber(accountNumber);
        bankaccount.setBalance(newBalance);
        bankAccountRepository.saveAndFlush(bankaccount);
        return null;
    }

    @Override public Customer addCustomer(final Customer customer)
    {
        Assert.notNull(customer, "customer must not be null!");
        return customerRepository.save(customer);
    }

    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------
}
