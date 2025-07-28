class Solution {
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int maxOr = 0;
        // First, compute the maximal OR for the full set
        for (int num : nums) {
            maxOr |= num;
        }

        int count = 0;
        int subsetCount = 1 << n; // Total subsets: 2^n

        // Start from 1 to skip the empty subset
        for (int mask = 1; mask < subsetCount; mask++) {
            int currOr = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currOr |= nums[i];
                }
            }
            if (currOr == maxOr) {
                count++;
            }
        }
        return count;
    }
}
