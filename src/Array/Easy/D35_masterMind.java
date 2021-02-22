package Array.Easy;

//计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）
//例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）
//作为用户，你试图猜出颜色组合
//打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；
//要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”
//
//Offer：一种颜色组合solution和一个猜测guess
//Target：编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数

import java.util.HashMap;

//示例：
//输入： solution="RGBY",guess="GGRR"
//输出： [1,1]
//解释： 猜中1次，伪猜中1次。
public class D35_masterMind {
//    解题思路1
//    因为solution和guess的长度都只有4，所以嵌套for循环暴力解的话，也很快

//    解题思路2
//    设置一个长度为26的数组map（目的是将RYGB对应到数组的index中）
//    for循环遍历solution和guess
//    如果solution和guess对应元素相等，则直接real++
//    若不相等，判断map中sol元素是否小于0（代表之前存过guess的元素），存在则fake++，然后更新map[sol - 'A']++;
//    对map中的gue元素做同等判断
//    返回答案ans
    public int[] masterMind1(String solution, String guess) {
        int fake = 0, real = 0;
        int[] map = new int[26];
        for(int i = 0; i < 4; i++){
            char sol = solution.charAt(i), gue = guess.charAt(i);
            if(sol == gue) real++;
            else{
                if(map[sol - 'A'] < 0) fake++;
                map[sol - 'A']++;

                if(map[gue - 'A'] > 0) fake++;
                map[gue - 'A']--;
            }
        }
        int[] ans = {real, fake};
        return ans;
    }

//    解题思路3
//    使用了HashMap，将solution的元素保存到map中（包含元素数量）
//    然后判断map中是否有guess的元素，有则fake++，注意要更新元素数量
//    在来个for循环判断一致的数据real，最后fake - real等于伪猜对
    public int[] masterMind2(String solution, String guess) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : solution.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int fake = 0, real = 0;
        for(char c : guess.toCharArray()){
            if(map.containsKey(c) && map.get(c) > 0){
                fake++;
                map.put(c, map.get(c) - 1);
            }
        }
        for(int i = 0; i < 4; i++){
            if(solution.charAt(i) == guess.charAt(i))
                real++;
        }
        int[] ans = {real, fake - real};
        return ans;
    }
}
