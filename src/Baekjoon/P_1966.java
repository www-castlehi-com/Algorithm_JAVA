package Baekjoon;

import java.io.*;
import java.util.*;

public class P_1966 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = arr[0], m = arr[1];
            int[] documents = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            LinkedList<int[]> queue = new LinkedList<>();
            for (int j = 0; j < n; j++) queue.offer(new int[] {j, documents[j]});

            int count = 0;
            while (!queue.isEmpty()) {
                int[] first = queue.poll();
                boolean firstIsMax = true;

                for (int j = 0; j < queue.size(); j++) {
                    if (first[1] < queue.get(j)[1]) {
                        queue.offer(first);

                        for (int k = 0; k < j; k++) queue.offer(queue.poll());
                        firstIsMax = false;
                        break;
                    }
                }

                if (!firstIsMax) continue;

                count++;
                if (first[0] == m) break;
            }

            bw.write(count + "\n");
        }
        bw.flush();
    }
}


