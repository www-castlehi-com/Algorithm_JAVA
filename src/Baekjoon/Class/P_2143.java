package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_2143 {

    static int t;
    static ArrayList<Integer> aSubSequence;
    static HashMap<Integer, Integer> aSubMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i < n; i++) a[i] += a[i - 1];
        int m = Integer.parseInt(br.readLine());
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i < m; i++) b[i] += b[i - 1];

        int ap1 = 0, ap2 = 0;
        HashSet<Integer> aSubSet = new HashSet<>();
        aSubMap = new HashMap<>();

        while (ap1 < n) {
            int aSum = 0;
            if (ap1 == 0) aSum += a[ap2];
            else aSum += a[ap2] - a[ap1 - 1];

            aSubSet.add(aSum);
            aSubMap.put(aSum, aSubMap.getOrDefault(aSum, 0) + 1);

            ap2++;
            if (ap2 == n) {
                ap1++;
                ap2 = ap1;
            }
        }

        aSubSequence = new ArrayList<>(aSubSet);
        Collections.sort(aSubSequence);

        long cnt = 0;
        int bp1 = 0, bp2 = 0;
        while (bp1 < m) {
            int bSum = 0;
            if (bp1 == 0) bSum += b[bp2];
            else bSum += b[bp2] - b[bp1 - 1];

            cnt += binarySearch(bSum);

            bp2++;
            if (bp2 == m) {
                bp1++;
                bp2 = bp1;
            }
        }

        bw.write(Long.toString(cnt));
        bw.flush();
    }

    private static int binarySearch(int bSum) {
        int start = 0, end = aSubSequence.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (aSubSequence.get(mid) + bSum == t) return aSubMap.get(aSubSequence.get(mid));
            else if (aSubSequence.get(mid) + bSum < t) start = mid + 1;
            else end = mid - 1;
        }

        return 0;
    }
}
