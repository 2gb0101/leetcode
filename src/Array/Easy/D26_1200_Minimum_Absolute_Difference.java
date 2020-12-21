package Array.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Offer：
//给你个整数数组 arr，其中每个元素都不相同
//Target：
//请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
public class D26_1200_Minimum_Absolute_Difference {
//    排序后遍历即可
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int dif = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i + 1] - arr[i] < dif) {
                dif = arr[i + 1] - arr[i];
                res.clear();
                List<Integer> l = new ArrayList<Integer>();
                l.add(arr[i]);
                l.add(arr[i + 1]);
                res.add(l);
            } else if(arr[i + 1] - arr[i] == dif) {
                List<Integer> l = new ArrayList<Integer>();
                l.add(arr[i]);
                l.add(arr[i + 1]);
                res.add(l);
            }
        }
        return res;
    }
}
