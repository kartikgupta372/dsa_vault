import java.util.*;

class Solution {
    public int totalFruit(int[] fruits) {

        HashMap<Integer, Integer> hp = new HashMap<>();

        int left = 0;
        int max = 0;

        for (int right = 0; right < fruits.length; right++) {

            hp.put(fruits[right], hp.getOrDefault(fruits[right], 0) + 1);

            while (hp.size() > 2) {

                hp.put(fruits[left], hp.get(fruits[left]) - 1);

                if (hp.get(fruits[left]) == 0) {
                    hp.remove(fruits[left]);
                }

                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}