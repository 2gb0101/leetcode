package Array.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

//Target：设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构
//注意: 允许出现重复元素
//insert(val)：向集合中插入元素 val
//remove(val)：当 val 存在时，从集合中移除一个 val
//getRandom：从现有集合中随机获取一个元素每个元素被返回的概率应该与其在集合中的数量呈线性相关
public class D6_381_RandomizedCollection {
//    我们需要实现三个操作：insert、remove、getRadom
//    getRandom 时间复杂度为 O(1)，并出现的概率与值的副本数成线性比例
//        最简单的解决方案是将所有值存储在一个列表中。
//        一旦存储了所有值，我们所要做的就是选择一个随机索引。
//    我们并不关心元素的顺序，所以可以使用动态数组（Java 中的 ArrayList 或 Python 中的 list）在 O(1) 的时间执行 insert。
//    由于我们不关心元素的顺序，如果我们想删除第 i 个索引处的元素
//        我们可以交换第 i 个元素和最后一个元素，并执行 O(1) 的 pop 操作
//        事实上我们不需要交换，我们只需要将最后一个元素复制到索引 i 中，再弹出最后一个元素
//    问题中最困难的部分就是要在 O(1) 的时间找到要删除元素的索引，我们可以通过一个哈希表将元素值映射到它们的索引。
//
//    方法：动态数组 + 哈希表
//    我们将用列表来存储所有元素
//    为了找到要删除元素的索引，我们将使用 HashMap 或 dictionary 将值映射到具有这些值的所有索引
//    困难的部分是在修改列表时正确地更新 HashMap
//    insert：添加元素到 list，并在 HaspMap 表中添加对应的索引
//    remove：这是比较困难的部分，我们使用HashMap 找到要删除元素的索引
//            使用概述中的方法实现 O(1) 时间删除元素
//            由于列表中的最后一个元素被移动，我们要更新 HashMap 中的最后一个元素对应的索引值，还要删除 HashMap 中要删除元素的索引
//    getRadom：从列表中随机抽取一个元素
    public class RandomizedCollection {
        ArrayList<Integer> lst;
        java.util.HashMap<Integer, Set<Integer>> idx;
        java.util.Random rand = new java.util.Random();

        public RandomizedCollection() {
            lst = new ArrayList<Integer>();
            idx = new HashMap<Integer, Set<Integer>>();
        }

        public boolean insert(int val) {
            if (!idx.containsKey(val)) idx.put(val, new LinkedHashSet<Integer>());
            idx.get(val).add(lst.size());
            lst.add(val);
            return idx.get(val).size() == 1;
        }

        public boolean remove(int val) {
            if (!idx.containsKey(val) || idx.get(val).size() == 0) return false;
            int remove_idx = idx.get(val).iterator().next();
            idx.get(val).remove(remove_idx);
            int last = lst.get(lst.size() - 1);
            lst.set(remove_idx, last);
            idx.get(last).add(remove_idx);
            idx.get(last).remove(lst.size() - 1);

            lst.remove(lst.size() - 1);
            return true;
        }

        public int getRandom() {
            return lst.get(rand.nextInt(lst.size()));
        }
    }
}
