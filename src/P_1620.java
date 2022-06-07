import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class P_1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0], m = arr[1];
        HashMap<Integer, String> pokemonsNum = new HashMap<>();
        HashMap<String, Integer> pokemonsName = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String pokemon = br.readLine();
            pokemonsNum.put(i + 1, pokemon);
            pokemonsName.put(pokemon, i + 1);
        }

        for (int i = 0; i < m; i++) {
            String s = br.readLine();

            if (s.charAt(0) - '0' >= 0 && s.charAt(0) - '0' <= 9) bw.write(pokemonsNum.get(Integer.parseInt(s)) + "\n");
            else bw.write(pokemonsName.get(s) + "\n");
        }
        bw.flush();
    }
}
