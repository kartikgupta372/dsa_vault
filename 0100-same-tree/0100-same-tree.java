class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;      // both null → same
        if (p == null || q == null) return false;     // one null → different
        if (p.val != q.val) return false;             // values different → not same

        // recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
