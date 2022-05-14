import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_20055 {

    static int N, K;
    static LinkedList<Integer> conveyor;
    static LinkedList<Boolean> robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = array[0]; K = array[1];
        int[] belt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        conveyor = new LinkedList<>();
        robot = new LinkedList<>();
        for (int i : belt) {
            conveyor.add(i);
            robot.add(false);
        }


        bw.write(Integer.toString(factory()));
        bw.flush();
    }

    public static int factory() {
        int step = 1;

        while (true) {
            rotate();
            move();
            put_robot();

            if (check() >= K) return step;

            step++;
        }
    }

    public static void rotate() {
        conveyor.addFirst(conveyor.removeLast());
        robot.addFirst(robot.removeLast());
        robot.set(N - 1, false);
    }

    public static void move() {
        for (int i = N - 2; i >= 0; i--) {
            if (robot.get(i) == true && robot.get(i + 1) == false && conveyor.get(i + 1) >= 1) {
                robot.set(i, false);
                robot.set(i + 1, true);
                conveyor.set(i + 1, conveyor.get(i + 1) - 1);
            }
        }

        robot.set(N - 1, false);
    }

    public static void put_robot() {
        if (conveyor.get(0) != 0 && !robot.get(0)) {
            robot.set(0, true);
            conveyor.set(0, conveyor.get(0) - 1);
        }
    }

    public static int check() {
        int cnt = 0;

        for (Integer integer : conveyor) {
            if (integer == 0) cnt++;
        }

        return cnt;
    }
}
