package edu.hm.bugcoin.service.Customer;

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.CustomerLevel;
import edu.hm.bugcoin.domain.CustomerState;
import edu.hm.bugcoin.service.BankAccount.BankAccountRepository;
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
public class CustomerServiceImpl implements CustomerService {

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------
    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;

    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------
    public CustomerServiceImpl(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    // ----------------------------------------------------------------------------------
    //  Request
    // ----------------------------------------------------------------------------------
    @Override
    public Customer getCustomer(String nickname) {
        Assert.notNull(nickname, "Nickname must not be null");
        return customerRepository.findByNickname(nickname);
    }

    @Override
    public List<Customer> getCustomers(String lastname, String firstname) {
        Assert.notNull(lastname, "Name must not be null");
        Assert.notNull(firstname, "Name must not be null");
        return customerRepository.findByLastnameAndFirstname(lastname, firstname);
    }

    @Override
    public List<Customer> getCustomers(CustomerLevel level) {
        Assert.notNull(level, "(Customer-)Level must not be null");
        return customerRepository.findByLevel(level);
    }

    @Override
    public List<Customer> getCustomers(CustomerState state) {
        Assert.notNull(state, "(Customer-)State must not be null");
        return customerRepository.findByState(state);
    }

    @Override
    public List<Bankaccount> getBankAccounts(String nickname) {
        Assert.notNull(nickname, "Nickname must not be null");
        Customer customer = customerRepository.findByNickname(nickname);
        return bankAccountRepository.findByCustomer(customer);
    }

    @Override
    public List<Bankaccount> getBankAccounts(Customer customer) {
        Assert.notNull(customer, "Customer must not be null");
        return bankAccountRepository.findByCustomer(customer);
    }


    // ----------------------------------------------------------------------------------
    //  Update / Add
    // ----------------------------------------------------------------------------------
    @Override
    public Customer addCustomer(final Customer customer) {
        Assert.notNull(customer, "customer must not be null!");
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer setCustomerLevel(Customer customer, CustomerLevel level) throws IllegalCustomerLevelException {
        Assert.notNull(customer, "customer must not be null!");
        Assert.notNull(level, "level must not be null!");
        Customer returnCustomer = null;

        if (permitCustomerLevelModification(customer.getLevel(), level)) {
            customer.setLevel(level);
            returnCustomer = customerRepository.saveAndFlush(customer);
        } else {
            throw new IllegalCustomerLevelException("Valid values are only CustomerLevel.USER and CustomerLevel.PRO_USER.\n" +
                    "CustomerLevel.ADMIN and CustomerLevel.SYSTEM down- and upgrades are forbidden.");
        }

        return returnCustomer;
    }

    /**
     * Valid values are only CustomerLevel.USER and CustomerLevel.PRO_USER.
     * CustomerLevel.ADMIN and CustomerLevel.SYSTEM down- and upgrades are forbidden.
     *
     * @param currentLevel
     * @param levelToChangeTo
     * @return true if permitted, else false.
     */
    private boolean permitCustomerLevelModification(CustomerLevel currentLevel, CustomerLevel levelToChangeTo) {
        boolean isPermitted = true;
        if (currentLevel == CustomerLevel.ADMIN || currentLevel == CustomerLevel.SYSTEM) {
            isPermitted = false;
        }else if(levelToChangeTo != CustomerLevel.USER && levelToChangeTo != CustomerLevel.PRO_USER) {
            isPermitted = false;
        }
        return isPermitted;
    }

    @Override
    public Customer setCustomerState(Customer customer, CustomerState state) {
        Assert.notNull(customer, "customer must not be null!");
        Assert.notNull(state, "state must not be null!");
        customer.setState(state);
        return customerRepository.saveAndFlush(customer);
    }


    @Override
    public Bankaccount addBankAccount(final Customer customer, final long accountNumber) {
        Assert.notNull(customer, "Customer must not be null");
        Assert.notNull(accountNumber, "Accountnumber must not be null");
        final float INITIAL_ACCOUNT_BALANCE = 0;
        Bankaccount bankaccount = new Bankaccount(customer, accountNumber);
        return bankAccountRepository.saveAndFlush(bankaccount);
    }

    // ----------------------------------------------------------------------------------
    //  Delete
    // ----------------------------------------------------------------------------------
}
