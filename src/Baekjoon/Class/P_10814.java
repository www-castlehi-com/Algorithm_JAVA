package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class P_10814 {

    public static class OnlineJudgeCustomer {
        int idx;
        int age;
        String name;

        public OnlineJudgeCustomer(int idx, int age, String name) {
            this.idx = idx;
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<OnlineJudgeCustomer> customers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            customers.add(new OnlineJudgeCustomer(i, Integer.parseInt(s[0]), s[1]));
        }

        customers.sort(new Comparator<OnlineJudgeCustomer>() {
            @Override
            public int compare(OnlineJudgeCustomer o1, OnlineJudgeCustomer o2) {
                if (o1.age == o2.age) return o1.idx - o2.idx;
                else return o1.age - o2.age;
            }
        });

        for (OnlineJudgeCustomer customer : customers) {
            bw.write(customer.age + " " + customer.name + "\n");
        }
        bw.flush();
    }
}
