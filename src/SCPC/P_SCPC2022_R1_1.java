package SCPC;/*
You should use the standard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class P_SCPC2022_R1_1 {
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

            PriorityQueue<int[]> ants = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) return o1[1] - o2[1];
                    else return o1[0] - o2[0];
                }
            });

            int[] loc = new int[n];
            int[] weigh = new int[n];
            for (int i = 0; i < n; i++) loc[i] = sc.nextInt();
            for (int i = 0; i < n; i++) weigh[i] = sc.nextInt();

            for (int i = 0; i < n; i++) {
                ants.add(new int[] {weigh[i], loc[i]});
            }

            for (int i = 0; i < n; i++) {
                int[] poll = ants.poll();
                Answer += Math.abs(poll[1] - loc[i]);
            }

            // Print the answer to standard output(screen).
            System.out.println("Case #"+(test_case+1));
            System.out.println(Answer);
        }
    }
}