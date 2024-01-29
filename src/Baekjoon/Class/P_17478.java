package Baekjoon.Class;

public class P_17478 {
	
	static int n;
	static BufferedWriter bw;
	static String recursionToken = "____";
	static String recursionSentences[] = {"\"재귀함수는 자기 자신을 호출하는 함수라네\"\n", "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n", "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n", "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		
		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		recursion(0);
		
		bw.flush();
	}

	private static void recursion(int cnt) throws IOException {
		bw.write(getRecursionToken(cnt) + recursionSentences[0]);
		if (cnt == n) {
			bw.write(getRecursionToken(cnt) + "\"재귀함수가 뭔가요?\"\n");
		}
		else {
			for (int i = 1; i < recursionSentences.length; i++) {				
				bw.write(getRecursionToken(cnt) + recursionSentences[i]);
			}
			recursion(cnt + 1);
		}
		bw.write(getRecursionToken(cnt) + "라고 답변하였지.\n");
	}
	
	private static String getRecursionToken(int cnt) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= cnt; i++) {
			sb.append(recursionToken);
		}
		return sb.toString();
	}
}
