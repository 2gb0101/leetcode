package Array.Easy;

//Offer：一个整数数组 nums
//Target：返回其中位数为 偶数 的数字的个数
public class D29_1295_findNumbers {
//    借助String
//    将int转为String，调用.length，然后%2==0即为偶数
    public int findNumbers1(int[] nums) {
        int res=0;
        for(int i:nums){
            if(String.valueOf(i).length()%2==0){
                res++;
            }
        }
        return res;
    }


//    循环做除法
//    数字循环除10，统计等于0的时候除10的次数，偶数次则该数为偶数位
    public int findNumbers2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int N = nums[i];
            //记录除10的次数
            int countTen = 0;
            while (N != 0) {
                N/=10;
                countTen++;
            }
            //如果除10的次数是偶数次，则该数为偶数位数
            if (countTen % 2 == 0) {
                count++;
            }
        }
        return count;
    }

//    取巧
//    已知范围为1 <= nums[i] <= 10^5，所以只有两个区间内的数为偶数位10~99以及1000~9999
    public int findNumbers(int[] nums) {
        int count=0;
        for(int i=0;i<nums.length;++i){
            if((nums[i]>=10&&nums[i]<100)||(nums[i]>=1000&&nums[i]<10000))
                count++;
        }
        return count;
    }


}
