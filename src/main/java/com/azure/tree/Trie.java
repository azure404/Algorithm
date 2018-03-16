package com.azure.tree;

import com.azure.util.StringUtil;

/**
 * 功能说明：前缀树、字典树
 *
 * @return <br/>
 * 修改历史：<br/>
 * 1.[2018年01月18日 上午9:39] 创建方法 by guyi
 */
public class Trie {

    /**
     * 叶子节点，前缀树26个字母，01字典树则为2
     */
    public static final int MAX_CHILD = 26;

    public static final int LOWER_CASE_A = 'a';

    private int count;
    private Trie[] next;

    public Trie() {
        count = 0;
        next = new Trie[MAX_CHILD];
    }

    public void insert(String word) {
        if (checkWordInvalid(word)) {
            return;
        }
        Trie root = this;
        for (char c : word.toCharArray()) {
            int idx = c - LOWER_CASE_A;
            if (root.next[idx] == null) {
                root.next[idx] = new Trie();
            }
            root = root.next[idx];
            root.count++;
        }
    }

    public void delete(String word) {
        if (checkWordInvalid(word)) {
            return;
        }
        Trie root = this;
        for (char c : word.toCharArray()) {
            int idx = c - LOWER_CASE_A;
            if(root.next[idx] == null){
                return;
            }
            //节点总数为1的直接删除
            if(--root.next[idx].count == 0){
                root.next[idx] = null;
                return ;
            }
            root = root.next[idx];
        }
    }

    public int search(String prefix) {
        if (checkWordInvalid(prefix)) {
            return 0;
        }
        Trie root = this;
        for (char c : prefix.toCharArray()) {
            int idx = c - LOWER_CASE_A;
            root = root.next[idx];
            if (root == null) {
                return 0;
            }
        }
        return root.count;
    }

    private boolean checkWordInvalid(String word) {
        return StringUtil.isEmpty(word);
    }

}
