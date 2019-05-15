package com.ccff.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 接收Http请求中的各类型参数，通过key值返回对应类型的参数值
 */
public class HttpServletRequestUtil {
    /**
     * 从Http请求中返回int类型的参数
     * @param request：Http请求对象
     * @param key：参数的键
     * @return 键名为key的int类型的参数值
     */
    public static int getInt(HttpServletRequest request,String key){
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e){
            return -1;
        }
    }

    /**
     * 从Http请求中返回long类型的参数
     * @param request：Http请求对象
     * @param key：参数的键
     * @return 键名为key的long类型的参数值
     */
    public static long getLong(HttpServletRequest request,String key){
        try {
            return Long.decode(request.getParameter(key));
        } catch (Exception e){
            return -1;
        }
    }

    /**
     * 从Http请求中返回double类型的参数
     * @param request：Http请求对象
     * @param key：参数的键
     * @return 键名为key的double类型的参数值
     */
    public static double getDouble(HttpServletRequest request,String key){
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (Exception e){
            return -1d;
        }
    }

    /**
     * 从Http请求中返回boolean类型的参数
     * @param request：Http请求对象
     * @param key：参数的键
     * @return 键名为key的boolean类型的参数值
     */
    public static boolean getBoolean(HttpServletRequest request,String key){
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 从Http请求中返回String类型的参数
     * @param request：Http请求对象
     * @param key：参数的键
     * @return 键名为key的String类型的参数值
     */
    public static String getString(HttpServletRequest request,String key){
        try {
            String result = request.getParameter(key);
            if (result != null){
                result = result.trim();
            }
            if ("".equals(result)){
                result = null;
            }
            return result;
        } catch (Exception e){
            return null;
        }
    }
}
