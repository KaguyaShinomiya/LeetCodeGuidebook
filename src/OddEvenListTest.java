class OddEvenListTest {

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return null;
        ListNode rear = head.next;
        ListNode odd = head;
        ListNode even = head.next;
        int count = 0;
        int move = 0;

        while (rear.next != null) {
            rear = rear.next;
            count++;
        }
        while (move <= (int) count * 0.5) {
            //System.out.println("odd: " + odd.val);
            //System.out.println("even: " + even.val);
            odd.next = even.next;
            rear.next = even;
            even.next = null;
            rear = rear.next;
            System.out.println("rear: " + rear.val);
            odd = odd.next;
            even = odd.next;
            move++;
            if (even == null) break;

        }

        return head;
    }

    public static void main(String[] args) {
        int count = 5;
        System.out.println((int) count * 0.5);
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head = oddEvenList(head);
        System.out.println("-----------");
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);
        System.out.println(head.next.next.next.val);
        System.out.println(head.next.next.next.next.val);
        System.out.println(head.next.next.next.next.next.val);

    }
}
