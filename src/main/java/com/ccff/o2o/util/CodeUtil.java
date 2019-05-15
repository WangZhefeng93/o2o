package com.ccff.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExcepted = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeActual.toLowerCase().equals(verifyCodeExcepted.toLowerCase()))
            return false;
        return true;
    }
}
