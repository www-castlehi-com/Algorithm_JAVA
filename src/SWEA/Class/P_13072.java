package SWEA.Class;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class P_13072
{
	private final static int CMD_INIT				= 1;
	private final static int CMD_HIRE				= 2;
	private final static int CMD_FIRE				= 3;
	private final static int CMD_UPDATE_SOLDIER		= 4;
	private final static int CMD_UPDATE_TEAM		= 5;
	private final static int CMD_BEST_SOLDIER		= 6;
	
	private final static UserSolution usersolution = new UserSolution();
	
	private static boolean run(BufferedReader br) throws Exception
	{
		StringTokenizer st;
		
		int numQuery;

		int mID, mTeam, mScore, mChangeScore;
	
		int userAns, ans;
	
		boolean isCorrect = false;

		numQuery = Integer.parseInt(br.readLine());
		
		for (int q = 0; q < numQuery; ++q)
		{
			st = new StringTokenizer(br.readLine(), " ");

			int cmd;
			cmd = Integer.parseInt(st.nextToken());
			
			switch(cmd)
			{
			case CMD_INIT:
				usersolution.init();
				isCorrect = true;
				break;
			case CMD_HIRE:
				mID = Integer.parseInt(st.nextToken());
				mTeam = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.hire(mID, mTeam, mScore);
				break;
			case CMD_FIRE:
				mID = Integer.parseInt(st.nextToken());
				usersolution.fire(mID);
				break;
			case CMD_UPDATE_SOLDIER:
				mID = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.updateSoldier(mID, mScore);
				break;
			case CMD_UPDATE_TEAM:
				mTeam = Integer.parseInt(st.nextToken());
				mChangeScore = Integer.parseInt(st.nextToken());
				usersolution.updateTeam(mTeam, mChangeScore);
				break;
			case CMD_BEST_SOLDIER:
				mTeam = Integer.parseInt(st.nextToken());
				userAns = usersolution.bestSoldier(mTeam);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans) {
					isCorrect = false;
				}
				break;
			default:
				isCorrect = false;
				break;
			}
		}
		
		return isCorrect;
	}
	
	public static void main(String[] args) throws Exception
	{
		int TC, MARK;
	
		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase)
		{
			int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}


class UserSolution
{
	
	static final int MAX_SOLDIER = 100_000;
	static final int MAX_TEAM = 5;
	static final int MAX_SCORE = 5;
	
	static class Soldier {
		int mID;
		int version;
		
		public Soldier(int mID, int version) {
			this.mID = mID;
			this.version = version;
		}
	}
	
	static class Node {
		Soldier soldier;
		Node next;
		Node prev;
		
		public Node(Soldier soldier, Node next, Node prev) {
			this.soldier = soldier;
			this.next = next;
			this.prev = prev;
		}
	}
	
	static class List {
		Node head;
		Node tail;
		
		public List() {
			this.head = new Node(null, null, null);
			this.tail = new Node(null, null, null);
			
			this.head.next = this.tail;
			this.tail.prev = this.head;
		}
	}
	
	static List[][] teams;
	static int[] soldiers;
	static int[] version;
	
	public void init()
	{
		soldiers = new int[MAX_SOLDIER + 1];
		teams = new List[MAX_TEAM + 1][MAX_SCORE + 1];
		for (int i = 1; i <= MAX_TEAM; i++) {
			for (int j = 1; j <= MAX_SCORE; j++) {
				teams[i][j] = new List();
			}
		}
		version = new int[MAX_SOLDIER + 1];
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		Soldier soldier = new Soldier(mID, ++version[mID]);
		Node newNode = new Node(soldier, teams[mTeam][mScore].tail, teams[mTeam][mScore].tail.prev);
		soldiers[mID] = mTeam;
		teams[mTeam][mScore].tail.prev.next = newNode;
		teams[mTeam][mScore].tail.prev = newNode;
	}
	
	public void fire(int mID)
	{
		version[mID] = -1;
	}

	public void updateSoldier(int mID, int mScore)
	{
		hire(mID, soldiers[mID], mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		for (int i = MAX_SCORE; i >= 1; i--) {
			changeMemory(mTeam, mChangeScore, (mChangeScore > 0) ? i : MAX_SCORE + 1 - i);
		}
	}

	private void changeMemory(int mTeam, int mChangeScore, int i) {
		int targetScore = addScore(i, mChangeScore);
		List mover = teams[mTeam][i];
		if (i != targetScore && mover.head.next != mover.tail) {
			List target = teams[mTeam][targetScore];
			
			target.tail.prev.next = mover.head.next;
			mover.head.next.prev = target.tail.prev;
			mover.tail.prev.next = target.tail;
			target.tail.prev = mover.tail.prev;
			mover.head.next = mover.tail;
			mover.tail.prev = mover.head;
			
		}
	}
	
	public int bestSoldier(int mTeam)
	{
		int max = 0;
		for (int i = MAX_SCORE; i >= 1; i--) {
			List target = teams[mTeam][i];
			for (Node ptr = target.head.next; ptr != target.tail; ptr = ptr.next) {
				if (version[ptr.soldier.mID] == ptr.soldier.version) {
					max = Math.max(max, ptr.soldier.mID);
				}
			}
			
			if (max != 0) {
				break;
			}
		}
		return max;
	}
	
	private int addScore(int score, int changeScore) {
		score += changeScore;
		if (score > 5) score = 5;
		else if (score < 1) score = 1;
		
		return score;
	}
}