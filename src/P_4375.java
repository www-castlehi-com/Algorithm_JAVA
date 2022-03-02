import java.util.Scanner;

public class P_4375 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int num = 0;

            for (int i = 1; i <= n; i++) {
                num = (num * 10 + 1) % n;

                if (num == 0) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
