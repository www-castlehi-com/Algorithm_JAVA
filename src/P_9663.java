import java.io.*;

public class P_9663 {

    static int n;
    static int[] board;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n];

        chess(0);

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    public static void chess(int queen_n) {
        if (queen_n == n) {
            cnt++;
            return ;
        }

        for (int i = 0; i < n; i++) {
            board[queen_n] = i;
            if (!checkmate(queen_n)) chess(queen_n + 1);
        }
    }

    public static boolean checkmate(int queen_n) {
        for (int i = 0; i < queen_n; i++) {
            if (board[queen_n] == board[i]) return true;
            if (queen_n - i == Math.abs(board[queen_n] - board[i])) return true;
        }
        return false;
    }
}
