package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
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
		int mScore;
		int mTeam;
		
		public Soldier(int mScore, int mTeam) {
			super();
			this.mScore = mScore;
			this.mTeam = mTeam;
		}
		
		public void addScore(int mChangeScore) {
			this.mScore += mChangeScore;
			if (this.mScore > 5) this.mScore = 5;
			else if (this.mScore < 1) this.mScore = 1;
		}
	}
	
	static LinkedList<Integer>[][] teams;
	static Soldier[] soldiers;
	
	public void init()
	{
		Soldier[] soldiers = new Soldier[MAX_SOLDIER + 1];
		LinkedList<Integer>[][] teams = new LinkedList[MAX_TEAM][MAX_SCORE];
		for (int i = 1; i <= MAX_TEAM; i++) {
			for (int j = 1; j <= MAX_SCORE; j++) {
				teams[i][j] = new LinkedList<>();
			}
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		Soldier soldier = new Soldier(mScore, mTeam);
		soldiers[mID] = soldier;
		teams[mTeam][mScore].add(mID);
	}
	
	public void fire(int mID)
	{
		Soldier soldier = soldiers[mID];
		teams[soldier.mTeam][soldier.mTeam].remove(soldier);
	}

	public void updateSoldier(int mID, int mScore)
	{
		Soldier soldier = soldiers[mID];
		teams[soldier.mTeam][soldier.mScore].remove(soldier);
		soldier.mScore = mScore;
		teams[soldier.mTeam][soldier.mScore].add(mID);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		
	}
	
	public int bestSoldier(int mTeam)
	{
		return 0;
	}
}