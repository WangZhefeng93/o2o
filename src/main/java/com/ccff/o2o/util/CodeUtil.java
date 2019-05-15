package com.ccff.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码工具类
 */
public class CodeUtil {
    /**
     * 对保存在Session中的和从请求中提交过来的验证码进行验证的方法
     * @param request：请求
     * @return 验证码验证成功返回true，验证码验证失败返回false
     */
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExcepted = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeActual.toLowerCase().equals(verifyCodeExcepted.toLowerCase()))
            return false;
        return true;
    }
}
