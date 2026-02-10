package kakao;

class Leaf {
	public long solution(long dist_limit, long split_limit) {
		long maxLeaves = 0;

		for (int b = 0; Math.pow(3, b) <= split_limit; b++) {
			long pow3 = (long) Math.pow(3, b);
			long remain = split_limit / pow3;
			long log2 = (long) (Math.log(remain) / Math.log(2));
			long maxA = Math.min(log2, dist_limit - b);
			if (maxA < 0) continue;

			// 리프는 2^a * 3^b 형태로 생성됨
			long leaves = (long) Math.pow(2, maxA) * (long) Math.pow(3, b);
			if (leaves > maxLeaves) maxLeaves = leaves;
		}
		return maxLeaves;
	}

	public static void main(String[] args) {
		Leaf s = new Leaf();
		System.out.println(s.solution(3, 100)); // 기대: 7
		System.out.println(s.solution(5, 16));  // 기대: 9
		System.out.println(s.solution(6, 18));  // 기대: 18
		System.out.println(s.solution(6, 30));  // 기대: 30
	}
}

