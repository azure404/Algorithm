package com.azure.util;

/**
 * 功能说明：TODO
 *
 * @author guyi
 * @date 2018年03月14日 下午5:09
 */
public class StringUtil {

    public static boolean isEmpty(CharSequence str){
        return str == null || str.length() == 0;
    }
}
