package edu.hm.bugcoin.domain;

import org.hibernate.annotations.NaturalId;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shreaker on 14.10.16.
 */
@Entity
public class Bankaccount implements Serializable{

    // ----------------------------------------------------------------------------------
    //  Member variable
    // ----------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @Column(nullable = false, unique = true)
    @NaturalId
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
}
