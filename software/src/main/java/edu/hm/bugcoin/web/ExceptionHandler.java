package edu.hm.bugcoin.web;
/*
 * Projekt: bugcoin
 * Autor: Maximilian Pachl
 * 2016-11-12 13:24
 * duplo, Windows 7 Ultimate, Oracle JDK 1.8.0_02
 */

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 *
 */
@ControllerAdvice
public class ExceptionHandler
{
    // ----------------------------------------------------------------------------------
    //  constants
    // ----------------------------------------------------------------------------------

    private static final String ERROR_VIEW = "error";


    // ----------------------------------------------------------------------------------
    //  http handlers
    // ----------------------------------------------------------------------------------

    public ModelAndView handleError(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        final ModelAndView mav = new ModelAndView();
        mav.setViewName(ERROR_VIEW);
        return mav;
    }
}
