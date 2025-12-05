class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        
        int closestSum = nums[0] + nums[1] + nums[2]; // initial baseline
        
        for(int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            
            while(left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // Update closest if current is better
                if(abs(currentSum - target) < abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                if(currentSum == target) {
                    return currentSum;  // exact match = best possible
                }
                else if(currentSum < target) {
                    left++;   // increase sum
                }
                else {
                    right--;  // decrease sum
                }
            }
        }
        
        return closestSum;
    }
};
