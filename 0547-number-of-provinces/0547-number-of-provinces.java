class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n=isConnected.length;
        int count=0;
        boolean[] visit= new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                bfs(isConnected, i, visit);
                count++;
            }
        }

        return count;
        
    }
    public void bfs(int[][] isConnected ,int i, boolean[] visit ){
        Queue<Integer> q= new LinkedList<>();
        q.add(i);
        visit[i]=true;
        while(q.size()>0){
            int front=q.remove();
            for(int j=0;j<isConnected.length;j++){
                if(isConnected[front][j]==1 && visit[j]==false){
                    q.add(j);
                    visit[j]=true;
                }
            }

        }
    }
}