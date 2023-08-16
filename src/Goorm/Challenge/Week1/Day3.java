package Goorm.Challenge.Week1;

import java.io.*;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int res = 0;
        while (t-- > 0) {
            String[] line = br.readLine().split(" ");

            int a = Integer.parseInt(line[0]), b = Integer.parseInt(line[2]);
            switch (line[1]) {
                case "+" :
                    res += (a + b);
                    break;
                case "-" :
                    res += (a - b);
                    break;
                case "*" :
                    res += (a * b);
                    break;
                case "/" :
                    res += (a / b);
            }
        }

        bw.write(Integer.toString(res));
        bw.flush();

        bw.close();
        br.close();
    }
}
