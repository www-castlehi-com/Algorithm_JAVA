package TMAX.RnD2023;

public class Problem_1 {
    public int[] solution(String[] fruits, int[][] model, String target) {
        int idx;
        for (idx = 0; idx < fruits.length; idx++) {
            if (fruits[idx].equals(target))
                break;
        }

        int[] answer = new int[4];
        for (int i = 0; i < fruits.length; i++) {
            for (int j = 0; j < fruits.length; j++) {
                if (i == idx && j == idx) answer[0] += model[i][j];
                else if (i == idx) answer[1] += model[i][j];
                else if (j == idx) answer[2] += model[i][j];
                else answer[3] += model[i][j];
            }
        }

        return answer;
    }
}
