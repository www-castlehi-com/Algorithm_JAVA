import java.io.*;

public class P_8958 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            int score = 0;
            int con = 0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'O') con++;
                else {
                    con = 0;
                }
                score += con;
            }
            bw.write(score + "\n");
        }
        bw.flush();
    }
}
