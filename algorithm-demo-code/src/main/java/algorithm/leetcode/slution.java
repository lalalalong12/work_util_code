package algorithm.leetcode;

import java.util.Stack;

/**
 * @author yangWenlong
 * @date 2021/4/5- ${time}
 * 使用栈括号匹配问题解决
 */
public class slution {

    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (int i= 0;i<s.length();i++){
            char c = s.charAt(i);
            if (c == '('||c=='{'||c=='[')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;

                char topChar = stack.pop();
                if (c==')'&&topChar!='(')
                    return false;
                if (c==']'&&topChar!='[')
                    return false;
                if (c=='}'&&topChar!='{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        boolean valid = isValid("[]{}");
        System.out.println(valid);
    }
}
