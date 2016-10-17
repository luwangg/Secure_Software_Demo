package edu.hm.bugcoin.web.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.web.auth.ACL;
import edu.hm.bugcoin.web.auth.InjectAttr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 */
@Controller
@InjectAttr(session = SessionKey.AUTH_USER, model = "me")
public class BankingController
{
    @RequestMapping("/banking/transactions")
    @ACL(ACL.Type.NORMAL)
    public String payments()
    {
        return "transactions";
    }

    @RequestMapping(value = "/banking/transfer", method = RequestMethod.GET)
    @ACL(ACL.Type.NORMAL)
    public String transfer()
    {
        return "transfer";
    }

    @RequestMapping(value = "/banking/transfer", method = RequestMethod.POST)
    @ACL(ACL.Type.NORMAL)
    public String transfer(@RequestParam(value="target") final String code)
    {
        return "transfer";
    }

    @RequestMapping(value = "/banking/coupon", method = RequestMethod.GET)
    @ACL(ACL.Type.NORMAL)
    public String coupon()
    {
        return "coupon";
    }

    @RequestMapping(value = "/banking/coupon", method = RequestMethod.POST)
    @ACL(ACL.Type.NORMAL)
    public String coupon(@RequestParam(value="code") final String code)
    {
        return "coupon";
    }
}