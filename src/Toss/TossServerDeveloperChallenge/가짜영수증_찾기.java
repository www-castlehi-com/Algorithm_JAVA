package Toss.TossServerDeveloperChallenge;

class 가짜영수증_찾기 {

    private static final char SEPARATOR = ',';

    public boolean solution(String amountText) {
        int length = amountText.length();

        if (length > 1 && amountText.charAt(0) == '0') {
            return false;
        }

        boolean hasSep = false;
        int countSinceLastSep = 0;

        for (int i = 0; i < length; i++) {
            char cur = amountText.charAt(i);

            if (cur < '0' || cur > '9') {
                if (cur == SEPARATOR) {
                    if (!hasSep && i >= 4) {
                        return false;
                    }
                    if (hasSep && countSinceLastSep != 3) {
                        return false;
                    }
                    if (i == 0 || i == length - 1) {
                        return false;
                    }
                    hasSep = true;
                    countSinceLastSep = 0;
                }
                else {
                    return false;
                }
            }
            else {
                countSinceLastSep++;
            }
        }

        return !hasSep || countSinceLastSep == 3;
    }
}
