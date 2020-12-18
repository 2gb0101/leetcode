package Array.Middle;

//Offer：
//    人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄
//    当满足以下条件时，A 不能给 B（A、B不为同一人）发送好友请求：
//    age[B] <= 0.5 * age[A] + 7
//    age[B] > age[A]
//    age[B] > 100 && age[A] < 100
//    否则，A 可以给 B 发送好友请求
//    注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求而且，人们不会给自己发送好友请求
//Target：求总共会发出多少份好友请求?

//说明:
//    1 <= ages.length <= 20000.
//    1 <= ages[i] <= 120.
public class D24_825_numFriendRequests {

//    解法：计数法
    public int numFriendRequests(int[] ages) {
        // 注意题目说的三个条件是满足其一就不能发！！！
        int[] count = new int[121];
        // 统计每个年龄的人的个数
        for(int age : ages)
            count[age]++;
        int result = 0;
        for(int i = 0; i <= 120; i++){
            // 该年龄没有人的，直接跳过
            if(count[i] == 0)
                continue;
            // 这里是针对同龄人的情况，设当前年龄为i
            // 对于同龄人，第2，3个条件均不满足，只考虑第一个条件
            // 对于第1个条件，可以发送的情况是：i > 0.5 * i + 7
            // 简单化简即可得到 i > 14，注意排除和自己发消息的情况
            if(i > 14)
                result += count[i] * (count[i] - 1);
            // 不同年龄的人，假设当前人为A，分析三个条件可知，另一个人B的年龄：
            // 1. ageB > 0.5 * ageA + 7
            // 2. 要小于A（等于的情况计算过了）
            // 3. 可以划入2
            // 这里在循环条件中控制了1，内层判断2.对于满足条件的，每个i可以给所有j发，所以是count[i] * count[j]
            for(int j = 0; j < i; j++){
                if(j > (i >>> 1) + 7){
                    result += count[i] * count[j];
                }
            }
        }
        return result;
    }
}
