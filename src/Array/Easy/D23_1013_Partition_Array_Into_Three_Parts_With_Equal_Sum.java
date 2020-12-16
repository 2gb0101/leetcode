package Array.Easy;

//Offer：给定一个整数数组A
//Target：只有当可以将其划分为三个和相等的非空部分时才返回true，否则返回 false
public class D23_1013_Partition_Array_Into_Three_Parts_With_Equal_Sum {
//    思路：累加，求平均数
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        //累加
        for (int i : A) {
            sum += i;
        }
        //求平均数
        int avg = 0;
        if (sum % 3 == 0) {
            avg = sum / 3;
        } else {
            return false;
        }
        sum = 0;
        //这里容易出错，见下图
        for (int i = 0; i < A.length; i++) {
            if (sum == avg) {
                //相当于清0，进行下一轮累加
                sum = A[i];
            } else {
                sum += A[i];
            }
        }
        return sum == avg || sum == 0 ? true : false;
        // 这里的 sum == 0 不可省略，因为数组有可能类似 [12,-4,16,-5,9,-3,3,8,0]
        // 数组前面部分能够分割成3个非空的数组，但数组末尾是0，此时也是符合题意的
    }
}
