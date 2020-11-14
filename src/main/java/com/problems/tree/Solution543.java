package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path between
 * any two nodes in a tree. This path may or may not pass through the root.
 * 
 *   Example: 
 *   Given a binary tree 
 *                1 
 *               / \ 
 *              2   3
 *             / \ 
 *            4   5 
 *            
 * Return 3, which is the length
 * of the path [4,2,1,3] or [5,2,1,3].
 * 
 * 
 */
public class Solution543 {

	int ans;

	int dps(TreeNode node) {
		if (node == null)
			return 0;
		int l = dps(node.left);//  inc in both
		int r = dps(node.right);// directions 
		ans = Math.max(ans, l + r + 1);// horizontal case (possibly not incl. root node)
		return Math.max(l, r) + 1;// choose longest path + cur node
	}

	public int diameterOfBinaryTree(TreeNode root) {
		ans = 1;
		dps(root);
		return ans - 1;
	}

}
