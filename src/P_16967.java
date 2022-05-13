import java.io.*;
import java.util.Arrays;

public class P_16967 {

    static int H, W, X, Y;
    static int[][] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        H = array[0]; W = array[1]; X = array[2]; Y = array[3];
        B = new int[H + X][W + Y];
        for (int i = 0; i < H + X; i++) B[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] A = find_A();
        for (int[] ints : A) {
            for (int anInt : ints) {
                bw.write(anInt+ " ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    public static int[][] find_A() {
        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (i >= X && j >= Y) A[i][j] = B[i][j] - A[i - X][j - Y];
                else A[i][j] = B[i][j];
            }
        }

        return A;
    }
}