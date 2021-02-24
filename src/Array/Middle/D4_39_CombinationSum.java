package Array.Middle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Offer：
//给定一个无重复元素的数组candidates和一个目标数target
//Target：
//找出candidates中所有可以使数字和为target的组合
//candidates中的数字可以无限制重复被选取

//说明：
//所有数字（包括target）都是正整数
//解集不能包含重复的组合
public class D4_39_CombinationSum {
    辅助函数递归法：
    像这种结果要求返回所有符合要求解的题十有八九都是要利用到递归
    且解题的思路都大同小异，相类似的题目有 Path Sum II，Subsets II，Permutations，Permutations II，Combinations 等等
    如果仔细研究这些题目发现都是一个套路，都是需要另写一个递归函数
            这里新加入三个变量
    start 记录当前的递归到的下标
    out 为一个解
    res 保存所有已经得到的解
    每次调用新的递归函数时，此时的 target 要减去当前数组的的数
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        LinkedList<Integer> out = new LinkedList<Integer>();
        combinationSumDFS(candidates, target, 0, out, res);
        return res;
    }

    void combinationSumDFS(int[] candidates, int target, int start, LinkedList<Integer> out, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            LinkedList<Integer> tmp = new LinkedList<Integer>();
            tmp.addAll(out);
            res.add(tmp);
            return;
        }
        for (int i = start; i < candidates.length; ++i) {
            out.add(candidates[i]);
            combinationSumDFS(candidates, target - candidates[i], i, out, res);
            out.removeLast();
        }
    }
    自身递归法：
    也可以不使用额外的函数，就在一个函数中完成递归
    先给数组排序，然后遍历
    比较当前数字和target的大小：
    当前数字大于 target
    说明肯定无法组成 target
    由于排过序，之后的也无法组成 target
    直接 break 掉
    当前数字正好等于 target
    则当前单个数字就是一个解
    组成一个数组然后放到结果 res 中
    当前数字小于 target
    将当前位置之后的数据作为子数组（这个子数组包括当前的位置的数据）取出来
            调用递归函数
    注意此时的 target 要减去当前的数字
            遍历递归结果返回的二维数组
    将当前数字加到每一个数组最前面
    然后再将每个数组加入结果 res 即可
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        for (int i = 0; i < candidates.length; ++i) {
            if (candidates[i] > target) break;
            if (candidates[i] == target) {
                LinkedList<Integer> tmp = new LinkedList<Integer>();
                tmp.add(candidates[i]);
                res.add(tmp);
                break;
            }
            //第一次做问题出在下面这两行代码（这两行是正确的）
            //这种写法是将当前位置之后的数据作为子数组（这个子数组包括当前的位置的数据）取出来
            int[] vec = new int[candidates.length - i];
            System.arraycopy(candidates, i, vec, 0, vec.length);
            //原本写的是:
            //int[] vec = new int[candidates.length - i + 1];
            //System.arraycopy(candidates, i + 1, vec, 0, vec.length);
            //这种写法是将当前位置之后的数据作为子数组（这个子数组不包括当前的位置的数据）取出来
            //如果取子数组的时候，不把当前位置的数据包含进去的话
            //就不满足题意说的：candidates中的数字可以无限制重复被选取
            //所以这种写法适合：candidates中的数字不可以无限制重复被选取的情况（leetcode的第40题就是）
            //但是仍然要注意一点，在循环的时候要记得排重，详见T40的解答笔记-解法二
            List<List<Integer>> back = combinationSum(vec, target - candidates[i]);
            for (List<Integer> a : back) {
                LinkedList<Integer> tmp = new LinkedList<Integer>();
                tmp.add(candidates[i]);
                tmp.addAll(a);
                res.add(tmp);
            }
        }
        return res;
    }
}
