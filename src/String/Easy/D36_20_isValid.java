package String.Easy;

//Offer: 一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s
//Target: 判断字符串是否有效
//        有效字符串需满足：
//        左括号必须用相同类型的右括号闭合。
//        左括号必须以正确的顺序闭合。

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

//示例 1：
//输入：s = "()"
//输出：true
//
//示例 2：
//输入：s = "()[]{}"
//输出：true
//
//提示：
//1 <= s.length <= 104， s 仅由括号 '()[]{}' 组成
public class D36_20_isValid {
//    解法1: 辅助栈法+Map
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }

//    解法2: 辅助栈法，不用Map
    public boolean isValid2(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
        if(stack.empty())
            return true;
        return false;
    }
}
