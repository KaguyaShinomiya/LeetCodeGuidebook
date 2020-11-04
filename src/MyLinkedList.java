public class MyLinkedList {
    /** Initialize your data structure here. */
    int size;
    ListNode head;
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= this.size) {
            return -1;
        }
        ListNode node = this.head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(this.size, val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index >= 0 && index <= this.size) {
            ListNode preNode = this.head;
            ListNode newNode = new ListNode(val);
            for (int i = 0; i < index; i++) {
                preNode = preNode.next;
            }
            newNode.next = preNode.next;
            preNode.next = newNode;
            this.size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= 0 && index < this.size) {
            ListNode preNode = this.head;
            for (int i = 0; i < index; i++) {
                preNode = preNode.next;
            }
            preNode.next = preNode.next.next;
            this.size--;
        }
    }

}


