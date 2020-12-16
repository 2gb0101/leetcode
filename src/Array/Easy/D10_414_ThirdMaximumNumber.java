package Array.Easy;
//Offer：一个给定数组
//Target：求数组中第三大的数，如果不存在的话那么就返回最大的数
//要求：这里的第三大不能和第二大相同，必须是严格的小于，而并非小于等于
public class D10_414_ThirdMaximumNumber {

    public int thirdMax(int[] nums) {
//    变量法
//    用三个变量保存前三大的数，并遍历数组进行判断

//    注意这里有个坑，就是初始化要用长整型long的最小值
//    否则当数组中有INT_MIN存在时，程序不知道该返回INT_MIN还是最大值first了
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second && num < first) {
                third = second;
                second = num;
            } else if (num > third && num < second) {
                third = num;
            }
        }
        return (third == Long.MIN_VALUE) ? (int)first : (int)third;
    }
}
