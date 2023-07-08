package TossNEXT.Preview;

import java.util.Scanner;

public class P_NEXT2023_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= i; j++) System.out.print('*');
            System.out.println();
        }
    }
}
