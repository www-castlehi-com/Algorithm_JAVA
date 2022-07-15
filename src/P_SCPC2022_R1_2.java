/*
You should use the standard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class P_SCPC2022_R1_2 {
    static long Answer;

    public static void main(String args[]) throws Exception	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */		

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new FileInputStream("input.txt"));

        int T = sc.nextInt();
        for(int test_case = 0; test_case < T; test_case++) {
            Answer = 0;

            int n = sc.nextInt();
            int k = sc.nextInt();

            long[] numbers = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                int num = sc.nextInt();
                numbers[i] = numbers[i - 1] + num;
            }

            if (numbers[n] % k != 0) {
                Answer = 0;
            }
            else if (numbers[n] == 0) {
                int zero = 0;
                for (int i = 1; i <= n; i++) {
                    if (numbers[i] == 0) zero++;
                }

                Answer = 1;
                for (int i = zero - 1; i >= k - 1; i--) Answer *= i;
                for (int i = k - 1; i > 0 ; i--) Answer /= i;
            }
            else {
                long divideK = numbers[n] / k;
                long[] dp = new long[k + 1];
                dp[0] = 1;
                for (int i = 1; i <= n; i++) {
                    long idx = numbers[i] / divideK;
                    if (numbers[i] % divideK != 0 || idx < 1 || idx >= k) continue;

                    dp[(int) idx] += (dp[(int) idx - 1] % 1000000007);
                }

                Answer = dp[k - 1];
            }

            // Print the answer to standard output(screen).
            System.out.println("Case #"+(test_case+1));
            System.out.println(Answer);
        }
    }
}