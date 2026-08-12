/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ls = new ArrayList<>();

        rightView(root, 0, ls);

        return ls;
    }

    private void rightView(TreeNode root, int level, List<Integer> ls) {
        if (root == null) {
            return;
        }

        if (level == ls.size()) {
            ls.add(root.val);
        }

        rightView(root.right, level + 1, ls);
        rightView(root.left, level + 1, ls);
    }
}