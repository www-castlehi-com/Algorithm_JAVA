import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		List<int[]> times = new ArrayList<>();
		for (int i = 0; i < n; i++) {
		    int[] time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		    times.add(time);
		}
		Collections.sort(times, new Comparator<int[]>() {
		    @Override
		    public int compare(int[] o1, int[] o2) {
		        if (o1[1] == o2[1]) return o1[0] - o2[0];
		        return o1[1] - o2[1];
		    }
		});
		
		int count = 0;
		int lastTime = 0;
		for (int[] time : times) {
		    if (time[0] >= lastTime) {
		        lastTime = time[1];
		        count++;
		    }
		}
		
		System.out.print(count);
	}
}