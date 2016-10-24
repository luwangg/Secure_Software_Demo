package edu.hm.bugcoin.web.controller;


import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.CustomerLevel;
import edu.hm.bugcoin.domain.CustomerState;
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
        final Customer admin = (Customer) session.getAttribute(SessionKey.AUTH_USER);
        model.addAttribute("me", admin);
    }

    // ----------------------------------------------------------------------------------
    //  http handlers
    // ----------------------------------------------------------------------------------

    @RequestMapping("/admin/userManagement")
    @ACL(ACL.Type.NORMAL)
    public String showUsers(@RequestParam(value = "customerLevel", required = false) final CustomerLevel customerLevel,
                            @SessionAttribute(SessionKey.AUTH_USER) Customer admin,
                            final HttpSession session, final Model model) {
        CustomerLevel selectedCustomerLevel;
        if (customerLevel == null) {
            selectedCustomerLevel = CustomerLevel.USER;
        } else {
            selectedCustomerLevel = customerLevel;
        }
        model.addAttribute("customers", customerService.getCustomers(selectedCustomerLevel));

        return "userManagement";
    }

    @RequestMapping(value = "/admin/userManagement", method = RequestMethod.POST)
    @ACL(ACL.Type.NORMAL)
    public String alterUser(@RequestParam(value = "customerLevel", required = false) final CustomerLevel customerLevel,
                            @RequestParam("action") final String action,
                            @RequestParam("customerCheckBox") final String customerCheckBox,
                            @SessionAttribute(SessionKey.AUTH_USER) Customer admin,
                            final HttpSession session, final Model model) {
        CustomerLevel selectedCustomerLevel;
        if (customerLevel == null) {
            selectedCustomerLevel = CustomerLevel.USER;
        } else {
            selectedCustomerLevel = customerLevel;
        }
        model.addAttribute("customers", customerService.getCustomers(selectedCustomerLevel));

        Customer selectedCustomer = customerService.getCustomer(customerCheckBox);

        if(action == null || selectedCustomer == null){
            model.addAttribute("message", "Keine Aktion oder keinen User ausgewählt!");
        }else{
            try {
                doActionOnCustomer(selectedCustomer, action);
            } catch (CustomerService.IllegalCustomerLevelException e) {
                System.out.println(e.getMessage());
            }
        }

        return "userManagement";
    }

    // ----------------------------------------------------------------------------------
    // private helper methods
    // ----------------------------------------------------------------------------------

    private void doActionOnCustomer(Customer customer, String action) throws CustomerService.IllegalCustomerLevelException, IllegalArgumentException {
        switch (action){
            case "activate":
                customerService.setCustomerState(customer, CustomerState.ACTIVE);
                break;
            case "deactivate":
                customerService.setCustomerState(customer, CustomerState.INACTIVE);
                break;
            case "upgrade":
                customerService.setCustomerLevel(customer, CustomerLevel.PRO_USER);
                break;
            case "downgrade":
                customerService.setCustomerLevel(customer, CustomerLevel.USER);
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
    }
}
