package edu.hm.bugcoin.web.filter;
/*
 * Projekt: software
 * Autor: Maximilian Pachl
 * 2016-10-31 13:57
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;


/**
 *
 */
public class XssFilter implements Filter
{

    // ----------------------------------------------------------------------------------
    //  Objektvariablen
    // ----------------------------------------------------------------------------------

    private FilterConfig filterConfig;


    // ----------------------------------------------------------------------------------
    //  filter definition
    // ----------------------------------------------------------------------------------

    @Override public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        System.out.println("XssFilter initialized!");
    }

    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        chain.doFilter(new RequestWrapper((HttpServletRequest)request), response);
    }

    @Override public void destroy()
    {
        this.filterConfig = null;
    }


    // ----------------------------------------------------------------------------------
    //  request wrapper
    // ----------------------------------------------------------------------------------

    private static class RequestWrapper extends HttpServletRequestWrapper
    {
        RequestWrapper(HttpServletRequest request)
        {
            super(request);
        }

        public String[] getParameterValues(String parameter) {
            String[] values = super.getParameterValues(parameter);
            if (values == null) {
                return null;
            }
            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                encodedValues[i] = cleanXSS(values[i]);
            }
            return encodedValues;
        }

        public String getParameter(String parameter) {
            String value = super.getParameter(parameter);
            if (value == null) {
                return null;
            }
            return cleanXSS(value);
        }

        public String getHeader(String name) {
            String value = super.getHeader(name);
            if (value == null)
                return null;
            return cleanXSS(value);
        }

        private String cleanXSS(String value) {
            // You'll need to remove the spaces from the html entities below
            //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
            //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
            //value = value.replaceAll("'", "& #39;");
            value = value.replaceAll("eval\\((.*)\\)", "");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

            value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
            value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
            value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
            value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
            //value = value.replaceAll("<script>", "");
            //value = value.replaceAll("</script>", "");
            return value;
        }
    }
}
