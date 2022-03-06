import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class P_15661 {
    static int n;
    static int[][] ability;
    static ArrayList<Integer> start_team = new ArrayList<>();
    static ArrayList<Integer> link_team = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ability = new int[n + 1][n + 1];
        for (int i = 0 ; i < n; i++) ability[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        team_ability(1);
        System.out.print(min);
    }

    public static void team_ability(int mem_num) {
        if (mem_num == n + 1) {
            if (start_team.size() > 0 && link_team.size() > 0) {
                int start_res = team_ability_sum(start_team);
                int link_res = team_ability_sum(link_team);

                min = (min > Math.abs(start_res - link_res) ? Math.abs(start_res - link_res) : min);
            }
        }
        else {
            start_team.add(mem_num);
            team_ability(mem_num + 1);
            start_team.remove(Integer.valueOf(mem_num));

            link_team.add(mem_num);
            team_ability(mem_num + 1);
            link_team.remove(Integer.valueOf(mem_num));
        }
    }

    public static int team_ability_sum(ArrayList<Integer> team) {
        int sum = 0;

        for (int i : team) {
            for (int j : team) sum += ability[i - 1][j - 1];
        }

        return sum;
    }
}
