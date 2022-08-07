package DXAlgorithm.review;

import java.util.Scanner;
import java.util.ArrayList;

public class SWEA_9999 {
    private static class Peek {
        int s;
        int e;
        int cumulative_sum;

        public Peek(int s, int e, int cumulative_sum) {
            this.s = s;
            this.e = e;
            this.cumulative_sum = cumulative_sum;
        }
    }

    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/


        for(int test_case = 1; test_case <= T; test_case++)
        {
            int l = sc.nextInt();
            int n = sc.nextInt();

            ArrayList<Peek> peeks = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                sum += (e - s);
                peeks.add(new Peek(s, e, sum));
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                int s = peeks.get(i).s, e = s + l;
                Peek lastOverlap = findPeek(peeks, e);

                int sumOverlap = lastOverlap.cumulative_sum - peeks.get(i).cumulative_sum + peeks.get(i).e - peeks.get(i).s;

                if (lastOverlap.e >= e && lastOverlap.s < e) sumOverlap -= lastOverlap.e - e;
                else if (lastOverlap.e >= e) sumOverlap -= lastOverlap.e -lastOverlap.s;

                result = Math.max(result, sumOverlap);
            }

            System.out.println("#" + test_case + " " + result);
        }
    }

    private static Peek findPeek(ArrayList<Peek> peeks, int e) {
        int start = 0;
        int end = peeks.size() - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (peeks.get(mid).e >= e) end = mid;
            else start = mid + 1;
        }

        return peeks.get(end);
    }
}