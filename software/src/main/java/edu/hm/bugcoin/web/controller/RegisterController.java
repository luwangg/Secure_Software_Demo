package edu.hm.bugcoin.web.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 21:55
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.security.CryptoUserData;
import edu.hm.bugcoin.security.CryptoUserDataBcrypt;
import edu.hm.bugcoin.service.BankAccountService;
import edu.hm.bugcoin.service.CustomerService;
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
public class RegisterController
{

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------

    private static final String OTP_HOST = "bugcoin";
    private static final String QR_FORMAT =  "http://chart.apis.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";


    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BankAccountService bankAccountService;


    // ----------------------------------------------------------------------------------
    //  HTTP Handler
    // ----------------------------------------------------------------------------------

    @GetMapping(value = "/register")
    public String register(final Registration registration)
    {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid Registration registration, BindingResult binding, HttpSession session)
    {
        if (binding.hasErrors() || !registration.validate())
            return "register";

        else
        {
            // create the new account using the identity provider
            // and generate a random secretkey for two-factor auth
            final String secret = Base32.random();
            final Customer customer = new Customer() //TODO why not a constructor?
                    .setNickname(registration.getUsername())
                    .setOtpKey(secret)
                    .setFirstname(registration.getFirstname())
                    .setLastname(registration.getLastname())
                    .setStreet(registration.getStreet())
                    .setPostcode(registration.getPostalcode())
                    .setCity(registration.getCity())
                    .setEmail(registration.getEmail());
            setPasswordHashAndSaltOfCustomer(customer, registration.getPassword());
            customerService.addCustomer(customer);
            setBankAccountOfCustomer(customer);

            // setup session, for the following pages and redirect
            session.setAttribute(SessionKey.REG_SECRET_KEY, secret);
            session.setAttribute(SessionKey.REG_FORM, registration);
            return "redirect:/register/twofactor";
        }
    }

    @GetMapping(value = "/register/twofactor")
    public String twofactor(HttpSession session, Model model)
    {
        // the user has to be at first at the registration page
        final Registration reg = (Registration)session.getAttribute(SessionKey.REG_FORM);
        final String secretKey = (String)session.getAttribute(SessionKey.REG_SECRET_KEY);
        if (reg == null || secretKey == null)
            return "redirect:/register";

        // setup the model
        model.addAttribute("secretkey", secretKey);
        model.addAttribute("qrcode", String.format(QR_FORMAT, reg.getUsername(), OTP_HOST, secretKey));
        return "twofactor";
    }

    @PostMapping(value = "/register/twofactor")
    public String finish(HttpSession session)
    {
        // clear the registration session
        session.removeAttribute(SessionKey.REG_FORM);
        session.removeAttribute(SessionKey.REG_SECRET_KEY);

        return "redirect:/";
    }

    private void setPasswordHashAndSaltOfCustomer(Customer customer, String password){
        CryptoUserData cryptoUserData = new CryptoUserDataBcrypt();
        String salt = cryptoUserData.generateSalt();
        String hash = cryptoUserData.hashUserData(password, salt);
        customer.setSalt(salt);
        customer.setPassword(hash);
    }

    private void setBankAccountOfCustomer(Customer customer){
        final float INITIAL_BALANCE = 0;
        Bankaccount newBankaccount = customerService.addBankAccount(customer, bankAccountService.getNewAccountNr());
        newBankaccount.setBalance(INITIAL_BALANCE);
    }
}
