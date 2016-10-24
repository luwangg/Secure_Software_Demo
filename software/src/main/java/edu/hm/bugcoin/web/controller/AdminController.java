package edu.hm.bugcoin.web.controller;


import edu.hm.bugcoin.domain.Bankaccount;
import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.UserState;
import edu.hm.bugcoin.service.Customer.CustomerService;
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
public class AdminController {

    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    @Autowired
    private CustomerService customerService;

    // ----------------------------------------------------------------------------------
    //  attributes
    // ----------------------------------------------------------------------------------
    @ModelAttribute
    public void attrs(final HttpSession session, final Model model) {
        final Customer customer = (Customer) session.getAttribute(SessionKey.AUTH_USER);
        model.addAttribute("me", customer);
    }

    // ----------------------------------------------------------------------------------
    //  http handlers
    // ----------------------------------------------------------------------------------

    @RequestMapping("/admin/userManagement")
    @ACL(ACL.Type.NORMAL)
    public String showUsers(@RequestParam(value = "userState", required = false) final UserState userState,
                            @SessionAttribute(SessionKey.AUTH_USER) Customer customer,
                            final HttpSession session, final Model model) {
        UserState selectedUserState;
        if (userState == null) {
            selectedUserState = UserState.USER;
        } else {
            selectedUserState = userState;
        }
        model.addAttribute("customers", customerService.getCustomers(selectedUserState));

        return "userManagement";
    }

    @RequestMapping(value = "/admin/userManagement", method = RequestMethod.POST)
    @ACL(ACL.Type.NORMAL)
    public String alterUser(@RequestParam(value = "userState", required = false) final UserState userState,
                            @RequestParam("action") final String action,
                            @RequestParam("customerCheckBox") final String customerCheckBox,
                            @SessionAttribute(SessionKey.AUTH_USER) Customer customer,
                            final HttpSession session, final Model model) {
        UserState selectedUserState;
        if (userState == null) {
            selectedUserState = UserState.USER;
        } else {
            selectedUserState = userState;
        }
        model.addAttribute("customers", customerService.getCustomers(selectedUserState));

        System.out.println("action: " + action);
        System.out.println("selected customer: " + customerCheckBox);

        return "userManagement";
    }

}
