package edu.hm.bugcoin.web.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.Application;
import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.service.BankAccountRepository;
import edu.hm.bugcoin.service.BankAccountService;
import edu.hm.bugcoin.service.CustomerService;
import edu.hm.bugcoin.service.TransactionRepository;
import edu.hm.bugcoin.task.TaskWorker;
import edu.hm.bugcoin.task.TransferTask;
import edu.hm.bugcoin.web.auth.ACL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


/**
 *
 */
@Controller
public class BankingController
{

    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @Autowired private CustomerService customerService;
    @Autowired private BankAccountService bankAccountService;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private TaskWorker tasks;


    // ----------------------------------------------------------------------------------
    //  attributes
    // ----------------------------------------------------------------------------------

    @ModelAttribute public void attrs(final HttpSession session, final Model model) {
        final Customer customer = (Customer)session.getAttribute(SessionKey.AUTH_USER);
        model.addAttribute("me", customer);
        model.addAttribute("accounts", customerService.getBankAccounts(customer));
    }


    // ----------------------------------------------------------------------------------
    //  http handlers
    // ----------------------------------------------------------------------------------

    @RequestMapping("/banking/transactions")
    @ACL(ACL.Type.NORMAL)
    public String transactions(@RequestParam(value = "account", required = false) final Integer account,
                               @SessionAttribute(SessionKey.AUTH_USER) Customer customer,
                               final HttpSession session, final Model model)
    {
        Bankaccount selectedAccount;
        if (account == null)
            selectedAccount = customerService.getBankAccounts(customer).get(0);
        else
            selectedAccount = bankAccountService.getAccount(account);

        // make sure the account belongs to the authenticated user
        if (selectedAccount == null || !selectedAccount.getCustomer().equals(customer))
            selectedAccount = customerService.getBankAccounts(customer).get(0);

        model.addAttribute("selectedAccount", selectedAccount);
        model.addAttribute("transactions",
                transactionRepository.findByTargetAccountOrSourceAccount(selectedAccount, selectedAccount));

        return "transactions";
    }

    @GetMapping("/banking/transfer")
    @ACL(ACL.Type.NORMAL)
    public String transfer()
    {
        return "transfer";
    }

    @RequestMapping(value = "/banking/transfer", method = RequestMethod.POST)
    @ACL(ACL.Type.NORMAL)
    public String transfer(@RequestParam("target") final long target,
                           @RequestParam("amount") final float amount,
                           @RequestParam("source") final long source,
                           @RequestParam("subject") final String subject)
    {
        tasks.add(new TransferTask(source, target, subject, amount));
        return "transfer";
    }
}
