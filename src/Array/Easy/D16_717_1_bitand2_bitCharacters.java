package Array.Easy;

import java.util.Arrays;

//Offer：有两种特殊的字符
//一种是两位字符，只能是二进制的11和10
//另一种是单个位字符，只能是二进制的0
//给一个只包含0和1的数组
//Target：
//问能否将数组正确的分割
//使得最后一个字符是个单个位字符
public class D16_717_1_bitand2_bitCharacters {
//    贪婪算法：
//    因为两种字符互不干扰
//      只要遍历到了数字1
//          必定是两位字符
//          后面一位也得跟着
//      遍历到了数字0
//          必定是单个位字符

    public boolean isOneBitCharacter1(int[] bits) {
        int n = bits.length, i = 0;
        while (i < n - 1) {
            if (bits[i] == 0)
                i++;
            else
                i+= 2;
        }
        return i == n - 1;
    }

    //简化isOneBitCharacter1()
    public boolean isOneBitCharacter2(int[] bits) {
        int n = bits.length, i = 0;
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }

//    递归：用的是回溯的思想
//    如果bits为空，直接返回false
//      因为题目初始给的bits是非空的
//      在调用递归函数过程中为空的话
//      说明最后一位跟倒数第二位组成了个两位字符
//      所以不合题意返回false
//    如果bits大小为1
//      那么返回这个数字是否为0
//      其实直接返回true也行
//      因为题目中说了最后一个数字一定是0
    public boolean isOneBitCharacter3(int[] bits) {
        if (bits.length ==0) return false;
        if (bits.length == 1) return bits[0] == 0;
        //这里直接写int[] t无法通过编译
        //会报variable t might not have been initialized
        //所以这里为了解决编译，将t初始化为一个长度为0的int数组
        int[] t = {};
        if (bits[0] == 0) {
            t = Arrays.copyOfRange(bits, 1, bits.length);
        } else if (bits[0] == 1) {
            t = Arrays.copyOfRange(bits, 2, bits.length);
        }
        return isOneBitCharacter3(t);
    }

    //简化isOneBitCharacter3()
    public boolean isOneBitCharacter4(int[] bits) {
        return helper(bits, 0);
    }
    public boolean helper(int[] bits, int idx) {
        int n = bits.length;
        if (idx == n) return false;
        if (idx == n - 1) return bits[idx] == 0;
        if (bits[idx] == 0) return helper(bits, idx + 1);
        return helper(bits, idx + 2);
    }
}
