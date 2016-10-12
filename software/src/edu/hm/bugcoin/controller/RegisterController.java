package edu.hm.bugcoin.controller;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 21:55
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 */
@Controller
public class RegisterController
{
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String view()
    {
        return "register";
    }

}
