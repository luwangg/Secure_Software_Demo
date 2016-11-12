package edu.hm.bugcoin.web.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.CustomerLevel;
import edu.hm.bugcoin.domain.CustomerState;
import edu.hm.bugcoin.security.CryptoUserData;
import edu.hm.bugcoin.security.CryptoUserDataBcrypt;
import edu.hm.bugcoin.service.Customer.CustomerService;
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
import javax.websocket.Session;

/**
 *
 */
@Controller
public class LoginController {

    // ----------------------------------------------------------------------------------
    //  Konstanten
    // ----------------------------------------------------------------------------------

    private static final String HOME_PAGE_USER = "/banking/transactions";
    private static final String HOME_PAGE_ADMIN = "/admin/userManagement";

    private static final String E_OK = null;


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
    public String login(@RequestParam(value = "username") final String username,
                        @RequestParam(value = "password") final String password,
                        @RequestParam(value = "oneTimePassword") final String oneTimePassword,
                        final HttpSession session, final Model model) {
        String redirect = "login";

        try {
            // verify parameter
            if (oneTimePassword.equals(""))
                throw new RuntimeException();

            // search for the user in database
            final Customer customer = customerService.getCustomer(username);
            final Totp totp = new Totp(customer.getOtpKey());

            String errorMessage = verifyCustomerLogin(customer, session, password, totp, oneTimePassword);

            if (errorMessage == E_OK) {
                redirect = redirectToUserOrAdminPage(customer);
            } else {
                model.addAttribute("message", errorMessage);
            }

        } catch (final RuntimeException ignored) {
        }

        if (redirect != "login") {
            return redirect; //success
        } else {
            return redirect; //no success: stay on login page
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SessionKey.AUTH_USER);
        session.invalidate();
        return "redirect:/";
    }

    /**
     * Verify customer login.
     *
     * @param customer        user to login
     * @param session         httpsession
     * @param password        password from user input
     * @param totp            time-based one time password with secret key from database
     * @param oneTimePassword oneTimePassword from user input
     * @return String error message. Valid login: null, invalid login: error message.
     */
    private String verifyCustomerLogin(final Customer customer, final HttpSession session, final String password, final Totp totp, final String oneTimePassword) {
        String errorMessage = E_OK;
        if (customer.getState() == CustomerState.ACTIVE) {
            if (verifyPassword(password, customer.getPassword()) && totp.verify(oneTimePassword)) {
                session.setAttribute(SessionKey.AUTH_USER, customer);

            } else {
                errorMessage = "Benutzername, Passwort oder Token ist falsch!";
            }
        } else {
            errorMessage = "Bitte PostIdent. Verfahren durchführen. Sie werden daraufhin freigeschalten.";
        }
        return errorMessage;
    }

    private boolean verifyPassword(String password, String passwordHash) {
        CryptoUserData cryptoUserData = new CryptoUserDataBcrypt();
        return cryptoUserData.verifyUserData(password, passwordHash);
    }

    private String redirectToUserOrAdminPage(Customer customer) {
        if (isAdmin(customer)) {
            return "redirect:" + HOME_PAGE_ADMIN;
        } else {
            return "redirect:" + HOME_PAGE_USER;
        }
    }

    private boolean isAdmin(Customer customer) {
        return customer.getLevel() == CustomerLevel.ADMIN;
    }
}
