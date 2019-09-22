package com.rom.rrm.config;

import com.rom.rrm.annotations.ReCaptchaValidation;
import com.rom.rrm.service.RestConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private RestConnection restConnection;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            ReCaptchaValidation reCaptchaValidation = ((HandlerMethod) handler).getMethodAnnotation(ReCaptchaValidation.class);
            if (null == reCaptchaValidation) {
                reCaptchaValidation = ((HandlerMethod) handler).getMethod().getDeclaringClass()
                        .getAnnotation(ReCaptchaValidation.class);
            }
            // Check login if you have login validation annotations
            if (null != reCaptchaValidation) {
                String rcToken = request.getHeader("rcToken");

                if (rcToken != null && !rcToken.isEmpty()) {
                    System.out.println("token received: " + rcToken);
                    if(!restConnection.verifyGoogleReCaptcha(rcToken)){
                        response.sendError(HttpStatus.FORBIDDEN.value(),"Unverified User");
                        return false;
                    }
                }else {
                    response.sendError(HttpStatus.FORBIDDEN.value(),"Unverified User");
                    return false;
                }
            }
        }
        return true;
    }
}
