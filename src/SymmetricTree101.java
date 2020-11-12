import utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class SymmetricTree101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        if (root.left.val != root.right.val) return false;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        Deque<TreeNode> leftDeque = new LinkedList<>();
        Deque<TreeNode> rightDeque = new LinkedList<>();
        leftDeque.offerLast(leftNode);
        rightDeque.offerLast(rightNode);
        while (!leftDeque.isEmpty() && !rightDeque.isEmpty()) {
            leftNode = leftDeque.pollFirst();
            rightNode = rightDeque.pollFirst();
            if (leftNode == null && rightNode != null) return false;
            if (leftNode != null && rightNode == null) return false;
            if (leftNode != null && rightNode != null) {
                if (leftNode.val != rightNode.val) return false;
                leftDeque.offerLast(leftNode.right);
                rightDeque.offerLast(rightNode.left);
                leftDeque.offerLast(leftNode.left);
                rightDeque.offerLast(rightNode.right);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        new SymmetricTree101().isSymmetric(root);
    }
}
