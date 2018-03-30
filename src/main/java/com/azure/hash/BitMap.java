package com.azure.hash;

/**
 * 功能说明：将数值映射到位上，适合大数据量排重处理
 *
 * @author
 * @date 2018年03月30日 下午10:59
 */
public class BitMap {

    private static int[] map = new int[1024];

    public static void put(int num){
        //每个int4字节，可以存储32个数
        //   num/32              num%32
        map[num >> 5] |= 1 << (num & 0x1F);
    }

    public static boolean exist(int num){
        return map[num >> 5] >> (num & 0x1F) == 0x01;
    }

    public static void main(String[] args) {
        BitMap.put(156);
        System.out.println(BitMap.exist(156));
    }
}
