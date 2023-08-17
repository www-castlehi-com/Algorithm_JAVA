    package Goorm.Challenge.Week1;

    import java.io.*;
    import java.util.Arrays;

    public class Day4 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int sum = line[0];
            boolean decent = false;
            boolean perfect = true;
            for (int i = 1; i < n; i++) {
                if (decent) {
                    if (line[i] > line[i - 1]) {
                        perfect = false;
                        break;
                    }
                }
                else {
                            if (line[i] < line[i - 1]) {
                                decent = true;
                            }
                }

                sum += line[i];
            }

            if (perfect) bw.write(Integer.toString(sum));
            else bw.write("0");
            bw.flush();

            bw.close();
            br.close();
        }
    }
