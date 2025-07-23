import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long minimumDifference(int[] nums) {
        int total_len = nums.length;
        int n = total_len / 3;

        long[] prefix_min = new long[total_len];

        long[] suffix_max = new long[total_len];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long currentSum = 0;

        for (int i = 0; i < total_len; i++) {
            maxHeap.offer(nums[i]);
            currentSum += nums[i];

            if (maxHeap.size() > n) {
                currentSum -= maxHeap.poll();
            }

            // Once we have n elements, we can start recording the minimum sum.
            if (maxHeap.size() == n) {
                prefix_min[i] = currentSum;
            }
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        currentSum = 0;

        for (int i = total_len - 1; i >= 0; i--) {
            minHeap.offer(nums[i]);
            currentSum += nums[i];
            

            if (minHeap.size() > n) {
                currentSum -= minHeap.poll();
            }
            

            if (minHeap.size() == n) {
                suffix_max[i] = currentSum;
            }
        }

        // --- Step 3: Combine results by iterating through all valid split points ---
        long minDifference = Long.MAX_VALUE;

        for (int i = n - 1; i < 2 * n; i++) {
            long sumFirst = prefix_min[i];
            long sumSecond = suffix_max[i + 1];
            minDifference = Math.min(minDifference, sumFirst - sumSecond);
        }

        return minDifference;
    }
}