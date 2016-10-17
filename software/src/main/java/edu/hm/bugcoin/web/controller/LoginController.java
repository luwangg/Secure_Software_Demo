package edu.hm.bugcoin.web.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.service.CustomerService;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
public class LoginController
{

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------

    private static final String HOME_PAGE = "/banking/transactions";


    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @Autowired
    private CustomerService customerService;


    // ----------------------------------------------------------------------------------
    //  http handlers
    // ----------------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String view() {
        return "login";
    }

    @PostMapping(value = "/")
    @Transactional(readOnly = true)
    public String login(@RequestParam(value="username") final String username,
                        @RequestParam(value="password") final String password,
                        @RequestParam(value="token") final String token,
                        HttpSession session, Model model)
    {
        // do the login
        try
        {
            // verify parameter
            if (token.equals(""))
                throw new RuntimeException();

            // search for the uesr in database
            final Customer id = customerService.getCustomer(username);
            final Totp otp = new Totp(id.getOtpKey());

            // validate password and otp key
            // TODO: store password as hash in database!
            if (id.getPassword().equals(password) && otp.verify(token))
            {
                session.setAttribute(SessionKey.AUTH_USER, id);
                return "redirect:" + HOME_PAGE;
            }

        } catch (final RuntimeException ignored) { }

        // when we reached this statement, then the login was not good
        model.addAttribute("message", "Benutzername, Passwort oder Token ist falsch!");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SessionKey.AUTH_USER);
        session.invalidate();
        return "redirect:/";
    }
}
