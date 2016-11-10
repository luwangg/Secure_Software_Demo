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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 *
 */
@Controller
public class AdminControllerUser {

    // ----------------------------------------------------------------------------------
    //  member variables
    // ----------------------------------------------------------------------------------

    @Autowired private CustomerService customerService;

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

    @RequestMapping(value = "/admin/userManagement")
    @ACL(CustomerLevel.ADMIN)
    public String showCustomers(@RequestParam(value = "level", required = false) final CustomerLevel level,
                                @SessionAttribute(SessionKey.AUTH_USER) Customer admin,
                                final HttpSession session, final Model model) {

        addDataForFilterToSessionModel(model, level);

        return "userManagement";
    }

    @RequestMapping(value = "/admin/userManagement", method = RequestMethod.POST)
    @ACL(CustomerLevel.ADMIN)
    public String alternateCustomer(@RequestParam(value = "level", required = false) final CustomerLevel level,
                                    @RequestParam(value = "action", required = false) final String action,
                                    @RequestParam(value = "customerNickname", required = false) final String customerNickname,
                                    @SessionAttribute(SessionKey.AUTH_USER) Customer admin,
                                    final HttpSession session, final Model model) {

        addDataForFilterToSessionModel(model, level);

        if (isUserInputValid(action, customerNickname)) {
            Customer selectedCustomer = customerService.getCustomer(customerNickname);
            if (selectedCustomer != null) {
                try {
                    doActionOnCustomer(selectedCustomer, action);
                } catch (CustomerService.IllegalCustomerLevelException e) {
                    System.err.println("IllegalCustomerLevelException: " + e.getMessage());
                    model.addAttribute("message", "Ein up- oder downgrade von Admin und System Accounts ist nicht erlaubt.");
                }
            }
        } else {
            model.addAttribute("message", "Keine Aktion oder keinen User ausgewählt!");
        }

        return "userManagement";
    }

    // ----------------------------------------------------------------------------------
    // private helper methods
    // ----------------------------------------------------------------------------------

    private void addDataForFilterToSessionModel(final Model model, final CustomerLevel level) {
        CustomerLevel selectedLevel;
        if (level == null) {
            selectedLevel = CustomerLevel.USER;
        } else {
            selectedLevel = level;
        }

        List<CustomerLevel> levels = Arrays.asList(CustomerLevel.values());
        model.addAttribute("levels", levels);
        model.addAttribute("selectedLevel", selectedLevel);
        model.addAttribute("customers", customerService.getCustomers(selectedLevel));
    }

    private boolean isUserInputValid(String action, String customerNickname) {
        return !(action == null || customerNickname == null);
    }

    private void doActionOnCustomer(Customer customer, String action) throws CustomerService.IllegalCustomerLevelException, IllegalArgumentException {
        switch (action) {
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
