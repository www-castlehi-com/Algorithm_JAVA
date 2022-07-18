package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_16198 {

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ArrayList<Integer> energy = new ArrayList<>();
        for (int i : arr) {
            energy.add(i);
        }

        energy_supply(energy, n,1, 0);

        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static void energy_supply(ArrayList<Integer> energy, int total, int idx, int sum) {
        if (idx == total - 1) {
            if (max < sum) max = sum;
            return ;
        }

        int supply = energy.get(idx - 1) * energy.get(idx + 1);
        ArrayList<Integer> n_energy = new ArrayList<>();
        for (Integer integer : energy) {
            n_energy.add(integer);
        }
        n_energy.remove(idx);
        energy_supply(n_energy, total - 1, 1, sum + supply);

        energy_supply(energy, total, idx + 1, sum);
    }
}
