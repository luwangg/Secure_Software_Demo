package edu.hm.bugcoin.web.controller;

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.CustomerLevel;
import edu.hm.bugcoin.domain.CustomerState;
import edu.hm.bugcoin.security.CryptoUserData;
import edu.hm.bugcoin.security.CryptoUserDataBcrypt;
import edu.hm.bugcoin.service.BankAccount.BankAccountService;
import edu.hm.bugcoin.service.Customer.CustomerService;
import edu.hm.bugcoin.web.controller.forms.Registration;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 *
 */
@Controller
public class BugBountyController
{
    // ----------------------------------------------------------------------------------
    //  HTTP Handler
    // ----------------------------------------------------------------------------------

    @GetMapping(value = "/bugBounty")
    public String loadbugBountyPage()
    {
        return "bugBounty";
    }

}
