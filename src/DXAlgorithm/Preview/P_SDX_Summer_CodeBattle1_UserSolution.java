package DXAlgorithm.Preview;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

class P_SDX_Summer_CodeBattle1_UserSolution
{

    private final static int maxTeam = 5;

    class Soldier {
        int mId;
        int mTeam;
        int mScore;

        public Soldier(int mId, int mTeam, int mScore) {
            this.mId = mId;
            this.mTeam = mTeam;
            this.mScore = mScore;
        }
    }

    TreeSet<Soldier>[] soldiers;
    HashMap<Integer, Soldier> map;

    public void init()
    {
        soldiers = new TreeSet[maxTeam + 1];
        for (int i = 1; i <= maxTeam; i++) soldiers[i] = new TreeSet<>(new Comparator<Soldier>() {
            @Override
            public int compare(Soldier o1, Soldier o2) {
                if (o1.mScore == o2.mScore) return o2.mId - o1.mId;
                else return o2.mScore - o1.mScore;
            }
        });

        map = new HashMap<>();
    }

    public void hire(int mID, int mTeam, int mScore)
    {
        Soldier soldier = new Soldier(mID, mTeam, mScore);

        if (mScore > 5) soldier.mScore = 5;
        if (mScore < 1) soldier.mScore = 1;

        soldiers[mTeam].add(soldier);
        map.put(mID, soldier);

//        for (int i = 1; i <= maxTeam; i++) {
//            System.out.print("team " + i + " : ");
//            for (Soldier soldier1 : soldiers[i]) {
//                System.out.print(soldier1.mId + "(" + soldier1.mScore + ") ");
//            }
//            System.out.println();
//        }
    }

    public void fire(int mID)
    {
        Soldier soldier = map.remove(mID);
        soldiers[soldier.mTeam].remove(soldier);

//        for (int i = 1; i <= maxTeam; i++) {
//            System.out.print("team " + i + " : ");
//            for (Soldier soldier1 : soldiers[i]) {
//                System.out.print(soldier1.mId + "(" + soldier1.mScore + ") ");
//            }
//            System.out.println();
//        }
    }

    public void updateSoldier(int mID, int mScore)
    {
        Soldier soldier = map.get(mID);
        fire(mID);
        hire(mID, soldier.mTeam, mScore);

//        for (int i = 1; i <= maxTeam; i++) {
//            System.out.print("team " + i + " : ");
//            for (Soldier soldier1 : soldiers[i]) {
//                System.out.print(soldier1.mId + "(" + soldier1.mScore + ") ");
//            }
//            System.out.println();
//        }
    }

    public void updateTeam(int mTeam, int mChangeScore)
    {
        if (mChangeScore != 0) {
            TreeSet<Soldier> mTeamSoldiers = new TreeSet<>(new Comparator<Soldier>() {
                @Override
                public int compare(Soldier o1, Soldier o2) {
                    if (o1.mScore == o2.mScore) return o2.mId - o1.mId;
                    else return o2.mScore - o1.mScore;
                }
            });

            for (Soldier soldier : soldiers[mTeam]) {
                soldier.mScore += mChangeScore;
                if (soldier.mScore > 5) soldier.mScore = 5;
                if (soldier.mScore < 1) soldier.mScore = 1;

                mTeamSoldiers.add(soldier);
            }

            soldiers[mTeam] = mTeamSoldiers;
        }

//        for (int i = 1; i <= maxTeam; i++) {
//            System.out.print("team " + i + " : ");
//            for (Soldier soldier1 : soldiers[i]) {
//                System.out.print(soldier1.mId + "(" + soldier1.mScore + ") ");
//            }
//            System.out.println();
//        }
    }

    public int bestSoldier(int mTeam) {
        return soldiers[mTeam].first().mId;
    }
}