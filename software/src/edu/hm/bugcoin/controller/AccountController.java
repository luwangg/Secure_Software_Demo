package edu.hm.bugcoin.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 21:27
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.auth.ACL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 */
@Controller
public class AccountController
{
    @RequestMapping("/account")
    @ACL(ACL.Type.NORMAL)
    public String view()
    {
        return "account";
    }
}
