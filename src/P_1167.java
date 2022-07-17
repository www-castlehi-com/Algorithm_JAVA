import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class P_1167 {

    static boolean[] visited;
    static int maxNode = 0;
    static int maxDist = Integer.MIN_VALUE;
    static ArrayList<int[]>[] tree;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int v = sc.nextInt();
        tree = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) tree[i] = new ArrayList<>();

        for (int i = 1; i <= v; i++) {
            int s = sc.nextInt();
            while (true) {
                int e = sc.nextInt();
                if (e == -1) break;
                int w = sc.nextInt();

                tree[s].add(new int[]{e, w});
            }
        }

        visited = new boolean[v + 1];
        dfs(1, 0);
        maxDist = 0;
        visited = new boolean[v + 1];
        dfs(maxNode, 0);

        bw.write(Integer.toString(maxDist));
        bw.flush();
    }

    private static void dfs(int root, int dist) {
        if (maxDist < dist) {
            maxDist = dist;
            maxNode = root;
        }

        visited[root] = true;

        for (int[] ints : tree[root]) {
            if (!visited[ints[0]]) dfs(ints[0], dist + ints[1]);
        }
    }
}
