package Array.Easy;

import java.util.ArrayList;
import java.util.List;

//Offer：一个数组
//Target：
//    把偶数都放在偶数坐标位置
//    把奇数都放在奇数坐标位置
public class D20_922_Sort_Array_By_Parity_II {
//    辅助List法
//    直接分别将奇数和偶数提取出来，存到两个不同的List中
//    再从两个List每次取一个放到结果 res 中即可
    public int[] sortArrayByParityII1(int[] A) {
        int[] res = new int[A.length];
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (int num : A) {
            if (num % 2 == 0)
                even.add(num);
            else
                odd.add(num);
        }
        for (int i = 0; i < even.size(); i++) {
            res[2*i] = even.get(i);
            res[2*i + 1] = odd.get(i);
        }
        return res;
    }

//    辅助数组法
//    建立辅助数组ans，进行两次遍历
//    一次遍历：把所有的偶数放进 ans[0]，ans[2]，ans[4]，依次类推
//    二次遍历：把所有的奇数依次放进 ans[1]，ans[3]，ans[5]，依次类推
//    时间复杂度：O(N)，其中 N 是 A 的长度。
//    空间复杂度：O(N)
    public int[] sortArrayByParityII2(int[] A) {
        int N = A.length;
        int[] ans = new int[N];

        int t = 0;
        for (int x: A) {
            if (x % 2 == 0) {
                ans[t] = x;
                t += 2;
            }
        }

        t = 1;
        for (int x: A) {
            if (x % 2 == 1) {
                ans[t] = x;
                t += 2;
            }
        }

        return ans;
    }

//    双指针法：
//    i指针一直指向偶数位置
//    j指针一直指向奇数位置
//    当 A[i] 是偶数时
//        则跳到下一个偶数位置
//        直到i指向一个偶数位置上的奇数
//    同理，当 A[j] 是奇数时
//        则跳到下一个奇数位置
//        直到j指向一个奇数位置上的偶数
//    当 A[i] 和 A[j] 分别是奇数和偶数的时候，则交换两个数字的位置
    public int[] sortArrayByParityII3(int[] A) {
        int n = A.length, i = 0, j = 1;
        while (i < n && j < n) {
            if (A[i] % 2 == 0)
                i += 2;
            else if (A[j] % 2 == 1)
                j += 2;
            else{
                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

        }
        return A;
    }
}
