package Codeforce;

import java.io.*;
import java.util.ArrayList;

public class Div3_883_B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            char[][] field = new char[3][3];
            for (int i = 0; i < 3; i++) {
                String[] line = br.readLine().split("");
                for (int j = 0; j < 3; j++) field[i][j] = line[j].charAt(0);
            }

            ArrayList<Character> bingo = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                //가로
                char target = field[i][0];
                int cnt = 0;
                if (target != '.') {
                    for (int j = 1; j < 3; j++) {
                        if (field[i][j] == target) cnt++;
                    }
                    if (cnt == 2) bingo.add(target);
                }

                //세로
                target = field[0][i];
                cnt = 0;
                if (target != '.') {
                    for (int j = 1; j < 3; j++) {
                        if (field[j][i] == target) cnt++;
                    }
                    if (cnt == 2) bingo.add(target);
                }
            }

            //대각선
            if (((field[0][0] == field[1][1] && field[0][0] == field[2][2]) || (field[0][2] == field[1][1] && field[0][2] == field[2][0])) && field[1][1] != '.') bingo.add(field[1][1]);

            if (bingo.size() > 1 || bingo.size() == 0) bw.write("DRAW\n");
            else bw.write(bingo.get(0) + "\n");
        }
        bw.flush();
    }
}
