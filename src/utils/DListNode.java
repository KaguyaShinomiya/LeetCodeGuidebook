package utils;

/**
 * The Class: DListNode
 *
 * @author: Kaguya yu
 * @since: 2020-12-29 01:33
 */
public class DListNode {
    public int key;
    public int val;
    public DListNode prev;
    public DListNode next;
    public DListNode() {}
    public DListNode(int key, int val) { this.key = key; this.val = val; }
}
