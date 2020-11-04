import org.junit.Assert;
import org.junit.Test;

public class UnitTest {
    @Test
    public void testQueue() {
        MyCircularQueue obj = new MyCircularQueue(3);
        boolean param_1 = obj.enQueue(1);
        System.out.println(obj.rear);
        boolean param_2 = obj.enQueue(2);
        System.out.println(obj.rear);
        boolean param_3 = obj.enQueue(3);
        System.out.println(obj.rear);
        Assert.assertTrue(param_3);
    }

    @Test
    public void TestSymmetric() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        Assert.assertFalse(new SymmetricTree101().isSymmetric(root));
    }
}
