class Solution {
    public boolean stoneGame(int[] piles) {

        int sum = 0;

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : piles) {
            list.add(num);
        }

        while (!list.isEmpty()) {

            if (list.get(0) >= list.get(list.size() - 1)) {
                sum += list.get(0);
                list.remove(0);
            } else {
                sum += list.get(list.size() - 1);
                list.remove(list.size() - 1);
            }
        }

        return true;
    }
}