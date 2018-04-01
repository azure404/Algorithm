package com.azure.hash;

import java.nio.charset.Charset;

/**
 * 功能说明：https://en.wikipedia.org/wiki/Bloom_filter
 *
 *
 * @author
 * @date 2018年03月30日 下午11:16
 */
public class BloomFilter {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * 布隆过滤器要映射元素的数量
     */
    private int size;
    /**
     * 布隆过滤器容器大小
     */
    private int capacity;
    /**
     * 哈希次数
     */
    private int hashTimes;

    private BitMap table;

    public BloomFilter(int capacity, int size, int hashTimes) {
        this.size = size;
        this.capacity = capacity;
        this.hashTimes = hashTimes;
        table = new BitMap(capacity);
    }

    /**
     * k = -lnf / ln2 = -log2(f)
     * m = kn / ln2
     * @param falsePositiveProbability f
     * @param size n
     */
    public BloomFilter(double falsePositiveProbability, int size){
        double ln2 = Math.log(2);
        this.hashTimes = (int) Math.ceil(-Math.log(falsePositiveProbability) / ln2);
        this.capacity = (int) Math.ceil(hashTimes * size / ln2);
        this.size = size;
        table = new BitMap(capacity);
    }

    /**
     * 插入
     * @param data
     */
    public void put(String data){
        int[] hashes = hash(data.getBytes(CHARSET));
        for (int i = 0; i < hashes.length; i++) {
            table.put(hashes[i]);
        }
    }

    /**
     * 查找
     * @param data
     * @return
     */
    public boolean contains(String data){
        int[] hashes = hash(data.getBytes(CHARSET));
        for (int i = 0; i < hashes.length; i++) {
            if(!table.contains(hashes[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 待实现
     * murmur hash, 参考google bloom filter
     * @param data
     * @return
     */
    private int[] hash(byte[] data){
        int[] res = new int[hashTimes];

        return null;
    }

}
