package com.azure.sort;

import com.azure.util.ArrayUtil;

import java.util.Random;

/**
 * 三种快排:单轴，双轴，三向切分，这里实现单轴
 * 单轴三种快排：固定枢轴，随机枢轴，三数取中
 * 优化：1.数组长度<=20使用插排
 *      2.聚集，在一次分割结束后，把与枢轴相等的元素聚在一起，下次分割时，不再对与枢轴相等的元素分割
 *
 * @author azure
 * @date 2017/11/10 上午11:59
 */
public class QuickSort {

    /**
     * 排序接口
     * @param nums
     * @param left
     * @param right
     * @param strategy 排序策略
     */
    public static void sort(int[] nums, int left
            , int right, QuickSortStrategy strategy){
        if(cantSort(nums, left, right)) {
            return;
        }
        //优化1，小数组时用插排，数组长度<20插排比快排好
        if(right - left + 1 <= 10){
            BaseSort.insertionSort(nums,left,right);
            return;
        }
        int low = left, high = right;
        int leftLen = 0, rightLen = 0;
        //移动枢轴到left位置
        ArrayUtil.swap(nums, left, strategy.selecter().select(nums, left, right));
        //基准数
        int pivot = nums[left];
        //根据基准数分区
        while(low < high){
            //须从基准数反方向开始查找
            //优化2，将与枢轴相等的数聚集在两端，而后转移到枢轴附近跳过分区操作
            while (low < high && nums[high] >= pivot) {
                if(nums[high] == pivot){
                    ArrayUtil.swap(nums, high, right - rightLen++);
                }
                high--;
            }
            while (low < high && nums[low] <= pivot) {
                if(nums[low] == pivot && low != left){
                    ArrayUtil.swap(nums, low, left + 1 + leftLen++);
                }
                low++;
            }
            ArrayUtil.swap(nums, low, high);
        }
        ArrayUtil.swap(nums, left, low);
        //左端聚集元素往中间移动
        int i = -1;
        while (++i < leftLen) {
            ArrayUtil.swap(nums, left + 1 + i, low - 1 - i);
        }
        //右端移动
        i = -1;
        while (++i < rightLen) {
            ArrayUtil.swap(nums, right - i, low + 1 + i);
        }
        //分区结束，跳过相等枢轴进行分区排序
        sort(nums, left, low - 1 - leftLen, strategy);
        sort(nums, low + 1 + rightLen, right, strategy);
    }

    /**
     * 检查是否可以排序
     * @param nums
     * @param left
     * @param right
     * @return 不需要排序返回true
     */
    private static boolean cantSort(int[] nums, int left, int right){
        return BaseSort.cantSort(nums, left, right);
    }

    /**
     * 枢轴选取策略接口定义
     */
    private interface PivotSelecter{
        int select(int[] nums, int left, int right);
    }

    /**
     * 枢轴选取策略
     */
    public enum QuickSortStrategy{
        FIXED_PIVOT((nums, left, right) -> left),

        RANDOM_PIVOT((nums, left, right) -> left + new Random().nextInt(right - left + 1)),

        MEDIAN_OF_THREE_PIVOT((nums, left, right) -> {
            int mid = left + (right - left) / 2;
            int pivot;
            //可以随机选取三个，一般直接选取起始，中间，末尾三者中间值的索引
            if(nums[left] <= nums[mid]
                    && nums[mid] <= nums[right]){
                pivot = mid;
            }else if(nums[mid] <= nums[left]
                    && nums[left] <= nums[right]){
                pivot = left;
            }else {
                pivot = right;
            }
            return pivot;
        });

        private PivotSelecter selecter;

        QuickSortStrategy(PivotSelecter selecter){
            this.selecter = selecter;
        }

        public PivotSelecter selecter() {
            return selecter;
        }
    }


}
