package edu.hm.bugcoin.task;
/*
 * Projekt: software
 * Autor: Maximilian Pachl
 * 2016-10-18 17:06
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.service.BankAccountService;
import edu.hm.bugcoin.service.CustomerService;
import edu.hm.bugcoin.service.TransactionRepository;

/**
 *
 */
abstract class Task
{

    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    private BankAccountService bankAccountService;
    private CustomerService customerService;
    private TransactionRepository transactionRepository;


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------

    public BankAccountService getBankAccountService()
    {
        return bankAccountService;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public TransactionRepository getTransactionRepository()
    {
        return transactionRepository;
    }

    // ----------------------------------------------------------------------------------
    //  Aendernde Methoden
    // ----------------------------------------------------------------------------------

    abstract void exectue();

    void setup(final BankAccountService bankAccountService, final CustomerService customerService,
               final TransactionRepository transactionRepository)
    {
        this.bankAccountService = bankAccountService;
        this.customerService = customerService;
        this.transactionRepository = transactionRepository;
    }
}
