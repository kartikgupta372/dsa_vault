class Solution {
    public boolean checkDivisibility(int n) {
        int m = n;
        int sum = 0;
        int mul = 1;

        while (m != 0) {
            int rem = m % 10;

            sum += rem;
            mul *= rem;

            m = m / 10;
        }

        return n % (sum + mul) == 0;
    }
}