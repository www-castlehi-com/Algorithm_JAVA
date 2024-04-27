package Toss.TossServerDeveloperChallenge;

class 멋쟁이_숫자 {

    private static final int ERROR = -1;

    public int solution(String s) {
        int length = s.length();

        int result = ERROR;

        if (length < 3) {
            return result;
        }

        char standard = s.charAt(0);
        int subCount = 1;
        for (int i = 1; i < length; i++) {
            char cur = s.charAt(i);
            if (cur == standard) {
                subCount++;
            }
            else {
                standard = cur;
                subCount = 1;
            }

            if (subCount == 3 && standard - '0' > result) {
                result = standard - '0';
            }
        }

        return result == ERROR ? result : result * 100 + result * 10 + result;
    }
}