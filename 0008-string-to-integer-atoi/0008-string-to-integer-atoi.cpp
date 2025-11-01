#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
int myAtoi(string s) {
int i = 0;
int n = (int)s.size();
// Skip leading whitespaces
while (i < n && s[i] == ' ') ++i;


    // Handle sign
    int sign = 1;
    if (i < n && (s[i] == '+' || s[i] == '-')) {
        sign = (s[i] == '-') ? -1 : 1;
        ++i;
    }

    // Parse digits and build the number with overflow checks
    long long result = 0;
    while (i < n && isdigit(static_cast<unsigned char>(s[i]))) {
        int digit = s[i] - '0';
        result = result * 10 + digit;

        // Clamp to 32-bit signed range during construction to avoid overflow
        if (sign == 1 && result > INT_MAX) {
            return INT_MAX;
        } else if (sign == -1 && -result < INT_MIN) {
            return INT_MIN;
        }
        ++i;
    }

    return static_cast<int>(sign * result);
}
};