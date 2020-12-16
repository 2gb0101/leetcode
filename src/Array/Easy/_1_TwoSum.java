package Array.Easy;


import java.util.HashMap;

//    Offer：
//            数组
//            目标数
//    Target：
//            数组中的两个数字，使其和为目标数
//            数组中每个元素只能用一次
//            每个目标数只对应一种解决方案
class _1_TwoSum {

//    ①暴力搜索
//    本质：遍历所有的两个数字的组合，求其和
//    缺点：
//    Time Limit Exceeded
//    虽然节省了空间，但是时间复杂度高 （O(n^2)）
//
//    转换思路：用空间换时间
//    只遍历一个数字
//    另一个数字可以事先将其存储起来（HashMap）
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0;i < nums.length ;i++){
            for(int j = 0;j < nums.length ;j++){       //这里写成j=i+1也是可以的
                if(nums[i] + nums[j] == target && i != j){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    //两遍HashMap
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            m.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; ++i) {
            int t = target - nums[i];
            if (m.containsKey(t) && m.get(t) != i) {	//这句易忘了写
                res[0] = i;
                res[1] = m.get(t);
                break;
            }
        }
        return res;
    }

    //一遍HashMap
    public int[] twoSum3(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            if (m.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = m.get(target - nums[i]);
                break;
            }
            m.put(nums[i], i);
        }
        return res;
    }
}