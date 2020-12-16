package Array.Middle;

import java.util.*;

//设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构
//insert(val)：当元素 val 不存在时，向集合中插入该项
//remove(val)：元素 val 存在时，从集合中移除该项
//getRandom：随机返回现有集合中的一项，每个元素应该有相同的概率被返回
public class D17_380_RandomizedSet {

//    insert
//    有两个平均插入时间为 O(1) 的选择
//    哈希表：Java 中为 HashMap，Python 中为 dictionary
//    动态数组：Java 中为 ArrayList，Python 中为 list
//
//    getRandom
//    思想：选择一个随机索引，使用该索引返回一个元素而哈希表中没有索引
//    虽然哈希表提供常数时间的插入和删除，但是实现 getRandom 时会出现问题
//    因此要获得真正的随机值，则要将哈希表中的键转换为列表
//        线性时间解决的方法：用列表存储值，并在该列表中实现常数时间的 getRandom
//
//    remove
//    删除任意索引元素需要线性时间，这里的解决方案是总是删除最后一个元素
//        将要删除元素和最后一个元素交换
//        将最后一个元素删除
//    为此，必须在常数时间获取到要删除元素的索引，因此需要一个哈希表来存储值到索引的映射
//
//    综上所述，我们使用这样的数据结构：哈希表 + 动态数组
//    动态数组存储元素值
//    哈希表存储存储值到索引的映射

    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    public D17_380_RandomizedSet() {
        dict = new HashMap();
        list = new ArrayList();
    }

//    添加元素到动态数组
//    在哈希表中添加值到索引的映射
    public boolean insert(int val) {
        if (dict.containsKey(val)) return false;

        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

//    在哈希表中查找要删除元素的索引
//    将要删除元素与最后一个元素交换
//    删除最后一个元素
//    更新哈希表中的对应关系
    public boolean remove(int val) {
        if (! dict.containsKey(val)) return false;

        int lastElement = list.get(list.size() - 1);
        int idx = dict.get(val);
        list.set(idx, lastElement);
        dict.put(lastElement, idx);
        
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }

    public int getRandom() {
        //借助 Java 中 的 Random 实现
        return list.get(rand.nextInt(list.size()));
    }

//    时间复杂度
//        getRandom 时间复杂度为 O(1)
//        insert 和 remove 平均时间复杂度为 O(1)，在最坏情况下为 O(N)
//            当元素数量超过当前分配的动态数组和哈希表的容量导致空间重新分配时
//            既然是使用哈希表，增删改查的时间复杂度都是O(1)，只有在出现哈希冲突的时候才会有最坏情况
//            在Java中出现哈希冲突我记得先是使用链表，如果冲突还是很大，会转红黑树
//
//    空间复杂度：O(N)，在动态数组和哈希表分别存储了 N 个元素的信息

}
