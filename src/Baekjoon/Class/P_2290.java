package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2290 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] info;
    static int width, height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        info = br.readLine().split(" ");
        width = Integer.parseInt(info[0]) + 2;
        height = Integer.parseInt(info[0]) * 2 + 3;

        lcdtest();

        bw.flush();
    }

    public static void lcdtest() throws IOException {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < info[1].length(); j++) {
                switch (info[1].charAt(j)) {
                    case '0':
                        number_zero(i);
                        break;
                    case '1':
                        number_one(i);
                        break;
                    case '2':
                        number_two(i);
                        break;
                    case '3':
                        number_three(i);
                        break;
                    case '4':
                        number_four(i);
                        break;
                    case '5':
                        number_five(i);
                        break;
                    case '6':
                        number_six(i);
                        break;
                    case '7':
                        number_seven(i);
                        break;
                    case '8':
                        number_eight(i);
                        break;
                    case '9':
                        number_nine(i);
                        break;
                }
                bw.write(" ");
            }
            bw.newLine();
        }
    }

    public static void number_zero(int idx) throws IOException {
        if (idx == 0 || idx == height - 1) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else if (idx == height / 2){
            for (int i = 0; i < width; i++) bw.write(" ");
        }
        else {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_one(int idx) throws IOException {
        for (int i = 0; i < width - 1; i++) bw.write(" ");
        if (idx == 0 || idx == height - 1 || idx == height / 2) bw.write(" ");
        else bw.write("|");
    }

    public static void number_two(int idx) throws IOException {
        if (idx == 0 || idx == height / 2 || idx == height - 1) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else if (idx < height / 2) {
            for (int i = 0; i < width; i++) {
                if (i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
        else {
            for (int i = 0 ;i < width; i++) {
                if (i == 0) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_three(int idx) throws IOException {
        if (idx == 0 || idx == height / 2 || idx == height - 1) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else {
            for (int i = 0; i < width; i++) {
                if (i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_four(int idx) throws IOException {
        if (idx == 0 || idx == height - 1) {
            for (int i = 0 ;i < width; i++) bw.write(" ");
        }
        else if (idx == height / 2) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else if (idx < height / 2) {
            for (int i = 0; i< width; i++) {
                if (i == 0 || i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
        else {
            for (int i = 0; i < width; i++) {
                if (i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_five(int idx) throws IOException {
        if (idx == 0 || idx == height / 2 || idx == height - 1) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else if (idx > height / 2) {
            for (int i = 0; i < width; i++) {
                if (i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
        else {
            for (int i = 0 ;i < width; i++) {
                if (i == 0) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_six(int idx) throws IOException {
        if (idx == 0 || idx == height / 2 || idx == height - 1) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else if (idx < height / 2) {
            for (int i = 0; i < width; i++) {
                if (i == 0) bw.write("|");
                else bw.write(" ");
            }
        }
        else {
            for (int i = 0 ;i < width; i++) {
                if (i == 0 || i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_seven(int idx) throws IOException {
        if (idx == 0) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else if (idx == height / 2 || idx == height - 1) {
            for (int i = 0 ;i < width; i++) bw.write(" ");
        }
        else {
            for (int i = 0; i < width; i++) {
                if (i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_eight(int idx) throws IOException {
        if (idx == 0 || idx == height / 2 || idx == height - 1) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else {
            for (int i = 0 ;i < width; i++) {
                if (i == 0 || i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
    }

    public static void number_nine(int idx) throws IOException {
        if (idx == 0 || idx == height / 2 || idx == height - 1) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1) bw.write(" ");
                else bw.write("-");
            }
        }
        else if (idx > height / 2) {
            for (int i = 0; i < width; i++) {
                if (i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
        else {
            for (int i = 0 ;i < width; i++) {
                if (i == 0 || i == width - 1) bw.write("|");
                else bw.write(" ");
            }
        }
    }
}
