import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static List<Integer>[] friends;
    private static int score = Integer.MAX_VALUE;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		friends = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
		    friends[i] = new ArrayList<>();
		}
		
		while (true) {
		    st = new StringTokenizer(br.readLine());
		    
		    int person1 = Integer.parseInt(st.nextToken());
		    int person2 = Integer.parseInt(st.nextToken());
		    
		    if (person1 == -1 && person2 == -1) {
		        break;
		    }
		    
		    friends[person1].add(person2);
		    friends[person2].add(person1);
		}
		
		List<Integer> candidates = getCandidates();
		System.out.println(score + " " + candidates.size());
		for (int candidate : candidates) {
		    System.out.print(candidate + " ");
		}
	}
	
	private static List<Integer> getCandidates() {
	    List<Integer> candidates = new ArrayList<>();
	    for (int i = 1; i <= n; i++) {
	        int friendScore = calculateScore(i);
	        if (friendScore < score) {
	            candidates.clear();
	            score = friendScore;
	            candidates.add(i);
	        } else if (friendScore == score) {
	            candidates.add(i);
	        }
	    }
	    
	    return candidates;
	}
	
	private static int calculateScore(int friend) {
	    Queue<int[]> queue = new LinkedList<>();
	    queue.add(new int[]{0, friend});
	    boolean[] visited = new boolean[n + 1];
	    visited[friend] = true;
	    
	    int score = 0;
	    while (!queue.isEmpty()) {
	        int[] friendInfo = queue.poll();
	        
	        for (int nextFriend : friends[friendInfo[1]]) {
	            if (!visited[nextFriend]) {
	                queue.add(new int[]{friendInfo[0] + 1, nextFriend});
	                score = Math.max(score, friendInfo[0] + 1);
	                visited[nextFriend] = true;
	            }
	        }
	    }
	    
	    return score;
	}
}