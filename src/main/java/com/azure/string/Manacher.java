package com.azure.string;

/**
 * 求最长回文子串
 * @author guyi
 * @version 1.0
 * @date 2018/08/19 下午9:00
 */
public class Manacher {

    private static String format(String str){
        int len = str.length();
        //abc 会被格式化成 $#a#b#c#
        char[] format = new char[(len + 1) << 1];
        format[0] = '$';
        format[1] = '#';
        for (int i = 0, idx; i < len; i++) {
            idx = (i + 1) << 1;
            format[idx] = str.charAt(i);
            //字符串中没出现过的字符
            format[idx + 1] = '#';
        }
        return new String(format);
    }

    /**
     * abdkcdnkjdmnkfkdmfk
     *  |  |  |  |  |
     * mx' j id  i  mx
     *  mx为目前已匹配到的最大回文串
     *  id为mx串的中心
     *  i和j对称，j = 2*id-i
     * @param str
     * @return
     */
    public static int manacher(String str) {
        str = format(str);
        int id = 0, mx = 0, len = str.length();
        int[] ans = new int[len];
        int res = 0;
        for (int i = 1; i < len; i++) {
            if(i < mx){
                //dp,要么取mx-i，要么取j点最长回文长度
                ans[i] = Math.min(mx - i, ans[2 * id - i]);
            } else {
                //没有匹配过
                ans[i] = 1;
            }
            //第一个字符$一定不会匹配到，所以只用判断i + ans[i]有没有越界
            while(i + ans[i] < len &&
                    str.charAt(i - ans[i]) == str.charAt(i + ans[i])){
                ans[i]++;
            }
            if(mx < i + ans[i]){
                id = i;
                mx = i + ans[i];
            }
            res = Math.max(res, ans[i] - 1);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(manacher("abcba"));
    }
}
