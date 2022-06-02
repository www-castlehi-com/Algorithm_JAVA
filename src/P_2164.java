import java.io.*;
import java.util.LinkedList;

public class P_2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        LinkedList<Integer> integers = new LinkedList<>();
        for (int i = 1; i <= n; i++) integers.add(i);

        while(integers.size() > 1) {
            integers.remove(0);
            integers.addLast(integers.remove(0));
        }

        bw.write(Integer.toString(integers.get(0)));
        bw.flush();
    }
}
