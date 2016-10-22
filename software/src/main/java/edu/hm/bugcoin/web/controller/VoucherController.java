package edu.hm.bugcoin.web.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.Voucher;
import edu.hm.bugcoin.service.*;
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
public class VoucherController {

    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private TaskWorker tasks;


    // ----------------------------------------------------------------------------------
    //  attributes
    // ----------------------------------------------------------------------------------

    @ModelAttribute
    public void attrs(final HttpSession session, final Model model) {
        final Customer customer = (Customer) session.getAttribute(SessionKey.AUTH_USER);
        model.addAttribute("me", customer);
        model.addAttribute("accounts", customerService.getBankAccounts(customer));
    }


    // -------------------------------------- --------------------------------------------
    //  http handlers
    // ----------------------------------------------------------------------------------

    @GetMapping("/banking/voucher")
    @ACL(ACL.Type.NORMAL)
    public String voucher() {
        return "voucher";
    }

    @RequestMapping(value = "/banking/voucher", method = RequestMethod.POST)
    @ACL(ACL.Type.NORMAL)
    public String voucher(@RequestParam(value = "account", required = false) final Integer account,
                          @RequestParam("voucherCode") final long voucherCodeUserInput,
                          @SessionAttribute(SessionKey.AUTH_USER) Customer customer,
                          final Model model) {

        Bankaccount selectedAccount;
        if(account == null) {
            selectedAccount = customerService.getBankAccounts(customer).get(0);
        } else {
            selectedAccount = bankAccountService.getAccount(account);
        }

        model.addAttribute("selectedAccount", selectedAccount);

        Voucher voucher = voucherService.getVoucher(voucherCodeUserInput);
        if(isVoucherValid(voucher)){
            String description = "voucher code: " + voucher.getCode();
            tasks.add(new TransferTask(bankAccountService.getAccountNrVoucher(), selectedAccount.getAccountNumber(), description, voucher.getValue()));
            voucher.setReedemed(true);
        }else{
            model.addAttribute("message", "Gutschein " + voucherCodeUserInput + " ist ungültig!");
        }
        return "voucher";
    }

    // ----------------------------------------------------------------------------------
    // helper methods
    // ----------------------------------------------------------------------------------
    private boolean isVoucherValid(Voucher voucher){
        boolean isValid = true;
        if(voucher == null){
            isValid =false;
        }else{
            if(voucher.isReedemed())
                isValid = false;
        }

        return isValid;
    }


}
