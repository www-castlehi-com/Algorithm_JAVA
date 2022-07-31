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
import java.util.*;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class SWEA_1244
{
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
            String num = sc.next();
            String[] sNum = num.split("");
            String[] maxNum = num.split("");
            Arrays.sort(maxNum, Comparator.reverseOrder());
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < maxNum.length; i++) {
                if (map.getOrDefault(maxNum[i], 0) == 0) map.put(maxNum[i], 1);
                else map.put(maxNum[i], map.get(maxNum[i]) + 1);
            }
            int chance = sc.nextInt();

            String[] sResult = change(sNum, maxNum, chance, map);
            int result = 0;
            for (int i = 0; i < sResult.length; i++) {
                result = result * 10 + Integer.parseInt(sResult[i]);
            }
            System.out.println("#" + test_case + " " + result);
        }
    }

    private static String[] change(String[] origin, String[] target, int chance, HashMap<String, Integer> map) {
        int cnt = 0;
        int idx = 0;
        while (true) {
            if (cnt == chance) return origin;

            if (equal(origin, target)) {
                boolean sameDigit = findSame(origin);
                if (sameDigit) return origin;
                else {
                    if ((chance - cnt) % 2 == 0 || origin.length == 1) return origin;
                    else {
                        String temp = origin[origin.length - 2];
                        origin[origin.length - 2] = origin[origin.length - 1];
                        origin[origin.length - 1] = temp;
                        return origin;
                    }
                }
            }
            else {
                while (idx < target.length && target[idx].equals(origin[idx])) idx++;
                if (idx < target.length) {
                    String obj = target[idx];
                    if (findSameTarget(origin, obj)) {
                        if (chance - cnt > 1) {
                            int grade = findGrade(origin[idx], obj, map);
                            int objCnt = 0;
                            for (int i = origin.length - 1; i > idx; i--) {
                                if (origin[i].equals(obj)) {
                                    if (objCnt == grade) {
                                        String temp = origin[i];
                                        origin[i] = origin[idx];
                                        origin[idx] = temp;
                                        break;
                                    }
                                    objCnt++;
                                }
                            }
                        }
                        else {
                            for (int i = origin.length - 1; i > idx; i--) {
                                if (origin[i].equals(obj)) {
                                    String temp = origin[i];
                                    origin[i] = origin[idx];
                                    origin[idx] = temp;
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        for (int i = idx + 1; i < origin.length; i++) {
                            if (origin[i].equals(obj)) {
                                String temp = origin[i];
                                origin[i] = origin[idx];
                                origin[idx] = temp;
                                break;
                            }
                        }
                    }
                    idx++;
                    cnt++;
                }
            }
        }
    }

    private static boolean equal(String[] origin, String[] target) {
        for (int i = 0; i < origin.length; i++) {
            if (!origin[i].equals(target[i])) return false;
        }
        return true;
    }

    private static int findGrade(String idx, String obj, HashMap<String, Integer> map) {
        ArrayList<String> key = new ArrayList<>(map.keySet());
        Collections.sort(key, Collections.reverseOrder());

        int grade = 0;
        for (int i = 0; i < key.size(); i++) {
            if (Integer.parseInt(key.get(i)) < Integer.parseInt(idx) && Integer.parseInt(key.get(i)) < Integer.parseInt(obj)) grade++;
        }
        return grade;
    }

    private static boolean findSame(String[] origin) {
        for (int i = 0; i< origin.length; i++) {
            for (int j = i + 1 ;j < origin.length; j++) {
                if (origin[i].equals(origin[j])) return true;
            }
        }
        return false;
    }

    private static boolean findSameTarget(String[] origin, String target) {
        int cnt = 0;
        for (int i = 0; i< origin.length; i++) {
            if (origin[i].equals(target)) cnt++;
        }
        if (cnt > 1) return true;
        return false;
    }
}