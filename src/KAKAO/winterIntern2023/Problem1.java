package KAKAO.winterIntern2023;

import java.util.*;

class Problem1 {

    public int solution(String[] friends, String[] gifts) {
        int friendsSize = friends.length;
        Map<String, Integer> friendIndexTable = new HashMap<>();
        for (int i = 0; i < friendsSize; i++) {
            friendIndexTable.put(friends[i], i);
        }

        int[][] giftGraph = new int[friendsSize][friendsSize];
        for (int i = 0; i < gifts.length; i++) {
            String[] parseGift = gifts[i].split(" ");
            int senderIdx = friendIndexTable.get(parseGift[0]);
            int receiverIdx = friendIndexTable.get(parseGift[1]);

            giftGraph[senderIdx][receiverIdx]++;
        }

        int[] giftIndex = new int[friendsSize];
        for (int i = 0; i < friendsSize; i++) {
            int index = 0;
            for (int j = 0; j < friendsSize; j++)
                index += giftGraph[i][j] - giftGraph[j][i];
            giftIndex[i] = index;
        }

        int[] giftNum = new int[friendsSize];
        for (int i = 0; i < friendsSize; i++) {
            for (int j = i + 1; j < friendsSize; j++) {
                if (giftGraph[i][j] == giftGraph[j][i]) {
                    compare(giftIndex[i], giftIndex[j], i, j, giftNum);
                }
                compare(giftGraph[i][j], giftGraph[j][i], i, j, giftNum);
            }
        }

        int answer = Arrays.stream(giftNum).max().getAsInt();
        return answer;
    }

    private void compare(int c1, int c2, int e1, int e2, int[] giftNum) {
        if (c1 < c2) giftNum[e2]++;
        else if (c1 > c2) giftNum[e1]++;
    }
}