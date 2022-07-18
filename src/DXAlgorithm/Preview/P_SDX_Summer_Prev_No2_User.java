package DXAlgorithm.Preview;

import java.util.*;

class P_SDX_Summer_Prev_No2_User {

    static TreeSet<User>[][] usersSet;
    static HashMap<Integer, User> usersMap;

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
        usersSet = new TreeSet[2][4];

        for (int i = 0; i < 2; i++) {
            for (int j = 0 ; j< 4; j++) {
                usersSet[i][j] = new TreeSet<>((o1, o2) -> {
                    if (o1.score == o2.score) return o2.id - o1.id;
                    else return o2.score - o1.score;
                });
            }
        }

        usersMap = new HashMap<>();
    }

    public int add(int mId, int mGrade, char mGender[], int mScore) {
        int sex = 0;
        if (mGender[0] == 'f') sex = 1;

        User user = new User(mId, mGrade, sex, mScore);
        usersSet[sex][mGrade].add(user);
        usersMap.put(mId, user);

        return usersSet[sex][mGrade].first().id;
    }

    public int remove(int mId) {
        if (!usersMap.containsKey(mId)) return 0;
        else {
            User remove = usersMap.remove(mId);

            usersSet[remove.gender][remove.grade].remove(remove);
            if (usersSet[remove.gender][remove.grade].isEmpty()) return 0;
            else return usersSet[remove.gender][remove.grade].last().id;
        }
    }

    public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        TreeSet<User> querySet = new TreeSet<>((o1, o2) -> {
            if (o1.score == o2.score) return o1.id - o2.id;
            else return o1.score - o2.score;
        });

        for (int i = 0; i < mGenderCnt; i++) {
            int sex = 0;
            if (mGender[i][0] == 'f') sex = 1;

            for (int j = 0; j < mGradeCnt; j++) {
                User target = new User(0, mGrade[j], sex, mScore);
                User floor = usersSet[sex][mGrade[j]].floor(target);

                if (floor != null) querySet.add(floor);
            }
        }

        if (querySet.isEmpty()) return 0;
        else return querySet.first().id;
    }
}