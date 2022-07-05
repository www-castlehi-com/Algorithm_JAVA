import java.io.*;

public class P_1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        bw.write(Integer.toString(n / 5 + n / 25 + n / 125));

        int fact = 1;
        while (n > 1) {
            fact *= n--;
        }
        System.out.println("fact = " + fact);
        int cnt = 0;
        for (int i = 0 ;i < Integer.toString(fact).length(); i++) {
            if (Integer.toString(fact).charAt(i) == '0') cnt++;
        }

        bw.write(Integer.toString(cnt));

        bw.flush();
    }
}
