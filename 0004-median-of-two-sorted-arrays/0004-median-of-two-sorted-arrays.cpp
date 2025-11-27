class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        // Ensure nums1 is smaller to apply binary search on it
        if(nums1.size() > nums2.size()) 
            return findMedianSortedArrays(nums2, nums1);

        int m = nums1.size();
        int n = nums2.size();

        int left = 0, right = m;
        while(left <= right) {
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // Edge values (if partition is at boundary)
            int leftA  = (i == 0) ? INT_MIN : nums1[i - 1];
            int rightA = (i == m) ? INT_MAX : nums1[i];
            int leftB  = (j == 0) ? INT_MIN : nums2[j - 1];
            int rightB = (j == n) ? INT_MAX : nums2[j];

            // Partition is correct
            if(leftA <= rightB && leftB <= rightA) {
                // Odd total length → median is max(left)
                if((m + n) % 2 == 1)
                    return max(leftA, leftB);
                // Even → average of two middle values
                return (max(leftA, leftB) + min(rightA, rightB)) / 2.0;
            }
            // Too far right → move left
            else if(leftA > rightB) {
                right = i - 1;
            }
            // Too far left → move right
            else {
                left = i + 1;
            }
        }

        return 0.0; // Dummy return (won't reach here)
    }
};
