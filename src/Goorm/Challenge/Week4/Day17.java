package Goorm.Challenge.Week4;

import java.io.*;
import java.util.*;

public class Day17 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    static class Component implements Comparable {
        PriorityQueue<Integer> nodes;
        int cnt;
        double density;

        public Component() {
            this.nodes = new PriorityQueue<>(Comparator.comparing(o -> o));
            this.cnt = 0;
            this.density = 0.0;
        }

        @Override
        public int compareTo(Object o) {
            Component component = (Component) o;
            if (component.density != this.density) {
                if (component.density > this.density) return 1;
                else return -1;
            }
            else if (component.nodes.size() != this.nodes.size()) return this.nodes.size() - component.nodes.size();
            else return this.nodes.peek() - component.nodes.peek();
        }
    }

    static ArrayList<Component> components = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[line[0]].add(line[1]);
            graph[line[1]].add(line[0]);
        }
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i])
                bfs(i);
        }

        Collections.sort(components);

        while(!components.get(0).nodes.isEmpty()) bw.write(components.get(0).nodes.poll() + " ");
        bw.flush();
    }

    private static void bfs(int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        Component component = new Component();

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            if (!visited[poll]) {
                visited[poll] = true;
                component.nodes.add(poll);

                for (Integer integer : graph[poll]) {
                    component.cnt++;
                    queue.add(integer);
                }
            }
        }
        component.cnt /= 2;
        component.density = (double) component.cnt / component.nodes.size();

        components.add(component);
    }
}
