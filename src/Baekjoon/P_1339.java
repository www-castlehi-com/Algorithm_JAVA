package Baekjoon;

import java.io.*;
import java.util.HashMap;

public class P_1339 {

    static String[] words;
    static HashMap<Character, Integer> wordMath;
    static Character[] alpha = new Character[10];
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        visited = new boolean[10];
        words = new String[n];
        wordMath = new HashMap<>();
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j++) {
                if (!wordMath.containsKey(words[i].charAt(j))) {
                    wordMath.put(words[i].charAt(j), -1);
                    alpha[wordMath.size() - 1] = words[i].charAt(j);
                }
            }
        }

        FindValue(0);

        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static void FindValue(int idx) {
        if (idx == wordMath.size()) {
            int result = ComputeSum();
            if (max < result) max = result;
        }
        else {
            for (int i = 0; i <= 9; i++) {
                if (!visited[i]) {
                    wordMath.replace(alpha[idx], i);
                    visited[i] = true;
                    FindValue(idx + 1);
                    wordMath.replace(alpha[idx], -1);
                    visited[i] = false;
                }
            }
        }
    }

    public static int ComputeSum() {
        int result = 0;

        for (int i = 0; i < words.length; i++) {
            int subResult = 0;
            for (int j = 0; j < words[i].length(); j++) {
                subResult = subResult * 10 + wordMath.get(words[i].charAt(j));
            }
            result += subResult;
        }

        return result;
    }
}
