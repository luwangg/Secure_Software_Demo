package edu.hm.bugcoin.domain;
/*
 * Created by shreaker on 14.10.16.
 */

import org.hibernate.annotations.NaturalId;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;


/**
 *
 */
@Entity
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
    private Long accountnumber;

    @Column(nullable = false, unique = false)
    private Float balance;


    // ----------------------------------------------------------------------------------
    //  Constructor
    // ----------------------------------------------------------------------------------
    public Bankaccount(Customer customer, Long accountnumber, Float balance){
        super();
        this.customer = customer;
        this.accountnumber = accountnumber;
        this.balance = balance;

    }

    protected Bankaccount() {
    }
    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------
    public Customer getCustomer() { return  customer;}

    public Long getAccountnumber() {
        return accountnumber;
    }

    public Float getBalance() {
        return balance;
    }

    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------
    public void setCustomer(Customer customer) { this.customer = customer;}

    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    // ----------------------------------------------------------------------------------
    //  Override
    // ----------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Bankaccount{" +
                "accountnumber=" + accountnumber +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bankaccount that = (Bankaccount) o;

        return accountnumber != null ? accountnumber.equals(that.accountnumber) : that.accountnumber == null;

    }

    @Override
    public int hashCode()
    {
        return accountnumber != null ? accountnumber.hashCode() : 0;
    }
}
