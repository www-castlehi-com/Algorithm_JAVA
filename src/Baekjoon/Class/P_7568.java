package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_7568 {

    public static class PersonBulk {
        int weigh;
        int tall;
        int rank;

        public PersonBulk(int weigh, int tall) {
            this.weigh = weigh;
            this.tall = tall;
            this.rank = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<PersonBulk> personBulks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            personBulks.add(new PersonBulk(arr[0], arr[1]));
        }

        for (int i = 0; i < personBulks.size(); i++) {
            for (int j = 0; j < personBulks.size(); j++) {
                if (i != j) {
                    if (personBulks.get(i).tall < personBulks.get(j).tall && personBulks.get(i).weigh < personBulks.get(j).weigh) personBulks.get(i).rank++;
                }
            }
            bw.write(personBulks.get(i).rank + " ");
        }
        bw.flush();
    }
}
