package edu.hm.bugcoin;
/*
 * Projekt: bugcoin
 * Autor: Team "Papa"
 * 2016-10-12 20:59
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import edu.hm.bugcoin.auth.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 *
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter
{
    @Override
    public void addInterceptors(final InterceptorRegistry registry)
    {
        registry.addInterceptor(new AuthInterceptor());
    }
}
