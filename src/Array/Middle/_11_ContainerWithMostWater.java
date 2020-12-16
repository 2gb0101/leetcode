package Array.Middle;

//Offer：
//    给定 n 个非负整数 a1，a2，...，an
//    每个数代表坐标中的一个点(i,ai)
//    在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0)
//Target：
//    找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水
public class _11_ContainerWithMostWater {
//    思路：双指针
//    定义i和j两个指针分别指向数组的左右两端，两个指针向中间搜索
//    每移动一次算一个值和结果比较取较大的
//    容器装水量的算法：找出左右两个边缘中较小的那个乘以两边缘的距离

//  解法一（双指针法）：
    public int maxArea0(int[] height) {
        int res = 0, i = 0, j = height.length - 1;
        while (i < j) {
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            //这里用不了三元运算符
            //因为于 Java 中的三元运算符 A?B:C 必须须要有返回值，所以只能用 if..else.. 来替换
            if (height[i] < height[j])
                ++i;
            else
                --j;
        }
        return res;
    }

//  解法二（优化解法一）：
    public int maxArea(int[] height) {
        int res = 0, i = 0, j = height.length - 1;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            res = Math.max(res, h * (j - i));
            //进行小幅度的优化，对于相同的高度们直接移动i和j就行了，不再进行计算容量了
            while (i < j && h == height[i]) ++i;
            while (i < j && h == height[j]) --j;
        }
        return res;
    }
}
