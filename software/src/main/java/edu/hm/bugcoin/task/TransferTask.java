package edu.hm.bugcoin.task;
/*
 * Projekt: software
 * Autor: Maximilian Pachl
 * 2016-10-18 18:35
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Transaction;
import org.springframework.util.Assert;
import org.springframework.util.ExceptionTypeFilter;


/**
 *
 */
public class TransferTask extends Task
{

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------

    private static final String TRANSFER_PREFIX = "ÜBERWEISUNG\\n";


    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    private final long sourceAccount;
    private final long targetAccount;
    private final String description;
    private final float amount;


    // ----------------------------------------------------------------------------------
    //  Konstruktoren
    // ----------------------------------------------------------------------------------

    public TransferTask(long sourceAccount, long targetAccount, String description, float amount)
    {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.description = description;
        this.amount = amount;
    }


    // ----------------------------------------------------------------------------------
    //  Getter
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Setter
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Auskunftsmethoden
    // ----------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------
    //  Aendernde Methoden
    // ----------------------------------------------------------------------------------

    @Override public void exectue()
    {
        Assert.isTrue(amount > 0, "amount must be positive");

        try
        {
            // find the bank accounts
            final Bankaccount source = getBankAccountService().getAccount(sourceAccount);
            final Bankaccount target = getBankAccountService().getAccount(targetAccount);

            // validate existence of all accounts
            if (source == null || target == null)
                throw new RuntimeException("reverted transaction, because one of the accounts does not exist");

            // validate the two accounts are different
            if (source.equals(target))
                throw new RuntimeException("source and target accounts are the same");

            // create the transaction in database
            getTransactionRepository().saveAndFlush(
                    new Transaction(source, target, TRANSFER_PREFIX + description, amount));

            System.out.println("transferred " + amount + " from " + sourceAccount + " to " + targetAccount);

        // transaction failed
        } catch (final Exception ex) { System.out.println(ex.getMessage()); }
    }


    // ----------------------------------------------------------------------------------
    //  Standardmethoden
    // ----------------------------------------------------------------------------------

}
