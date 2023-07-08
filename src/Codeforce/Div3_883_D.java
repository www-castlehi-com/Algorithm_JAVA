package Codeforce;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Div3_883_D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = line[0], d = line[1], h = line[2];
            int[] branchHeight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            BigDecimal totalWidth = BigDecimal.valueOf(d).multiply(BigDecimal.valueOf(h)).multiply(BigDecimal.valueOf(n)).multiply(BigDecimal.valueOf(0.5));
            for (int i = 0; i < n - 1; i++) {
                long hide = branchHeight[i] + h - branchHeight[i + 1];

                if (hide > 0) {
                    BigDecimal hideWidth = BigDecimal.valueOf(hide / (double)h).multiply(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(d)).multiply(BigDecimal.valueOf(hide));
                    totalWidth = totalWidth.subtract(hideWidth);
                }
            }

            bw.write(totalWidth + "\n");
        }
        bw.flush();
    }
}
