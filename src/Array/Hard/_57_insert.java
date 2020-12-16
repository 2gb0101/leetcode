package Array.Hard;

import java.util.ArrayList;
import java.util.List;
//Offer：一个无重叠的 ，按照区间起始端点排序的区间列表。
//Target：
//     在列表中插入一个新的区间
//     需要确保列表中的区间仍然有序且不重叠
//     （如果有必要的话，可以合并区间）
public class _57_insert {
//    解法：贪心算法，从最简单的子结构开始拆分
//    1.当interval整体在newInterval整体之前时：List<int[]> res直接添加interval整体，并将插入位置insertPos+1；
//                2.当interval整体在newInterval整体之后时：说明此时newInterval已经添加☞List<int[]> res中，直接添加interval整体；
//                3.interval与newInterval存在相交的情况，(不用分析有几个interval与newInterval相交了)，只要相交了，将interval与newInterval融合：
//                1.newInterval[0] = Math.min(interval[0], newInterval[0]);
//    2.newInterval[1] = Math.max(interval[1], newInterval[1]);
//    4.最后遍历完interval，将newInterval插入到res指定的位置insertPos；
    public int[][] insert(int[][] intervals, int[] newInterval){
        if (newInterval == null || intervals == null) {
            return intervals;
        }

        List<int[]> results = new ArrayList<int[]>();
        int insertPos = 0;//插入位置

        for (int[] interval : intervals) {//遍历intervals
            if (interval[1] < newInterval[0]) {//如果当前区间的end小于新区间的start，说明无重叠，直接添加
                results.add(new int[]{interval[0], interval[1]});
                insertPos++;//插入位置+1
            } else if (interval[0] > newInterval[1]) {//如果当前区间的start大于新区间的end，说明无重叠，直接添加
                results.add(new int[]{interval[0], interval[1]});
            } else {//否则一定有重叠，取两个区间的最小start，和最大end, 作为新区间
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }

        results.add(insertPos, new int[]{newInterval[0], newInterval[1]});

        return results.toArray(new int[results.size()][2]);
    }
}
