package Programmers;

import java.util.*;

public class 혼자_놀기의_달인 {
    static List<Integer> graph = new ArrayList<>();

    public int solution(int[] cards) {
        int numberOfCards = cards.length;

        boolean[] visited = new boolean[numberOfCards];
        for (int i = 0; i < numberOfCards; i++) {
            if (!visited[i]) {
                graph.add(getNumberOfGroup(i, visited, cards));
            }
        }

        if (graph.size() == 1) {
            return 0;
        }

        Collections.sort(graph, Collections.reverseOrder());
        return graph.get(0) * graph.get(1);
    }

    public static int getNumberOfGroup(int cardNumber, boolean[] visited, int[] cards) {
        int cnt = 0;
        while (visited[cardNumber] != true) {
            visited[cardNumber] = true;
            cnt++;
            cardNumber = cards[cardNumber] - 1;
        }

        return cnt;
    }
}
