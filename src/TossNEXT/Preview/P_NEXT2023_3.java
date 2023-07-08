package TossNEXT.Preview;

import java.util.Scanner;

public class P_NEXT2023_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        int[] answer = solution(a);
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n) {
        int[] answer = new int[n];
        for(int i=0; i<n; i++)
            answer[i] = i + 1;
        return answer;
    }
}
