package Array.Easy;

//Offer：一个整数数组 arr
//Target：检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
//        更正式地，检查是否存在两个下标 i 和 j 满足：
//        i != j
//        0 <= i, j < arr.length
//        arr[i] == 2 * arr[j]

import java.util.*;

//示例 1：
//    输入：arr = [10,2,5,3]
//    输出：true
//    解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5
public class D31_1346_checkIfExist {
//    解法1：先排序，第二遍解决负数问题
    public boolean checkIfExist1(int[] arr) {
        Arrays.sort(arr); // 负数...0...0...正数
        Set<Integer> set = new HashSet<>();
        for (int value : arr) {
            if (value <= 0) continue; // 只对非负数
            if (set.contains(value)) return true;
            set.add(value * 2);
        }
        set.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > 0) continue; // 只对负数
            if (set.contains(arr[i])) return true;
            set.add(arr[i] * 2);
        }
        return false;
    }


//    解法2：不排序，直接Set
    public boolean checkIfExist2(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int value : arr) {
            if (set.contains(value)) return true;
            set.add(value * 2);
            if ((value % 2) == 0) set.add(value / 2); // 能整除才放入
        }
        return false;
    }


//    解法3
//    显然，0 是特殊元素，0 和任何元素相乘都得0，因此，如果 0 的个数大于 1，直接返回 true 即可。
//    基本思路：map 存储，key 为元素值，value 为索引，然后第二次遍历，查找 2倍元素是否存在即可
//    时间复杂度O(n)，空间复杂度O(n)
    public boolean checkIfExist3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int num_zero = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                num_zero++;
            } else {
                map.put(arr[i], i);
            }
            if (num_zero > 1) {
                return true;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int temp = 2 * arr[i];
            if (map.containsKey(temp)) {
                return true;
            }
        }
        return false;
    }
}
