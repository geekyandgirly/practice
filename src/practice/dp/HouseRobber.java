package dp;

public class HouseRobber {

    public int rob(int[] nums) {
        int rob = 0;
        int notRob = 0;

        for (int num : nums) {
            int prevRob = rob;
            rob = num + notRob;
            notRob = Math.max(prevRob, notRob);
        }
        return Math.max(rob, notRob);
    }
}

