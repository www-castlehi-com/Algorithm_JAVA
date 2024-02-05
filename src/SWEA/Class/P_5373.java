package SWEA.Class;

import java.io.*;
import java.util.StringTokenizer;

public class P_5373
{
    // 0 : 앞면
    // 1 : 뒷면
    // 2 : 윗면
    // 3 : 아랫면
    // 4 : 좌측면
    // 5 : 우측면

    static char[][][] cube;

    public static void main (String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt (st.nextToken());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            initializeCube();

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                String line = st.nextToken();
                char aspect = line.charAt(0);
                char direction = line.charAt(1);

                cubing(aspect, direction);
            }

            for (int i = 0; i < 3; i++) {
                String res = "";
                for (int j = 0; j < 3; j++) {
                    res += cube[2][i][j];
                }
                bw.write(res + "\n");
            }
        }
        bw.flush();
    }

    private static void cubing(char aspect, char direction) {
        char[][][] oldCube = copyCube();
        int column = (aspect == 'L') ? 0 : 2;
        int row = (aspect == 'U' || aspect == 'B') ? 0 : 2;

        if (aspect == 'L') rotateSide(4, direction);
        else if (aspect == 'R') rotateSide(5, direction);
        else if (aspect == 'U') rotateSide(2, direction);
        else if (aspect == 'D') rotateSide(3, direction);
        else if (aspect == 'F') rotateSide(0, direction);
        else rotateSide(1, direction);

        if ((aspect == 'L' && direction == '-') || (aspect == 'R' && direction == '+')) {
            for (int i = 0; i < 3; i++) {
                rotate(0, i, column, 3, 2 - column, i, oldCube);
                rotate(3, 2 - column, i, 1, 2 - i, 2 - column, oldCube);
                rotate(1, 2 - i, 2 - column, 2, i, column, oldCube);
                rotate(2, i, column, 0, i, column, oldCube);
            }
        }
        else if ((aspect == 'L' && direction == '+') || (aspect == 'R' && direction == '-')) {
            for (int i = 0; i < 3; i++) {
                rotate(0, i, column, 2, i, column, oldCube);
                rotate(2, i, column, 1, 2 - i, 2 - column, oldCube);
                rotate(1, 2 - i, 2 - column, 3, 2 - column, i, oldCube);
                rotate(3, 2 - column, i, 0, i, column, oldCube);
            }
        }
        else if ((aspect == 'U' && direction == '-') || (aspect == 'D' && direction == '+')) {
            for (int i = 0; i < 3; i++) {
                rotate(0, row, i, 4, row, i, oldCube);
                rotate(4, row, i, 1, row, i, oldCube);
                rotate(1, row, i, 5, row, i, oldCube);
                rotate(5, row, i, 0, row, i, oldCube);
            }
        }
        else if ((aspect == 'U' && direction == '+') || (aspect == 'D' && direction == '-')) {
            for (int i = 0; i < 3; i++) {
                rotate(0, row, i, 5, row, i, oldCube);
                rotate(5, row, i, 1, row, i, oldCube);
                rotate(1, row, i, 4, row, i, oldCube);
                rotate(4, row, i, 0, row, i, oldCube);
            }
        }
        else if ((aspect == 'F' && direction == '-') || (aspect == 'B' && direction == '+')) {
            for (int i = 0; i < 3; i++) {
                rotate(2, row, 2 - i, 5, 2 - i, 2 - row, oldCube);
                rotate(5, 2 - i, 2 - row, 3, 2 - i, 2 - row, oldCube);
                rotate(3, 2 - i, 2 - row, 4, i, row, oldCube);
                rotate(4, i, row, 2, row, 2 - i, oldCube);
            }
        }
        else {
            for (int i = 0; i < 3; i++) {
                rotate(2, row, 2 - i, 4, i, row, oldCube);
                rotate(4, i, row, 3, 2 - i, 2 - row, oldCube);
                rotate(3, 2 - i, 2 - row, 5, 2 - i, 2 - row, oldCube);
                rotate(5, 2 - i, 2 - row, 2, row, 2 - i, oldCube);
            }
        }
        // 0 : 앞면
        // 1 : 뒷면
        // 2 : 윗면
        // 3 : 아랫면
        // 4 : 좌측면
        // 5 : 우측면
    }

    private static void rotateSide(int side, char direction) {
        char[][] oldSide = copySide(side);
        if (direction == '-') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    oldSide[i][j] = cube[side][j][2 - i];
                }
            }
        }
        else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    oldSide[i][j] = cube[side][2 - j][i];
                }
            }
        }
        cube[side] = oldSide;
    }

    private static char[][] copySide(int side) {
        char[][] copySide = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copySide[i][j] = cube[side][i][j];
            }
        }
        return copySide;
    }

    private static char[][][] copyCube() {
        char[][][] copyCube = new char[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    copyCube[i][j][k] = cube[i][j][k];
                }
            }
        }
        return copyCube;
    }

    private static void rotate(int prevSide, int prevRow, int prevColumn, int newSide, int newRow, int newColumn, char[][][] oldCube) {
        cube[prevSide][prevRow][prevColumn] = oldCube[newSide][newRow][newColumn];
    }

    private static void initializeCube() {
        cube = new char[6][3][3];

        for (int i = 0; i < 6; i++) {
            initializeSide(i);
        }
    }

    private static void initializeSide(int side) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char color = 'r';
                switch (side) {
                    case 1:
                        color = 'o';
                        break;
                    case 2:
                        color = 'w';
                        break;
                    case 3:
                        color = 'y';
                        break;
                    case 4:
                        color = 'g';
                        break;
                    case 5:
                        color = 'b';
                        break;
                }

                cube[side][i][j] = color;
            }
        }
    }
}
