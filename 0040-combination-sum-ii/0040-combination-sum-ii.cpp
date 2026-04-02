class Solution {
public:
    void backtrack(int index, int target, vector<int>& candidates, 
                   vector<int>& temp, vector<vector<int>>& result) {
        
        if (target == 0) {
            result.push_back(temp);
            return;
        }

        for (int i = index; i < candidates.size(); i++) {
            
            // 🔴 Skip duplicates
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            // 🔴 Stop if number exceeds target
            if (candidates[i] > target) break;

            temp.push_back(candidates[i]);

            // move to next index (i + 1 because only one use allowed)
            backtrack(i + 1, target - candidates[i], candidates, temp, result);

            temp.pop_back(); // backtrack
        }
    }

    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<vector<int>> result;
        vector<int> temp;

        sort(candidates.begin(), candidates.end()); // 🔑 important

        backtrack(0, target, candidates, temp, result);

        return result;
    }
};