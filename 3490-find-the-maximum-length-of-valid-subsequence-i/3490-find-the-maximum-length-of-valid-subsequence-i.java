class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
   
        int oddCount = 0, evenCount = 0;
        for (int num : nums) {
            if (num % 2 == 0) evenCount++;
            else oddCount++;
        }
        
        int maxSameParity = Math.max(evenCount, oddCount);

        int altStartEven = 0, altStartOdd = 0;
        int curr = 0; 


        curr = 0;
        for (int num : nums) {
            if (num % 2 == curr) {
                altStartEven++;
                curr ^= 1; 
            }
        }
      
        curr = 1;
        for (int num : nums) {
            if (num % 2 == curr) {
                altStartOdd++;
                curr ^= 1;
            }
        }

        int maxAlternate = Math.max(altStartEven, altStartOdd);

        return Math.max(maxSameParity, maxAlternate);
    }
}
