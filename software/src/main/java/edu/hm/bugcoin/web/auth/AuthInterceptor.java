package edu.hm.bugcoin.web.auth;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.web.controller.SessionKey;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 *
 */
public class AuthInterceptor implements HandlerInterceptor
{
    private static final int SESSION_TIMEOUT_MIN = 10;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception
    {
        // get the session if no session is found -> redirect to home
        final HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(SESSION_TIMEOUT_MIN * 60);

        // get the handler method which will be executed
        final Method method = ((HandlerMethod)handler).getMethod();
        final ACL acl = method.getAnnotation(ACL.class);

        // if a handler is annotated with ACL check if the SESSION attribute is defined
        if (acl != null && session.getAttribute(SessionKey.AUTH_USER) == null)
        {
            res.sendRedirect("/");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e)
            throws Exception
    {
    }
}
