package com.problems.tree;

import java.util.LinkedList;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise
 * direction starting from root. Boundary includes left boundary, leaves, and
 * right boundary in order without duplicate nodes. (The values of the nodes may
 * still be duplicates.)
 * 
 * Left boundary is defined as the path from root to the left-most node. Right
 * boundary is defined as the path from root to the right-most node. If the root
 * doesn't have left subtree or right subtree, then the root itself is left
 * boundary or right boundary. Note this definition only applies to the input
 * binary tree, and not applies to any subtrees.
 * 
 * The left-most node is defined as a leaf node you could reach when you always
 * firstly travel to the left subtree if exists. If not, travel to the right
 * subtree. Repeat until you reach a leaf node.
 * 
 * The right-most node is also defined by the same way with left and right
 * exchanged.
 * 
 * Example
 * 
 * Input:  
 *     1 
 *      \ 
 *       2 
 *      / \ 
 *     3   4
 * 
 * Ouput: [1, 3, 4, 2]
 * 
 * Explanation: The root doesn't have left subtree, so the root itself is left
 * boundary. The leaves are node 3 and 4. The right boundary are node 1,2,4.
 * Note the anti-clockwise direction means you should output reversed right
 * boundary. So order them in anti-clockwise without duplicates and we have
 * [1,3,4,2].
 * 
 * 
 */
public class Solution545 {
	
	boolean isLeaf(TreeNode cur) {
		return (cur.left == null && cur.right == null);
	}

	boolean isRightBoundary(int flag) {
		return (flag == 2);
	}

	boolean isLeftBoundary(int flag) {
		return (flag == 1);
	}

	boolean isRoot(int flag) {
		return (flag == 0);
	}

	int leftChildFlag(TreeNode cur, int flag) {
		if (isLeftBoundary(flag) || isRoot(flag))
			return 1;
		else if (isRightBoundary(flag) && cur.right == null)
			return 2;
		else
			return 3;
	}

	int rightChildFlag(TreeNode cur, int flag) {
		if (isRightBoundary(flag) || isRoot(flag))
			return 2;
		else if (isLeftBoundary(flag) && cur.left == null)
			return 1;
		else
			return 3;
	}

	void preorder(TreeNode cur, List<Integer> leftBoundary, List<Integer> rightBoundary, List<Integer> leaves,
			int flag) {
		if (cur == null)
			return;
		if (isRightBoundary(flag))
			rightBoundary.add(0, cur.val);
		else if (isLeftBoundary(flag) || isRoot(flag))
			leftBoundary.add(cur.val);
		else if (isLeaf(cur))
			leaves.add(cur.val);
		preorder(cur.left, leftBoundary, rightBoundary, leaves, leftChildFlag(cur, flag));
		preorder(cur.right, leftBoundary, rightBoundary, leaves, rightChildFlag(cur, flag));
	}


	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		List<Integer> leftBoundary = new LinkedList<>(), rightBoundary = new LinkedList<>(),
				leaves = new LinkedList<>();
		preorder(root, leftBoundary, rightBoundary, leaves, 0);
		leftBoundary.addAll(leaves);
		leftBoundary.addAll(rightBoundary);
		return leftBoundary;
	}


}
