package Array.Middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Offer：一组不含重复元素的整数数组 nums
//Target：返回该数组所有可能的子集（幂集）
//说明：解集不能包含重复的子集
public class D11_78_binaryBit {
//    解法1：位运算
//    集合的每个元素，都有可以选或不选，用二进制和位运算，可以很好的表示
public static List<List<Integer>> binaryBit(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < (1 << nums.length); i++) {
            // 1 << nums.length的理解如下：
            // 假设nums=[1,2,3,4]
            // 二进制的0可以写成0000，代表一个数也不取
            // 1=0001表示去第一个数也就是[1]
            // 2=0010，表示取第二个数[2]
            // 3=0011表示取1和2位[1,2]
            // 4=0100表示[3]...
            // 15=1111表示[1,2,3,4]
            // 因此这里的循环结束值，取的就是i全部为1的情况
            List<Integer> sub = new ArrayList<Integer>();
            for (int j = 0; j < nums.length; j++)
                // 判断当前的方案i中有哪几位是1
                // 把是1的那些位的nums值放到List中
                if (((i >> j) & 1) == 1) sub.add(nums[j]);
            res.add(sub);
        }
        return res;
    }

//    解法2:逐个枚举
//    空集的幂集只有空集，每增加一个元素，让之前幂集中的每个集合，追加这个元素，就是新增的子集。

//    写法1：循环枚举
    public static List<List<Integer>> enumerate(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
    }


//    写法2：递归枚举
    public static void recursion(int[] nums, int i, List<List<Integer>> res) {
        if (i >= nums.length) return;
        int size = res.size();
        for (int j = 0; j < size; j++) {
            List<Integer> newSub = new ArrayList<Integer>(res.get(j));
            newSub.add(nums[i]);
            res.add(newSub);
        }
        recursion(nums, i + 1, res);
    }

//    解法3：二叉树
//    集合中每个元素的选和不选，构成了一个满二叉状态树，比如，左子树是不选，右子树是选，从根节点、到叶子节点的所有路径，构成了所有子集。可以有前序、中序、后序的不同写法，结果的顺序不一样。本质上，其实是比较完整的中序遍历。
//    写法1：DFS，前序遍历
    public static void preOrder(int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) return;
        // 到了新的状态，记录新的路径，要重新拷贝一份
        subset = new ArrayList<Integer>(subset);

        // 这里
        res.add(subset);
        preOrder(nums, i + 1, subset, res);
        subset.add(nums[i]);
        preOrder(nums, i + 1, subset, res);
    }

//    写法2：DFS，中序遍历
    public static void inOrder(int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) return;
        subset = new ArrayList<Integer>(subset);

        inOrder(nums, i + 1, subset, res);
        // 这里
        res.add(subset);
        inOrder(nums, i + 1, subset, res);
    }

//    写法3：DFS，后序遍历
    public static void postOrder(int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) return;
        subset = new ArrayList<Integer>(subset);

        postOrder(nums, i + 1, subset, res);
        subset.add(nums[i]);
        postOrder(nums, i + 1, subset, res);
        // 这里
        res.add(subset);
    }

//    也可以左子树是选，右子树是不选，相当于，也可以前序、中序、后序的不同写法。程序实现上，需要用一个栈，元素入栈遍历左子树，（最近入栈的那个）元素出栈，遍历右子树。
    public static void newPreOrder(int[] nums, int i, LinkedList<Integer> stack, List<List<Integer>> res) {
        if (i >= nums.length) return;
        stack.push(nums[i]);
        // 这里
        res.add(new ArrayList<Integer>(stack));
        newPreOrder(nums, i + 1, stack, res);
        stack.pop();
        newPreOrder(nums, i + 1, stack, res);
    }

//    解法4：递归回溯
//    典型的递归回溯题，本文详细介绍递归回溯的套路。
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> subsets1(int[] nums) {
        if(nums == null || nums.length ==0){
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(list, nums, 0);
        return lists;

    }

    private void process(List<Integer>list, int[] nums, int start){

        lists.add(new ArrayList(list));
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            process(list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}

