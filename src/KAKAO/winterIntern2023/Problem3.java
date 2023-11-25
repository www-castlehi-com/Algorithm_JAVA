package KAKAO.winterIntern2023;

import java.util.*;

class Problem3 {

    static Map<Integer, Integer>[] diceValues;
    static int maxWinRate = 0;
    static int[] answer;

    public int[] solution(int[][] dice) {
        int numberOfDice = dice.length;
        answer = new int[numberOfDice / 2];

        diceValues = new HashMap[dice.length];
        for (int i = 0; i < dice.length; i++) {
            Map<Integer, Integer> valueCountMap = new HashMap<>();
            for (int value : dice[i]) {
                valueCountMap.put(value, valueCountMap.getOrDefault(value, 0) + 1);
            }
            diceValues[i] = valueCountMap;
        }

        getDice(0, 1, numberOfDice / 2, new int[numberOfDice / 2]);

        return answer;
    }

    private void getDice(int cur, int start, int limit, int[] comb) {
        if (cur == limit) {
            getWinRate(comb);
        }
        else {
            for (int i = start; i <= limit * 2; i++) {
                comb[cur % limit] = i;
                getDice(cur + 1, i + 1, limit, comb);
            }
        }
    }

    private void getWinRate(int[] comb) {
        Map<Integer, Integer> resultA = new HashMap<>();
        generateCombinations(resultA, 0, 0, 0, comb);

        boolean[] isCombA = new boolean[diceValues.length + 1];
        for (int i = 0; i < comb.length; i++) {
            isCombA[comb[i]] = true;
        }
        int[] combB = new int[comb.length];
        for (int i = 1, idx = 0; i <= diceValues.length; i++) {
            if (!isCombA[i]) {
                combB[idx++] = i;
            }
        }

        Map<Integer, Integer> resultB = new HashMap<>();
        generateCombinations(resultB, 0, 0, 0, combB);

        int cnt = 0;
        for (Map.Entry<Integer, Integer> eA : resultA.entrySet()) {
            for (Map.Entry<Integer, Integer> eB : resultB.entrySet()) {
                if (eA.getKey() > eB.getKey()) {
                    cnt += (eA.getValue() * eB.getValue());
                }
            }
        }

        if (maxWinRate < cnt) {
            maxWinRate = cnt;
            answer = comb.clone();
        }
    }

    private void generateCombinations(Map<Integer, Integer> results, int sum, int count, int depth, int[] comb) {
        if (depth == comb.length) {
            results.put(sum, results.getOrDefault(sum, 0) + count);
            return;
        }

        int diceIndex = comb[depth] - 1;
        for (Map.Entry<Integer, Integer> e : diceValues[diceIndex].entrySet()) {
            int newValue = sum + e.getKey();
            int newCount = depth == 0 ? e.getValue() : count * e.getValue();
            generateCombinations(results, newValue, newCount, depth + 1, comb);
        }
    }
}
