package Programmers;

public class 올바른_괄호 {
    boolean solution(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            }
            else {
                if (--cnt < 0) {
                    return false;
                }
            }
        }

        return (cnt != 0) ? false : true;
    }
}
