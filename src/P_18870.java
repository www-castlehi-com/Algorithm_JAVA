import java.io.*;
import java.util.*;

public class P_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ArrayList<Integer> arr2 = new ArrayList<>();
        for (int i = 0; i < n; i++) arr2.add(arr[i]);
        Collections.sort(arr2);

        HashMap<Integer, Integer> hashmap = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < arr2.size(); i++) {
            if (!hashmap.containsKey(arr2.get(i))) hashmap.put(arr2.get(i), idx++);
        }

        for (int i = 0; i < n; i++) {
            bw.write(hashmap.get(arr[i]) + " ");
        }
        bw.flush();
    }
}