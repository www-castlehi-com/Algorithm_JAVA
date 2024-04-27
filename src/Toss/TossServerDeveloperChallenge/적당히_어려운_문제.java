package Toss.TossServerDeveloperChallenge;

import java.util.*;

class 적당히_어려운_문제 {

    private static final int GRADE = 4;

    public int solution(int[] levels) {
        int length = levels.length;

        if (length < GRADE) {
            return -1;
        }

        Arrays.sort(levels);

        int numberOfProblem = length / GRADE;
        return levels[length - numberOfProblem];
    }
}
