import java.util.*;

class P_SDX_Summer_Prev_No2_User {

    //treeset"?
    
    static PriorityQueue<User>[][] pqMax = new PriorityQueue[2][4];
    static PriorityQueue<User>[][] pqMin = new PriorityQueue[2][4];
    static HashMap<Integer, User> map;

    static class User {
        int id;
        int grade;
        int gender;
        int score;

        public User(int id, int grade, int gender, int score) {
            this.id = id;
            this.grade = grade;
            this.gender = gender;
            this.score = score;
        }
    }

    public void init() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                pqMax[i][j] = new PriorityQueue<>((o1, o2) -> {
                    if (o1.score == o2.score) return o2.id - o1.id;
                    return o2.score - o1.score;
                });

                pqMin[i][j] = new PriorityQueue<>((o1, o2) -> {
                        if (o1.score == o2.score) return o1.id - o2.id;
                        return o1.score - o2.score;
                });
            }
        }
        map = new HashMap<>();
    }

    public int add(int mId, int mGrade, char mGender[], int mScore) {
        int sex = 0;
        if (mGender[0] == 'f') sex = 1;

        User user = new User(mId, mGrade, sex, mScore);
        map.put(mId, user);
        pqMax[sex][mGrade].add(user);
        pqMin[sex][mGrade].add(user);

        return pqMax[sex][mGrade].peek().id;
    }

    public int remove(int mId) {
        if (!map.containsKey(mId)) return 0;
        else {
            User remove = map.remove(mId);

            pqMax[remove.gender][remove.grade].remove(remove);
            pqMin[remove.gender][remove.grade].remove(remove);

            if (pqMin[remove.gender][remove.grade].size() == 0) return 0;
            else return pqMin[remove.gender][remove.grade].peek().id;
        }
    }

    public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        int minScore = Integer.MAX_VALUE;
        int minId = Integer.MAX_VALUE;
        for (int i = 0; i < mGenderCnt; i++) {
            int sex = 0;
            if (mGender[i][0] == 'f') sex = 1;
            for (int j = 0; j < mGradeCnt; j++) {
                if (pqMin[sex][mGrade[j]].size() != 0) {
                }
            }
        }

        if (minId == Integer.MAX_VALUE) return 0;
        else return minId;
    }
}