package SWEA;

import java.io.*;
import java.util.Arrays;
import java.util.OptionalInt;

class Problem1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int curT = 1; curT <= t; curT++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = line[0], k = line[1];

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr);

            int res = 1;
            if (k != 1) {
                int start = 0, end = 0;
                int cnt = 0;
                while (end < n) {
                    cnt = end - start + 1;
                    if (arr[end] - arr[start] <= k) {
                        res = Math.max(res, cnt);
                        end++;
                    }
                    else {
                        start++;
                    }
                }
            }

            OptionalInt max = (int) Arrays.stream(arr).max();

            bw.write("#" + curT + " " + res + "\n");
        }
        bw.flush();
    }
}

테스트 1 〉 통과 (8.54ms, 72.5MB)
        테스트 2 〉 통과 (10.77ms, 77.8MB)
        테스트 3 〉 통과 (8.50ms, 74.4MB)
        테스트 4 〉 통과 (9.72ms, 77.3MB)
        테스트 5 〉 통과 (11.57ms, 76.6MB)
        테스트 6 〉 통과 (10.37ms, 78.5MB)
        테스트 7 〉 통과 (14.71ms, 84MB)
        테스트 8 〉 통과 (16.51ms, 94.1MB)
        테스트 9 〉 통과 (16.92ms, 83.1MB)
        테스트 10 〉 통과 (20.60ms, 90.5MB)
        테스트 11 〉 통과 (26.01ms, 91MB)
        테스트 12 〉 통과 (19.14ms, 86.3MB)
        테스트 13 〉 통과 (26.32ms, 89.8MB)
        테스트 14 〉 통과 (22.84ms, 100MB)
        테스트 15 〉 통과 (28.63ms, 99.5MB)
        테스트 16 〉 통과 (25.39ms, 106MB)
        테스트 17 〉 통과 (10.79ms, 75.7MB)
        테스트 18 〉 통과 (16.58ms, 97.8MB)
        테스트 19 〉 통과 (23.80ms, 103MB)
        테스트 20 〉 통과 (20.85ms, 89.2MB)