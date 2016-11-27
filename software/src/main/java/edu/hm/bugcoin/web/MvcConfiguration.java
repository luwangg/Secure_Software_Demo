package edu.hm.bugcoin.web;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.web.auth.AuthInterceptor;
import edu.hm.bugcoin.web.filter.XssFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 *
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter
{
    // ignore intellj errors... they are wrong! "Man is always greater than machine..."
    @Autowired private AuthInterceptor authInterceptor;
    @Autowired private ProtocolInterceptor protocolInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry)
    {
        registry.addInterceptor(authInterceptor);
        registry.addInterceptor(protocolInterceptor);
    }

    @Override public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/public/").setCachePeriod(31556926);
    }

    @Bean public FilterRegistrationBean XssFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("XssFilter");
        registration.setOrder(1);
        return registration;
    }
}

