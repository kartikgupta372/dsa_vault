class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {

            boolean aPresent = map.containsKey(a);
            boolean bPresent = map.containsKey(b);

            if (aPresent && bPresent) {
                return map.get(a) - map.get(b);
            }

            if (aPresent) return -1;
            if (bPresent) return 1;

            return a - b;
        });

        for (int num : arr1) {
            pq.offer(num);
        }
        int i = 0;

        while (!pq.isEmpty()) {
            arr1[i++] = pq.poll();
        }

        return arr1;




        
    }
}