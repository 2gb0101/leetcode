package Array.Hard;
//给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1
public class D11_1721_trap {
//    解法：双指针
//  缓存两端最大值，从最大值较小的一边进行储水结算、移动，并更新最大值。
    public int trap(int[] height) {
        if(height.length < 3) return 0;

        int left = 0, right = height.length - 1;
        int leftmax = height[left], rightmax = height[right];
        int res = 0;

        while(left < right){
            if(leftmax < rightmax){
                res += leftmax - height[left++];
                //说明：这里的leftmax - height[left++]不可能为负数
                //因为leftmax会先被height[left]更新，保证leftmax一定是当前计算中较大的数
                leftmax = Math.max(height[left], leftmax);
            }else{
                res += rightmax - height[right--];
                rightmax = Math.max(height[right], rightmax);
            }
        }
        return res;
    }
}
