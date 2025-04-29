import java.util.HashSet;

class LongestConsecutive {
    public static int longestSequence(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int longest = 0;

        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int current = num;
                int currentStreak = 1;

                while (set.contains(current + 1)) {
                    current++;
                    currentStreak++;
                }
                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }
}