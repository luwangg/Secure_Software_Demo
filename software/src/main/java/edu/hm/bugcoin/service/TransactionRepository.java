package edu.hm.bugcoin.service;
/*
 * Projekt: bugcoin
 * Autor: Maximilian Pachl
 * 2016-10-17 12:43
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.Transaction;
import org.springframework.data.repository.Repository;

import java.util.List;


/**
 *
 */
public interface TransactionRepository extends Repository<Transaction, Long>
{
    List<Transaction> findByTargetAccountOrSourceAccount(Bankaccount targetAccount, Bankaccount sourceAccount);

    Transaction saveAndFlush(Transaction transaction);
}
