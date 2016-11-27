package edu.hm.bugcoin.web;
/*
 * Projekt: bugcoin
 * Autor: Maximilian Pachl
 * 2016-11-12 13:40
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Objects;


/**
 *
 */
@Component
public class ProtocolInterceptor implements HandlerInterceptor
{

    // ----------------------------------------------------------------------------------
    //  constants
    // ----------------------------------------------------------------------------------

    public static final String HEROKU_PROTO_HEADER = "X-Forwarded-Proto";


    // ----------------------------------------------------------------------------------
    //  member variables
    // ----------------------------------------------------------------------------------

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${bugcoin.forcessl}") private boolean forceSsl;


    // ----------------------------------------------------------------------------------
    //  interceptor definition
    // ----------------------------------------------------------------------------------


    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o)
            throws Exception
    {
        if (forceSsl)
        {
            final String proto = req.getHeader(HEROKU_PROTO_HEADER);
            if (proto != null && !proto.equalsIgnoreCase("https"))
            {
                res.sendRedirect(req.getRequestURL().toString().replace("http://", "https://"));
                log.info("upgrading insecure http connection to https");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView modelAndView)
            throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object o, Exception e)
            throws Exception
    {

    }
}
