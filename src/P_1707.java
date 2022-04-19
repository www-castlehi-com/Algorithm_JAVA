import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_1707 {

    static ArrayList<Integer>[] graph;
    static int[] colors;
    static boolean status;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph = new ArrayList[info[0] + 1];
            for (int j = 0; j <= info[0]; j++) graph[j] = new ArrayList<>();
            status = true;
            colors = new int[info[0] + 1];

            for (int j = 0; j < info[1]; j++) {
                int[] edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                graph[edges[0]].add(edges[1]);
                graph[edges[1]].add(edges[0]);
            }

            for (int j = 1; j <= info[0]; j++) {
                if (colors[j] == 0) bfs(j, 1);
                if (status == false) break;
            }
            bw.write((status == true) ? "YES\n" : "NO\n");
        }

        bw.flush();
    }

    public static void bfs(int idx, int color) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(idx);
        colors[idx] = color;

        while (!queue.isEmpty() && status) {
            idx = queue.remove();

            for (int v : graph[idx]) {
                if (colors[v] == 0) {
                    queue.add(v);
                    colors[v] = colors[idx] * (-1);
                }
                else if (colors[v] == colors[idx]) {
                    status = false;
                }
            }
        }
    }
}
