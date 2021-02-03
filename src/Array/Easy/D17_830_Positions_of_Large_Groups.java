package Array.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Offer：给了一个全小写的字符串
//Target：
//    已知重复出现的字符可以当作一个群组
//    如果重复次数大于等于3次
//    可以当作一个大群组
//    要求找出所有大群组的起始和结束位置并返回
public class D17_830_Positions_of_Large_Groups {
    //    本题实际上就是让计数连续重复字符的出现次数
//    由于要连续，所以可以使用双指针来做
//    一个指针指向重复部分的开头
//    一个往后遍历计数
//    只要不相同了就停止
//    然后看次数是否大于等3
//    是的话就将双指针位置存入结果res中
//    并更新指针
//
//    实现：可以分别用while循环和for循环来实现，本次解法只写for循环方式
    public List<List<Integer>> largeGroupPositions(String S) {
        //我一开始写的是List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<List<Integer>> ans = new ArrayList();
        int i = 0, N = S.length(); // i is the start of each group
        for (int j = 0; j < N; ++j) {
            if (j == N-1 || S.charAt(j) != S.charAt(j+1)) {
                // Here, [i, j] represents a group.
                if (j-i+1 >= 3){
                    //我一开始是这样写的,但OJ显示超出时间限制了
                    //ArrayList<Integer> tmp = new ArrayList<Integer>();
                    //tmp.add(i);
                    //tmp.add(j);
                    //res.add(tmp);
                    ans.add(Arrays.asList(new Integer[]{i, j}));
                }
                i = j + 1;
            }
        }
        return ans;
    }
}
