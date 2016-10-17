package edu.hm.bugcoin.web.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 21:27
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.service.BankAccountRepository;
import edu.hm.bugcoin.service.CustomerService;
import edu.hm.bugcoin.web.auth.ACL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 *
 */
@Controller
public class AccountController
{
    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @Autowired private CustomerService customerService;
    @Autowired private BankAccountRepository bankAccountRepository;


    // ----------------------------------------------------------------------------------
    //  attributes
    // ----------------------------------------------------------------------------------

    @ModelAttribute public void attrs(final HttpSession session, final Model model) {
        final Customer user = (Customer)session.getAttribute(SessionKey.AUTH_USER);
        model.addAttribute("me", user);
        model.addAttribute("accounts", bankAccountRepository.findByCustomer(user));
    }

    // ----------------------------------------------------------------------------------
    //  http handlers
    // ----------------------------------------------------------------------------------

    @RequestMapping("/account/settings")
    @ACL(ACL.Type.NORMAL)
    public String view()
    {
        return "account";
    }
}
