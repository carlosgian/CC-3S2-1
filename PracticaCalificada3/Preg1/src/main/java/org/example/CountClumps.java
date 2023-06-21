package org.example;

public class CountClumps {
    /**
     * Preconditions: nums.length > 0 AND nums != null
     * @param nums This is the array for which we are counting clumps.
     * @return The number of clumps.
     */
    public int countClumps(int[] nums){
        if(nums == null || nums.length == 0) return 0;

        int previousValue = nums[0];
        int count = 1;
        int nClumps = 0;
        for(int i = 1; i < nums.length; i++){
            if (nums[i] == previousValue) count++;
            else if (nums[i] != previousValue) {
                if (count >= 2) nClumps++;
                count = 1;
            }
            previousValue = nums[i];
        }
        if (count >= 2) nClumps++;

        return nClumps;
    }

}
