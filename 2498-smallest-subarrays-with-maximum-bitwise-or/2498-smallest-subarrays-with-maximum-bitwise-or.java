import java.util.*;

public class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

       
        int[] lastSeen = new int[32];

        for (int i = n - 1; i >= 0; i--) {
            
            for (int b = 0; b < 32; b++) {
                if (((nums[i] >> b) & 1) == 1) {
                    lastSeen[b] = i;
                }
            }

            
            int farthest = i;
            for (int b = 0; b < 32; b++) {
                farthest = Math.max(farthest, lastSeen[b]);
            }

            result[i] = farthest - i + 1;
        }

        return result;
    }
}
