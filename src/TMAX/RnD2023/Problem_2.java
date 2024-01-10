package TMAX.RnD2023;

import java.util.Set;
import java.util.TreeSet;

public class Problem_2 {
    public String[] solution(String letters) {
        Set<String> permutations = new TreeSet<>();
        permute(letters, 0, letters.length(), permutations);

        String[] answer = new String[permutations.size()];
        int idx = 0;
        for (String letter : permutations) {
            answer[idx++] = letter;
        }
        return answer;
    }

    private void permute(String letters, int cur, int end, Set<String> permutations) {
        if (cur == end) {
            if (checkSuccessiveLetter(letters)) {
                permutations.add(letters);
            }
        }
        else {
            for (int i = cur; i < end; i++) {
                letters = swap(letters, cur, i);
                permute(letters, cur + 1, end, permutations);
                letters = swap(letters, cur, i);
            }
        }
    }

    private boolean checkSuccessiveLetter(String letters) {
        for (int i = 1; i < letters.length(); i++) {
            if (letters.charAt(i) == letters.charAt(i - 1))
                return false;
        }
        return true;
    }

    private String swap(String letters, int idx1, int idx2) {
        char[] convertLetters = letters.toCharArray();
        char temp = convertLetters[idx1];
        convertLetters[idx1] = convertLetters[idx2];
        convertLetters[idx2] = temp;
        return String.valueOf(convertLetters);
    }
}
