import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_1917 {

    static String[][] cubes = { // 정육면체의 전개도는 총 11개 밖에 없다.
            {"0010",
                    "1111",
                    "0010"},
            {"0100",
                    "1111",
                    "1000"},
            {"0010",
                    "1111",
                    "0100"},
            {"0001",
                    "1111",
                    "1000"},
            {"0001",
                    "1111",
                    "0100"},
            {"11100",
                    "00111"},
            {"1100",
                    "0111",
                    "0010"},
            {"1100",
                    "0111",
                    "0001"},
            {"0010",
                    "1110",
                    "0011"},
            {"0001",
                    "1111",
                    "0001"},
            {"1100",
                    "0110",
                    "0011"}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int t = 3;

        while(t-- > 0) {
            int map[][] = new int[6][6];

            for(int i=0; i<6; i++) {
                stk = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<6; j++) {
                    map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            boolean ans = false;
            loop :
            for(String c[] : cubes) {
                String[] cube = new String[c.length];

                // c배열을 cube 배열에 복사
                System.arraycopy(c, 0, cube, 0, c.length);

                for(int mir=0; mir<2; mir++) {
                    for(int rot=0; rot<4; rot++) {
                        for(int i=0; i<6; i++) {
                            for(int j=0; j<6; j++) {
                                // 한번이라도 true면 yes
                                ans |= check(map, cube, i, j);

                                if(ans) break loop;
                            }
                        }
                        cube = rotate(cube);
                    }
                    cube = mirror(cube);
                }
            }
            System.out.println(ans ? "yes" : "no");
        }
    }

    public static boolean check(int[][] map, String[] cube, int y, int x) {
        int n = map.length;

        for(int i=0; i<cube.length; i++) {
            for(int j=0; j<cube[0].length(); j++) {
                int ny = y + i;
                int nx = x + j;

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if(cube[i].charAt(j) == '0') {
                        if(map[ny][nx] == 1) return false;
                    } else if(cube[i].charAt(j) == '1') {
                        if(map[ny][nx] == 0) return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
    // 뒤집기(옆), 위 혹은 아래로 뒤집는건 의미 x
    public static String[] mirror(String[] cube) {
        String[] ans = new String[cube.length];

        for(int i=0; i<cube.length; i++) {
            ans[i] = new StringBuffer(cube[i]).reverse().toString();
        }

        return ans;
    }
    // 시계 방향 90도 회전
    public static String[] rotate(String[] cube) {
        String[] ans = new String[cube[0].length()];

        for(int j=0; j<cube[0].length(); j++) {
            StringBuffer sb = new StringBuffer();
            for(int i=cube.length-1; i>=0; i--) {
                sb.append(cube[i].charAt(j));
            }
            ans[j] = sb.toString();
        }

        return ans;
    }
}
