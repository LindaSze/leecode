import java.util.HashMap;
import java.util.Stack;

public class valid_parenthese {


    public static void main(String args[]) {
        String s = "";
        System.out.println("answer:" + valid_parenthese_v2(s));
    }


    /**
     * 思路：固定好hashmap中的key,value；匹配栈顶的value,如果key不等于栈顶value对应的key则匹配失败；如果数据不是key则说明是value，压入栈中即可
     * 3 ms,59.16%,34.3 MB, 85.22%
     */
    public static boolean valid_parenthese_v0(String s) {
        HashMap<Character, Character> mappings = new HashMap<Character, Character>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }


    /**
     * 3 ms,59.16%,32.1 MB, 87.02%
     */
    public static boolean valid_parenthese_v2(String s) {
        if (s.length() == 0)
            return true;
        if (s.length() % 2 == 1)
            return false;
        HashMap<Character, Character> hashMap = new HashMap<Character, Character>();
        hashMap.put(')', '(');
        hashMap.put('}', '{');
        hashMap.put(']', '[');

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hashMap.containsKey(c)) {
                if (stack.pop() != hashMap.get(c)) {
                    return false;
                } else return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }


    public boolean valid_parenthese_v3(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            if (s.contains("{}")) s = s.replace("{}", "");
            if (s.contains("()")) s = s.replace("()", "");
            if (s.contains("[]")) s = s.replace("[]", "");
        }
        return s.isEmpty();


    }
}
