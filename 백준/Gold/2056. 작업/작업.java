import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] jobTimes;
    private static List<Integer>[] trailings;
    private static int[] precedingCount;
    private static int[] dp;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		jobTimes = new int[n + 1];
		trailings = new ArrayList[n + 1];
		precedingCount = new int[n + 1];
		dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
		    trailings[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= n; i++) {
		    st = new StringTokenizer(br.readLine());
		    
		    jobTimes[i] = Integer.parseInt(st.nextToken());
		    precedingCount[i] = Integer.parseInt(st.nextToken());
		    for (int j = 0; j < precedingCount[i]; j++) {
		        trailings[Integer.parseInt(st.nextToken())].add(i);
		    }
		}
		
		System.out.println(getTotalWorkTime());
		
// 		for (int i = 1; i <= n; i++) {
// 		    System.out.println("index : " + i + ", times : " + jobTimes[i] + ", precedingCount : " + precedingCount[i] + ", trailings : " + trailings[i].toString());
// 		}
	}
	
	private static int getTotalWorkTime() {
	    Queue<Integer> workingList = new LinkedList<>();
	    for (int i = 1; i <= n; i++) {
	        if (precedingCount[i] == 0) {
	            workingList.add(i);
	            dp[i] = jobTimes[i];
	        }
	    }
	    
	    int totalWorkTime = 0;
	    while(!workingList.isEmpty()) {
	        int currentJob = workingList.poll();
	        totalWorkTime = Math.max(totalWorkTime, dp[currentJob]);
	        
	        for (int trailing : trailings[currentJob]) {
	            dp[trailing] = Math.max(dp[trailing], dp[currentJob] + jobTimes[trailing]);
	            
	            if (--precedingCount[trailing] == 0) {
	                workingList.add(trailing);
	            }
	        }
	    }
	    
	    return totalWorkTime;
	}
}