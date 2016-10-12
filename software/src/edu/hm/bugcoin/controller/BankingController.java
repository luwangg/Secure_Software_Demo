package edu.hm.bugcoin.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.auth.ACL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 */
@Controller
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
}
