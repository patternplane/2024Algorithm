//결과 제출시 : 삭제
package _5_H_SearchStructures;

//결과 제출시 : 학번 이름 기재하기

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class SearchStructuresHomework2 {
	public void start(int[] list) {
		ListNode head = null;
		for (int i = list.length - 1; i >= 0; i--) {
			head = new ListNode(list[i],head);
		}
			
		new Solution().sortedListToBST(head);
	}
}

//결과 제출시 : 클래스 명은 그대로
class Solution {
	public TreeNode sortedListToBST(ListNode head) {
        AVLT tree = new AVLT();
        while (head != null) {
        	tree.add(head.val);
        	head = head.next;
        }
        return tree.convertToTree();
    }
}

class Node {
	int value;
	int height;
	
	Node parent;
	Node left;
	Node right;
	
	public Node(int value, Node parent) {
		this.value = value;
		this.height = 1;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}
	
	public Node popChild(boolean isLeft) {
		Node result;
		if (isLeft) {
			result = left;
			left = null;
		}
		else {
			result = right;
			right = null;
		}
		return result;
	}
}

class AVLT {
	private Node root;
	
	public TreeNode convertToTree() {
		return convertToTree(root);
	}
	
	private TreeNode convertToTree(Node rootNode) {
		if (rootNode == null)
			return null;
		return new TreeNode(rootNode.value, convertToTree(rootNode.left), convertToTree(rootNode.right));
	}
	
	public void add(int value) {
		Node parent = findNode(value);
		if (parent == null)
			root = new Node(value, null);
		else if (parent.value == value)
			return;
		else {
			attach(new Node(value, parent), parent, value < parent.value);
			restructure(parent);
		}
	}

	private Node findNode(int value) {
		if (root == null || root.value == value)
			return root;
		
		Node node = root;
		while (true) {
			if (node.value == value)
				return node;
			else if (node.value < value) {
				if (node.right == null)
					return node;
				else
					node = node.right;
			}
			else if (value < node.value) {
				if (node.left == null)
					return node;
				else
					node = node.left;
			}
		}
	}
	
	private void attach(Node child, Node parent, boolean isLeft) {
		if (parent == null)
			root = child;
		else if (isLeft)
			parent.left = child;
		else
			parent.right = child;
		if (child != null)
			child.parent = parent;
	}
	
	private void restructure(Node startNode) {
		while (startNode != null) {
			if (unBalance(startNode)) {
				Node firstChild = tallerChild(startNode);
				Node secondChild = tallerChild(tallerChild(startNode));
				if ((startNode.left == firstChild) == (firstChild.left == secondChild) )
					startNode = LLRRrotate(startNode);
				else {
					startNode = LRRLrotate(startNode);
					/*
					 * LLRRrotate(firstChild);
					 * startNode = LLRRrotate(startNode);
					 */
				}
			}
			else
				updateHeight(startNode);
			startNode = startNode.parent;
		}
	}
	
	private Node LLRRrotate(Node targetNode) {
		Node childNode = tallerChild(targetNode);
		boolean isLeftChild = targetNode.left == childNode;

		attach(childNode, targetNode.parent, (targetNode.parent == null || targetNode.parent.left == targetNode));
		attach(childNode.popChild(!isLeftChild), targetNode, isLeftChild);
		attach(targetNode, childNode, !isLeftChild);
		
		updateHeight(targetNode);
		updateHeight(childNode);
		
		return childNode;
	}
	
	private Node LRRLrotate(Node targetNode) {
		Node child1Node = tallerChild(targetNode);
		Node child2Node = tallerChild(child1Node);
		boolean isLeftChild = child1Node.left == child2Node;

		attach(child2Node, targetNode.parent, (targetNode.parent == null || targetNode.parent.left == targetNode));
		attach(child2Node.popChild(!isLeftChild), child1Node, isLeftChild);
		attach(child2Node.popChild(isLeftChild), targetNode, !isLeftChild);
		attach(child1Node, child2Node, !isLeftChild);
		attach(targetNode, child2Node, isLeftChild);
		
		updateHeight(targetNode);
		updateHeight(child1Node);
		updateHeight(child2Node);
		
		return child2Node;
	}
	
	private Node tallerChild(Node parent) {
		if (height(parent.left) < height(parent.right))
			return parent.right;
		else
			return parent.left;
	}
	
	private void updateHeight(Node node) {
		node.height = 1 + Math.max(height(node.left), height(node.right));
	}
	
	private boolean unBalance(Node node) {
		int difference = height(node.left) - height(node.right);
		if (difference < -1 || 1 < difference)
			return true;
		return false;
	}
	
	private int height(Node node) {
		return (node == null ? 0 : node.height);
	}
}
