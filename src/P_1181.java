import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class P_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        HashSet<String> strings = new HashSet<>();
        for (int i = 0; i < n; i++) {
            strings.add(br.readLine());
        }

        String[] results = new String[strings.size()];
        strings.toArray(results);

        Arrays.sort(results, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) return o1.compareTo(o2);
                else return o1.length() - o2.length();
            }
        });

        for (String result : results) {
            bw.write(result + "\n");
        }
        bw.flush();
    }
}
