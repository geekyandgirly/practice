package strings;

import java.util.*;

public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder prefix = new StringBuilder();
            StringBuilder prefixRev = new StringBuilder();
            StringBuilder surfix = new StringBuilder(word);
            StringBuilder surfixRev = new StringBuilder(word).reverse();

            while (surfix.length() > 0) {
                Integer surfixMatch = map.get(surfixRev.toString());
                if (surfixMatch != null && surfixMatch != i && isPalindrome(prefix)) {
                    ret.add(new ArrayList(Arrays.asList(new Integer[]{surfixMatch, i})));
                }
                Integer prefixMatch = map.get(prefixRev.toString());
                if (prefixMatch != null && prefixMatch != i && isPalindrome(surfix)) {
                    ret.add(new ArrayList(Arrays.asList(new Integer[]{i, prefixMatch})));

                    // If this word is a Palindrome itself and there's an empty string in the input, then this word is a match
                    // for that empty string. Add it now since we'll not enter this loop when looking at the empty string.
                    if (prefix.length() == 0) {
                        ret.add(new ArrayList(Arrays.asList(new Integer[]{prefixMatch, i})));
                    }
                }

                Character c = surfix.charAt(0);
                prefix.append(c);
                prefixRev.insert(0, c);
                surfix.deleteCharAt(0);
                surfixRev.deleteCharAt(surfixRev.length() - 1);
            }
        }

        return ret;
    }

    private boolean isPalindrome(StringBuilder sb) {
        int len = sb.length();
        if (len == 1 || len == 0) {
            return true;
        }
        int start = 0, end = len - 1;
        while (start < end) {
            if (sb.charAt(start) != sb.charAt(end)) {
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }
}
