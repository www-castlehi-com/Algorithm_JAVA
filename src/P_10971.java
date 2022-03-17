import java.io.*;
import java.util.Arrays;

public class P_10971 {

    static int[][]  info;
    static int[]    perm;
    static int      min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        int             n = Integer.parseInt(br.readLine());

        info = new int[n][n];
        for (int i = 0; i < n; i++)
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        perm = new int[n];
        for (int i = 1; i<= n; i++) perm[i - 1] = i;

        do {
            int cost = itinerant_route();
            if (cost != -1)
                min= (min < cost) ? min: cost;
        } while(find_next_perm());

        System.out.print(min);
    }

    public static boolean find_next_perm() throws IOException {

        int idx = -1;

        for (int i = perm.length - 2; i>= 0; i--) {
            if (perm[i] < perm[i + 1]) {
                idx = i;
                break;
            }
        }

        if (idx == -1) return false;
        else {
            for (int j = perm.length - 1; j > idx; j--) {
                if (perm[j] > perm[idx]) {
                    int temp = perm[j];
                    perm[j] = perm[idx];
                    perm[idx] = temp;
                    break;
                }
            }
            Arrays.sort(perm, idx + 1, perm.length);
            return true;
        }

    }

    public static int itinerant_route() {

        int cost = 0;

        for (int i = 0; i < perm.length; i++) {
            int medium_cost = info[perm[i] - 1][perm[(i + 1) % perm.length] - 1];

            if (medium_cost == 0) return -1;
            else cost += medium_cost;
        }

        return cost;
    }
}
