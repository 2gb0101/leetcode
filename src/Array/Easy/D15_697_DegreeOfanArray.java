package Array.Easy;

import java.util.HashMap;

//Offer：一个数组，定义数组的度为某个或某些数字出现最多的次数
//Target：要找最短的子数组，使其和原数组拥有相同的度
public class D15_697_DegreeOfanArray {
//    由题知，肯定需要统计每个数字出现的次数，首先想到的就是哈希表
//    双遍历：
//    利用哈希表建立映射
//    由于要求包含原度的最小长度的子数组
//    最好的情况
//        子数组的首位数字就是统计度的数字，即出现最多的数字
//    还得知道该数字的第一次出现的位置和最后一次出现的位置
//        由于最初不知道哪些数字会出现最多次
//        所以要统计所有数字的首尾出现位置
//    即哈希表需要保存
//        每个数字的出现的频率（度）
//        每个数字的首尾出现的位置
//        所以新建一个Node类来保存这些数据

    public class Node{
        int firstPostion;
        int lastPostion;
        int nodeDegree;
    }

    public int findShortestSubArray(int[] nums) {
        int res = Integer.MAX_VALUE, degree = 0;
        HashMap<Integer, Node> m = new HashMap<Integer, Node>();
        for (int i = 0; i < nums.length; ++i) {
            if(m.get(nums[i]) == null){
                Node tmp = new Node();
                tmp.firstPostion = i;
                tmp.lastPostion = i;
                tmp.nodeDegree = 1;
                m.put(nums[i],tmp);
            }else{
                Node tmp = m.get(nums[i]);
                tmp.lastPostion = i;
                tmp.nodeDegree++;
                m.put(nums[i],tmp);
            }
            degree = Math.max(degree, m.get(nums[i]).nodeDegree);
        }
        for (int i = 0; i < nums.length; ++i) {
            if (degree == m.get(nums[i]).nodeDegree) {
                res = Math.min(res, m.get(nums[i]).lastPostion - m.get(nums[i]).firstPostion + 1);
            }
        }
        return res;
    }

//    单遍历：
//    建立每个数字和其第一次出现位置之间的映射
//    把当前遍历的位置看作是尾位置
//    由此计算子数组的长度
    public class Node2{
        int firstPostion;
        int nodeDegree;
    }

    public int findShortestSubArray2(int[] nums) {
        int n = nums.length, res = Integer.MAX_VALUE, degree = 0;
        HashMap<Integer, Node2> m = new HashMap<Integer, Node2>();
        for (int i = 0; i < n; ++i) {
            if(m.get(nums[i]) == null){
                Node2 tmp = new Node2();
                tmp.firstPostion = i;
                tmp.nodeDegree = 1;
                m.put(nums[i],tmp);
            }else{
                Node2 tmp = m.get(nums[i]);
                tmp.nodeDegree++;
                m.put(nums[i],tmp);
            }
            if (m.get(nums[i]).nodeDegree == degree) {
                res = Math.min(res, i - m.get(nums[i]).firstPostion + 1);
            } else if (m.get(nums[i]).nodeDegree > degree) {
                res = i - m.get(nums[i]).firstPostion + 1;
                degree = m.get(nums[i]).nodeDegree;
            }
        }
        return res;
    }

}


