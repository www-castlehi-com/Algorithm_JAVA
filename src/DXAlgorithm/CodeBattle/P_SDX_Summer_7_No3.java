package DXAlgorithm.CodeBattle;/////////////////////////////////////////////////////////////////////////////////////////////
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

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class P_SDX_Summer_7_No3
{

    static int HASH_ROW_MOD = 10007;
    static int HASH_COL_MOD = 10009;
    static int result;

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
            result = 0;

            int h = sc.nextInt();
            int w = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[][] pattern = new int[h][w];
            for (int i = 0; i < h; i++) {
                char[] chars = sc.next().toCharArray();
                for (int j = 0; j < w; j++) pattern[i][j] = (chars[j] == 'o' ? 1 : 0);
            }
            int[][] target = new int[n][m];
            for (int i = 0; i < n; i++) {
                char[] chars = sc.next().toCharArray();
                for (int j = 0; j < m; j++) target[i][j] = (chars[j] == 'o' ? 1 : 0);
            }

            int patternHash = getPatternHash(pattern, h, w);
            int[][] targetRowHash = new int[n][m];
            int[][] targetColHash = new int[n][m];
            getTargetHash(patternHash, target, targetRowHash, targetColHash, h, w, n, m);

            System.out.println("#" + test_case + " " + result);
        }
    }

    private static void getTargetHash(int patternHash, int[][] target, int[][] targetRowHash, int[][] targetColHash, int h, int w, int n, int m) {
        int power = 1;
        for (int i = 0; i < n; i++) {
            power = 1;
            for (int j = 0; j < w; j++) {
                targetRowHash[i][0] += target[i][w - 1 - j] * power;
                if (j < w - 1) power *= HASH_ROW_MOD;
            }
            for (int j = 1; j <= m - w; j++) {
                targetRowHash[i][j] = (targetRowHash[i][j - 1] - target[i][j - 1] * power) * HASH_ROW_MOD + target[i][j + w - 1];
            }
        }
        for (int i = 0; i <= m - w; i++) {
            power = 1;
            for (int j = 0; j < h; j++) {
                targetColHash[0][i] += targetRowHash[h - j - 1][i] * power;
                if (j < h - 1) power *= HASH_COL_MOD;
            }
            if (patternHash == targetColHash[0][i]) result++;
            for (int j = 1; j <= n - h; j++) {
                targetColHash[j][i] = (targetColHash[j - 1][i] - targetRowHash[j - 1][i] * power) * HASH_COL_MOD + targetRowHash[j + h - 1][i];
                if (targetColHash[j][i] == patternHash) result++;
            }
        }
    }

    private static int getPatternHash(int[][] pattern, int h, int w) {
        int[] patternRowHash = new int[h];

        int power;
        for (int i = 0; i < h; i++) {
            power = 1;
            for (int j = 0; j < w; j++) {
                patternRowHash[i] += pattern[i][w - j - 1] * power;
                power *= HASH_ROW_MOD;
            }
        }

        int patternHash = 0;
        power = 1;
        for (int i = 0; i < h; i++) {
            patternHash += patternRowHash[h - i - 1] * power;
            power *= HASH_COL_MOD;
        }

        return patternHash;
    }
}