package Array.Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Offer：一个整数数组 arr
//        序号代表了一个元素有多大序号编号的规则如下：
//        序号从 1 开始编号
//        一个元素越大，那么序号越大如果两个元素相等，那么它们的序号相同
//        每个数字的序号都应该尽可能地小
//Target：将数组中的每个元素替换为它们排序后的序号
public class D30_1331_arrayRankTransform {
//    java用hashmap求解
//    在比赛的时候的想法是先将arr深拷贝到数组a，然后将a排序，接着遍历arr
//    找到arr[i]在排序数组a中的位置，其大小为下标加一，但是这种方法时间复杂度为n方，所以会超出时间限制
//    因此通过hashmap提高存取速度，key为数组元素，value为元素对应的rank
    public static int[] arrayRankTransform(int[] arr) {
        int a[]=new int[arr.length];
        a=arr.clone();
        Arrays.sort(a);
        Map<Integer,Integer> map=new HashMap<>();
        int index=1;
        for(int i=0;i<a.length;i++) {
            if(i>0&&a[i]!=a[i-1]) {
                map.put(a[i], index);
                index++;
            }
            if(i==0) {
                map.put(a[i], index);
                index++;
            }
        }
        for(int i=0;i<arr.length;i++) {
            arr[i]=map.get(arr[i]);
        }
        return arr;
    }
}
