import utils.TreeNode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class HasPathSum112 {
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        Deque<TreeNode> treeStack = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        TreeNode curr = root;
        treeStack.push(curr);
        visited.add(curr);
        int temp = curr.val;
        while (!treeStack.isEmpty()) {
            if (null != curr.left & visited.add(curr.left)) {
                curr = curr.left;
                treeStack.push(curr);
                temp += curr.val;
                System.out.println("curr: " + curr.val);
                System.out.println("sum: " + temp);
            } else if (null != curr.right & visited.add(curr.right)) {
                curr = curr.right;
                treeStack.push(curr);
                temp += curr.val;
                System.out.println("curr: " + curr.val);
                System.out.println("sum: " + temp);
            } else if (curr.left == null && curr.right == null) {
                if (temp == sum) return true;
                System.out.println("curr: " + curr.val);
                System.out.println("sum: " + temp);
                if (treeStack.size() == 1) return false;
                temp -= curr.val;
                treeStack.pop();
                curr = treeStack.pop();
            } else {
                curr = treeStack.pop();
                temp -= curr.val;
                System.out.println("curr: " + curr.val);
                System.out.println("sum: " + temp);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(-2);
        root.left.left.left = new TreeNode(-1);
        hasPathSum(root, 2);

    }

}
