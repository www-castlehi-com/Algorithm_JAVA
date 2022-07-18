package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_20327 {

    static int N;
    static int size;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = array[0]; int R = array[1];
        size = (int) Math.pow(2, N);
        map = new int[size][size];
        for (int i = 0; i < size; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < R; i++) {
            int[] com = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            map = rotate(com[0], com[1]);
        }

        for (int[] ints : map) {
            for (int anInt : ints) {
                bw.write(anInt + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    public static int[][] rotate(int k, int l) {
        switch (k) {
            case 1:
                return com_1(l);
            case 2:
                return com_2(l);
            case 3:
                return com_3(l);
            case 4:
                return com_4(l);
            case 5:
                return com_5(l);
            case 6:
                return com_6(l);
            case 7:
                return com_7(l);
            case 8:
                return com_8(l);
            default:
                return null;
        }
    }

    public static int[][] com_1(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[sub_size * i + sub_size - 1 - k][sub_size * j + m];
                    }
                }
            }
        }

        return after_map;
    }

    public static int[][] com_2(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[sub_size * i + k][sub_size * j + sub_size - 1 - m];
                    }
                }
            }
        }

        return after_map;
    }

    public static int[][] com_3(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[sub_size * i + sub_size - 1 - m][sub_size * j + k];
                    }
                }
            }
        }

        return after_map;
    }

    public static int[][] com_4(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[sub_size * i + m][sub_size * j + sub_size - 1 - k];
                    }
                }
            }
        }

        return after_map;
    }

    public static int[][] com_5(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[size - sub_size * i - sub_size + k][sub_size * j + m];
                    }
                }
            }
        }

        return after_map;
    }

    public static int[][] com_6(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[sub_size * i + k][size - sub_size * j - sub_size + m];
                    }
                }
            }
        }

        return after_map;
    }

    public static int[][] com_7(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[size - sub_size * j - sub_size + k][sub_size * i + m];
                    }
                }
            }
        }

        return after_map;
    }

    public static int[][] com_8(int l) {
        int[][] after_map = new int[size][size];
        int sub_size = (int) Math.pow(2, l);

        for (int i = 0; i < size / sub_size; i++) {
            for (int j = 0; j < size / sub_size; j++) {
                for (int k = 0; k < sub_size; k++) {
                    for (int m = 0; m < sub_size; m++) {
                        after_map[sub_size * i + k][sub_size * j + m] = map[sub_size * j + k][size - sub_size * i - sub_size + m];
                    }
                }
            }
        }

        return after_map;
    }
}
