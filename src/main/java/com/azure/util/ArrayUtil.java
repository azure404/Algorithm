package com.azure.util;

/**
 * 功能说明：TODO
 *
 * @author guyi
 * @date 2018年03月14日 下午5:03
 */
public class ArrayUtil {

    public static void swap(int[] arr, int from, int to) {
        //加法和异或算法交换的索引位置必须不一样，即from!=to
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }
}
