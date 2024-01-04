package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_16235
{

    static int n, m, k;
    static int [][] map;
    static int [][] a;
    static ArrayList<Tree> trees = new ArrayList<>();
    static ArrayList<Tree> liveTrees;
    static ArrayList<Tree> deadTrees;

    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    static public class Tree implements Comparable<Tree> {
        int x, y;
        int age;

        Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree t) {
            return this.age - t.age;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = 5;
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            trees.add(new Tree(x, y, z));
        }

        while (k > 0) {
            liveTrees = new ArrayList<>();
            deadTrees = new ArrayList<>();

            Collections.sort(trees);
            spring();
            summer();
            autumn();
            winter();
            k--;
        }

        System.out.println(trees.size());
    }

    public static void spring() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.age > map[tree.x][tree.y]) {
                deadTrees.add(tree);
            } else {
                map[tree.x][tree.y] -= tree.age;
                tree.age += 1;
                liveTrees.add(tree);
            }
        }
        trees.clear();
        trees.addAll(liveTrees);
    }

    public static void summer() {
        for (int i = 0; i < deadTrees.size(); i++) {
            Tree tree = deadTrees.get(i);
            map[tree.x][tree.y] += tree.age / 2;
        }
    }

    public static void autumn() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.age % 5 == 0) {
                for (int l = 0; l < 8; l++) {
                    int nextY = tree.y + dy[l];
                    int nextX = tree.x + dx[l];

                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                        trees.add(new Tree(nextX, nextY, 1));
                    }
                }
            }
        }
    }

    public static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += a[i][j];
            }
        }
    }
}
