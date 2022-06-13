import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2630 {

    static int n;
    static int[][] papers;
    static boolean[][] visited;
    static ArrayList<ColorPaper> colorPapers;

    public static class ColorPaper {
        int y;
        int x;
        int size;
        int color;

        public ColorPaper(int y, int x, int size, int color) {
            this.y = y;
            this.x = x;
            this.size = size;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        papers = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) papers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        colorPapers = new ArrayList<>();

        cutPaper();

        int bluePaper = 0;
        int whitePaper = 0;
        for (ColorPaper colorPaper : colorPapers) {
            if (colorPaper.color == 1) bluePaper++;
            else whitePaper++;
        }

        bw.write(whitePaper + "\n" + bluePaper);
        bw.flush();
    }

    private static void cutPaper() {
        LinkedList<ColorPaper> queue = new LinkedList<>();

        queue.add(new ColorPaper(0, 0, n, 2));

        while (!queue.isEmpty()) {
            ColorPaper paper = queue.poll();

            int color = papers[paper.y][paper.x];
            int cnt = 0;
            for (int i = paper.y; i < paper.y + paper.size; i++) {
                for (int j = paper.x; j < paper.x + paper.size; j++) {
                    if (color == papers[i][j]) cnt++;
                }
            }
            if (cnt != paper.size * paper.size) {
                for (int i = paper.y; i < paper.y + paper.size; i += (paper.size / 2)) {
                    for (int j = paper.x; j < paper.x + paper.size; j += (paper.size / 2)) {
                        queue.add(new ColorPaper(i, j, paper.size / 2, 2));
                    }
                }
            }
            else {
                colorPapers.add(new ColorPaper(paper.y, paper.x, paper.size, color));
            }
        }
    }
}
