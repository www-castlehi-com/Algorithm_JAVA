import java.io.*;

public class P_1644 {

    static boolean[] prime = new boolean[4000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        findPrime();

        int ptr1 = 1, ptr2 = 1;
        int sum = 0, cnt = 0;
        while (true) {
            if (sum >= n) {
                while (ptr1 < 4000000 && !prime[++ptr1]) {}
                if (ptr1 == 4000000) break;
                sum -= ptr1;
            }
            else {
                if (ptr2 > n) break;
                else {
                    while (ptr2 < 4000000 && !prime[++ptr2]) {}
                    if (ptr2 == 4000000) break;
                    sum += ptr2;
                }
            }

            if (sum == n) cnt++;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static void findPrime() {
        for (int i = 2 ; i <= 4000000; i++) prime[i] = true;

        for (int i = 2; i * i <= 4000000; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= 4000000; j += i) prime[j] = false;
            }
        }
    }
}
