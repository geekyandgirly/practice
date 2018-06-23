package dp;

public class EditDistance {
    /**
     * https://leetcode.com/problems/edit-distance/description/
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }

        return dp[n][m];
    }

    /**
     * https://leetcode.com/problems/one-edit-distance/description/
     * *********** NO DP ************
     */
    public boolean isOneEditDistance(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen > tLen) {
            return isOneEditDistance(t, s);
        }

        if ((tLen - sLen) > 1) {
            return false;
        }

        if (s.length() == 0 && t.length() == 1) {
            return true;
        }

        int i = 0, j = 0;
        int edit = 0;
        while (i < sLen && j <tLen) {
            if (edit > 1) {
                return false;
            }
            char sc = s.charAt(i);
            char tc = t.charAt(j);
            if (sc == tc) {
                i++;
                j++;
            } else {
                if (sLen == tLen) {
                    edit++;
                    i++;
                    j++;
                } else {
                    edit++;
                    j++;
                }
            }
        }

        if (j == tLen - 1) {
            edit++;
        }

        return edit == 1;
    }

}
