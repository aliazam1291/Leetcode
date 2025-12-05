class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> result;
        sort(nums.begin(), nums.end());  // Step 1: Sort the array
        
        int n = nums.size();
        
        for(int i = 0; i < n - 2; i++) {
            // Skip duplicate elements for i
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1;
            int right = n - 1;
            
            while(left < right) {
                long sum = (long)nums[i] + nums[left] + nums[right];
                
                if(sum == 0) {
                    result.push_back({nums[i], nums[left], nums[right]});
                    
                    // Skip duplicates for left
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for right
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                }
                else if(sum < 0) {
                    left++;   // increase sum
                }
                else {
                    right--;  // decrease sum
                }
            }
        }
        
        return result;
    }
};
