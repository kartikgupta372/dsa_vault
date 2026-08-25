class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n=rooms.size();
        boolean[] visit= new boolean[n];
        Queue<Integer> q= new LinkedList<>();
        q.add(0);
        visit[0] = true;
        while(q.size()>0){
            int front = q.remove();
            for(int e:rooms.get(front)){
                if(!visit[e]){
                    q.add(e);
                    visit[e]=true;

                }
            }
        }
        for(boolean flag: visit){
            if (flag==false) return false;
        }
        return true;

        
    }
}