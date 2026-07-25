class Solution {
    public String largestNumber(int[] nums) {
        // Convert to String array for easy concatenation comparison
        String[] strNums = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        // Manual bubble sort with custom comparison
        for(int i = 0; i < strNums.length - 1; i++) {
            for(int j = 0; j < strNums.length - 1 - i; j++) {
                String order1 = strNums[j] + strNums[j + 1];
                String order2 = strNums[j + 1] + strNums[j];
                // If order2 is greater, swap
                if(order2.compareTo(order1) > 0) {
                    swap(strNums, j, j + 1);
                }
            }
        }

        // Edge case: if after sorting the first number is "0", return "0"
        if(strNums[0].equals("0")) {
            return "0";
        }

        // Build the result string
        StringBuilder sb = new StringBuilder();
        for(String s : strNums) {
            sb.append(s);
        }
        return sb.toString();
    }

    // Swap helper for String array
    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
