import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_13549 {

    static int size = 200001;
    static int[] info;
    static int[] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new int[size];
        visited = new boolean[size];

        bfs();

        bw.write(Integer.toString(map[info[1]]));
        bw.flush();
    }

    public static void bfs() {
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(info[0]);
        visited[info[0]] = true;

        while (!queue.isEmpty()) {
            int loc = queue.remove();

            if (loc == info[1]) return;

            if (loc * 2 < size && !visited[loc * 2]) {
                queue.addFirst(loc * 2);
                map[loc * 2] = map[loc];
                visited[loc * 2] = true;
            }

            if (loc + 1 < size && !visited[loc + 1]) {
                queue.add(loc + 1);
                map[loc + 1] = map[loc] + 1;
                visited[loc + 1] = true;
            }

            if (loc - 1 >= 0 && !visited[loc - 1]) {
                queue.add(loc - 1);
                map[loc - 1] = map[loc] + 1;
                visited[loc - 1] = true;
            }
        }
    }
}
