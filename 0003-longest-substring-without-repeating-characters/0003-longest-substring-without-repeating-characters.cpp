#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
int lengthOfLongestSubstring(string s) {
// ASCII optimization: track last index of each character
vector<int> lastIndex(256, -1);
int maxLen = 0;
int left = -1; // inclusive left boundary of the current window


    for (int right = 0; right < (int)s.size(); ++right) {
        unsigned char c = static_cast<unsigned char>(s[right]);
        // If this character was seen inside the current window, move left forward
        if (lastIndex[c] > left) {
            left = lastIndex[c];
        }
        // Update the last seen index of the current character
        lastIndex[c] = right;
        // Current window size
        maxLen = max(maxLen, right - left);
    }
    return maxLen;
}
};