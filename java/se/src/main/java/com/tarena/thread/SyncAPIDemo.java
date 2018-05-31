package com.tarena.thread;

import java.util.*;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/24
 * @description  将集合转换为线程安全， 集合不是线程安全的
 * 线程安全的集合具有互斥效果，使用迭代器遍历与集合存取没有互斥性
 */
public class SyncAPIDemo {
    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        /**
         * collections.synchronizaedList
         */
        list = Collections.synchronizedList(list);

        Set<String> set = new HashSet<>(list);
        set = Collections.synchronizedSet(set);

        Map<String, Integer> map = new HashMap<>();
        map = Collections.synchronizedMap(map);
    }
}
