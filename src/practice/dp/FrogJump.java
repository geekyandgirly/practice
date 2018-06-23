package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/frog-jump/description/
 */
public class FrogJump {

    public boolean canCross(int[] stones) {
        if (stones.length == 0) {
            return false;
        }
        if (stones[0] > 0) {
            return false;
        }

        if (stones[stones.length-1] >= 605000) {
            // 1100*1100/2 is the maximum units the frogs can cross. since there's no more than 1100 stones.
            return false;
        }
        char[] expanded = new char[stones[stones.length-1] + 1];

        int pos = stones[0];
        expanded[pos] = 'S';  // first stone
        int i = 1;
        while (i < stones.length) {
            int gap = stones[i] - stones[i-1] - 1;
            while (gap > 0) {
                expanded[++pos] = 'W';
                gap--;
            }
            expanded[++pos] = 'S';
            i++;
        }

        // for (char c : expanded) {
        // System.out.print(c + " ");
        // }

        Map<String, Boolean> memo = new HashMap<>();
        return canCross(expanded, memo, stones[0], 1);
    }

    public boolean canCross(char[] stones, Map<String, Boolean> memo, int pos, int speed) {
        // System.out.println("pos: " + pos + " speed: " + speed);

        if (pos == stones.length -1) {
            // System.out.println("reach end");
            return true;
        }
        if (pos >= stones.length) {
            // System.out.println("over end");
            return false;
        }
        if (stones[pos] == 'W') {
            // System.out.println("water");
            return false;
        }

        if (speed == 0) {
            // System.out.println("speed 0");
            return false;
        }

        String key = pos + "+" + speed;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (pos == 0) {
            return canCross(stones, memo, pos + speed, speed);
        } else {
            boolean sameSpeed = canCross(stones, memo, pos + speed, speed);
            memo.put((pos+speed) + "+" + speed, sameSpeed);
            if (sameSpeed) {
                return sameSpeed;
            }

            boolean addSpeed = canCross(stones, memo, pos + speed + 1, speed + 1);
            memo.put((pos+speed+1) + "+" + (speed + 1), addSpeed);
            if (addSpeed) {
                return addSpeed;
            }

            boolean reduceSpeed = canCross(stones, memo, pos + speed - 1, speed - 1);
            memo.put((pos+speed-1) + "+" + (speed-1), reduceSpeed);
            return reduceSpeed;
        }
    }
}
