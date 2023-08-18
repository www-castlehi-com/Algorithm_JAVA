package Goorm.Challenge.Week1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Day5 {

    static public class Num implements Comparable{
        int base10;
        int base2Cnt;

        public Num(int base10) {
            this.base10 = base10;
            this.base2Cnt = base2Convert(base10);
        }

        private int base2Convert(int base10) {
            int num = base10;

            int cnt = 0;
            while (num > 0) {
                if (num % 2 == 1) cnt++;
                num /= 2;
            }

            return cnt;
        }

        @Override
        public int compareTo(Object o) {
            Num compare = (Num) o;

            if (this.base2Cnt - compare.base2Cnt != 0) return -(this.base2Cnt - compare.base2Cnt);
            else return -(this.base10 - compare.base10);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], k = line[1] - 1;
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ArrayList<Num> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(new Num(arr[i]));
        }
        Collections.sort(nums);

        bw.write(Integer.toString(nums.get(k).base10));
        bw.flush();
    }
}
