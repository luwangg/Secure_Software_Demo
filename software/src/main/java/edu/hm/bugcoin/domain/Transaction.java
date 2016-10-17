package edu.hm.bugcoin.domain;
/*
 * Projekt: bugcoin
 * Autor: Maximilian Pachl
 * 2016-10-17 12:35
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 *
 */
@Entity
public class Transaction implements Serializable
{

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;


    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Bankaccount sourceAccount;

    @ManyToOne
    private Bankaccount targetAccount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float amount;


    // ----------------------------------------------------------------------------------
    //  Konstruktoren
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------

    public Bankaccount getSourceAccount()
    {
        return sourceAccount;
    }

    public Bankaccount getTargetAccount()
    {
        return targetAccount;
    }

    public String getDescription()
    {
        return description;
    }

    public Float getAmount()
    {
        return amount;
    }


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Auskunftsmethoden
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Aendernde Methoden
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Standardmethoden
    // ----------------------------------------------------------------------------------


    @Override
    public String toString()
    {
        return "Transaction{" +
                "id=" + id +
                ", sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
