package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_1759 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] info;
    static String[] alpha;
    static String[] password;
    static int consonant = 0;
    static int vowel = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        alpha = br.readLine().split(" ");
        password = new String[info[0]];
        Arrays.sort(alpha);
        find_password(0);
    }

    public static void find_password(int pos) throws IOException {
        if (pos == info[0]) {
            consonant = vowel = 0;
            for (String elem : password) {
                if (elem.equals("a") || elem.equals("e") || elem.equals("i") || elem.equals("o") || elem.equals("u"))
                    consonant++;
                else
                    vowel++;
            }

            if (consonant >= 1 && vowel >= 2) {
                for (String elem : password) bw.write(elem);
                bw.newLine();
                bw.flush();
            }
        }
        else {
            for (int i = pos; i < info[1]; i++) {
                password[pos] = alpha[i];
                if (check_redundancy(pos))
                    password[pos] = null;
                else {
                    find_password(pos + 1);
                }
            }
        }
    }

    public static boolean check_redundancy(int pos) {
        for (int i = 0; i < pos; i++) {
            if (password[i].compareTo(password[pos]) > 0) return true;
            if (password[i].equals(password[pos])) return true;
        }
        return false;
    }
}
