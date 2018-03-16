package com.azure.sort;

import com.azure.util.ArrayUtil;

/**
 * 功能说明：
 *      基础排序：选择排序，插入排序
 *
 * @author guyi
 * @date 2018年03月14日 下午5:04
 */
public class BaseSort {

    /**
     * 插排
     * @param nums
     * @param left
     * @param right
     */
    public static void insertionSort(int[] nums, int left, int right){
        if(cantSort(nums, left, right)){
            return;
        }
        int tmp, j;
        for (int i = left + 1; i <= right; i++) {
            tmp = nums[i];
            for(j = i - 1; j >= left && nums[j] > tmp; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = tmp;
        }
    }

    /**
     * 选择排序
     * @param nums
     * @param left
     * @param right
     */
    public static void selectionSort(int[] nums, int left, int right){
        if(cantSort(nums, left, right)){
            return;
        }
        int min;
        for (int i = left; i < right; i++) {
            min = i;
            for (int j = i + 1; j <= right; j++) {
                if(nums[j] < nums[min]){
                    min = j;
                }
            }
            ArrayUtil.swap(nums, i, min);
        }
    }

    /**
     * 检查是否可以排序
     * @param nums
     * @param left
     * @param right
     * @return 不需要排序返回true
     */
    static boolean cantSort(int[] nums, int left, int right){
        return left >= right
                || nums == null
                || left < 0
                || right > nums.length - 1;
    }
}
