package DXAlgorithm.review;

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class SWEA_7091
{

    final static int ROW_HASH = 10007;
    final static int COL_HASH = 10009;

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
            int h = sc.nextInt();
            int w = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] eungi = new int[h][w];
            for (int i = 0; i < h; i++) {
                String line = sc.next();
                for (int j = 0; j < w; j++) eungi[i][j] = (line.charAt(j) == 'o' ? 1 : 0);
            }
            int eungiHash = makeAllHash(eungi, h, w);

            int[][] teacher = new int[n][m];
            for (int i = 0; i < n; i++) {
                String line = sc.next();
                for (int j = 0; j < m; j++) teacher[i][j] = (line.charAt(j) == 'o' ? 1 : 0);
            }

            int cnt = subPicture(teacher, h, w, n, m, eungiHash);
            System.out.println("#" + test_case + " " + cnt);
        }
    }

    private static int subPicture(int[][] origin, int h, int w, int n, int m, int targetHash) {
        int cnt = 0;
        int power = 1;

        int[][] rowSubHash = new int[n][m];
        for (int i = 0; i < n; i++) {
            power = 1;
            for (int j = 0; j < w; j++) {
                rowSubHash[i][0] += origin[i][w - j - 1] * power;
                if (j < w - 1) power *= ROW_HASH;
            }
            for (int j = 1; j <= m - w; j++) {
                rowSubHash[i][j] = (rowSubHash[i][j - 1] - origin[i][j - 1] * power) * ROW_HASH + origin[i][j + w - 1];
            }
        }

        int[][] colSubHash = new int[n][m];
        for (int i = 0; i <= m - w; i++) {
            power = 1;
            for (int j = 0; j < h; j++) {
                colSubHash[0][i] += rowSubHash[h - j - 1][i] * power;
                if (j < h - 1) power *= COL_HASH;
            }
            if (targetHash == colSubHash[0][i]) cnt++;
            for (int j = 1; j <= n - h; j++) {
                colSubHash[j][i] = (colSubHash[j - 1][i] - rowSubHash[j - 1][i] * power) * COL_HASH + rowSubHash[j + h - 1][i];
                if (colSubHash[j][i] == targetHash) cnt++;
            }
        }

        return cnt;
    }

    private static int makeAllHash(int[][] target, int h, int w) {
        int power = 1;

        int[] rowHash = new int[h];
        for (int i = 0; i < h; i++) {
            power = 1;
            for (int j = w - 1; j >= 0; j--) {
                rowHash[i] += target[i][j] * power;
                power *= ROW_HASH;
            }
        }

        int totalHash = 0;
        power = 1;
        for (int i = h - 1; i>= 0; i--) {
            totalHash += rowHash[i] * power;
            power *= COL_HASH;
        }

        return totalHash;
    }
}