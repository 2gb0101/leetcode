import java.util.HashMap;

public class test {
    public int maxArea(int[] height) {
        int res = 0, i = 0, j = height.length - 1;
        while(i < j){
            int h = Math.min(height[i], height[j]);
            res = Math.max(res, h * (j - i));
            while(i < j && h == height[i]) ++i;
            while(i < j && h == height[j]) --j''
        }
        return res;
    }
}
