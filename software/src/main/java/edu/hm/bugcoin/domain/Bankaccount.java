package edu.hm.bugcoin.domain;
/*
 * Created by shreaker on 14.10.16.
 */

import edu.hm.bugcoin.service.BankAccount.BankAccountService;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;


/**
 *
 */
@Entity
@Component
public class Bankaccount implements Serializable{

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    private Customer customer;

    @Column(nullable = false, unique = true)
    @NaturalId
    @Id
    private Long accountNumber;

    @Transient
    private static BankAccountService bankAccountService;


    // ----------------------------------------------------------------------------------
    //  Konstrutoren
    // ----------------------------------------------------------------------------------

    public Bankaccount() { }

    public Bankaccount(final Customer customer, final Long accountNumber)
    {
        this.customer = customer;
        this.accountNumber = accountNumber;
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing Bankaccount as [" + Bankaccount.bankAccountService + "]");
    }


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------

    public Customer getCustomer() { return  customer;}

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Float getBalance() {
        return bankAccountService.getBalance(getAccountNumber());
    }


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------

    public void setCustomer(Customer customer) { this.customer = customer;}

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Autowired
    public void setBankAccountService(BankAccountService bankAccountService)
    {
        Bankaccount.bankAccountService = bankAccountService;
    }

    // ----------------------------------------------------------------------------------
    //  Override
    // ----------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Bankaccount{" +
                "accountNumber=" + accountNumber +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bankaccount that = (Bankaccount) o;

        return accountNumber != null ? accountNumber.equals(that.accountNumber) : that.accountNumber == null;

    }

    @Override
    public int hashCode()
    {
        return accountNumber != null ? accountNumber.hashCode() : 0;
    }
}
