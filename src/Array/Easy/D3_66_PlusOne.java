package Array.Easy;

//Offer：一个以数组形式给的非负数
//Target：给该数加1，并且保证数组的开头是该数的最高有效位
public class D3_66_PlusOne {
//    解法一（暴力法）：（需要对末尾第一个数进行特殊处理）
//    将非负数每位上的数字分别存在数组中，且最高位在最开头
//    需要给这个数字加一，即在末尾数字加一
//    如果末尾数字是9，那么则会有进位问题
//    而如果前面位上的数字仍为9，则需要继续向前进位
    public int[] plusOne1(int[] digits) {
        int n = digits.length;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                ++digits[i];
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }


//    解法二（利用carry进位标志）：（不需要对末尾第一个数进行特殊处理）
//    利用carry，在循环中针对是否需要进位进行判断
    public int[] plusOne2(int[] digits) {
        if (digits.length == 0)
            return digits;
        int carry = 1, n = digits.length;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (carry == 0)
                return digits;
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return carry == 0 ? digits : res;
    }
}
