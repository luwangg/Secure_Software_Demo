package edu.hm.bugcoin.web.controller;


import edu.hm.bugcoin.domain.Customer;
import edu.hm.bugcoin.domain.CustomerLevel;
import edu.hm.bugcoin.domain.CustomerState;
import edu.hm.bugcoin.domain.Voucher;
import edu.hm.bugcoin.service.Customer.CustomerService;
import edu.hm.bugcoin.service.Voucher.VoucherService;
import edu.hm.bugcoin.task.TaskWorker;
import edu.hm.bugcoin.task.TransferTask;
import edu.hm.bugcoin.web.auth.ACL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


/**
 *
 */
@Controller
public class AdminControllerVoucher {

    // ----------------------------------------------------------------------------------
    //  member variables
    // ----------------------------------------------------------------------------------

    @Autowired private VoucherService voucherService;
    @Autowired private TaskWorker tasks;

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

    @RequestMapping(value = "/admin/voucher")
    @ACL(CustomerLevel.ADMIN)
    public String showVouchers(@RequestParam(value = "isVoucherReedemedFilter", required = false) final boolean isVoucherReedemedFilter,
                               @SessionAttribute(SessionKey.AUTH_USER) Customer admin,
                               final HttpSession session, final Model model) {

        addDataForFilterToSessionModel(model, isVoucherReedemedFilter);

        return "admin/voucher";
    }

    @RequestMapping(value = "/admin/voucher", method = RequestMethod.POST)
    @ACL(CustomerLevel.ADMIN)
    public String transfer(@RequestParam("amount") final float amount,
                           @SessionAttribute(SessionKey.AUTH_USER) Customer admin,
                           final HttpSession session, final Model model)
    {
        voucherService.addNewVoucher(amount);

        //show new voucher
        boolean isVoucherReedemedFilter = false;
        addDataForFilterToSessionModel(model, isVoucherReedemedFilter);

        return "admin/voucher";
    }

    // ----------------------------------------------------------------------------------
    // private helper methods
    // ----------------------------------------------------------------------------------

    private void addDataForFilterToSessionModel(final Model model, final boolean isVoucherReedemedFilter) {


        model.addAttribute("isVoucherReedemedFilter", isVoucherReedemedFilter);

        List<Voucher> vouchers;
        if(isVoucherReedemedFilter){
            vouchers = voucherService.getAllReedemedVouchers();
        }else{
            vouchers = voucherService.getAllNotReedemedVouchers();
        }
        model.addAttribute("vouchers", vouchers);
    }

}
